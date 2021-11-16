package gui;

import javax.swing.*;
import java.awt.*;

/*
    해당 클래스로 Label 객체 생성 시
    개발자가 추가로 작업해줘야 하는 작업은 다음과 같습니다.

    1. 라벨의 크기 지정 (선택적으로 위치 지정)
    ex1. label.setPreferredSize(new Dimension(width, height));
         (선택) label.setLocation(x, y);
    ex2. label.setBounds(x, y, width, height);

    2. 라벨의 BOLD 처리 여부 및 크기 지정
    ex1. label.setFontAttribute(18);
    ex2. label.setFontAttribute(18, true);
 */

public class BasicLabel extends JLabel {
    public BasicLabel() {
        setForeground(Color.WHITE);
        setFont(new Font("NanumGothic", Font.PLAIN, 10));
    }

    public BasicLabel(String phrase) {
        setForeground(Color.WHITE);
        setFont(new Font("NanumGothic", Font.PLAIN, 10));
        setText(phrase);
    }

    void setFontAttribute(int size) {
        setFont(new Font("NanumGothic", Font.PLAIN, size));
    }

    void setFontAttribute(int size, boolean bold) {
        if (bold) {
            setFont(new Font("NanumGothic", Font.BOLD, size));
        } else {
            setFont(new Font("NanumGothic", Font.PLAIN, size));
        }
    }
}
