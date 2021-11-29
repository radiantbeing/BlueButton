package gui;

import boardgamecafe.Order;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SnackOrderOption extends Template{
    BasicPanel snackOptionPanel;
    BasicLabel orderListTitle, oderSizeTitle, ondoTitle, syrupTitle, syrupText, vanillaText, hazelText, creamText;

    @Override
    public void addComponents() {
        snackOptionPanel = new BasicPanel();
        orderListTitle = new BasicLabel("선택하신 메뉴의 옵션을 고르세요.");
        oderSizeTitle = new BasicLabel("사이즈                +0/+1000");
        ondoTitle = new BasicLabel("온도                +0");
        syrupTitle = new BasicLabel("시럽        +500");
        syrupText = new BasicLabel("일반");
        vanillaText = new BasicLabel("바닐라");
        hazelText = new BasicLabel("헤이즐넛");
        creamText = new BasicLabel("휘핑크림 / 드리즐        +500");

        // Set
        setLayout(null);
        setSnackOptionPanel(snackOptionPanel);
    }

    private void setSnackOptionPanel(BasicPanel mainPanel) {
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(41, 42, 45));
        mainPanel.setBounds(160, 20, 640, 560);

        // -------------- 타이틀
        orderListTitle.setBounds(10, 0, 300, 50);
        orderListTitle.setFontAttribute(20, true);
        orderListTitle.setHorizontalAlignment(JLabel.LEFT);
        // -------------- 타이틀 끝

        // -------------- 사이즈
        oderSizeTitle.setBounds(10, 40, 300, 50);
        oderSizeTitle.setFontAttribute(20, true);
        oderSizeTitle.setHorizontalAlignment(JLabel.LEFT);

        // L 버튼
        BasicButton lButton = new BasicButton("L");
        lButton.setBounds(10, 80, 150, 40);
        lButton.setFontAttribute(20);

        // XL 버튼
        BasicButton xlButton = new BasicButton("XL");
        xlButton.setBounds(200, 80, 150, 40);
        xlButton.setFontAttribute(20);
        // ------------------ 사이즈 끝

        // ------------------ 온도
        ondoTitle.setBounds(10, 120, 300, 50);
        ondoTitle.setFontAttribute(20, true);
        ondoTitle.setHorizontalAlignment(JLabel.LEFT);

        // hot 버튼
        BasicButton hotButton = new BasicButton("HOT");
        hotButton.setBounds(10, 160, 150, 40);
        hotButton.setFontAttribute(20);

        // ice 버튼
        BasicButton iceButton = new BasicButton("ICE");
        iceButton.setBounds(200, 160, 150, 40);
        iceButton.setFontAttribute(20);
        // ------------------ 온도 끝

        // ------------------ 시럽
        syrupTitle.setBounds(10, 200, 300, 50);
        syrupTitle.setFontAttribute(20, true);
        syrupTitle.setHorizontalAlignment(JLabel.LEFT);

        // CHECK1
        BasicButton chekc1Button = new BasicButton("X");
        chekc1Button.setBounds(10, 250, 50, 50);
        chekc1Button.setFontAttribute(20);
        // 일반
        syrupText.setBounds(70, 250, 150, 50);
        syrupText.setFontAttribute(20, false);
        syrupText.setHorizontalAlignment(JLabel.LEFT);

        // CHECK2
        BasicButton chekc2Button = new BasicButton("X");
        chekc2Button.setBounds(10, 310, 50, 50);
        chekc2Button.setFontAttribute(20);
        // 바닐라
        vanillaText.setBounds(70, 310, 150, 50);
        vanillaText.setFontAttribute(20, false);
        vanillaText.setHorizontalAlignment(JLabel.LEFT);

        // CHECK3
        BasicButton chekc3Button = new BasicButton("X");
        chekc3Button.setBounds(10, 370, 50, 50);
        chekc3Button.setFontAttribute(20);
        // 헤이즐
        hazelText.setBounds(70, 370, 150, 50);
        hazelText.setFontAttribute(20, false);
        hazelText.setHorizontalAlignment(JLabel.LEFT);
        // ------------------ 시럽 끝

        // ------------------ 크림
        creamText.setBounds(10, 420, 300, 50);
        creamText.setFontAttribute(20, true);
        creamText.setHorizontalAlignment(JLabel.LEFT);

        // 휘핑크림
        BasicButton creamButton = new BasicButton("L");
        creamButton.setBounds(10, 470, 150, 40);
        creamButton.setFontAttribute(20);
        // 카라멜드리즐
        BasicButton caramelButton = new BasicButton("L");
        caramelButton.setBounds(180, 470, 150, 40);
        caramelButton.setFontAttribute(20);
        // 초콜릿드리즐
        BasicButton chocolateButton = new BasicButton("L");
        chocolateButton.setBounds(350, 470, 150, 40);
        chocolateButton.setFontAttribute(20);
        // ------------------ 크림 끝

        // ------------------ 완료 버튼
        BasicButton finalButton = new BasicButton("완료");
        finalButton.setBounds(485, 510, 150, 40);
        finalButton.setFontAttribute(20);

        finalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomViewWindow);
            }
        });
        // ------------------

        // ------------------ 구성 : 위에서 부터 아래루
        mainPanel.add(orderListTitle);

        mainPanel.add(oderSizeTitle);
        mainPanel.add(lButton);
        mainPanel.add(xlButton);

        mainPanel.add(ondoTitle);
        mainPanel.add(hotButton);
        mainPanel.add(iceButton);

        mainPanel.add(syrupTitle);
        mainPanel.add(chekc1Button);
        mainPanel.add(chekc2Button);
        mainPanel.add(chekc3Button);

        mainPanel.add(syrupTitle);
        mainPanel.add(syrupText);
        mainPanel.add(syrupTitle);
        mainPanel.add(vanillaText);
        mainPanel.add(syrupTitle);
        mainPanel.add(hazelText);
        mainPanel.add(syrupTitle);

        mainPanel.add(creamText);
        mainPanel.add(creamButton);
        mainPanel.add(caramelButton);
        mainPanel.add(chocolateButton);

        mainPanel.add(finalButton);
        // ————————— 구성 끝

        // 메인 패널에 위 블럭에서 추가한거 적용하는
        add(mainPanel);
    }
}
