package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;

import javax.swing.*;
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
    public void addComponents() {
        setLayout(null);
        JPanel signUpPanel = new BasicPanel();
        BasicButton cancleButton = new BasicButton();
        setSignupComponenet(signUpPanel);
        setButtonComponent(cancleButton);
        add(signUpPanel);
        add(cancleButton);
    }

    void setSignupComponenet(JPanel signUpPanel) {
        signUpPanel.setLayout(null);
        signUpPanel.setBounds(480, 90, 320, 400);

        //Member Label
        BasicLabel memberNameLabel = new BasicLabel("회원가입");
        memberNameLabel.setFontAttribute(20, true);
        memberNameLabel.setBounds(130, 40, 150, 40);
        signUpPanel.add(memberNameLabel);

        //member ID Label
        BasicLabel memberIdLabel = new BasicLabel("이름");
        memberIdLabel.setFontAttribute(15);
        memberIdLabel.setBounds(25, 120, 100, 32);
        signUpPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(105, 120, 190, 32);
        signUpPanel.add(memberIdTextField);

        //member Phone Label
        BasicLabel memberPhoneLabel = new BasicLabel("전화번호");
        memberPhoneLabel.setFontAttribute(15);
        memberPhoneLabel.setBounds(25, 184, 100, 32);
        signUpPanel.add(memberPhoneLabel);

        JTextField memberPhoneTextField = new JTextField(40);
        memberPhoneTextField.setBounds(105, 184, 190, 32);
        signUpPanel.add(memberPhoneTextField);

        //member PW Label
        BasicLabel memberPWLabel = new BasicLabel("비밀번호");
        memberPWLabel.setFontAttribute(15);
        memberPWLabel.setBounds(25, 248, 100, 32);
        signUpPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(105, 248, 190, 32);
        signUpPanel.add(memberPWTextField);

        //Signup button
        BasicButton signUpButton = new BasicButton("회원가입");
        signUpButton.setBounds(25, 320, 270, 40);
        signUpButton.setFontAttribute(18);
        signUpPanel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (BoardGameCafe.memberMgr.find(memberPhoneTextField.getText()) != null) {// phone이 중복일경우
                    JOptionPane.showMessageDialog(MainGUI.bFrame, "중복된 전화번호입니다.");
                }
                else if (BoardGameCafe.memberMgr.find(memberPWTextField.getText()) != null) { //passWord 중복인 경우
                    JOptionPane.showMessageDialog(MainGUI.bFrame, "중복된 비밀번호입니다.");
                }
                else {
                    registerMember(memberIdTextField, memberPhoneTextField, memberPWTextField);
                    JOptionPane.showMessageDialog(MainGUI.bFrame, "회원가입이 완료되었습니다.\n 다시 로그인해 주세요.");
                    MainGUI.changeWindow(MainGUI.logInWindow);
                }
                memberIdTextField.setText("");
                memberPhoneTextField.setText("");
                memberPWTextField.setText("");
            }
        });
    }

    void setButtonComponent(BasicButton cancleButton) {
        cancleButton.setText("취소");
        cancleButton.setBounds(160,580,320,40);
        cancleButton.setFontAttribute(18);

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
            writer.write("\n"+id + " " + phoneNumber + " " + passWord);
            writer.close();
        } catch (IOException e) {
            System.out.println("파일쓰기오류");
            System.exit(0);
        }
    }
}
