package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomViewWindow extends Template {
    @Override
    void addComponents() {
        // Initialize
        setLayout(null);

        // About roomViewPanel
        JPanel roomViewPanel = new JPanel();
        add(roomViewPanel);
        roomViewPanel.setBounds(150, 70, 1000, 500);
        roomViewPanel.setBackground(Color.WHITE);
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        ArrayList<JPanel> roomPanelArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            roomPanel.setBackground(new Color(41, 42, 45));
            roomPanel.setBorder(new LineBorder(new Color(30, 31, 33), 1));

            JLabel roomNumberLabel = new JLabel("" + (i + 1));
            roomNumberLabel.setFont(new Font("NanumGothic", Font.PLAIN, 20));
            roomNumberLabel.setForeground(Color.WHITE);
            roomNumberLabel.setBounds(95, 75, 100, 100);
            roomPanel.add(roomNumberLabel);

            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel: roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }

        // About loginButton
        JButton loginButton = new JButton();
        add(loginButton);
        loginButton.setText("로그인");
        loginButton.setBounds(150, 580, 1000, 40);
        loginButton.setBackground(new Color(0, 120, 242));
        loginButton.setFont(new Font("NanumGothic", Font.BOLD, 18));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });

        // if this button click, go to adminMenu.
        JButton adminButton = new JButton("관리자");
        adminButton.setBorderPainted(false);
        adminButton.setFocusPainted(false);
        adminButton.setForeground(Color.WHITE);
        adminButton.setBackground(new Color(0, 120, 242));
        adminButton.setFont(new Font("NanumGothic", Font.BOLD, 15));
        adminButton.setBounds(1000, 30, 150, 30);
        add(adminButton);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.adminLoginWindow);
            }
        });
    }
}
