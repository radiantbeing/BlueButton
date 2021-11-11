package gui;

import javax.swing.*;
import java.awt.*;
/*
    Template 클래스 사용법
    1. ~Window 클래스를 만들고 Template를 상속시킵니다.
    2. addComponents()을 오버라이딩하여 구현합니다.
    3. MainGUI의 "// Create Windows" 주석 아랫 부분에 static으로 인스턴스를 만듭니다.
        * MainGUI 클래스의 4~6줄 참고
    3. 리스너에서 Window를 전환하는 명령을 작성합니다.
        * RoomViewWindow 클래스의 57~62줄 참고

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
}
