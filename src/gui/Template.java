package gui;

import javax.swing.*;
import java.awt.*;

/*
    <Template 클래스 이용법>
    1. '~~Window' 클래스를 생성합니다.
    2. Template 클래스를 상속합니다.(ex. public class TestWindow extends Template)
    3. void addComponentsToPane()를 @Override하여 구현합니다.
    4. primaryPanel이 수업에서 배운 contentPane과 같은 역할을 하기 때문에
        Components들을 primaryPanel에 add해주시면 됩니다.
    사용 예시는 TestWindow 클래스를 참고해주세요.
 */

public abstract class Template extends JFrame {
    JPanel topPanel = new JPanel();
    JLabel topLabel = new JLabel("BLUEBUTTON");
    JPanel primaryPanel = new JPanel();
    Color primaryColor = new Color(69,116,203);
    Color secondaryColor = new Color(48, 87, 232);

    public void createAndShowGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1296, 839)); // contentPane의 size는 1280*800
        setLocation(300, 100);
        setTitle("BLUEBUTTON");
        setInitialComponents();
        addComponentsToPane();
        pack();
        setVisible(true);
    }

    void setInitialComponents() {
        Container contentPane = getContentPane();

        // About topPanel
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1280, 80));
        topPanel.setBackground(Color.WHITE);
        topPanel.setForeground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,secondaryColor));

        // About topLabel
        topPanel.add(topLabel, FlowLayout.LEFT);
        topLabel.setFont(new Font("맑은고딕", Font.BOLD, 30));
        topLabel.setForeground(primaryColor);
        topLabel.setBounds(20, 12, 400, 50);

        // About primaryPanel
        contentPane.add(primaryPanel, BorderLayout.CENTER);
        primaryPanel.setPreferredSize(new Dimension(1280, 720));
        primaryPanel.setBackground(primaryColor);
    }

    abstract void addComponentsToPane();
}
