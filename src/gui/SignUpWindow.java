package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        signUpPanel.setBackground(Color.white);
        signUpPanel.setPreferredSize(new Dimension(500, 800));
        signUpPanel.setBounds(500, 90, 300, 400);

        //Member Label
        JLabel memberNameLabel = new JLabel("Member Sign Up");

        memberNameLabel.setForeground(new Color(48, 87, 232));
        memberNameLabel.setFont(new Font("맑은고딕", Font.BOLD, 20));
        memberNameLabel.setBounds(40, 10, 300, 100);
        signUpPanel.add(memberNameLabel);

        //member ID Label
        JLabel memberIdLabel = new JLabel("ID");
        memberIdLabel.setForeground(new Color(48, 87, 232));
        memberIdLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
        memberIdLabel.setBounds(20, 120, 100, 50);
        signUpPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(80, 130, 200, 30);
        signUpPanel.add(memberIdTextField);

        //member Phone Label
        JLabel memberPhoneLabel = new JLabel("Phone");
        memberPhoneLabel.setForeground(new Color(48, 87, 232));
        memberPhoneLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
        memberPhoneLabel.setBounds(20, 200, 100, 50);
        signUpPanel.add(memberPhoneLabel);

        JTextField memberPhoneTextField = new JTextField(40);
        memberPhoneTextField.setBounds(80, 210, 200, 30);
        signUpPanel.add(memberPhoneTextField);

        //member PW Label
        JLabel memberPWLabel = new JLabel("PW");
        memberPWLabel.setForeground(new Color(48, 87, 232));
        memberPWLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
        memberPWLabel.setBounds(20, 280, 100, 50);
        signUpPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(80, 290, 200, 30);
        signUpPanel.add(memberPWTextField);

        //Signup button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(30, 340, 250, 40);
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        signUpButton.setForeground(new Color(69, 116, 203));
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
        cancleButton.setText("Cancle");
        cancleButton.setBounds(100, 580, 300, 40);
        cancleButton.setBackground(Color.WHITE);
        cancleButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        cancleButton.setForeground(new Color(69, 116, 203));
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
