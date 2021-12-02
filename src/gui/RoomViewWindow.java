package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;
import boardgamecafe.NonMember;
import boardgamecafe.Room;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.Template;
import mgr.Manageable;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomViewWindow extends Template {
    static ArrayList<JPanel> roomPanelArrayList = new ArrayList<>();
    static BasicLabel[] roomPanelTexts = new BasicLabel[10];
    BasicLabel remainingRoomLabel;
    @Override
    public void addComponents() {
        // Initialize
        setLayout(null);
        // About roomViewPanel
        JPanel roomViewPanel = new JPanel();
        add(roomViewPanel);
        roomViewPanel.setBounds(160, 70, 960, 500);
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            roomPanel.setBackground(new Color(62,111,74));
            roomPanel.setBorder(new LineBorder(new Color(30, 31, 33), 1));
            roomPanelTexts[i] = new BasicLabel("" + (i + 1));
            roomPanelTexts[i].setFont(new Font("NanumGothic", Font.PLAIN, 20));
            roomPanelTexts[i].setForeground(Color.WHITE);
            roomPanelTexts[i].setBounds(95, 75, 100, 100);
            roomPanel.add(roomPanelTexts[i]);
            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel: roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }

        // About Number Of remainingRoom Label
        remainingRoomLabel = new BasicLabel();
        remainingRoomLabel.setText(String.format("잔여 방: %d개", getNumberOfRemainingRoom()));
        remainingRoomLabel.setFontAttribute(15);
        remainingRoomLabel.setBounds(160, 10, 100, 50);
        add(remainingRoomLabel);



        // About loginButton
        BasicButton loginButton = new BasicButton("로그인");
        add(loginButton);
        loginButton.setBounds(160, 580, 960, 40);
        loginButton.setFontAttribute(18);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });

        // About AdminButton
        JPanel iconPanel = new JPanel();
        iconPanel.setBounds(1080, 15, 50, 50);
        iconPanel.setOpaque(false);
        add(iconPanel);

        JButton adminButton = new JButton(MainGUI.scaleImageIcon("imgs/settings.png", 28, 28));
        adminButton.setBorderPainted(false);
        adminButton.setContentAreaFilled(false);
        adminButton.setFocusPainted(false);
        adminButton.setOpaque(false);
        iconPanel.add(adminButton);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.adminLoginWindow);
            }
        });
    }


    boolean checkUsingRoom(int roomNumber){
        Room r = (Room) BoardGameCafe.roomMgr.getList().get(roomNumber);
        return r.use;
    }


    // 잔여 방 개수 반환
    int getNumberOfRemainingRoom() {
        int numberOfRemainingRoom = 10;
        for (int i = 0; i < 10; i++) {
            if (checkUsingRoom(i)) {
                numberOfRemainingRoom--;
            }
        }
        return numberOfRemainingRoom;
    }

    void updateRemainingRoomLabel() {
        remainingRoomLabel.setText(String.format("잔여 방: %d개", getNumberOfRemainingRoom()));
    }


    void changeRoomInfo(int roomNum, boolean flag) {
        JPanel jPanel = roomPanelArrayList.get(roomNum - 1);
        if (flag) {//방이 이용중일때
            jPanel.setBackground(new Color(115,52,54));
            return;
        }
        //이용시간이 끝나거나 이용중이 아니면
        jPanel.setBackground(new Color(62,111,74));
    }

    public static void changeRoomText(int roomNum,String name, String gameName){
        if(LogInWindow.getNowLoginMember().timerFlag){
            roomPanelTexts[roomNum-1].setFontAttribute(15);
            roomPanelTexts[roomNum-1].setBounds(10, 75, 300, 100);
            roomPanelTexts[roomNum-1].setText("<HTML><body><center>사용자: " + name +
                    "<br>사용중인게임: " + gameName + "</center></body></HTML>");//레이아웃에 맞게 수정예정
            return;
        }
        roomPanelTexts[roomNum-1].setFontAttribute(18);
        roomPanelTexts[roomNum-1].setBounds(95, 75, 100, 100);
        roomPanelTexts[roomNum-1].setText(roomNum+"");
    }
}
