package gui;

import boardgamecafe.Member;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;
import mgr.Manageable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeSelectWindow extends Template {
    String hour;
    int price;

    @Override
    public void addComponents() {
        setLayout(null);
        JPanel timeSelectPanel = new BasicPanel();
        BasicButton[] basicButtons = new BasicButton[8];
        BasicButton nextButton = new BasicButton("다음");
        setTimeComponents(timeSelectPanel, nextButton);
        generateButtons(basicButtons, timeSelectPanel, nextButton);
        add(timeSelectPanel);
    }

    void setTimeComponents(JPanel timeSelectPanel, BasicButton nextButton) {
        // mainPanel
        timeSelectPanel.setLayout(null);
        timeSelectPanel.setBounds(90, 60, 1100, 600);

        //headLine
        BasicLabel headLine = new BasicLabel("시간선택");
        headLine.setFontAttribute(30);
        headLine.setBounds(500, 5, 200, 100);
        timeSelectPanel.add(headLine);

        //뒤로가기 버튼
        BasicButton prevButton = new BasicButton("뒤로");
        prevButton.setFontAttribute(20);
        prevButton.setBounds(30, 550, 150, 40);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButton.setEnabled(false);
                nextButton.setBackground(new Color(121, 117, 117));
                MainGUI.changeWindow(MainGUI.roomSelectWindow);
            }
        });
        timeSelectPanel.add(prevButton);

        //다음 화변버튼
        nextButton.setFontAttribute(20);
        nextButton.setEnabled(false);
        nextButton.setBounds(930, 550, 150, 40);
        nextButton.setBackground(new Color(121, 117, 117));
        timeSelectPanel.add(nextButton);

    }

    void generateButtons(BasicButton[] basicButtons, JPanel timeSelectPanel, BasicButton nextButton) {
        String[] buttonStr = {"1H", "2H", "3H", "4H"};
        String[] timeStr = {"10000원", "15000원", "20000원", "25000원"};
        int xGap = 20;
        //상단 button4개
        for (int i = 0; i < 4; i++) {
            basicButtons[i] = new BasicButton("<HTML><body><center>" + buttonStr[i] +
                    "<br>" + timeStr[i] + "</center></body></HTML>");
            basicButtons[i].setFontAttribute(20);
            basicButtons[i].setBounds(10 + xGap, 200, 150, 150);
            xGap += 300;

            int tmp = i;
            basicButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hour = buttonStr[tmp];
                    price = Integer.parseInt(timeStr[tmp].substring(0, timeStr[tmp].length() - 1));
                    JOptionPane.showMessageDialog(null, hour + " " + price + "원으로 선택합니다.");
                    nextButton.setEnabled(true);
                    nextButton.setBackground(new Color(0, 120, 242));
                }
            });
            timeSelectPanel.add(basicButtons[i]);
        }

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Member m = (Member)LogInWindow.getNowLoginMember();//멤버가올지 논멤버가 올지 구별이안가는데,,,일단은 Member라고 가정
                m.addTime(Integer.parseInt(hour)*3600);
                MainGUI.changeWindow(MainGUI.gameSelectWindow);
            }
        });
    }

}
