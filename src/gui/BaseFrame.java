package gui;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    JPanel topPanel = new JPanel();
    JLabel topLabel = new JLabel("BLUEBUTTON");
    static JPanel centerPanel = new JPanel();

    public void createAndShowGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1296, 839));
        setLocation(300, 100);
        setTitle("BLUEBUTTON");
        setInitialComponents();
        pack();
        setVisible(true);
    }

    void setInitialComponents() {
        Container contentPane = getContentPane();

        // About topPanel
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1280, 80));
        topPanel.setBackground(Color.WHITE);
        topPanel.setForeground(Color.WHITE);

        // About topLabel
        topPanel.add(topLabel, FlowLayout.LEFT);
        topLabel.setFont(new Font("맑은고딕", Font.BOLD, 30));
        topLabel.setForeground(new Color(69,116,203));
        topLabel.setBounds(20, 15, 400, 50);

        // About centerPanel
        contentPane.add(centerPanel, BorderLayout.CENTER);
    }
}
