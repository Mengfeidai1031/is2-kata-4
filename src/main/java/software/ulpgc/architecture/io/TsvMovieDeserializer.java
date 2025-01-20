package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Movie;

import java.time.Year;

import static java.lang.Integer.parseInt;

public class TsvMovieDeserializer implements MovieDeserializer {
    @Override
    public Movie deserialize(String data) {
        return deserialize(data.split("\t"));
    }

    private Movie deserialize(String[] fields) {
        return new Movie(
                fields[0],
                toTitleType(fields[1]),
                fields[2],
                toYear(fields[5])
        );
    }

    private Year toYear(String field) {
        return field.equals("\\N") ? Year.of(0) : Year.of(parseInt(field));
    }

    private Movie.TitleType toTitleType(String field) {
        return Movie.TitleType.valueOf(normalize(field));
    }

    private String normalize(String field) {
        String upperCase = field.toUpperCase();
        String temp =  field.replace("-","");
        return upperCase.toCharArray()[0] + temp.substring(1);
    }


}
