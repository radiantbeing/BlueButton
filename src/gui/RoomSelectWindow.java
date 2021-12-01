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
    static BasicLabel[] roomPanelTexts = new BasicLabel[10];
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
            roomPanelTexts[i] = new BasicLabel("" + (i + 1));
            roomPanelTexts[i].setFont(new Font("NanumGothic", Font.PLAIN, 20));
            roomPanelTexts[i].setForeground(Color.WHITE);
            roomPanelTexts[i].setBounds(95, 75, 100, 100);
            roomPanel.add(roomPanelTexts[i]);

            int finalI = i;
            roomPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() % 2 != 0) { //클릭시
                        roomNum = Integer.parseInt(roomPanelTexts[finalI].getText());
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

        int i=0;
        for(JPanel rPanel : roomPanelArrayList){
            int finalI = i;
            rPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clearRoom();
                    if (e.getClickCount() % 2 != 0) { //클릭시
                        roomNum = Integer.parseInt(roomPanelTexts[finalI].getText());
                        rPanel.setBorder(new LineBorder(new Color(0, 120, 242), 3));
                        nextButton.setBackground(new Color(0, 120, 242));
                        nextButton.setEnabled(true);
                    }
                    else {//재클릭시
                        rPanel.setBorder(new LineBorder(new Color(30, 31, 33)));
                        nextButton.setBackground(new Color(121, 117, 117));
                        nextButton.setEnabled(false);
                    }
                }
            });
            i++;
        }
    }

    void clearRoom(){
        for(JPanel rPanel : roomPanelArrayList){
            rPanel.setBorder(new LineBorder(new Color(30, 31, 33)));
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

                selectedRoom = (Room) BoardGameCafe.roomMgr.getList().get(roomNum-1);
                if(selectedRoom.use) {
                	JOptionPane.showMessageDialog(null, "이미 사용중인 방입니다");
                	return;
                }


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

    void changeRoomInfo(int roomNum, boolean flag) {
        JPanel jPanel = roomPanelArrayList.get(roomNum - 1);
        if (flag) {//방이 이용중일때
            jPanel.setBackground(new Color(115,52,54));
            return;
        }
        //이용시간이 끝나거나 이용중이 아니면
        jPanel.setBackground(new Color(62,111,74));
    }

    static void changeRoomText(int roomNum,String name, String gameName){
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
