package gui;

/*
    실행시켜 보고 싶으시면 MainGUI 클래스의 코드 중
    bFrame.centerPanel.add(roomViewWindow);
    // bFrame.centerPanel.add(new TestWindow());
    위에 해당하는 부분을
    // bFrame.centerPanel.add(roomViewWindow);
    bFrame.centerPanel.add(new TestWindow());
    와 같이 바꾼 후 실행하세요.
*/
public class TestWindow extends Template {
    @Override
    void addComponents()
    {
        // Initialize
        setLayout(null);

        // Create Components
        BasicPanel testPanel = new BasicPanel();
        BasicLabel testLabel = new BasicLabel("Test Label");
        BasicButton testButton = new BasicButton("Test Button");

        // About testPanel
        add(testPanel);
        testPanel.setBounds(100, 100, 100, 500);
        testPanel.add(new BasicLabel("Test Panel"));

        // About testLabel
        add(testLabel);
        testLabel.setBounds(400, 100, 100, 20);

        // About testButton
        add(testButton);
        testButton.setBounds(600, 100, 200, 50);
    }
}
