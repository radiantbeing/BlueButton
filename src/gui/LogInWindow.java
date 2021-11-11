package gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LogInWindow extends Template {
    JPanel memberPanel = new JPanel();
    JPanel nonMemberPanel = new JPanel();
    JLabel explainLabel = new JLabel();
    JButton signupButton;
    JButton signInButton;

    @Override
    void addComponentsToPane() {
        primaryPanel.setLayout(null);
        memberLoginWindow();
        nonmemberLoginWindow();
        textWindow();
        buttonComponent();
        primaryPanel.add(memberPanel);
        primaryPanel.add(nonMemberPanel);
        primaryPanel.add(explainLabel);
        primaryPanel.add(signupButton);
        primaryPanel.add(signInButton);
    }

    void memberLoginWindow(){
        //about memberPanel
        memberPanel.setLayout(null);
        memberPanel.setBackground(Color.white);
        memberPanel.setPreferredSize(new Dimension(500,500));
        memberPanel.setBounds(100,90,300,400);

        //Member Label
        JLabel memberNameLabel = new JLabel("회원");

        memberNameLabel.setForeground(new Color(48, 87, 232));
        memberNameLabel.setFont(new Font("맑은고딕",Font.BOLD, 20));
        memberNameLabel.setBounds(40,10,100,100);
        memberPanel.add(memberNameLabel);

        //member ID Label
        JLabel memberIdLabel = new JLabel("ID");
        memberIdLabel.setForeground(new Color(48, 87, 232));
        memberIdLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        memberIdLabel.setBounds(40,120,100,50);
        memberPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(80,130,200,30);
        memberPanel.add(memberIdTextField);

        //member PW Label
        JLabel memberPWLabel = new JLabel("PW");
        memberPWLabel.setForeground(new Color(48, 87, 232));
        memberPWLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        memberPWLabel.setBounds(20,200,100,50);
        memberPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(80,210,200,30);
        memberPanel.add(memberPWTextField);
    }

    void nonmemberLoginWindow(){
        //about nonmemberPanel
        nonMemberPanel.setLayout(null);
        nonMemberPanel.setBackground(Color.white);
        nonMemberPanel.setPreferredSize(new Dimension(500,500));
        nonMemberPanel.setBounds(710,90,300,400);

        //nonMember Label
        JLabel nonMemberNameLabel = new JLabel("비회원");

        nonMemberNameLabel.setForeground(new Color(48, 87, 232));
        nonMemberNameLabel.setFont(new Font("맑은고딕",Font.BOLD, 20));
        nonMemberNameLabel.setBounds(40,10,100,100);

        nonMemberPanel.add(nonMemberNameLabel);

        //nonmember ID Label
        JLabel nonMemberIdLabel = new JLabel("이름");
        nonMemberIdLabel.setForeground(new Color(48, 87, 232));
        nonMemberIdLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        nonMemberIdLabel.setBounds(20,120,100,50);
        nonMemberPanel.add(nonMemberIdLabel);

        JTextField nonMemberIdTextField = new JTextField(60);
        nonMemberIdTextField.setBounds(80,130,200,30);
        nonMemberPanel.add(nonMemberIdTextField);

        //nonmember PW Label
        JLabel nonMemberPWLabel = new JLabel("전화번호");
        nonMemberPWLabel.setForeground(new Color(48, 87, 232));
        nonMemberPWLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        nonMemberPWLabel.setBounds(20,200,100,50);
        nonMemberPanel.add(nonMemberPWLabel);

        JTextField nonMemberPWTextField = new JTextField(40);
        nonMemberPWTextField.setBounds(80,210,200,30);
        nonMemberPanel.add(nonMemberPWTextField);
    }

    void textWindow(){
        explainLabel = new JLabel("Membership benefits: You can earn 5% of the payment amount.");
        explainLabel.setForeground(Color.white);
        explainLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        explainLabel.setBounds(100,500,1000,50);
    }

    void buttonComponent(){
        signupButton = new JButton("Sign Up");
        signupButton.setBounds(100,580,300,40);
        signupButton.setBackground(Color.WHITE);
        signupButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        signupButton.setForeground(new Color(69,116,203));
        signupButton.setBorderPainted(false);
        signupButton.setFocusPainted(false);

        signInButton = new JButton("Sign In");
        signInButton.setBounds(710,580,300,40);
        signInButton.setBackground(Color.WHITE);
        signInButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        signInButton.setForeground(new Color(69,116,203));
        signInButton.setBorderPainted(false);
        signInButton.setFocusPainted(false);
    }

    public static void main(String[]args){
        new LogInWindow().createAndShowGUI();
    }
}
