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
	ArrayList<Order> orderList;
	
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
		BasicButton prevButton = new BasicButton("메뉴");
		prevButton.setBounds(160, 580, 320, 40);
        prevButton.setFontAttribute(20);
        
        prevButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.changeWindow(MainGUI.sampleOptionWindow);
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
					JOptionPane.showMessageDialog(MainGUI.bFrame, "주문내역이 존재하지 않습니다.");
					return;
				}			
				Order od = orderList.get(0);
				JOptionPane.showMessageDialog(MainGUI.bFrame,
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

				// SampleOptionWindow로 전환
				MainGUI.changeWindow(MainGUI.sampleOptionWindow);
				MainGUI.sampleOptionWindow.grayScaleButton(MainGUI.sampleOptionWindow.foodButton);
				MainGUI.sampleOptionWindow.decideEnablePayButton();
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
		ArrayList<Manageable> coffeeList = BoardGameCafe.snackMgr.findAll("coffee");
		ArrayList<BasicButton> menuBtnList = makeMenuBtn(coffeeList);
		int panelCount = menuBtnList.size() / 8;
		
		mainMenuPanel(coffeePanel, panelCount);				
		addMenuBtn(coffeePanel, menuBtnList, panelCount);
		for(int i = 0; i < coffeeList.size(); i++) {
			Snack coffee = (Snack)coffeeList.get(i);
			menuBtnList.get(i).addActionListener(new CoffeeBtnListener(coffee.name, coffee.price));
		}
		for(BasicPanel panel : coffeePanel) {
			add(panel);
		}
	}
	
	class CoffeeBtnListener implements ActionListener{
		String name;
		int price;
		public CoffeeBtnListener(String name, int price) {
			this.name = name;
			this.price = price;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			new SnackOrderOption(name, price);
		}		
	}
	
	private void setNoncoffeePanel(ArrayList<BasicPanel> nonCoffeePanel) {
		ArrayList<Manageable> nonCoffeeList = BoardGameCafe.snackMgr.findAll("noncoffee");
		ArrayList<BasicButton> menuBtnList = makeMenuBtn(nonCoffeeList);
		int panelCount = menuBtnList.size() / 8;
		
		mainMenuPanel(nonCoffeePanel, panelCount); 
		addMenuBtn(nonCoffeePanel, menuBtnList, panelCount);
		for(int i = 0; i < menuBtnList.size(); i++) {
			Snack snack = (Snack)nonCoffeeList.get(i);
			menuBtnList.get(i).addActionListener(new NotCoffeeBtnListener(snack.name, snack.price));
		}
		for(BasicPanel panel : nonCoffeePanel) {
			add(panel);
		}
	}
	
	class NotCoffeeBtnListener implements ActionListener{
		String name;
		int price;
		public NotCoffeeBtnListener(String name, int price) {
			this.name = name;
			this.price = price;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new SnackCountOption(name, price);			
		}
		
	}
	
	private void setSnackPanel(ArrayList<BasicPanel> snackPanel) {
		ArrayList<Manageable> snackList = BoardGameCafe.snackMgr.findAll("snack");
		ArrayList<BasicButton> menuBtnList = makeMenuBtn(snackList);
		int panelCount = menuBtnList.size() / 8;
		mainMenuPanel(snackPanel, panelCount);
		
		addMenuBtn(snackPanel, menuBtnList, panelCount);
		for(int i = 0; i < menuBtnList.size(); i++) {
			Snack snack = (Snack)snackList.get(i);
			menuBtnList.get(i).addActionListener(new NotCoffeeBtnListener(snack.name, snack.price));
		}
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
			ImageIcon img1 = null, img3 = null;
			String name = snack.name;
			int price = snack.price;
			img1 = new ImageIcon("imgs/snack/"+ name +".jpg");
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
	
	// 수량 입력 창
	class SnackCountOption extends JFrame{
		private static final long serialVersionUID = 1L;
		BasicPanel countPanel;
		BasicButton okBtn;
		BasicLabel label;
		JTextField countField;
		String name, option;
		int inputCount, price;
		
		public SnackCountOption(String name, int price) {
			setLayout(null);
			setBounds(450, 250, 300, 100);
			setUndecorated(true);
			setVisible(true);
			this.name = name;
			this.price = price;
			this.option = "";
			addComponentToPane();
		}
		
		public SnackCountOption(String name, int price, String subText) {
			setLayout(null);
			setBounds(450, 250, 300, 100);
			setUndecorated(true);
			setVisible(true);
			this.name = name;
			this.price = price;
			this.option = "<br>추가옵션 : " + subText;
			addComponentToPane();
		}

		private void addComponentToPane() {
			countPanel = new BasicPanel();
			countPanel.setLayout(null);
			countPanel.setSize(300, 100);
			
			label = new BasicLabel("주문을 원치 않으시면 0 입력후 확인 버튼을 누르세요");
			label.setBounds(0, 0, 300, 50);
			label.setFontAttribute(12, true);
			
			countField = new JTextField("1");
			countField.setBounds(0, 60, 200, 30);
			
			okBtn = new BasicButton("확인");
			okBtn.setBounds(200, 50, 100, 50);
			okBtn.setFontAttribute(12, true);
			okBtn.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					String text = null;
					inputCount = Integer.parseInt(countField.getText());
					if(inputCount == 0) {	// 수량 0으로 선택시 주문 리스트에 올라가지 않도록 설정
						dispose();
						return;
					}
					Order od = new Order(name, inputCount, price);
					orderList.add(od);
					if(option.equals(null)) {	// 세부 옵션이 존재하지 않는경우
						text = String.format("<br>[%d] %-8s\t %2d개 %6d원", 
								orderList.size(), name, inputCount, od.totalPrice);
					}
					else {
						text = String.format("<br>[%d] %-8s\t %2d개 %6d원", 
								orderList.size(), name, inputCount, od.totalPrice) + option;
					}
					orderListLabel.setText(orderListLabel.getText() + text);
					dispose();
				}
			});
			countPanel.add(label);
			countPanel.add(countField);
			countPanel.add(okBtn);
			
			add(countPanel);
		}
	}

	// 커피 선택시 나오는 옵션창
	class SnackOrderOption extends JFrame{
		private static final long serialVersionUID = 1L;
		BasicPanel snackOptionPanel;
	    BasicLabel orderListTitle, orderSizeTitle, 
	    		ondoTitle, syrupTitle, syrupText, vanillaText, hazelText, creamText, nowAddOption;
	    String coffeeName, subText;
		int coffeePrice;
	    
	    public SnackOrderOption(String name, int price) {
	    	this.coffeeName = name;
	    	this.coffeePrice = price;
	    	this.subText = "";
	        snackOptionPanel = new BasicPanel();
	        orderListTitle = new BasicLabel("선택하신 메뉴의 옵션을 고르세요.");
	        orderSizeTitle = new BasicLabel("사이즈                +0/+1000");
	        ondoTitle = new BasicLabel("온도                +0");
	        syrupTitle = new BasicLabel("시럽        +500");
	        syrupText = new BasicLabel("일반");
	        vanillaText = new BasicLabel("바닐라");
	        hazelText = new BasicLabel("헤이즐넛");
	        creamText = new BasicLabel("휘핑크림 / 드리즐        +500");
	        nowAddOption = new BasicLabel("세부옵션 :" + subText);

	        // Set
	        setLayout(null);
	        setBounds(300, 200, 640, 560);
	        setUndecorated(true);
	        setVisible(true);
	        addComponentToPane();
	    }

	    private void addComponentToPane() {
	        snackOptionPanel.setLayout(null);
	        snackOptionPanel.setBackground(new Color(41, 42, 45));
	        snackOptionPanel.setBounds(0, 0, 640, 560);

	        // -------------- 타이틀
	        orderListTitle.setBounds(10, 0, 640, 50);
	        orderListTitle.setFontAttribute(20, true);
	        orderListTitle.setHorizontalAlignment(JLabel.LEFT);
	        // -------------- 타이틀 끝
	        
	        // -------------- 현재 선택 옵션
	        nowAddOption.setBounds(0, 510, 480, 50);
	        nowAddOption.setFontAttribute(16, true);
	        // -------------- 사이즈
	        orderSizeTitle.setBounds(10, 40, 640, 50);
	        orderSizeTitle.setFontAttribute(20, true);
	        orderSizeTitle.setHorizontalAlignment(JLabel.LEFT);
	        // L 버튼
	        BasicButton lButton = new BasicButton("L");
	        lButton.setBounds(10, 80, 150, 40);
	        lButton.setFontAttribute(20);	        
	        // XL 버튼
	        BasicButton xlButton = new BasicButton("XL");
	        xlButton.setBounds(200, 80, 150, 40);
	        xlButton.setFontAttribute(20);   
	        // ------------------ 사이즈 끝

	        // ------------------ 온도
	        ondoTitle.setBounds(10, 120, 640, 50);
	        ondoTitle.setFontAttribute(20, true);
	        ondoTitle.setHorizontalAlignment(JLabel.LEFT);
	        // hot 버튼
	        BasicButton hotButton = new BasicButton("HOT");
	        hotButton.setBounds(10, 160, 150, 40);
	        hotButton.setFontAttribute(20);       
	        // ice 버튼
	        BasicButton iceButton = new BasicButton("ICE");
	        iceButton.setBounds(200, 160, 150, 40);
	        iceButton.setFontAttribute(20);	        
	        // ------------------ 온도 끝

	        // ------------------ 시럽
	        syrupTitle.setBounds(10, 200, 640, 50);
	        syrupTitle.setFontAttribute(20, true);
	        syrupTitle.setHorizontalAlignment(JLabel.LEFT);
	        // CHECK1
	        BasicButton check1Button = new BasicButton("X");
	        check1Button.setBounds(10, 250, 50, 50);
	        check1Button.setFontAttribute(20);	       
	        // 일반
	        syrupText.setBounds(70, 250, 640, 50);
	        syrupText.setFontAttribute(20, false);
	        syrupText.setHorizontalAlignment(JLabel.LEFT);
	        // CHECK2
	        BasicButton check2Button = new BasicButton("X");
	        check2Button.setBounds(10, 310, 50, 50);
	        check2Button.setFontAttribute(20);       
	        // 바닐라
	        vanillaText.setBounds(70, 310, 640, 50);
	        vanillaText.setFontAttribute(20, false);
	        vanillaText.setHorizontalAlignment(JLabel.LEFT);	        
	        // 헤이즐
	        hazelText.setBounds(70, 370, 640, 50);
	        hazelText.setFontAttribute(20, false);
	        hazelText.setHorizontalAlignment(JLabel.LEFT);
	        // CHECK3
	        BasicButton check3Button = new BasicButton("X");
	        check3Button.setBounds(10, 370, 50, 50);
	        check3Button.setFontAttribute(20);        
	        // ------------------ 시럽 끝

	        // ------------------ 크림
	        creamText.setBounds(10, 420, 640, 50);
	        creamText.setFontAttribute(20, true);
	        creamText.setHorizontalAlignment(JLabel.LEFT);

	        // 휘핑크림
	        BasicButton creamButton = new BasicButton("휘핑크림");
	        creamButton.setBounds(10, 470, 150, 40);
	        creamButton.setFontAttribute(20);
	        
	        // 카라멜드리즐
	        BasicButton caramelButton = new BasicButton("카라멜드리즐");
	        caramelButton.setBounds(180, 470, 150, 40);
	        caramelButton.setFontAttribute(20);
	       
	        // 초콜릿드리즐
	        BasicButton chocolateButton = new BasicButton("초콜릿드리즐");
	        chocolateButton.setBounds(350, 470, 150, 40);
	        chocolateButton.setFontAttribute(20);
	        
	        // ------------------ 크림 끝

	        // ------------------ 완료 버튼
	        BasicButton finalButton = new BasicButton("완료");
	        finalButton.setBounds(485, 520, 150, 40);
	        finalButton.setFontAttribute(20);

	        
	        // 리스너 추가------------------
	        lButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 0;
					subText += " L";
					xlButton.setEnabled(false);
					nowAddOption.setText(nowAddOption.getText() + " L");
				}
			});
	        xlButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 1000;
					subText += " XL";
					lButton.setEnabled(false);
					nowAddOption.setText(nowAddOption.getText() + " XL");
				}
			});
	        hotButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					subText += " HOT";
					iceButton.setEnabled(false);
					nowAddOption.setText(nowAddOption.getText() + " HOT");
				}
			});
	        iceButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					subText += " ICE";
					hotButton.setEnabled(false);
					nowAddOption.setText(nowAddOption.getText() + " ICE");
				}
			});
	        check1Button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 500;
					subText = " 일반시럽";
					nowAddOption.setText(nowAddOption.getText() + " 일반시럽");
				}
			});
	        check2Button.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 500;
					subText += " 바닐라시럽";
					nowAddOption.setText(nowAddOption.getText() + " 바닐라시럽");
				}
			});
	        check3Button.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 500;
					subText += " 헤이즐시럽";
					nowAddOption.setText(nowAddOption.getText() + " 헤이즐시럽");
				}
			});
	        creamButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 500;
					subText += " 휘핑추가";
					nowAddOption.setText(nowAddOption.getText() + " 휘핑추가");
				}
			});
	        caramelButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 500;
					subText += " 카라멜드리즐추가";
					nowAddOption.setText(nowAddOption.getText() + " 카라멜드리즐추가");
				}
			});
	        chocolateButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					coffeePrice += 500;
					subText += " 초콜릿드리즐추가";		
					nowAddOption.setText(nowAddOption.getText() + " 초콜릿드리즐추가");
				}
			});
	        
	        finalButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               dispose();
	               new SnackCountOption(coffeeName, coffeePrice , subText);
	            }
	        });
	        
	        // ------------------ 구성 : 위에서 부터 아래루
	        snackOptionPanel.add(orderListTitle);

	        snackOptionPanel.add(nowAddOption);
	        
	        snackOptionPanel.add(orderSizeTitle);
	        snackOptionPanel.add(lButton);
	        snackOptionPanel.add(xlButton);

	        snackOptionPanel.add(ondoTitle);
	        snackOptionPanel.add(hotButton);
	        snackOptionPanel.add(iceButton);

	        snackOptionPanel.add(syrupTitle);
	        snackOptionPanel.add(check1Button);
	        snackOptionPanel.add(check2Button);
	        snackOptionPanel.add(check3Button);

	        snackOptionPanel.add(syrupTitle);
	        snackOptionPanel.add(syrupText);
	        snackOptionPanel.add(syrupTitle);
	        snackOptionPanel.add(vanillaText);
	        snackOptionPanel.add(syrupTitle);
	        snackOptionPanel.add(hazelText);
	        snackOptionPanel.add(syrupTitle);

	        snackOptionPanel.add(creamText);
	        snackOptionPanel.add(creamButton);
	        snackOptionPanel.add(caramelButton);
	        snackOptionPanel.add(chocolateButton);

	        snackOptionPanel.add(finalButton);
	        // ————————— 구성 끝

	        // 메인 패널에 위 블럭에서 추가한거 적용하는
	        add(snackOptionPanel);
	    }
	}

}


