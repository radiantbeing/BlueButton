package gui;

public class MainGUI {
    // Create Windows
    static RoomViewWindow roomViewWindow = new RoomViewWindow();
    static LogInWindow logInWindow = new LogInWindow();
    static AdminLoginWindow adminLoginWindow = new AdminLoginWindow();
    
    void run() {
        BaseFrame frame = new BaseFrame();
        frame.createAndShowGUI();
        BaseFrame.centerPanel.add(roomViewWindow);
    }

    static void changeWindow(Template window) {
        BaseFrame.centerPanel.removeAll();
        BaseFrame.centerPanel.revalidate();
        BaseFrame.centerPanel.repaint();
        BaseFrame.centerPanel.add(window);
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        main.run();
    }

}
