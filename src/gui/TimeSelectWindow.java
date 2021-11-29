package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;
import boardgamecafe.NonMember;
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
        setTimeComponents(timeSelectPanel);
        generateButtons(basicButtons, timeSelectPanel);
        add(timeSelectPanel);
    }

    void setTimeComponents(JPanel timeSelectPanel) {
        // mainPanel
        timeSelectPanel.setLayout(null);
        timeSelectPanel.setBounds(160, 60, 960, 460);

        //headLine
        BasicLabel headLine = new BasicLabel("시간선택");
        headLine.setFontAttribute(30);
        headLine.setBounds(430, 5, 200, 100);
        timeSelectPanel.add(headLine);

        //뒤로가기 버튼
        BasicButton prevButton = new BasicButton("메뉴");
        prevButton.setFontAttribute(20);
        prevButton.setBounds(160,580,320,40);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NonMember m = (NonMember) LogInWindow.getNowLoginMember();
                try{//여기서 null 오류가  발생하는데 후에 수정예정
                    m.addTime(Integer.parseInt(hour.substring(0,1))*3600);
                }catch (Exception a){
                    MainGUI.changeWindow(MainGUI.sampleOptionWindow);
                }
                MainGUI.changeWindow(MainGUI.sampleOptionWindow);
            }
        });
        add(prevButton);

    }

    void generateButtons(BasicButton[] basicButtons, JPanel timeSelectPanel) {
        String[] buttonStr = {"1H", "2H", "3H", "4H"};
        String[] timeStr = {"10000원", "15000원", "20000원", "25000원"};
        int xGap = 20;
        //상단 button4개
        for (int i = 0; i < 4; i++) {
            basicButtons[i] = new BasicButton("<HTML><body><center>" + buttonStr[i] +
                    "<br>" + timeStr[i] + "</center></body></HTML>");
            basicButtons[i].setFontAttribute(20);
            basicButtons[i].setBounds(55 + xGap, 180, 150, 150);
            xGap += 220;

            int tmp = i;
            basicButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	NonMember m = (NonMember) LogInWindow.getNowLoginMember();
                    hour = buttonStr[tmp];
                    price = Integer.parseInt(timeStr[tmp].substring(0, timeStr[tmp].length() - 1));            
                    JOptionPane.showMessageDialog(null, hour + " " + price + "원으로 선택합니다.");
                    // OptionWindow 화면으로 전환
                    MainGUI.changeWindow(MainGUI.sampleOptionWindow);
                    MainGUI.sampleOptionWindow.grayScaleButton(MainGUI.sampleOptionWindow.timeButton);
                    MainGUI.sampleOptionWindow.decideEnablePayButton();
                }
            });
            timeSelectPanel.add(basicButtons[i]);
        }
    }

}
