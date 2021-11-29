package gui;

import boardgamecafe.NonMember;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MypageWindow extends Template {
    BasicPanel MypagePanel;
    BasicLabel remaintimeLabel, gameLabel, totalpriceLabel;
    NonMember m = (NonMember) LogInWindow.getNowLoginMember();
    @Override
    public void addComponents() {
        MypagePanel = new BasicPanel();
        remaintimeLabel = new BasicLabel("잔여 시간: ");
        gameLabel = new BasicLabel("게임: ");
        totalpriceLabel = new BasicLabel("현재 결제 금액: ");

        setLayout(null);
        setMypagePanel(MypagePanel);

    }
    private void setMypagePanel(BasicPanel mypagePanel) {
        mypagePanel.setLayout(null);
        mypagePanel.setBackground(new Color(41, 42, 45));
        mypagePanel.setBounds(20, 20, 1200, 640);

        // -------------- 잔여 시간
        remaintimeLabel.setBounds(25,25,300,50);
        remaintimeLabel.setFontAttribute(20, true);
        remaintimeLabel.setHorizontalAlignment(JLabel.LEFT);

        // -------------- 선택한 게임
        gameLabel.setBounds(25,50,300,50);
        gameLabel.setFontAttribute(20, true);
        gameLabel.setHorizontalAlignment(JLabel.LEFT);

        // --------------현재 전체 금액
        totalpriceLabel.setBounds(25,75,300,50);
        totalpriceLabel.setFontAttribute(20, true);
        totalpriceLabel.setHorizontalAlignment(JLabel.LEFT);

        // -------------- 시간 추가 버튼
        BasicButton timeaddButton = new BasicButton("시간 추가");
        timeaddButton.setBounds(25, 150, 150, 40);
        timeaddButton.setFontAttribute(20);
        timeaddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.timeSelectWindow);
            }
        });

        // -------------- 게임 변경 버튼
        BasicButton gameButton = new BasicButton("게임 변경");
        gameButton.setBounds(10, 200, 150, 40);
        gameButton.setFontAttribute(20);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.gameSelectWindow);
            }
        });

        // -------------- 간식 주문 버튼
        BasicButton snackaddButton = new BasicButton("간식 주문");
        snackaddButton.setBounds(10, 250, 150, 40);
        snackaddButton.setFontAttribute(20);
        snackaddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.snackOrderWindow);
            }
        });

        // -------------- 결제 완료 버튼
        BasicButton paymentButton = new BasicButton("결제 완료");
        paymentButton.setBounds(1000, 600, 150, 40);
        paymentButton.setFontAttribute(20);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomViewWindow);
            }
        });


        mypagePanel.add(remaintimeLabel);
        mypagePanel.add(gameLabel);
        mypagePanel.add(totalpriceLabel);
        mypagePanel.add(timeaddButton);
        mypagePanel.add(gameButton);
        mypagePanel.add(snackaddButton);
        mypagePanel.add(paymentButton);

    }
}