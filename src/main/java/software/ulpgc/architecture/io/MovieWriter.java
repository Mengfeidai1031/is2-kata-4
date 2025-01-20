package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

import java.util.List;

public interface MovieWriter {
    void write(List<Movie> movies);
}
