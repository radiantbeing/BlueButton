package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;
import boardgamecafe.NonMember;
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
            // 예약된 방은 빨강, 예약 가능 방은 초록
            boolean roomReserved =  checkRoomReserved(i+1);
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
            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel: roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }

        // About Number Of remainingRoom Label
        BasicLabel remainingRoomLabel = new BasicLabel();
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
                MainGUI.changeWindow(MainGUI.gameSelectWindow);
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

    // 해당 방이 예약된 방인지 여부를 반환
    static boolean checkRoomReserved(int roomNumber) {
        ArrayList<Manageable> memberList = BoardGameCafe.memberMgr.getList();
        ArrayList<Manageable> nonmemberList = BoardGameCafe.nonMemberMgr.getList();
        for (Manageable m: memberList) {
            Member tmp = (Member)m;
            if (tmp.getRoomNumber() == roomNumber)
                return true;
        }
        for (Manageable m: nonmemberList) {
            NonMember tmp = (NonMember)m;
            if (tmp.getRoomNumber() == roomNumber)
                return true;
        }
        return false;
    }

    // 잔여 방 개수 반환
    int getNumberOfRemainingRoom() {
        int numberOfRemainingRoom = 10;
        for (int i = 0; i < 10; i++) {
            if (checkRoomReserved(i+1)) {
                numberOfRemainingRoom--;
            }
        }
        return numberOfRemainingRoom;
    }


    void changeRoomInfo(String roomNum, boolean flag) {
        JPanel jPanel = roomPanelArrayList.get(Integer.parseInt(roomNum) - 1);
        if (flag) {//방이 이용중일때
            jPanel.setBackground(new Color(121, 117, 117));
            return;
        }
        //이용시간이 끝나거나 이용중이 아니면
        jPanel.setBackground(new Color(41, 42, 45));
    }
}
