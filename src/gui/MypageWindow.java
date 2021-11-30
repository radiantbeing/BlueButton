package gui;

import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;

import javax.swing.*;

import boardgamecafe.Member;
import boardgamecafe.NonMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MypageWindow extends Template {
	private static final long serialVersionUID = 1L;
	BasicPanel MypagePanel;
    BasicLabel remainTimeLabel, gameLabel, totalpriceLabel;
    NonMember m = null;
    
    @Override
    public void addComponents() {
        MypagePanel = new BasicPanel();
        remainTimeLabel = new BasicLabel();
        gameLabel = new BasicLabel();
        totalpriceLabel = new BasicLabel("현재 결제 금액: ");

        setLayout(null);
        setMypagePanel(MypagePanel);
        add(MypagePanel);
    }
    private void setMypagePanel(BasicPanel mypagePanel) {
    	m = LogInWindow.getNowLoginMember();
    	
        mypagePanel.setLayout(null);
        mypagePanel.setBackground(new Color(41, 42, 45));
        mypagePanel.setBounds(20, 20, 1200, 640);
        
        BasicLabel nameLabel = new BasicLabel(m.getName() +"님 안녕하세요");
        nameLabel.setBounds(25, 25, 300, 50);
        nameLabel.setFontAttribute(20, true);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        
        BasicLabel roomLabel = new BasicLabel((""+m.getRoomNumber()) + "번 방 사용중");
        roomLabel.setBounds(25,75,300,50);
        roomLabel.setFontAttribute(20, true);
        
        // -------------- 잔여 시간
        remainTimeLabel.setBounds(25,125,300,50);
        remainTimeLabel.setFontAttribute(20, true);
        remainTimeLabel.setHorizontalAlignment(JLabel.LEFT);
        remainTimeLabel.setText("잔여 시간 : " + getTimeText(m));
        
        // -------------- 선택한 게임
        gameLabel.setBounds(25,175,300,50);
        gameLabel.setFontAttribute(20, true);
        gameLabel.setHorizontalAlignment(JLabel.LEFT);
        gameLabel.setText("현재 선택한 게임 : " + m.getPlayingGame().name);

        // --------------현재 전체 금액
        totalpriceLabel.setBounds(25,225,300,50);
        totalpriceLabel.setFontAttribute(20, true);
        totalpriceLabel.setHorizontalAlignment(JLabel.LEFT);
        totalpriceLabel.setText("현재 사용 금액 : " + ("" + m.getTotalPrice()) + "원");
        
        BasicLabel pointLabel = new BasicLabel();
        
        if(LogInWindow.flag) {	// member가 로그인한 경우
        	Member member = (Member) LogInWindow.getNowLoginMember();
        	pointLabel.setBounds(25, 275, 300, 50);
        	pointLabel.setText("잔여 포인트 : " + ("" + member.point) + "점");
        	pointLabel.setFontAttribute(20, true);
        	int getPoint = (int) (member.getTotalPrice() * 0.05);
        	int totalPoint = member.point + getPoint;
        	
        	BasicLabel getPointLabel = new BasicLabel("결제 후 얻는 포인트 : " + ("" + getPoint));
        	getPointLabel.setBounds(25,325,300,50);
        	getPointLabel.setFontAttribute(20, true);
        	mypagePanel.add(getPointLabel);
        	
        	BasicLabel totalPointLabel = new BasicLabel("결제 후 포인트 : " + ("" + totalPoint));
        	totalPointLabel.setBounds(25,375,300,50);
        	totalPointLabel.setFontAttribute(20, true);
        	mypagePanel.add(totalPointLabel);
        }
        else {		// 비회원 로그인
        	pointLabel.setBounds(25,275,600,50);
        	pointLabel.setFontAttribute(20, true);
        	pointLabel.setText("회원 가입시 총 결제 금액의 5%를 포인트로 적립합니다");
        }
        // -------------- 여기부터 다른 메뉴 이동 버튼
        BasicLabel label = new BasicLabel("더 이용하시고 싶으시면 아래 버튼을 통해 이동해 주세요");
        label.setBounds(25, 500, 600, 50);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFontAttribute(20, true);
        
        // -------------- 시간 추가 버튼
        BasicButton timeaddButton = new BasicButton("시간 추가");
        timeaddButton.setBounds(25, 550, 150, 40);
        timeaddButton.setFontAttribute(20);
        timeaddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.timeSelectWindow);
            }
        });
         
        // -------------- 게임 변경 버튼
        BasicButton gameButton = new BasicButton("게임 변경");
        gameButton.setBounds(185, 550, 150, 40);
        gameButton.setFontAttribute(20);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.gameSelectWindow);
            }
        });

        // -------------- 간식 주문 버튼
        BasicButton snackaddButton = new BasicButton("간식 주문");
        snackaddButton.setBounds(345, 550, 150, 40);
        snackaddButton.setFontAttribute(20);
        snackaddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.snackOrderWindow);
            }
        });

        // -------------- 결제 완료 버튼
        BasicButton paymentButton = new BasicButton("결제 완료");
        paymentButton.setBounds(1000, 550, 150, 40);
        paymentButton.setFontAttribute(20);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	NonMember m = LogInWindow.getNowLoginMember();
            	JOptionPane.showMessageDialog(null, String.format("%d원이 결제됩니다.", m.getTotalPrice()));
            	m.totalPrice = 0;
            	m.orderList.clear();
                m.startTimer();//결제하는 순간부터 시간이 흐름
                MainGUI.changeWindow(MainGUI.roomViewWindow);
            }
        });
        
        JLabel centerLabel = new JLabel();
        centerLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/dices.png",450,450));
        centerLabel.setBounds(600, 0, 600, 600);
        
        mypagePanel.add(centerLabel);
        mypagePanel.add(nameLabel);
        mypagePanel.add(roomLabel);
        mypagePanel.add(remainTimeLabel);
        mypagePanel.add(gameLabel);
        mypagePanel.add(totalpriceLabel);
    	mypagePanel.add(pointLabel);
        mypagePanel.add(label);
        mypagePanel.add(timeaddButton);
        mypagePanel.add(gameButton);
        mypagePanel.add(snackaddButton);
        mypagePanel.add(paymentButton);

    }
    
	private String getTimeText(NonMember nonMember) {
		int remainTime = nonMember.getRemainingTime();
		int hour = remainTime / 3600;
		int minute = (remainTime % 3600) / 60;
		int second = (remainTime % 3600) % 60;
		String timeText = hour + "시간 " + minute + "분 " + second + "초";
		return timeText;
	}
	
}
