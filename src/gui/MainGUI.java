package gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    JPanel topPanel = new JPanel();
    JLabel topLabel = new JLabel("BLUEBUTTON");
    static JPanel centerPanel = new JPanel();
    Color primaryColor = new Color(69,116,203);

    public void createAndShowGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1296, 839)); // contentPane의 size는 1280*800
        setLocation(300, 100);
        setTitle("BLUEBUTTON");
        setInitialComponents();
        centerPanel.add(new RoomViewWindow());
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
        topLabel.setForeground(primaryColor);
        topLabel.setBounds(20, 15, 400, 50);

        // About centerPanel
        contentPane.add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        main.createAndShowGUI();
    }

}
