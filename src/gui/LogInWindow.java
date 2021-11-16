package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class LogInWindow extends Template {
    String phoneNumber;
    String name;
    String password;
    String id;

    @Override
    void addComponents() {
        JPanel memberPanel = new JPanel();
        JPanel nonMemberPanel = new JPanel();
        JLabel explainLabel = new JLabel();
        JButton signupButton = new JButton();
        JButton prevButton = new JButton();
        setLayout(null);
        memberLoginWindow(memberPanel);
        nonmemberLoginWindow(nonMemberPanel);
        textWindow(explainLabel);
        buttonComponent(signupButton, prevButton);
        add(memberPanel);
        add(nonMemberPanel);
        add(explainLabel);
        add(signupButton);
        add(prevButton);
    }

    void memberLoginWindow(JPanel memberPanel){
        //about memberPanel
        memberPanel.setLayout(null);
        memberPanel.setBackground(Color.white);
        memberPanel.setPreferredSize(new Dimension(500,500));
        memberPanel.setBounds(100,90,300,400);

        //Member Label
        JLabel memberNameLabel = new JLabel("Member");

        memberNameLabel.setForeground(new Color(48, 87, 232));
        memberNameLabel.setFont(new Font("맑은고딕",Font.BOLD, 20));
        memberNameLabel.setBounds(40,10,100,100);
        memberPanel.add(memberNameLabel);

        //member ID Label
        JLabel memberIdLabel = new JLabel("ID");
        memberIdLabel.setForeground(new Color(48, 87, 232));
        memberIdLabel.setFont(new Font("맑은고딕",Font.BOLD, 15));
        memberIdLabel.setBounds(20,120,100,50);
        memberPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(80,130,200,30);
        memberPanel.add(memberIdTextField);

        //member PW Label
        JLabel memberPWLabel = new JLabel("PW");
        memberPWLabel.setForeground(new Color(48, 87, 232));
        memberPWLabel.setFont(new Font("맑은고딕",Font.BOLD, 15));
        memberPWLabel.setBounds(20,200,100,50);
        memberPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(80,210,200,30);
        memberPanel.add(memberPWTextField);

        //login button
        JButton logInButton = new JButton("Log in");
        logInButton.setBounds(30,300,250,40);
        logInButton.setBackground(Color.WHITE);
        logInButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        logInButton.setForeground(new Color(69,116,203));
        logInButton.setBorderPainted(false);
        logInButton.setFocusPainted(false);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = memberIdTextField.getText();
                Member m = (Member) BoardGameCafe.memberMgr.find(id);
                password = memberPWTextField.getText();
                if (m == null){// 잘못된 아이디 입력시
                    JOptionPane.showMessageDialog(null, "ID doesn't exist");
                    memberPWTextField.setText("");
                    memberIdTextField.setText("");
                    return;
                }
                
                if (m.matches(password)){ //로그인 성공
                    JOptionPane.showMessageDialog(null, "Login Complete");
                    //후에 좌석선택, 시간선택으로 넘어가야함
                }
                else{//다른 비밀번호
                    JOptionPane.showMessageDialog(null, "Wrong Password");
                    memberPWTextField.setText("");
                }
            }
        });

        memberPanel.add(logInButton);
    }

    void nonmemberLoginWindow(JPanel nonMemberPanel){
        //about nonmemberPanel
        nonMemberPanel.setLayout(null);
        nonMemberPanel.setBackground(Color.white);
        nonMemberPanel.setPreferredSize(new Dimension(500,500));
        nonMemberPanel.setBounds(710,90,300,400);

        //nonMember Label
        JLabel nonMemberNameLabel = new JLabel("None Member");

        nonMemberNameLabel.setForeground(new Color(48, 87, 232));
        nonMemberNameLabel.setFont(new Font("맑은고딕",Font.BOLD, 20));
        nonMemberNameLabel.setBounds(40,10,200,100);

        nonMemberPanel.add(nonMemberNameLabel);

        //nonmember ID Label
        JLabel nonMemberIdLabel = new JLabel("Name");
        nonMemberIdLabel.setForeground(new Color(48, 87, 232));
        nonMemberIdLabel.setFont(new Font("맑은고딕",Font.BOLD, 15));
        nonMemberIdLabel.setBounds(20,120,100,50);
        nonMemberPanel.add(nonMemberIdLabel);

        JTextField nonMemberIdTextField = new JTextField(60);
        nonMemberIdTextField.setBounds(80,130,200,30);
        nonMemberPanel.add(nonMemberIdTextField);

        //nonmember PW Label
        JLabel nonMemberPWLabel = new JLabel("Phone");
        nonMemberPWLabel.setForeground(new Color(48, 87, 232));
        nonMemberPWLabel.setFont(new Font("맑은고딕",Font.BOLD, 15));
        nonMemberPWLabel.setBounds(20,200,100,50);
        nonMemberPanel.add(nonMemberPWLabel);

        JTextField nonMemberPWTextField = new JTextField(40);
        nonMemberPWTextField.setBounds(80,210,200,30);
        nonMemberPanel.add(nonMemberPWTextField);

        //login button
        JButton logInButton = new JButton("Log in");
        logInButton.setBounds(30,300,250,40);
        logInButton.setBackground(Color.WHITE);
        logInButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        logInButton.setForeground(new Color(69,116,203));
        logInButton.setBorderPainted(false);
        logInButton.setFocusPainted(false);


        nonMemberPanel.add(logInButton);
    }

    void textWindow(JLabel explainLabel){
        explainLabel.setText("Membership benefits: You can earn 5% of the payment amount.");
        explainLabel.setForeground(Color.white);
        explainLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        explainLabel.setBounds(100,500,1000,50);
    }

    void buttonComponent(JButton signupButton, JButton prevButton){
        prevButton.setText("Prev");
        prevButton.setBounds(100,580,300,40);
        prevButton.setBackground(Color.WHITE);
        prevButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        prevButton.setForeground(new Color(69,116,203));
        prevButton.setBorderPainted(false);
        prevButton.setFocusPainted(false);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomViewWindow);
            }
        });

        signupButton.setText("Sign Up");
        signupButton.setBounds(710,580,300,40);
        signupButton.setBackground(Color.WHITE);
        signupButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        signupButton.setForeground(new Color(69,116,203));
        signupButton.setBorderPainted(false);
        signupButton.setFocusPainted(false);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.signUpWindow);
            }
        });
    }

}
