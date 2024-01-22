package me.karpiesiuk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game extends JPanel {
    private ImageIcon grid;
    private JLabel gridLabel;
    private JButton abortButton;
    private JFrame frame = null;
    private int movesLeft;
    private JButton[][] buttons = new JButton[12][8];
    static final int ZEROXY = 12;
    static final int TILESIZE = 44;
    Icon icon1 = new ImageIcon(this.getClass().getResource("/ginger.png"));
    Icon icon2 = new ImageIcon(this.getClass().getResource("/mario.png"));
    Icon icon3 = new ImageIcon(this.getClass().getResource("/penguin.png"));
    Icon icon4 = new ImageIcon(this.getClass().getResource("/pikachu.png"));
    Icon icon5 = new ImageIcon(this.getClass().getResource("/angrybird.png"));
    private JPanel gridButtons = new JPanel();
    private JButton tempButton1, tempButton2;

    private int clicks = 0;
    private JLabel mLeft;
    private JLabel scoreLabel;
    private int score;





    Game(final JFrame frame){
        this.frame = frame;
        setOpaque(false);
        setBounds(0,0,1280,800);
        setLayout(null);
        grid = new ImageIcon(this.getClass().getResource("/grid.png"));
        gridLabel = new JLabel(grid);
        gridLabel.setOpaque(false);
        gridLabel.setSize(600,610);
        gridLabel.setBounds(340,150,600,610);
        abortButton = new JButton("Back to Menu");
        abortButton.setBounds(200, 600, 150, 75);
        abortButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(Game.this);
                frame.getContentPane().remove(scoreLabel);
                frame.getContentPane().remove(abortButton);
                frame.getContentPane().repaint();
                frame.add(new Menu(frame));
            }
        });
        add(abortButton);
        gridButtons.setBounds(354+TILESIZE*2,158,TILESIZE*9,610);
        gridButtons.setOpaque(false);
        scoreLabel = new JLabel("Your score is: "+ score);
        scoreLabel.setBounds(100,200,400,100);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        scoreLabel.setForeground(Color.yellow);
        movesLeft = 2;
        mLeft = new JLabel("Moves left: "+movesLeft);
        mLeft.setBounds(850,200,400,100);
        mLeft.setOpaque(false);
        mLeft.setFont(new Font("Arial", Font.PLAIN, 40));
        mLeft.setForeground(Color.blue);
        add(scoreLabel);
        add(mLeft);
        add(initBoard(gridButtons, buttons));
        add(gridLabel);
        setVisible(true);
    }

    private JPanel initBoard(final JPanel gridButtons, final JButton[][] buttons ){
        do{
            gridButtons.removeAll();
            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 8; j++){
                    createButtons(buttons, i, j);
                    buttons[i][j].addActionListener(buttonFunctionality());
                    buttons[i][j].setPreferredSize(new Dimension(TILESIZE, TILESIZE) );
                    gridButtons.add(buttons[i][j]);

                }
            }
        }while(hasMatch3(buttons));
        return gridButtons;
    }
    private ActionListener buttonFunctionality(){
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clicks++;
                if(clicks == 1){
                    tempButton1 = (JButton) e.getSource();
                }else{
                    tempButton2 = (JButton) e.getSource();
                    if(nextTo(tempButton1,tempButton2)) {
                        int index1 = getButtonIndex(tempButton1);
                        int index2 = getButtonIndex(tempButton2);
                        gridButtons.add(tempButton2, index1);
                        gridButtons.add(tempButton1, index2);

                        int indexSwap1 = index1 / 8;
                        int indexSwap11 = index1 % 8;
                        int indexSwap2 = index2 / 8;
                        int indexSwap22 = index2 % 8;
                        JButton tButton = buttons[indexSwap1][indexSwap11];
                        buttons[indexSwap1][indexSwap11] = buttons[indexSwap2][indexSwap22];
                        buttons[indexSwap2][indexSwap22] = tButton;
                        if(hasMatch3(buttons)) {

                            movesLeft--;
                            mLeft.setText("Moves left:" + movesLeft);
                            frame.getContentPane().revalidate();
                            frame.getContentPane().repaint();

                            ArrayList<JButton> chosen = erase3(buttons);
                            while (!chosen.isEmpty()) {
                                for (int i = 11; i >= 0; i--) {
                                    for (int j = 7; j >= 0; j--) {
                                        while (chosen.contains(buttons[i][j])) {
                                            int x = i;
                                            while (x != 0) {
                                                tempButton1 = buttons[x][j];
                                                tempButton2 = buttons[x - 1][j];
                                                index1 = getButtonIndex(tempButton1);
                                                index2 = getButtonIndex(tempButton2);
                                                gridButtons.add(tempButton2, index1);
                                                gridButtons.add(tempButton1, index2);
                                                indexSwap1 = index1 / 8;
                                                indexSwap11 = index1 % 8;
                                                indexSwap2 = index2 / 8;
                                                indexSwap22 = index2 % 8;
                                                tButton = buttons[indexSwap1][indexSwap11];
                                                buttons[indexSwap1][indexSwap11] = buttons[indexSwap2][indexSwap22];
                                                buttons[indexSwap2][indexSwap22] = tButton;
                                                x--;

                                            }
                                            createButtons(buttons, x, j);
                                            buttons[x][j].addActionListener(buttonFunctionality());
                                            buttons[x][j].setPreferredSize(new Dimension(TILESIZE, TILESIZE));
                                            gridButtons.remove(x * 8 + j);

                                            gridButtons.add(buttons[x][j], x * 8 + j);
                                        }
                                    }
                                }
                                chosen = erase3(buttons);
                            }
                            scoreLabel.setText("Your score is: " + score);
                            if (movesLeft == 0) {
                                frame.getContentPane().remove(Game.this);
                                frame.getContentPane().repaint();
                                scoreLabel.setText("Your final score is: " + score);
                                scoreLabel.setBounds(465, 350, 355, 100 );
                                scoreLabel.setBackground(Color.YELLOW);
                                scoreLabel.setOpaque(true);
                                scoreLabel.setForeground(Color.BLACK);
                                scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
                                abortButton.setBounds(565,600 ,150,75);
                                frame.add(abortButton);
                                frame.add(scoreLabel);
                            }
                        }else{

                            gridButtons.add(tempButton2, index2);
                            gridButtons.add(tempButton1, index1);

                            tButton = buttons[indexSwap2][indexSwap22];
                            buttons[indexSwap2][indexSwap22] = buttons[indexSwap1][indexSwap11];
                            buttons[indexSwap1][indexSwap11] = tButton;
                            JOptionPane.showMessageDialog(null, "U have to match 3 with your move", "Rule", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    clicks = 0;
                }
            }
        };
    }
    private void createButtons(JButton[][] buttons, int i, int j) {
        int random = (int)(Math.random()*5);
        switch (random){
            case 0:
                buttons[i][j] = new JButton(icon1);
                buttons[i][j].setBackground(Color.gray);
                break;
            case 1:
                buttons[i][j] = new JButton(icon2);
                buttons[i][j].setBackground(Color.BLUE);
                break;
            case 2:
                buttons[i][j] = new JButton(icon3);
                buttons[i][j].setBackground(Color.YELLOW);
                break;
            case 3:
                buttons[i][j] = new JButton(icon4);
                buttons[i][j].setBackground(Color.BLACK);
                break;
            case 4:
                buttons[i][j] = new JButton(icon5);
                buttons[i][j].setBackground(Color.red);
                break;
        }
    }
    private boolean nextTo(JButton but1, JButton but2){
        int index1 = getButtonIndex(but1);
        int index2 = getButtonIndex(but2);
        if((index1 == index2-1 && index1%8 != 7) || (index2 == index1-1 && index2%8 != 7) || (index1 == index2-8) || (index2 == index1 -8) ){
            return true;
        }
       return false;
    }
    private ArrayList<JButton> erase3(JButton[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Set<JButton> matched = new HashSet<>();
        // Sprawdzanie wierszy
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (board[i][j].getBackground() == board[i][j + 1].getBackground() && board[i][j].getBackground() == board[i][j + 2].getBackground()) {
                    matched.add(board[i][j]);
                    matched.add(board[i][j+1]);
                    matched.add(board[i][j+2]);

                }
            }
        }

        // Sprawdzanie kolumn
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows - 2; i++) {
                if (board[i][j].getBackground() == board[i + 1][j].getBackground() && board[i][j].getBackground() == board[i + 2][j].getBackground()) {
                    matched.add(board[i][j]);
                    matched.add(board[i+1][j]);
                    matched.add(board[i+2][j]);
                }
            }
        }
        score+= matched.size();
        return new ArrayList<>(matched);
    }
    private int getButtonIndex(JButton button) {
        Container parent = button.getParent();
        if (parent instanceof JPanel) {
            JPanel panel = (JPanel) parent;
            Component[] components = panel.getComponents();
            for (int i = 0; i < components.length; i++) {
                if (components[i] == button) {
                    return i;
                }
            }
        }
        return -1;
    }
    private static boolean hasMatch3(JButton[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Sprawdzanie wierszy
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (board[i][j].getBackground() == board[i][j + 1].getBackground() && board[i][j].getBackground() == board[i][j + 2].getBackground()) {
                    return true;
                }
            }
        }

        // Sprawdzanie kolumn
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows - 2; i++) {
                if (board[i][j].getBackground() == board[i + 1][j].getBackground() && board[i][j].getBackground() == board[i + 2][j].getBackground()) {
                    return true;
                }
            }
        }

        return false;
    }
}
