package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeSelectWindow extends Template {
    ButtonGroup buttonGroup = new ButtonGroup();
    String hour;
    int price;
    @Override
    void addComponents() {
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
        timeSelectPanel.setBounds(90, 60, 1100, 600);

        //headLine
        BasicLabel headLine = new BasicLabel("시간선택");
        headLine.setFontAttribute(30);
        headLine.setBounds(500, 5, 200, 100);
        timeSelectPanel.add(headLine);
    }

    void generateButtons(BasicButton[] basicButtons, JPanel timeSelectPanel) {
        String[] buttonStr = {"30M", "1H", "1H30M", "2H",
                "2H30M", "3H", "5H", "8H"};
        String[] timeStr = {"5000원", "10000원", "15000원", "20000원",
                "25000원", "30000원", "45000원", "70000원"};
        int xGap = 20;
        //상단 button4개
        for (int i = 0; i < 4; i++) {
            basicButtons[i] = new BasicButton("<HTML><body><center>" + buttonStr[i] +
                    "<br>" + timeStr[i] + "</center></body></HTML>");
            basicButtons[i].setFontAttribute(20);
            basicButtons[i].setBounds(10 + xGap, 100, 150, 150);
            xGap += 300;

            int tmp = i;
            basicButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hour = buttonStr[tmp];
                    price = Integer.parseInt(timeStr[tmp].substring(0,timeStr[tmp].length()-1));
                    JOptionPane.showMessageDialog(null, hour+" "+price+"원으로 선택합니다.");
                }
            });

            try {
                buttonGroup.add(basicButtons[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeSelectPanel.add(basicButtons[i]);
        }
        xGap = 20;
        //하단 button4개
        for (int i = 4; i < 8; i++) {
            basicButtons[i] = new BasicButton("<HTML><body><center>" + buttonStr[i] +
                    "<br>" + timeStr[i] + "</center></body></HTML>");
            basicButtons[i].setFontAttribute(20);
            basicButtons[i].setBounds(10 + xGap, 300, 150, 150);
            xGap += 300;

            int tmp = i;
            basicButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hour = buttonStr[tmp];
                    price = Integer.parseInt(timeStr[tmp].substring(0,timeStr[tmp].length()-1));
                    JOptionPane.showMessageDialog(null, hour+" "+price+"원으로 선택합니다.");
                }
            });

            try {
                buttonGroup.add(basicButtons[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeSelectPanel.add(basicButtons[i]);
        }
    }

}
