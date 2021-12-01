package gui;

import boardgamecafe.BoardGameCafe;
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
	BasicPanel mypagePanel;
    BasicLabel remainTimeLabel, gameLabel, totalpriceLabel, nameLabel, pointLabel, roomLabel;
    NonMember m;
    
    @Override
    public void addComponents() {
    	mypagePanel = new BasicPanel();
        nameLabel = new BasicLabel();
        remainTimeLabel = new BasicLabel();
        gameLabel = new BasicLabel();
        totalpriceLabel = new BasicLabel("현재 결제 금액: ");
        pointLabel = new BasicLabel();

        setLayout(null);
        setMypagePanel();
        add(mypagePanel);
    }
    private void setMypagePanel() {
    	m = LogInWindow.getNowLoginMember();
    	
        mypagePanel.setLayout(null);
        mypagePanel.setBackground(new Color(41, 42, 45));
        mypagePanel.setBounds(20, 20, 1200, 640);
        
       
        
        if(LogInWindow.flag) {	// member가 로그인한 경우
        	setmypagePanel(m);
            int salePrice= (int) (m.getTotalPrice()*0.05);
        	BasicLabel getPointLabel = new BasicLabel("할인된 금액 : " + ("" + salePrice+"원"));
        	getPointLabel.setBounds(25,325,300,50);
        	getPointLabel.setFontAttribute(20, true);
        	mypagePanel.add(getPointLabel);
        	m.totalPrice = (m.getTotalPrice()-salePrice);
            System.out.println("total:"+m.totalPrice);
        	BasicLabel totalPointLabel = new BasicLabel("최종 결제금액 : " + ("" + m.totalPrice)+"원");
        	totalPointLabel.setBounds(25,375,300,50);
        	totalPointLabel.setFontAttribute(20, true);
        	mypagePanel.add(totalPointLabel);
        }
        else {		// 비회원 로그인
        	setmypagePanel(m);
        	pointLabel.setBounds(25,275,600,50);
        	pointLabel.setFontAttribute(20, true);
        	pointLabel.setText("회원 가입시 총 결제 금액의 5%를 할인해 드립니다");
        }

        System.out.println(m.totalPrice);
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
            	JOptionPane.showMessageDialog(MainGUI.bFrame, String.format("%d원이 결제됩니다.", m.totalPrice));
            	if(m.getPlayingGame() == null) {
            		JOptionPane.showMessageDialog(null, "게임이 선택되어있지않아\n 결제가 불가능 합니다");
            	}
            	m.totalPrice = 0;
            	if(m.orderList.size() > 0) {
            		m.orderList.clear();
            	}
                m.startTimer();//결제하는 순간부터 시간이 흐름
                // 방의 색 변경, 정보 텍스트 추가
                RoomViewWindow.changeRoomText(m.getRoomNumber(),m.getName(),m.getPlayingGame().name);
                RoomSelectWindow.changeRoomText(m.getRoomNumber(),m.getName(),m.getPlayingGame().name);
                MainGUI.roomViewWindow.changeRoomInfo(m.getRoomNumber(), true);
                MainGUI.roomSelectWindow.changeRoomInfo(m.getRoomNumber(), true);
                MainGUI.roomSelectWindow.selectedRoom.setUse();
                MainGUI.changeWindow(MainGUI.roomViewWindow);
                // 결제 완료 버튼을 누르면 게임 table과 gamrMgr에서 선택된 게임 삭제. Timer 종료되면 복구
                MainGUI.gameSelectWindow.removeSelectedRows(MainGUI.gameSelectWindow.table);
                BoardGameCafe.gameMgr.getList().remove(MainGUI.gameSelectWindow.selectedGame);
                // 잔여 방 개수 새로고침
                MainGUI.roomViewWindow.updateRemainingRoomLabel();
            }
        });
        
        JLabel centerLabel = new JLabel();
        centerLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/dices.png",450,450));
        centerLabel.setBounds(600, 0, 600, 600);
        
        mypagePanel.add(centerLabel);
        mypagePanel.add(label);
        mypagePanel.add(timeaddButton);
        mypagePanel.add(gameButton);
        mypagePanel.add(snackaddButton);
        mypagePanel.add(paymentButton);

    }
    
    private void setmypagePanel(NonMember m) {
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
        gameLabel.setBounds(25,175,500,50);
        gameLabel.setFontAttribute(20, true);
        gameLabel.setHorizontalAlignment(JLabel.LEFT);
        gameLabel.setText("현재 선택한 게임 : " + m.getPlayingGame().name);

        // --------------현재 전체 금액
        totalpriceLabel.setBounds(25,225,300,50);
        totalpriceLabel.setFontAttribute(20, true);
        totalpriceLabel.setHorizontalAlignment(JLabel.LEFT);
        totalpriceLabel.setText("현재 사용 금액 : " + ("" + m.totalPrice) + "원");
        
        mypagePanel.add(nameLabel);
        mypagePanel.add(roomLabel);
        mypagePanel.add(remainTimeLabel);
        mypagePanel.add(gameLabel);
        mypagePanel.add(totalpriceLabel);
    	mypagePanel.add(pointLabel);
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
