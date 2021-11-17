package gui;

import boardgamecafe.BoardGameCafe;

public class MainGUI {
    static BoardGameCafe boardgamecafe = new BoardGameCafe();
    // Create BaseFrame
    static BaseFrame bFrame = new BaseFrame();
    // Create Windows
    static RoomViewWindow roomViewWindow = new RoomViewWindow();
    static LogInWindow logInWindow = new LogInWindow();
    static AdminLoginWindow adminLoginWindow = new AdminLoginWindow();
    static SignUpWindow signUpWindow = new SignUpWindow();
    static TimeSelectWindow timeSelectWindow = new TimeSelectWindow();
    
    void run() {
        boardgamecafe.run(); // 이거 없으면 모든 기능이 동작하지 않습니다...
        bFrame.createAndShowGUI();
        bFrame.centerPanel.add(roomViewWindow);
        // bFrame.centerPanel.add(new TestWindow());
    }

    static void changeWindow(Template window) {
        bFrame.centerPanel.removeAll();
        bFrame.centerPanel.revalidate();
        bFrame.centerPanel.repaint();
        bFrame.centerPanel.add(window);
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        main.run();
    }

}
