package gui;

import javax.swing.*;
import java.awt.*;

/*
    해당 클래스로 Panel 객체 생성 시
    개발자가 추가로 작업해줘야 하는 작업은 다음과 같습니다.

    1. 패널에 들어갈 컴포넌트를 절대 위치로 지정 시 panel.setLayout(null);

    2. 패널의 크기 지정 (선택적으로 위치 지정)
    ex1. panel.setPreferredSize(new Dimension(width, height));
         (선택) panel.setLocation(x, y);
    ex2. panel.setBounds(x, y, width, height);
 */

public class BasicPanel extends JPanel {
    public BasicPanel() {
        setBackground(new Color(41, 42, 45));
    }
}
