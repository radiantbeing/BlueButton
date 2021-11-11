package gui;

import javax.swing.*;
import java.awt.*;
/*
Template 클래스 사용법
1. 만들고자 하는 Window 클래스를 extends Template 시킵니다.
2. addComponents()을 오버라이딩하여 구현합니다.
3. 리스너에서 Window를 전환하는 명령을 작성할 때는 changeWindow() 메소드를 이용합니다.

*** Template를 상속한 클래스는 1280*720 크기의 JPanel입니다.
*** 사용 예시를 보고 싶다면 RoomViewWindow를 참고하세요.
 */
public abstract class Template extends JPanel {
    public Template() {
        setPreferredSize(new Dimension(1280, 720));
        setBackground(new Color(69,116,203));
        addComponents();
    }

    abstract void addComponents();

    void changeWindow(Template window) {
        MainGUI.centerPanel.remove(this);
        MainGUI.centerPanel.revalidate();
        MainGUI.centerPanel.repaint();
        MainGUI.centerPanel.add(window);
    }
}
