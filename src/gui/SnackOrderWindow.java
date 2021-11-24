package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Snack;
import gui.template.BasicButton;
import gui.template.BasicPanel;
import gui.template.Template;
import mgr.Manageable;

public class SnackOrderWindow extends Template{
	BasicPanel snackOptionPanel, orderListPanel;
	ArrayList<BasicPanel> nonCoffeePanel, snackPanel, coffeePanel;
	@Override
	public void addComponents() {
		nonCoffeePanel = new ArrayList<>();
		snackOptionPanel = new BasicPanel();
		coffeePanel = new ArrayList<>();
		snackPanel = new ArrayList<>();
		setLayout(null);
		setCoffeePanel(coffeePanel);
		setNoncoffeePanel(nonCoffeePanel);
		setSnackPanel(snackPanel);
		setSnackOptionPanel(snackOptionPanel);
		setOrderListPanel(orderListPanel);
		panelVisibleSet(coffeePanel, nonCoffeePanel, snackPanel, 0);
	}
	private void setSnackOptionPanel(BasicPanel mainPanel) {		
		String[] optionName = {"커피류", "음료류", "간식류"};
		BasicButton[] optionBtn = new BasicButton[3];
		
		snackOptionPanel.setLayout(null);
		snackOptionPanel.setBackground(new Color(41, 42, 45));
		snackOptionPanel.setBounds(160, 20, 640, 60);
		
		int xGap = 10;
		for(int i = 0; i < optionName.length; i++) {
			optionBtn[i] = new BasicButton(optionName[i]);
			optionBtn[i].setBounds(xGap, 0, 200, 60);
			optionBtn[i].setFontAttribute(24, true);
			snackOptionPanel.add(optionBtn[i]);
			xGap += 210;
			optionBtn[i].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// coffee button clicked
					if(e.getSource().equals(optionBtn[0])) {
						panelVisibleSet(coffeePanel, nonCoffeePanel, snackPanel, 0);
					}
					// nonCoffee button clicked
					if(e.getSource().equals(optionBtn[1])) {
						panelVisibleSet(nonCoffeePanel, coffeePanel, snackPanel, 0);
					}
					// snack button clicked
					if(e.getSource().equals(optionBtn[2])) {
						panelVisibleSet(snackPanel, nonCoffeePanel, coffeePanel, 0);
					}
				}
			});
		}
		
		add(snackOptionPanel);
	}
	
	private void setOrderListPanel(BasicPanel orderPanel) {
		// 오른쪽 아래 주문현황 패널 넣을 예정
	}
	
	private void setCoffeePanel(ArrayList<BasicPanel> coffeePanel) {
		ArrayList<BasicButton> menuBtnList = makeMenuBtn(BoardGameCafe.snackMgr.findAll("coffee"));
		int panelCount = menuBtnList.size() / 8;
		
		mainMenuPanel(coffeePanel, panelCount);				
		addMenuBtn(coffeePanel, menuBtnList, panelCount);

		for(BasicPanel panel : coffeePanel) {
			add(panel);
		}
	}
	
	private void setNoncoffeePanel(ArrayList<BasicPanel> nonCoffeePanel) {
		ArrayList<BasicButton> menuBtnList = makeMenuBtn(BoardGameCafe.snackMgr.findAll("noncoffee"));
		int panelCount = menuBtnList.size() / 8;
		
		mainMenuPanel(nonCoffeePanel, panelCount); 
		addMenuBtn(nonCoffeePanel, menuBtnList, panelCount);

		for(BasicPanel panel : nonCoffeePanel) {
			add(panel);
		}
	}
	
	private void setSnackPanel(ArrayList<BasicPanel> snackPanel) {
		ArrayList<BasicButton> menuBtnList = makeMenuBtn(BoardGameCafe.snackMgr.findAll("snack"));
		int panelCount = menuBtnList.size() / 8;
		mainMenuPanel(snackPanel, panelCount);
		
		addMenuBtn(snackPanel, menuBtnList, panelCount);

		for(BasicPanel panel : snackPanel) {
			add(panel);
		}
	}
		
	private void mainMenuPanel(ArrayList<BasicPanel> menuPanel, int panelCount) {
		for(int i = 0; i <= panelCount; i++) {
			BasicPanel panel = new BasicPanel();
			panel.setLayout(null);
			panel.setBounds(160, 100, 640, 480);
			ArrayList<BasicButton> indexBtnList = makeIndexBtnList(menuPanel, panelCount);
			for(BasicButton btn : indexBtnList) {
				panel.add(btn);
			}
			menuPanel.add(panel);
		}		
	}
	
	private ArrayList<BasicButton> makeIndexBtnList(ArrayList<BasicPanel> menuPanel, int panelCount) {
		ArrayList<BasicButton> result = new ArrayList<>();
		int xGap = 10;
		for(int i = 0; i <= panelCount; i++) {
			String index = "" + (i + 1);
			BasicButton tmp = new BasicButton(index);
			tmp.setBounds(xGap + (40 * i), 440, 40, 40);
			tmp.setBackground(new Color(41,42,45));
			tmp.setFontAttribute(10, false);
			tmp.setHorizontalAlignment(JLabel.CENTER);
			tmp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource().equals(tmp)) {
						for(int i = 0; i <= panelCount; i++) {
							menuPanel.get(i).setVisible(false);
						}
						BasicButton tmp = (BasicButton)e.getSource();
						String index = tmp.getText();
						menuPanel.get(Integer.parseInt(index) - 1).setVisible(true);
					}					
				}				
			});
			result.add(tmp);
		}
		return result;
	}
	
	private void addMenuBtn(ArrayList<BasicPanel> menuPanel, 
	ArrayList<BasicButton> menuBtnList, int panelCount) {
		for(int i = 0; i <= panelCount; i++) {
			for(int j = 1; j <= 8; j++) {
				int index = (j - 1) + (8 * i);
				int xGap = 10;
				int yGap = 100 * (j / 2) + 10;
				if(menuBtnList.size() <= index)
					break;
				BasicButton tmp = menuBtnList.get(index);
				if(j % 2 != 0) {
					tmp.setBounds(xGap, yGap, 300, 100);
				}
				else {
					tmp.setBounds(300 + xGap, yGap - 100, 300, 100);
				}
				menuPanel.get(i).add(tmp);
			}
		}
		for(int i = 0; i <= panelCount; i++) {
			for(int j = 1; j <= 8; j++) {
				int index = (j - 1) + (8 * i);
				if(menuBtnList.size() <= index)
					break;
				menuPanel.get(i).add(menuBtnList.get(index));
			}
		}
	}

	private ArrayList<BasicButton> makeMenuBtn(ArrayList<Manageable> menuList) {
		ArrayList<BasicButton> menuBtnList = new ArrayList<>();
		for(Manageable m : menuList) {
			Snack snack = (Snack)m;
			ImageIcon img1, img3 = null;
			String name = snack.getName();
			int price = snack.getPrice();
			try {
				img1 = new ImageIcon("imgs/snack/"+ name +".jpg");
			} catch(NullPointerException e) {
				img1 = new ImageIcon("imgs/이미지 준비중.jpg");
			}
			Image img2 = img1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			img3 = new ImageIcon(img2);
			BasicButton menuBtn = new BasicButton("<html>" + name +
								"<br>" + price + "원</html>");
			menuBtn.setHorizontalAlignment(JLabel.LEFT);
			menuBtn.setIcon(img3);
			menuBtn.setBackground(new Color(41, 42, 45));
			menuBtn.setFontAttribute(20, true);
			menuBtnList.add(menuBtn);
		}
		return menuBtnList;
	}
	
	private void panelVisibleSet(ArrayList<BasicPanel> visiblePanel, 
				ArrayList<BasicPanel> nonVisiblePanel1, 
				ArrayList<BasicPanel> nonVisiblePanel2, int index) {
		for(BasicPanel panel : visiblePanel) {
			panel.setVisible(false);
		}
		for(BasicPanel panel : nonVisiblePanel1) {
			panel.setVisible(false);
		}
		for(BasicPanel panel : nonVisiblePanel2) {
			panel.setVisible(false);
		}
		visiblePanel.get(index).setVisible(true);
	}
}
