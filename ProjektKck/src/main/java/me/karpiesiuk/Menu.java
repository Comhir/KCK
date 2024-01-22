package me.karpiesiuk;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {


    Menu(JFrame frame){
        setOpaque(false);
        setBounds(0,0,1280,800);
        setLayout(null);


        JButton playButton = new JButton("Play");
        playButton.setBounds(565, 300, 150, 75);
        playButton.setFont(new Font("Arial", Font.PLAIN, 40));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(Menu.this);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
                frame.getContentPane().add(new Game(frame));

            }
        });
        add(playButton);

        JButton descriptionButton = new JButton("Rules");
        descriptionButton.setBounds(565, 450, 150, 75);
        descriptionButton.setFont(new Font("Arial", Font.PLAIN, 40));
        descriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(Menu.this);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
                frame.add(new Rules(frame));

            }
        });
        add(descriptionButton);


        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(565, 600, 150, 75);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 40));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);



    }
}
