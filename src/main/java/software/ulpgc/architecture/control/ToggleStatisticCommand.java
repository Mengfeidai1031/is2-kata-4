package software.ulpgc.architecture.control;

import software.ulpgc.apps.windows.io.CustomBarchartLoader;
import software.ulpgc.apps.windows.view.JFreeBarchartDisplay;

public class ToggleStatisticCommand implements Command {
    private final CustomBarchartLoader loader;
    private final JFreeBarchartDisplay display;

    public ToggleStatisticCommand(CustomBarchartLoader loader, JFreeBarchartDisplay display) {
        this.loader = loader;
        this.display = display;
    }

    private int i = 0;

    @Override
    public void execute() {
        display.show(loader.loadBarchart(i++%2));
    }
}
