package gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    void run() {
        RoomViewWindow roomViewWindow = new RoomViewWindow();
        roomViewWindow.createAndShowGUI();
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        main.run();
    }
}