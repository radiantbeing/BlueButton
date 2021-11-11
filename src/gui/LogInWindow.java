package gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LogInWindow extends Template {
    JPanel memberPanel = new JPanel();
    JPanel nonMemberPanel = new JPanel();
    @Override
    void addComponentsToPane() {
        primaryPanel.setLayout(null);
        memberLoginWindow();
        nonmemberLoginWindow();
        primaryPanel.add(memberPanel);
        primaryPanel.add(nonMemberPanel);
    }

    void memberLoginWindow(){
        //about memberPanel
        memberPanel.setLayout(null);
        memberPanel.setBackground(Color.white);
        memberPanel.setPreferredSize(new Dimension(500,500));
        memberPanel.setBounds(100,150,300,400);

        //Member Label
        JLabel memberNameLabel = new JLabel("회원");

        memberNameLabel.setForeground(new Color(48, 87, 232));
        memberNameLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        memberNameLabel.setBounds(40,40,100,100);

        memberPanel.add(memberNameLabel);

        //member ID Label
        JLabel memberIdLabel = new JLabel("ID");
        memberIdLabel.setForeground(new Color(48, 87, 232));
        memberIdLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        memberIdLabel.setBounds(40,200,100,50);
        memberPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(80,210,200,30);
        memberPanel.add(memberIdTextField);

        //member PW Label
        JLabel memberPWLabel = new JLabel("PW");
        memberPWLabel.setForeground(new Color(48, 87, 232));
        memberPWLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        memberPWLabel.setBounds(40,400,100,50);
        memberPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(100,410,200,30);
        memberPanel.add(memberPWTextField);
    }

    void nonmemberLoginWindow(){
        //about nonmemberPanel
        nonMemberPanel.setLayout(null);
        nonMemberPanel.setBackground(Color.white);
        nonMemberPanel.setPreferredSize(new Dimension(500,500));
        nonMemberPanel.setBounds(710,150,500,500);

        //nonMember Label
        JLabel nonMemberNameLabel = new JLabel("비회원");

        nonMemberNameLabel.setForeground(new Color(48, 87, 232));
        nonMemberNameLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        nonMemberNameLabel.setBounds(40,40,100,100);

        nonMemberPanel.add(nonMemberNameLabel);

        //nonmember ID Label
        JLabel nonMemberIdLabel = new JLabel("ID");
        nonMemberIdLabel.setForeground(new Color(48, 87, 232));
        nonMemberIdLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        nonMemberIdLabel.setBounds(40,200,100,50);
        nonMemberPanel.add(nonMemberIdLabel);

        JTextField nonMemberIdTextField = new JTextField(60);
        nonMemberIdTextField.setBounds(80,210,200,30);
        nonMemberPanel.add(nonMemberIdTextField);

        //nonmember PW Label
        JLabel nonMemberPWLabel = new JLabel("PW");
        nonMemberPWLabel.setForeground(new Color(48, 87, 232));
        nonMemberPWLabel.setFont(new Font("맑은고딕",Font.BOLD, 30));
        nonMemberPWLabel.setBounds(40,400,100,50);
        nonMemberPanel.add(nonMemberPWLabel);

        JTextField nonMemberPWTextField = new JTextField(40);
        nonMemberPWTextField.setBounds(100,410,200,30);
        nonMemberPanel.add(nonMemberPWTextField);
    }

    public static void main(String[]args){
        new LogInWindow().createAndShowGUI();
    }
}
