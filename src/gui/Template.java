package gui;

import javax.swing.*;
import java.awt.*;

public abstract class Template extends JFrame {
    JPanel topPanel = new JPanel();
    JLabel topLabel = new JLabel("BLUEBUTTON");
    JPanel centerPanel = new JPanel();
    Color primaryColor = new Color(69,116,203);
    Color secondaryColor = new Color(48, 87, 232);

    public void createAndShowGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1296, 839)); // contentPane의 size는 1280*800
        setLocation(300, 100);
        setTitle("BLUEBUTTON");
        addComponentsToPane();
        pack();
        setVisible(true);
    }

    abstract void addComponentsToPane();
}
