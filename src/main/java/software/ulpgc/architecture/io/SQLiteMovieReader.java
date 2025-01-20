package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SQLiteMovieReader implements MovieReader, AutoCloseable {
    private final Connection connection;

    public SQLiteMovieReader(File fileToWrite) {
        try {
            this.connection = connectToDatabase(fileToWrite);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connectToDatabase(File fileToWrite) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + fileToWrite.getAbsolutePath());
    }

    @Override
    public List<Movie> read() throws IOException {
        try {
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM movies").executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                Movie.TitleType titleType = Movie.TitleType.valueOf(resultSet.getString(2));
                String primaryTitle = resultSet.getString(3);
                Year year = Year.of(resultSet.getInt(4));
                movies.add(new Movie(id, titleType, primaryTitle, year));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

}