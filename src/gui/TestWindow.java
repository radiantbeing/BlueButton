package gui;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends Template {
    @Override
    void addComponents() {
        JPanel testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(300, 300));
        testPanel.setBackground(Color.RED);
        add(testPanel);
    }
}
