package gui;

import com.sun.tools.javac.Main;
import gui.template.BasicButton;
import gui.template.BasicPanel;
import gui.template.Template;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleOptionWindow extends Template {
    BasicPanel basicPanel;

    @Override
    public void addComponents() {
        basicPanel = new BasicPanel();
        setMenu();
        setLayout(null);
        addButton();
        add(basicPanel);
    }

    void setMenu() {
        //basicPanel 세팅
        basicPanel.setBounds(new Rectangle(160, 15, 960, 550));
        basicPanel.setBorder(new LineBorder(new Color(30, 31, 33), 3));
        basicPanel.setLayout(null);

        //가운데 주사위 이미지
        JLabel centerLabel = new JLabel();
        centerLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/dices.png",150,150));
        centerLabel.setBounds(new Rectangle(415,100,300,300));
        basicPanel.add(centerLabel);

        //가운데 글씨
        JLabel blueLabel = new JLabel();
        blueLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/bluebutton.png",400,450));
        blueLabel.setBounds(new Rectangle(290,240,300,300));
        basicPanel.add(blueLabel);


        //방선택
        BasicPanel roomPanel = new BasicPanel();
        roomPanel.setBounds(new Rectangle(60,5,300,300));
        roomPanel.setLayout(null);
        JLabel roomLabel = new JLabel();
        roomLabel.setBounds(new Rectangle(50,0,200,200));
        BasicButton roomButton = new BasicButton("방 선택");
        roomButton.setBounds(new Rectangle(0,200,200,40));
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomSelectWindow);
            }
        });
        roomLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/room.png",100,100));
        roomPanel.add(roomLabel);
        roomPanel.add(roomButton);
        basicPanel.add(roomPanel);

        //시간선택
        BasicPanel timePanel = new BasicPanel();
        timePanel.setBounds(new Rectangle(700,5,200,300));
        timePanel.setLayout(null);
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(new Rectangle(50,0,200,200));
        BasicButton timeButton = new BasicButton("시간선택");
        timeButton.setBounds(new Rectangle(0,200,200,40));
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.timeSelectWindow);
            }
        });
        timeLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/clock.png",100,100));
        timePanel.add(timeLabel);
        timePanel.add(timeButton);
        basicPanel.add(timePanel);

        //게임선택
        BasicPanel gamePanel = new BasicPanel();
        gamePanel.setBounds(new Rectangle(60,270,200,240));
        gamePanel.setLayout(null);
        JLabel gameLabel = new JLabel();
        gameLabel.setBounds(new Rectangle(50,0,200,200));
        BasicButton gameButton = new BasicButton("게임선택");
        gameButton.setBounds(new Rectangle(0,200,200,40));
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.gameSelectWindow);
            }
        });
        gameLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/boardgame.png",100,100));
        gamePanel.add(gameLabel);
        gamePanel.add(gameButton);
        basicPanel.add(gamePanel);

        //음식선택
        BasicPanel foodPanel = new BasicPanel();
        foodPanel.setBounds(new Rectangle(700,270,200,240));
        foodPanel.setLayout(null);
        JLabel foodLabel = new JLabel();
        foodLabel.setBounds(new Rectangle(50,0,200,200));
        BasicButton foodButton = new BasicButton("음식선택");
        foodButton.setBounds(new Rectangle(0,200,200,40));
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.snackOrderWindow);
            }
        });
        foodLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/food.png",100,100));
        foodPanel.add(foodLabel);
        foodPanel.add(foodButton);
        basicPanel.add(foodPanel);
    }

    void addButton(){
        BasicButton payButton = new BasicButton("결제");
        payButton.setBackground(new Color(121, 117, 117));

        payButton.setBounds(new Rectangle(800,580,320,40));
        payButton.setEnabled(false);
        add(payButton);

        BasicButton logOutButton = new BasicButton("로그아웃");
        logOutButton.setBounds(new Rectangle(160,580,320,40));
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInWindow.nowLoginMember = null;
                LogInWindow.nowLoginNonMember = null;
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });
        add(logOutButton);
    }
}
