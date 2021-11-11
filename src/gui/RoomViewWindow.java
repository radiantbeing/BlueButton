package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomViewWindow extends Template {
    JPanel roomViewPanel = new JPanel();

    @Override
    void addComponentsToPane() {
        Container contentPane = getContentPane();

        // About topPanel
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1280, 80));
        topPanel.setBackground(Color.WHITE);
        topPanel.setForeground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,secondaryColor));

        // About topLabel
        topPanel.add(topLabel, FlowLayout.LEFT);
        topLabel.setFont(new Font("맑은고딕", Font.BOLD, 30));
        topLabel.setForeground(primaryColor);
        topLabel.setBounds(20, 12, 400, 50);

        // About centerPanel
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(1280, 720));
        centerPanel.setBackground(primaryColor);
        centerPanel.setLayout(null);

        // About roomViewPanel
        centerPanel.add(roomViewPanel);
        roomViewPanel.setBounds(150,70,1000,500);
        roomViewPanel.setBackground(Color.WHITE);
        roomViewPanel.setBorder(BorderFactory.createLineBorder(secondaryColor, 1));
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        ArrayList<JPanel> roomPanelArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            roomPanel.setBorder(new LineBorder(primaryColor, 1));

            JLabel roomNumberLabel = new JLabel("" + (i + 1));
            roomNumberLabel.setFont(new Font("맑은고딕", Font.PLAIN, 20));
            roomNumberLabel.setForeground(primaryColor);
            roomNumberLabel.setBounds(95,75,100,100);
            roomPanel.add(roomNumberLabel);

            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel: roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }

        // About loginButton
        JButton loginButton = new JButton();
        centerPanel.add(loginButton);
        loginButton.setText("LOGIN");
        loginButton.setBounds(150,580,1000,40);
        loginButton.setBackground(Color.WHITE);
        loginButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        loginButton.setForeground(new Color(69,116,203));
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // new LogInWindow().createAndShowGUI();
            }
        });
    }
}
