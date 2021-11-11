package gui;

import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame{
    JLabel leftSeatsLabel = new JLabel("남은 방:");
    JPanel seatsIMG = new JPanel();
    JButton nextButton = new JButton("Log-In");
    BackGroundPanel backGroundPanel = new BackGroundPanel();
    public void addComponent(Container container){
        container.setLayout(new BorderLayout());
        leftSeatsLabel.setFont(new Font("남은 방",Font.BOLD, 50));
        leftSeatsLabel.setForeground(Color.blue);
        leftSeatsLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(leftSeatsLabel,BorderLayout.NORTH);
        container.add(nextButton, BorderLayout.SOUTH);
    }

    public void createGUI() {
        JFrame jFrame = new JFrame("Swing assignment");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setContentPane(backGroundPanel);
        addComponent(jFrame);
        jFrame.pack();
        jFrame.setSize(new Dimension(1200, 800));
        jFrame.setLocation(500,200);
        jFrame.setVisible(true);
    }

    public static void main(String[]args){
        new MainGui().createGUI();
    }

    class BackGroundPanel extends JPanel{
        ImageIcon icon = new ImageIcon("backgroundimg.png");
        Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img,0,0,getWidth(),getHeight(),this);
        }
    }
}
