package me.karpiesiuk;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JPanel;

public class Main extends JFrame {

    public JFrame frame;
    private ImageIcon background;
    private JLabel tloLabel;
    private JLabel logoLabel;
    private ImageIcon logo;



    public Main(){

        background = new ImageIcon(this.getClass().getResource("/background.jpg"));
        tloLabel = new JLabel(background);
        tloLabel.setSize(1280, 800);
        logo = new ImageIcon(this.getClass().getResource("/logote.png"));
        logoLabel = new JLabel(logo);
        logoLabel.setBounds(415,0,450, 200);

        frame = new JFrame("Projekt");
        Menu menu = new Menu(frame);
        frame.setSize(1280, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(tloLabel);
        frame.add(logoLabel);
        frame.getContentPane().add(menu);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}
