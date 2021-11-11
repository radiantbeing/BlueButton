package gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LogInWindow extends JFrame {
    JLabel topLabel = new JLabel("Log-In");
    JPanel memberPanel = new JPanel();
    JPanel nonMemberPanel = new JPanel();
    void createComponent(){
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        topLabel.setBackground(new Color(48, 87, 232));
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(new Font("Log-In",Font.BOLD, 50));
        topLabel.setOpaque(true);
        add(topLabel,BorderLayout.NORTH);

        memberLoginWindow();
        nonmemberLoginWindow();

        add(memberPanel,BorderLayout.LINE_START);
        add(nonMemberPanel,BorderLayout.LINE_END);
    }

    void memberLoginWindow(){
        JLabel memberNameLabel = new JLabel("회원");
        memberNameLabel.setForeground(new Color(48, 87, 232));
        memberNameLabel.setFont(new Font("회원",Font.BOLD, 50));

        JTextField memberIdTextField = new JTextField(40);
        JLabel memberIdLabel = new JLabel("ID");
        memberIdLabel.setForeground(new Color(48, 87, 232));
        memberIdLabel.setFont(new Font("ID",Font.BOLD, 30));

        JTextField memberPassWordField = new JTextField(40);
        JLabel memberPwLabel = new JLabel("PW");
        memberPwLabel.setForeground(new Color(48, 87, 232));
        memberPwLabel.setFont(new Font("PW",Font.BOLD, 30));

        memberPanel.add(memberNameLabel);
        memberPanel.add(memberIdLabel);
        memberPanel.add(memberIdTextField);
        memberPanel.add(memberPwLabel);
        memberPanel.add(memberPassWordField);
    }

    void nonmemberLoginWindow(){
        JLabel nonMemberNameLabel = new JLabel("비회원");
        nonMemberNameLabel.setForeground(new Color(48, 87, 232));
        nonMemberNameLabel.setFont(new Font("비회원",Font.BOLD, 50));

        JTextField nonMemberIdTextField = new JTextField(40);
        JLabel nonMemberIdLabel = new JLabel("이름");
        nonMemberIdLabel.setForeground(new Color(48, 87, 232));
        nonMemberIdLabel.setFont(new Font("이름",Font.BOLD, 30));

        JTextField nonMemberPassWordField = new JTextField(40);
        JLabel nonMemberPwLabel = new JLabel("전화번호");
        nonMemberPwLabel.setForeground(new Color(48, 87, 232));
        nonMemberPwLabel.setFont(new Font("전화번호",Font.BOLD, 30));

        nonMemberPanel.add(nonMemberNameLabel);
        nonMemberPanel.add(nonMemberIdLabel);
        nonMemberPanel.add(nonMemberIdTextField);
        nonMemberPanel.add(nonMemberPwLabel);
        nonMemberPanel.add(nonMemberPassWordField);
    }

    public static void main(String[]args){
        new LogInWindow().createComponent();
    }
}
