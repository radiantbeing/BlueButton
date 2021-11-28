package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.NonMember;
import boardgamecafe.Order;
import boardgamecafe.Snack;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;
import mgr.Manageable;

public class SnackSelectWindow extends Template{
	private static final long serialVersionUID = 1L;
	BasicPanel snackOptionPanel, orderListPanel;
	BasicLabel orderListTitle, orderListLabel;
	ArrayList<BasicPanel> nonCoffeePanel, snackPanel, coffeePanel;
	static ArrayList<Order> orderList;
	@Override
	public void addComponents() {
		nonCoffeePanel = new ArrayList<>();
		snackOptionPanel = new BasicPanel();
		coffeePanel = new ArrayList<>();
		snackPanel = new ArrayList<>();
		orderListPanel = new BasicPanel();
		orderListLabel = new BasicLabel("<html>");
		orderListTitle = new BasicLabel("<현재 주문 내역>");
		orderList = new ArrayList<>();
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
		BasicButton prevButton = new BasicButton("이전");
		prevButton.setBounds(150, 580, 150, 40);
        prevButton.setFontAttribute(20);
        
        prevButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.changeWindow(MainGUI.roomViewWindow);
			}
		});
		add(prevButton);
		add(snackOptionPanel);
	}
	
	private void setOrderListPanel(BasicPanel orderPanel) {
		// 주문 내역 패널
		orderPanel.setLayout(null);
		orderPanel.setBounds(880, 260, 300, 300);
		
		// 주문 내역 버튼
		BasicButton orderButton = new BasicButton("주문하기");
		orderButton.setBounds(0, 250, 150, 50);
		orderButton.setBackground(new Color(41, 42, 45));
		orderButton.setFontAttribute(16, true);
		
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderList.size() == 0) {
					JOptionPane.showMessageDialog(null, "주문내역이 존재하지 않습니다.");
					return;
				}			
				Order od = orderList.get(0);
				JOptionPane.showMessageDialog(null,
					String.format("<html>%s 외 %d개가 주문됩니다"
						+"<br>총액 : %5d원",
							od.orderedMenu, orderList.size(), 
								calcTotalPrice(orderList, orderList.size())));
				NonMember m = (NonMember) LogInWindow.getNowLoginMember();
				for(Order od1 : orderList) {
					m.orderList.add(od1);
				}
				orderList.clear();
				orderListLabel.setText("<html>");
			}

			private int calcTotalPrice(ArrayList<Order> orderList, int totalCount) {
				int sum = 0;
				for(Order od : orderList) {
					sum += od.totalPrice;
				}
				return sum;
			}			
		});
		
		BasicButton deleteButton = new BasicButton("전체삭제");
		deleteButton.setBounds(150, 250, 150, 50);
		deleteButton.setBackground(new Color(41, 42, 45));
		deleteButton.setFontAttribute(16, true);
		
		deleteButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				orderListLabel.setText("<html>");
				orderList.clear();
			}
		});
		
		orderPanel.add(orderButton);
		orderPanel.add(deleteButton);
		
		// 주문 내역 라벨
		orderListTitle.setBounds(0, 0, 300, 50);
		orderListTitle.setFontAttribute(20, true);
		orderListTitle.setHorizontalAlignment(JLabel.CENTER);
		
		orderListLabel.setBounds(0, 50, 300, 200);
		orderListLabel.setFontAttribute(14, true);
		orderListLabel.setHorizontalAlignment(JLabel.LEFT);
		
		JScrollPane scroll = new JScrollPane(orderListLabel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(0, 50, 300, 200);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getViewport().setBackground(new Color(41,42,45));
		
		orderPanel.add(scroll);
		orderPanel.add(orderListTitle);
		
		add(orderPanel);
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
			panel.setBounds(160, 100, 640, 460);
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
			tmp.setBounds(xGap + (40 * i), 420, 40, 40);
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
			String name = snack.name;
			int price = snack.price;
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
			menuBtn.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					new SnackCountDialog(name, price);
				}
			});
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
	class SnackCountDialog extends JFrame {
		private static final long serialVersionUID = 1L;
		BasicPanel countPanel;
		BasicButton okBtn;
		JTextField countField;
		String name;
		int inputCount, price;
		
		public SnackCountDialog(String name, int price) {
			setLayout(null);
			setBounds(450, 250, 300, 50);
			setUndecorated(true);
			setVisible(true);
			this.name = name;
			this.price = price;
			addComponentToPane();
		}

		private void addComponentToPane() {
			// TODO Auto-generated method stub
			countPanel = new BasicPanel();
			countPanel.setLayout(null);
			countPanel.setSize(300, 100);
			
			countField = new JTextField("1");
			countField.setBounds(0, 10, 200, 30);
			
			okBtn = new BasicButton("확인");
			okBtn.setBounds(200, 0, 100, 50);
			okBtn.setFontAttribute(12, true);
			okBtn.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					inputCount = Integer.parseInt(countField.getText());
					Snack s = (Snack) BoardGameCafe.snackMgr.find(name);
					Order od = new Order(name, inputCount, s.price);
					orderList.add(od);
					String text = String.format("<br>[%d] %-8s\t %2d개 %6d원", 
								orderList.size(), od.orderedMenu, od.orderedCount, od.totalPrice);
					orderListLabel.setText(orderListLabel.getText() + text);
					dispose();
				}
			});
			countPanel.add(countField);
			countPanel.add(okBtn);
			
			add(countPanel);
		}
	}
}


