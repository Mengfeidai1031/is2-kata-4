package software.ulpgc.apps.windows;

import software.ulpgc.apps.windows.io.CustomBarchartLoader;
import software.ulpgc.apps.windows.view.MainFrame;
import software.ulpgc.architecture.control.ToggleStatisticCommand;
import software.ulpgc.architecture.io.MovieLoader;
import software.ulpgc.architecture.io.SQLiteMovieReader;
import software.ulpgc.architecture.model.Movie;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        File sqliteFile = new File("C:\\3º carrera\\1º semestre\\IS2\\is2-kata-4\\sqlite.sqlite");
        new MovieLoader(new File("title.basics.tsv"), sqliteFile).execute();
        List<Movie> movies = new SQLiteMovieReader(sqliteFile).read();


        MainFrame mainFrame = new MainFrame();
        mainFrame.put("next", new ToggleStatisticCommand(new CustomBarchartLoader(), mainFrame.getBarchartDisplay()));
        mainFrame.setVisible(true);
    }
}
