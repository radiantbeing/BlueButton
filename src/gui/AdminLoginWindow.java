package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boardgamecafe.Admin;
import boardgamecafe.Administrator;
import boardgamecafe.BoardGameCafe;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;
import mgr.Manageable;

public class AdminLoginWindow extends Template {
	private static final long serialVersionUID = 1L;
	private static Administrator adm = new Administrator();
	@Override
	public void addComponents() {
		JPanel adminLoginPanel = new BasicPanel();
		setLayout(null);
		adminLoginWindow(adminLoginPanel);
		add(adminLoginPanel);
	}
	
	private void adminLoginWindow(JPanel adminLoginPanel) {
		
		// About loginPanel
		adminLoginPanel.setLayout(null);
		adminLoginPanel.setBounds(480, 90, 320, 400);

		BasicLabel adminLabel = new BasicLabel("관리자 로그인11");
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFontAttribute(20, true);
		adminLabel.setBounds(100, 40, 150, 40);
		adminLoginPanel.add(adminLabel);

		BasicLabel adminIdLabel = new BasicLabel("ID");
		adminIdLabel.setFontAttribute(15);
		adminIdLabel.setBounds(25, 120, 70, 53);
		adminLoginPanel.add(adminIdLabel);

		JTextField adminIdField = new JTextField(60);
		adminIdField.setBounds(105, 130, 190, 33);
		adminLoginPanel.add(adminIdField);

		BasicLabel adminPwdLabel = new BasicLabel("PW");
		adminPwdLabel.setFontAttribute( 15);
		adminPwdLabel.setBounds(25, 227, 70, 53);
		adminLoginPanel.add(adminPwdLabel);
		
		JTextField adminPwdField = new JTextField(40);
		adminPwdField.setBounds(105, 237, 190, 33);
		adminLoginPanel.add(adminPwdField);

		BasicButton adminLoginBtn = new BasicButton("로그인");
		adminLoginBtn.setFontAttribute( 18);
		adminLoginBtn.setBounds(25, 320, 270, 40);
		adminLoginPanel.add(adminLoginBtn);

		adminLoginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String adminId = adminIdField.getText();
				String adminPwd = adminPwdField.getText();
				
				Admin admin = (Admin) BoardGameCafe.adminMgr.find(adminId);
				if(admin == null || adminId.equals("")) {
					JOptionPane.showMessageDialog(null, "관리자 아이디가 일치하지 않습니다.");
					adminIdField.setText("");
					adminPwdField.setText("");
					return;
				}
				
				if(admin.matches(adminPwd)) {
					JOptionPane.showMessageDialog(null, "로그인 성공!");
					adm.run();
					return;
				}
				else {
					JOptionPane.showMessageDialog(null, "관리자 비밀번호가 일치하지 않습니다!");
					adminPwdField.setText("");
					return;
				}
			}
		});
		
		// About prevBtn
		BasicButton prevBtn = new BasicButton("이전");
		prevBtn.setFontAttribute( 18);
		prevBtn.setBounds(160,580,320,40);
		
		// if you click this button, go to roomViewWindow.
		prevBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.changeWindow(MainGUI.roomViewWindow);
			}
		});
		
		add(prevBtn);
	}
}
