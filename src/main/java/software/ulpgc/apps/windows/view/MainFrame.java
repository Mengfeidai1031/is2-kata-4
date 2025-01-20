package software.ulpgc.apps.windows.view;

import software.ulpgc.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final JFreeBarchartDisplay barchartDisplay;

    public MainFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kata 3 App");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(toolbar(), BorderLayout.NORTH);
        this.add(barchartDisplay = statisticPanel());
        this.commands = new HashMap<>();
    }

    private JFreeBarchartDisplay statisticPanel() {
        return new JFreeBarchartDisplay();
    }

    public void put(String key, Command value) {
        commands.put(key, value);
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel();
        panel.add(nextChart());
        return panel;
    }

    private JButton nextChart() {
        JButton button = new JButton("Next");
        button.addActionListener(e -> {
            commands.get("next").execute();
        });
        return button;
    }


    public JFreeBarchartDisplay getBarchartDisplay() {
        return barchartDisplay;
    }
}


