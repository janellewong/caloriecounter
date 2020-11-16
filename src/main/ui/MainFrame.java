package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel dayPanel;
    private SidePanel userPanel;

    public MainFrame(String title) {
        super(title);
        refresh();
    }

    public void refresh() {
        // Set layout manager
        setLayout(new BorderLayout());

        // Add panels
        dayPanel = new MainPanel();
        userPanel = new SidePanel(this);

        Container c = getContentPane();
        c.add(dayPanel, BorderLayout.WEST);
        c.add(userPanel, BorderLayout.EAST);
    }
}
