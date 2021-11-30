package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.NonMember;
import boardgamecafe.Room;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.Template;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static gui.RoomViewWindow.roomPanelArrayList;

public class RoomSelectWindow extends Template {
    static int roomNum;
    Room selectedRoom;
    ArrayList<JPanel> roomPanelArrayList;
    public void addComponents() {
        setLayout(null);
        JPanel roomViewPanel = new JPanel();
        BasicButton nextButton = new BasicButton("확인");
        BasicButton prevButton = new BasicButton("메뉴");
        setButton(nextButton, prevButton);
        generateSelectPanel(roomViewPanel, nextButton);
        add(roomViewPanel);
        add(nextButton);
        add(prevButton);
    }

    void generateSelectPanel(JPanel roomViewPanel, BasicButton nextButton) {
        // About roomViewPanel
        roomViewPanel.setBounds(160, 70, 960, 500);
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        roomPanelArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            // 예약된 방은 빨강, 예약 가능 방은 초록
            boolean roomReserved =  RoomViewWindow.checkRoomReserved(i+1);
            if (roomReserved) {
                roomPanel.setBackground(new Color(115,52,54));
            } else {
                roomPanel.setBackground(new Color(62,111,74));
            }
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
                        roomNum = Integer.parseInt(roomNumberLabel.getText());
                        roomPanel.setBorder(new LineBorder(new Color(0, 120, 242), 3));
                        nextButton.setBackground(new Color(0, 120, 242));
                        nextButton.setEnabled(true);
                    }
                    else {//재클릭시
                        roomPanel.setBorder(new LineBorder(new Color(30, 31, 33)));
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
        nextButton.setBounds(800, 580, 320, 40);
        nextButton.setEnabled(false);
        nextButton.setFontAttribute(20);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	NonMember m = LogInWindow.getNowLoginMember();
                MainGUI.roomViewWindow.changeRoomInfo(roomNum, true);
                selectedRoom = (Room) BoardGameCafe.roomMgr.getList().get(roomNum-1);
                selectedRoom.setUse();

                //선택효과 초기화
                for(JPanel j: roomPanelArrayList){
                    j.setBorder(new LineBorder(new Color(30, 31, 33), 1));
                }
                //버튼초기화
                nextButton.setBackground(new Color(121, 117, 117));
                m.setRoomNumber(roomNum);
                JOptionPane.showMessageDialog(null, "방이 선택되었습니다.");
                // SampleOptionWindow 화면으로 전환
                MainGUI.changeWindow(MainGUI.sampleOptionWindow);
                MainGUI.sampleOptionWindow.grayScaleButton(MainGUI.sampleOptionWindow.roomButton);
                MainGUI.sampleOptionWindow.decideEnablePayButton();
            }
        });

        prevButton.setBounds(160, 580, 320, 40);
        prevButton.setEnabled(true);
        prevButton.setFontAttribute(20);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.sampleOptionWindow);
            }
        });
    }
}
