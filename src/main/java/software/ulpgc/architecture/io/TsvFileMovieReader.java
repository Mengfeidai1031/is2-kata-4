package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TsvFileMovieReader implements MovieReader {
    private final File file;

    public TsvFileMovieReader(File file) {
        this.file = file;
    }

    @Override
    public List<Movie> read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            return readWith(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Movie> readWith(BufferedReader reader) throws IOException {
        TsvMovieDeserializer deserializer = new TsvMovieDeserializer();
        List<Movie> result = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            result.add(deserializer.deserialize(line));
        }
        return result;
    }

}
