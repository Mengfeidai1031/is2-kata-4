package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLiteMovieWriter implements MovieWriter, AutoCloseable {
    private final Connection connection;

    private PreparedStatement preparedStatement;
    public SQLiteMovieWriter(File fileToWrite) {
        try {
            this.connection = connectToDatabase(fileToWrite);
            prepareDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void prepareDatabase() throws SQLException {
        connection.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS movies(
                id TEXT PRIMARY KEY,
                titleType TEXT,
                primaryTitle TEXT,
                year TEXT
            );
        """);
        preparedStatement = connection.prepareStatement("""
            INSERT INTO movies(id, titleType, primaryTitle, year) VALUES(?,?,?,?)
        """);
        connection.setAutoCommit(false);
    }

    private Connection connectToDatabase(File fileToWrite) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + fileToWrite.getAbsolutePath());
    }

    @Override
    public void write(List<Movie> movies) {
        try {
            for (Movie movie : movies) {
                preparedStatement.setString(1, movie.id());
                preparedStatement.setString(2, String.valueOf(movie.titleType()));
                preparedStatement.setString(3, movie.primaryTitle());
                preparedStatement.setString(4, movie.startYear() != null ? movie.startYear().toString() : null);
                preparedStatement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
