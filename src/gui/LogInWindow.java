package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;
import boardgamecafe.NonMember;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;
import mgr.Manageable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInWindow extends Template {
    String phoneNumber;
    String name;
    String password;
    static Member nowLoginMember;
    static NonMember nowLoginNonMember;
    static boolean flag = true;
    @Override
    public void addComponents() {
        JPanel memberPanel = new BasicPanel();
        JPanel nonMemberPanel = new BasicPanel();
        BasicButton signupButton = new BasicButton();
        BasicButton prevButton = new BasicButton();
        setLayout(null);
        memberLoginWindow(memberPanel);
        nonmemberLoginWindow(nonMemberPanel);
        buttonComponent(signupButton, prevButton);
        add(memberPanel);
        add(nonMemberPanel);
        add(signupButton);
        add(prevButton);
    }

    void memberLoginWindow(JPanel memberPanel){
        //about memberPanel
        memberPanel.setLayout(null);
        memberPanel.setBounds(160,90,320,400);

        //Member Label
        BasicLabel memberNameLabel = new BasicLabel("회원");
        memberNameLabel.setFontAttribute(20, true);
        memberNameLabel.setBounds(135, 40, 150, 40);
        memberPanel.add(memberNameLabel);

        //member ID Label
        BasicLabel memberIdLabel = new BasicLabel("전화번호");
        memberIdLabel.setFontAttribute(15);
        memberIdLabel.setBounds(25, 120, 70, 53);
        memberPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(105, 130, 190, 33);
        memberPanel.add(memberIdTextField);

        //member PW Label
        BasicLabel memberPWLabel = new BasicLabel("비밀번호");
        memberPWLabel.setFontAttribute(15);
        memberPWLabel.setBounds(25, 227, 70, 53);
        memberPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(105, 237, 190, 33);
        memberPanel.add(memberPWTextField);

        //login button
        BasicButton logInButton = new BasicButton("로그인");
        logInButton.setBounds(25, 320, 270, 40);
        logInButton.setFontAttribute(18);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneNumber = memberIdTextField.getText();
                Member m = (Member) BoardGameCafe.memberMgr.find(phoneNumber);
                password = memberPWTextField.getText();
                if (m == null || phoneNumber.equals("")){ // 잘못된 전화번호 입력시
                    JOptionPane.showMessageDialog(null, "전화번호가 존재하지 않습니다");
                    memberPWTextField.setText("");
                    memberIdTextField.setText("");
                    return;
                }
                
                if (m.matches(password)){ // 로그인 성공
                    JOptionPane.showMessageDialog(null, "로그인 성공");
                    nowLoginMember = m;
                    flag = true;
                    MainGUI.changeWindow(MainGUI.sampleOptionWindow);
                    // 후에 좌석선택, 시간선택으로 넘어가야함
                }
                else{ // 다른 비밀번호
                    JOptionPane.showMessageDialog(null, "비밀번호를 정확히 입력해 주세요");
                    memberPWTextField.setText("");
                }
                memberPWTextField.setText("");
                memberIdTextField.setText("");
            }
        });
        memberPanel.add(logInButton);

        // About membership benefit ToolTip
        JButton benefitViewer = new JButton(MainGUI.scaleImageIcon("imgs/question.png", 28, 28));
        benefitViewer.setBorderPainted(false);
        benefitViewer.setContentAreaFilled(false);
        benefitViewer.setFocusPainted(false);
        benefitViewer.setOpaque(false);
        memberPanel.add(benefitViewer);
        benefitViewer.setBounds(265, 45, 30, 30);
        benefitViewer.setToolTipText("<html>회원 혜택<br><br>-결제금액의 5% 적립가능<br>-회원 등급에 따른 할인부여</html>");
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }

    void nonmemberLoginWindow(JPanel nonMemberPanel){
        //about nonmemberPanel
        nonMemberPanel.setLayout(null);
        nonMemberPanel.setBounds(800,90,320,400);

        //nonMember Label
        BasicLabel nonMemberNameLabel = new BasicLabel("비회원");
        nonMemberNameLabel.setFontAttribute(20, true);
        nonMemberNameLabel.setBounds(135, 40, 150, 40);
        nonMemberPanel.add(nonMemberNameLabel);

        //nonmember ID Label
        BasicLabel nonMemberIdLabel = new BasicLabel("이름");
        nonMemberIdLabel.setFontAttribute( 15);
        nonMemberIdLabel.setBounds(25, 120, 70, 53);
        nonMemberPanel.add(nonMemberIdLabel);

        JTextField nonMemberIdTextField = new JTextField(60);
        nonMemberIdTextField.setBounds(105, 130, 190, 33);
        nonMemberPanel.add(nonMemberIdTextField);

        //nonmember PW Label
        BasicLabel nonMemberPWLabel = new BasicLabel("전화번호");
        nonMemberPWLabel.setFontAttribute( 15);
        nonMemberPWLabel.setBounds(25, 227, 70, 53);
        nonMemberPanel.add(nonMemberPWLabel);

        JTextField nonMemberPWTextField = new JTextField(40);
        nonMemberPWTextField.setBounds(105, 237, 190, 33);
        nonMemberPanel.add(nonMemberPWTextField);

        //login button
        BasicButton logInButton = new BasicButton("로그인");
        logInButton.setBounds(25, 320, 270, 40);
        logInButton.setFontAttribute(18);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nonMemberIdTextField.getText();
                phoneNumber = nonMemberPWTextField.getText();

                if (name.equals("")){// 잘못된 아이디 입력시
                    JOptionPane.showMessageDialog(null, "아이디를 정확히 입력하세요");
                    nonMemberIdTextField.setText("");
                    nonMemberPWTextField.setText("");
                    return;
                }
                //입력이 제대로 될때-> 파일이 입력이 된다.
                JOptionPane.showMessageDialog(null, "로그인 성공");
                flag = false;
                MainGUI.changeWindow(MainGUI.sampleOptionWindow);
                nonMemberIdTextField.setText("");
                nonMemberPWTextField.setText("");
            }
        });

        nonMemberPanel.add(logInButton);
    }

    void buttonComponent(BasicButton signupButton, BasicButton prevButton){
        prevButton.setText("이전");
        prevButton.setBounds(160,580,320,40);
        prevButton.setFontAttribute(18);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomViewWindow);
            }
        });

        signupButton.setText("회원가입");
        signupButton.setBounds(800,580,320,40);
        signupButton.setFontAttribute(18);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.signUpWindow);
            }
        });
    }

    static Manageable getNowLoginMember(){
        if (flag){
            return nowLoginMember;
        }
        return nowLoginNonMember;
    }
}
