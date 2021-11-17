package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RoomSelectWindow extends Template {
    String roomNum;
    void addComponents() {
        setLayout(null);
        JPanel roomViewPanel = new JPanel();
        BasicButton nextButton = new BasicButton("다음");
        BasicButton prevButton = new BasicButton("이전");
        setButton(nextButton, prevButton);
        generateSelectPanel(roomViewPanel, nextButton);
        add(roomViewPanel);
        add(nextButton);
        add(prevButton);
    }

    void generateSelectPanel(JPanel roomViewPanel, BasicButton nextButton) {
        // About roomViewPanel
        roomViewPanel.setBounds(150, 70, 1000, 500);
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        ArrayList<JPanel> roomPanelArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            roomPanel.setBackground(new Color(41, 42, 45));
            roomPanel.setBorder(new LineBorder(new Color(30, 31, 33), 1));

            BasicLabel roomNumberLabel = new BasicLabel("" + (i + 1));
            roomNumberLabel.setFont(new Font("NanumGothic", Font.PLAIN, 20));
            roomNumberLabel.setForeground(Color.WHITE);
            roomNumberLabel.setBounds(95, 75, 100, 100);
            roomPanel.add(roomNumberLabel);

            roomPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() % 2 != 0) { //클릭시
                        roomNum = roomNumberLabel.getText();
                        roomPanel.setBackground(new Color(121, 117, 117));
                        nextButton.setBackground(new Color(0, 120, 242));
                        nextButton.setEnabled(true);
                    }
                    else {//재클릭시
                        MainGUI.roomViewWindow.changeRoomInfo(roomNum, true);
                        roomPanel.setBackground(new Color(41, 42, 45));
                        nextButton.setBackground(new Color(121, 117, 117));
                        nextButton.setEnabled(false);
                    }
                }
            });

            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel : roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }
    }

    void setButton(BasicButton nextButton, BasicButton prevButton) {
        nextButton.setBackground(new Color(121, 117, 117));
        nextButton.setBounds(1000, 580, 150, 40);
        nextButton.setEnabled(false);
        nextButton.setFontAttribute(20);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.roomViewWindow.changeRoomInfo(roomNum, true);
                MainGUI.changeWindow(MainGUI.timeSelectWindow);
            }
        });

        prevButton.setBackground(new Color(121, 117, 117));
        prevButton.setBounds(150, 580, 150, 40);
        prevButton.setEnabled(true);
        prevButton.setFontAttribute(20);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });
    }
}
