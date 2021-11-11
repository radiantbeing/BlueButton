package gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    public void addComponentsToPane() {
        setTitle("BLUEBUTTON");
        Container pane = getContentPane();

        RoomViewWindow roomViewWindow = new RoomViewWindow();
        roomViewWindow.launchWindow(pane);
    }

    private void createAndShowGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1296, 839)); // contentPane의 size는 1280*800
        setLocation(300, 100);
        addComponentsToPane();
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        MainGUI frame = new MainGUI();
        frame.createAndShowGUI();
    }
}