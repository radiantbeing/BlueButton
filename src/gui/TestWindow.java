package gui;

import javax.swing.*;
import java.awt.*;

/*
    실행하려면 MainGUI 클래스의 run() 메소드에서 주석 처리 된 부분과
    주석 처리가 되지 않은 부분을 서로 바꿔서 사용해보시면 됩니다.
 */

public class TestWindow extends Template {
    @Override
    void addComponentsToPane() {
        JPanel testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(300, 300));
        testPanel.setBackground(Color.RED);
        primaryPanel.add(testPanel);
    }
}
