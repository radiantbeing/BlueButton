package gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    void run() {
//        RoomViewWindow roomViewWindow = new RoomViewWindow();
//        roomViewWindow.createAndShowGUI();

        LogInWindow logInWindow = new LogInWindow();
        logInWindow.createAndShowGUI();
        // TestWindow test = new TestWindow();
        // test.createAndShowGUI();
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        main.run();
    }
}