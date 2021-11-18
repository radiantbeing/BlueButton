package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BaseFrame extends JFrame {
    JPanel topPanel = new JPanel();
    JLabel topLabel = new JLabel("BLUEBUTTON");
    JPanel centerPanel = new JPanel();

    public void createAndShowGUI() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1296, 839));
        setTitle("BLUEBUTTON");
        setInitialComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void setInitialComponents() {
        Container contentPane = getContentPane();

        // About topPanel
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1280, 80));
        topPanel.setBackground(new Color(41, 42, 45));
        topPanel.setForeground(Color.WHITE);

        // About topLabel
        topPanel.add(topLabel, FlowLayout.LEFT);
        topLabel.setFont(new Font("NanumGothic", Font.PLAIN, 30));
        topLabel.setForeground(new Color(0, 120, 242));
        topLabel.setBounds(20, 15, 400, 50);

        // About centerPanel
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(new Color(30, 31, 33));

        // Add NunumGothic Font
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File("Fonts/NanumGothic.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
