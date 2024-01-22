package me.karpiesiuk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JPanel {

    Rules(JFrame frame){
        setOpaque(false);
        setBounds(0,0,1280,800);
        setLayout(null);
        JLabel tloLabel = new JLabel("Rules of the Game");
        tloLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        tloLabel.setBounds(50, 50, 360, 75);
        tloLabel.setBackground(new Color(192,192,192, 150));
        tloLabel.setOpaque(true);
        add(tloLabel);

        JTextArea rulesArea = new JTextArea("Your most important and basic rule is to connect 3+ pieces of the same type horizontally or diagonally.\n\n" +
                "To do so, you need to click 2 tiles you want to swap with each other but you can switch them only if they can create a 3+ line.\n\n" +
                "If they cannot, your move will be canceled.\n\n" +
                "You have 20 moves. Try your best and achieve the highest score!");
        rulesArea.setBounds(240,200,800,300);
        rulesArea.setFont(new Font("Arial", Font.PLAIN, 26));
        rulesArea.setLineWrap(true);
        rulesArea.setWrapStyleWord(true);
        rulesArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        rulesArea.setEditable(false);
        add(rulesArea);

        JButton backButton = new JButton("Back");
        backButton.setBounds(565, 600, 150, 75);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(Rules.this);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
                frame.add(new Menu(frame));

            }
        });
        add(backButton);

    }

}
