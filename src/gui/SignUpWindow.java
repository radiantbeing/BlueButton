package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpWindow extends Template {
    //황지필 01063458126 6512 9856
    String id;
    String passWord;
    String phoneNumber;

    @Override
    void addComponents() {
        setLayout(null);
        JPanel signUpPanel = new JPanel();
        JButton cancleButton = new JButton();
        setSignupComponenet(signUpPanel);
        setButtonComponent(cancleButton);
        add(signUpPanel);
        add(cancleButton);
    }

    void setSignupComponenet(JPanel signUpPanel) {
        signUpPanel.setLayout(null);
        signUpPanel.setBackground(new Color(41, 42, 45));
        signUpPanel.setPreferredSize(new Dimension(500, 800));
        signUpPanel.setBounds(500, 90, 300, 400);

        //Member Label
        JLabel memberNameLabel = new JLabel("회원가입");

        memberNameLabel.setForeground(Color.WHITE);
        memberNameLabel.setFont(new Font("NanumGothic", Font.BOLD, 20));
        memberNameLabel.setBounds(40, 10, 300, 100);
        signUpPanel.add(memberNameLabel);

        //member ID Label
        JLabel memberIdLabel = new JLabel("ID");
        memberIdLabel.setForeground(Color.WHITE);
        memberIdLabel.setFont(new Font("NanumGothic", Font.BOLD, 15));
        memberIdLabel.setBounds(20, 120, 100, 50);
        signUpPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(120, 130, 150, 30);
        signUpPanel.add(memberIdTextField);

        //member Phone Label
        JLabel memberPhoneLabel = new JLabel("전화번호");
        memberPhoneLabel.setForeground(Color.WHITE);
        memberPhoneLabel.setFont(new Font("NanumGothic", Font.BOLD, 15));
        memberPhoneLabel.setBounds(20, 200, 100, 50);
        signUpPanel.add(memberPhoneLabel);

        JTextField memberPhoneTextField = new JTextField(40);
        memberPhoneTextField.setBounds(120, 210, 150, 30);
        signUpPanel.add(memberPhoneTextField);

        //member PW Label
        JLabel memberPWLabel = new JLabel("비밀번호");
        memberPWLabel.setForeground(Color.WHITE);
        memberPWLabel.setFont(new Font("NanumGothic", Font.BOLD, 15));
        memberPWLabel.setBounds(20, 280, 100, 50);
        signUpPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(120, 290, 150, 30);
        signUpPanel.add(memberPWTextField);

        //Signup button
        JButton signUpButton = new JButton("회원가입");
        signUpButton.setBounds(30, 340, 250, 40);
        signUpButton.setBackground(new Color(0, 120, 242));
        signUpButton.setFont(new Font("NanumGothic", Font.BOLD, 18));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        signUpPanel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (BoardGameCafe.memberMgr.find(memberPhoneTextField.getText()) != null) {// phone이 중복일경우
                    JOptionPane.showMessageDialog(null, "Duplicated Phone Number");
                }
                else if (BoardGameCafe.memberMgr.find(memberPWTextField.getText()) != null) { //passWord 중복인 경우
                    JOptionPane.showMessageDialog(null, "Duplicated PassWord");
                }
                else {
                    registerMember(memberIdTextField, memberPhoneTextField, memberPWTextField);
                    JOptionPane.showMessageDialog(null, "Sign up Complete\n Please Login again");
                    MainGUI.changeWindow(MainGUI.logInWindow);
                }
                memberIdTextField.setText("");
                memberPhoneTextField.setText("");
                memberPWTextField.setText("");
            }
        });
    }

    void setButtonComponent(JButton cancleButton) {
        cancleButton.setText("취소");
        cancleButton.setBounds(100, 580, 300, 40);
        cancleButton.setBackground(new Color(0, 120, 242));
        cancleButton.setFont(new Font("NanumGothic", Font.BOLD, 18));
        cancleButton.setForeground(Color.WHITE);
        cancleButton.setBorderPainted(false);
        cancleButton.setFocusPainted(false);

        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });
    }

    void registerMember(JTextField idTextField, JTextField phoneTextField, JTextField pwTextField) {
        FileWriter writer = BoardGameCafe.memberMgr.openfileWriter("member.txt");
        id = idTextField.getText();
        phoneNumber = phoneTextField.getText();
        passWord = pwTextField.getText();

        Member newMember = new Member();
        newMember.read(id, phoneNumber, passWord);
        BoardGameCafe.memberMgr.addList(newMember);

        try {
            writer.write("\n"+id + " " + phoneNumber + " " + passWord + " " + 0);
            writer.close();
        } catch (IOException e) {
            System.out.println("파일쓰기오류");
            System.exit(0);
        }
    }
}
