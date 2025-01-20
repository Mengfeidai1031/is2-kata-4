package software.ulpgc.architecture.model;

import java.time.Year;

public record Movie(String id, TitleType titleType, String primaryTitle,
                    Year startYear) {

    public enum TitleType {
        VideoGame,
        TvPilot,
        Movie,
        TvSeries,
        TvMiniSeries,
        Short,
        TvSpecial,
        TvShort,
        Video,
        TvMovie,
        TvEpisode
    }
}