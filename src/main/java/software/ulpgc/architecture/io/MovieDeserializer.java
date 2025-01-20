package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

public interface MovieDeserializer {
    Movie deserialize(String data);
}
