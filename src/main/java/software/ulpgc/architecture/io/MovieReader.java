package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieReader {
    List<Movie> read() throws IOException;
}
