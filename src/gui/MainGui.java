package gui;

import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame{
    JLabel leftSeatsLabel = new JLabel("남은 방:");
    JPanel seatsIMG = new JPanel();
    JButton nextButton = new JButton("Log-In");

    public void addComponent(Container container){
        leftSeatsLabel.setFont(new Font("남은 방",Font.BOLD, 50));
        leftSeatsLabel.setForeground(Color.blue);
        leftSeatsLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(leftSeatsLabel,BorderLayout.NORTH);
        container.add(seatsIMG, BorderLayout.CENTER);
        container.add(nextButton, BorderLayout.SOUTH);
    }

    public void createGUI() {
        JFrame jFrame = new JFrame("Swing assignment");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponent(jFrame);
        jFrame.pack();
        jFrame.setSize(new Dimension(300, 300));
        jFrame.setLocation(500,200);
        jFrame.setVisible(true);
    }

    public static void main(String[]args){
        new MainGui().createGUI();
    }
}
