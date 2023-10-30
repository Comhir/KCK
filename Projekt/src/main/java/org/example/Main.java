package org.example;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(70, 60));
        Terminal terminal = defaultTerminalFactory.createTerminal();

        Screen screen = new TerminalScreen(terminal);
        TextGraphics tg = screen.newTextGraphics();
        screen.startScreen();
        boolean keeprunning = true;
        StringBuilder sb = new StringBuilder();

        int ktore = Menu(screen,tg,keeprunning);
        boolean end = true;
        while(end) {
            switch (ktore) {
                case 0:
                    ktore = Game(screen, tg);
                    break;
                case 1:
                    ktore = Zasady(screen, tg);
                    break;
                case 2:
                    ktore = Menu(screen, tg, keeprunning);
                    break;
                case 3:
                    end = false;
            }
        }
    }
    public static int Menu(Screen screen, TextGraphics tg, boolean keeprunning) throws IOException {
        screen.clear();
        tg.putString(10,3, "  _______         ______              ______         ");
        tg.putString(10,4," /_  __(_)____   /_  __/___ ______   /_  __/___  ___ ");
        tg.putString(10,5,"  / / / / ___/    / / / __ `/ ___/    / / / __ \\/ _ \\");
        tg.putString(10,6," / / / / /__     / / / /_/ / /__     / / / /_/ /  __/");
        tg.putString(10,7,"/_/ /_/\\___/    /_/  \\__,_/\\___/    /_/  \\____/\\___/ ");
        tg.putString(10,9,"(Wish You luck)");
        tg.putString(25,20, "//////////////");
        tg.putString(25,21, "//          //");
        tg.putString(25,22, "//  Zagraj  //");
        tg.putString(25,23, "//          //");
        tg.putString(25,24, "//////////////");

        tg.putString(25,27, "//////////////");
        tg.putString(25,28, "//          //");
        tg.putString(25,29, "// Jak Grać //");
        tg.putString(25,30, "//          //");
        tg.putString(25,31, "//////////////");

        screen.refresh();
        int licznik = 0;
        int ktore = 0;
        while (keeprunning){
            KeyStroke keyPressed = screen.pollInput();

            if (keyPressed != null){
                switch (keyPressed.getKeyType()){
                    case ArrowDown:
                        if(licznik == 0)
                            licznik = 1;
                        else licznik = 0;
                        if(licznik == 0){
                            tg.enableModifiers(SGR.BOLD);
                            tg.putString(25,20, "//////////////");
                            tg.putString(25,21, "//          //");
                            tg.putString(25,22, "//  Zagraj  //");
                            tg.putString(25,23, "//          //");
                            tg.putString(25,24, "//////////////");
                            tg.clearModifiers();
                            tg.putString(25,27, "//////////////");
                            tg.putString(25,28, "//          //");
                            tg.putString(25,29, "// Jak Grać //");
                            tg.putString(25,30, "//          //");
                            tg.putString(25,31, "//////////////");
                            screen.refresh();
                            break;
                        }else{
                            tg.putString(25,20, "//////////////");
                            tg.putString(25,21, "//          //");
                            tg.putString(25,22, "//  Zagraj  //");
                            tg.putString(25,23, "//          //");
                            tg.putString(25,24, "//////////////");
                            tg.enableModifiers(SGR.BOLD);
                            tg.putString(25,27, "//////////////");
                            tg.putString(25,28, "//          //");
                            tg.putString(25,29, "// Jak Grać //");
                            tg.putString(25,30, "//          //");
                            tg.putString(25,31, "//////////////");
                            tg.clearModifiers();
                            screen.refresh();
                            break;
                        }
                    case ArrowUp:

                        if(licznik == 1)
                            licznik = 0;
                        else licznik = 1;
                        if(licznik == 0){
                            screen.refresh();
                            tg.enableModifiers(SGR.BOLD);
                            tg.putString(25,20, "//////////////");
                            tg.putString(25,21, "//          //");
                            tg.putString(25,22, "//  Zagraj  //");
                            tg.putString(25,23, "//          //");
                            tg.putString(25,24, "//////////////");
                            tg.clearModifiers();
                            tg.putString(25,27, "//////////////");
                            tg.putString(25,28, "//          //");
                            tg.putString(25,29, "// Jak Grać //");
                            tg.putString(25,30, "//          //");
                            tg.putString(25,31, "//////////////");
                            screen.refresh();
                            break;
                        }else {
                            screen.refresh();
                            tg.putString(25,20, "//////////////");
                            tg.putString(25,21, "//          //");
                            tg.putString(25,22, "//  Zagraj  //");
                            tg.putString(25,23, "//          //");
                            tg.putString(25,24, "//////////////");
                            tg.enableModifiers(SGR.BOLD);
                            tg.putString(25,27, "//////////////");
                            tg.putString(25,28, "//          //");
                            tg.putString(25,29, "// Jak Grać //");
                            tg.putString(25,30, "//          //");
                            tg.putString(25,31, "//////////////");
                            tg.clearModifiers();
                            screen.refresh();
                            break;
                        }

                    case Escape:
                        keeprunning = false;
                        screen.refresh();
                        screen.readInput();
                        screen.stopScreen();
                        ktore = 3;
                        break;
                    case EOF:
                        keeprunning = false;
                        screen.readInput();
                        screen.stopScreen();
                        ktore = 3;
                        break;

                    case Enter:
                        screen.clear();
                        keeprunning = false;
                        screen.refresh();
                        if(licznik == 0)
                            ktore = 0;
                        else ktore  = 1;
                        break;
                }
            }
        }
        return ktore;

    }
    public static int Zasady(Screen screen, TextGraphics tg) throws IOException {
        screen.clear();
        tg.putString(10,3, "  _______         ______              ______         ");
        tg.putString(10,4," /_  __(_)____   /_  __/___ ______   /_  __/___  ___ ");
        tg.putString(10,5,"  / / / / ___/    / / / __ `/ ___/    / / / __ \\/ _ \\");
        tg.putString(10,6," / / / / /__     / / / /_/ / /__     / / / /_/ /  __/");
        tg.putString(10,7,"/_/ /_/\\___/    /_/  \\__,_/\\___/    /_/  \\____/\\___/ ");
        tg.putString(10,9,"(Wish You luck)");

        tg.putString(30,20,"ZASADY GRY:");
        tg.putString(3,21,"Głównym zadaniem jest ułożenie 3 jednakowych znaków w linii (X/O)");
        tg.putString(3,22,"Jeśli nie uda się żadnemu z graczy osiągnąć powyższego wyniku,");
        tg.putString(3,23,"gra kończy się remisem.");

        tg.putString(8,25,"Enter -> Wróć do menu   Escape -> Wyjdź z gry");
        tg.enableModifiers(SGR.BOLD);
        tg.putString(25,27, "//////////////");
        tg.putString(25,28, "//          //");
        tg.putString(25,29, "//  Cofnij  //");
        tg.putString(25,30, "//          //");
        tg.putString(25,31, "//////////////");
        tg.clearModifiers();
        screen.refresh();
        boolean going = true;

        int returning = 0;
        while(going){
            KeyStroke keyPressed = screen.pollInput();
            if (keyPressed != null) {
                switch (keyPressed.getKeyType()) {
                    case Enter:
                        screen.clear();
                        returning = 2;
                        going = false;
                        break;
                    case Escape:
                        going = false;
                        screen.refresh();
                        screen.readInput();
                        screen.stopScreen();
                        returning = 3;
                        break;
                    case EOF:
                        going = false;

                        returning = 3;
                        break;
                }
            }
        }
        return returning;
    }

    public static int Game(Screen screen, TextGraphics tg) throws IOException {
        screen.clear();
        tg.putString(10,3, "  _______         ______              ______         ");
        tg.putString(10,4," /_  __(_)____   /_  __/___ ______   /_  __/___  ___ ");
        tg.putString(10,5,"  / / / / ___/    / / / __ `/ ___/    / / / __ \\/ _ \\");
        tg.putString(10,6," / / / / /__     / / / /_/ / /__     / / / /_/ /  __/");
        tg.putString(10,7,"/_/ /_/\\___/    /_/  \\__,_/\\___/    /_/  \\____/\\___/ ");
        tg.putString(10,9,"(Wish You luck)");

        tg.drawLine(25,12,25,36,Symbols.BLOCK_SOLID);
        tg.drawLine(45,12,45,36,Symbols.BLOCK_SOLID);
        tg.drawLine(5,20,65,20,Symbols.BLOCK_SOLID);
        tg.drawLine(5,28,65,28,Symbols.BLOCK_SOLID);
        screen.refresh();

        char[][] board = new char[3][3];
        int temp = 1;
        for (int row = 0; row < board.length; row++) {
            for(int col = 0; col<board[row].length; col++){
                board[col][row] = (char)(temp++ +'0');
            }
        }
        boolean won = false;
        int x = 0;
        int y = 0;
        int taken = 0;
        while(!won){
            screen.refresh();
            tg.putString(12,47,"Jesteś w pozycji "+x+"x"+y);
            int x1 = 0;
            int y1 = 0;
            char a = ' ';
            boolean free = true;
            Random random = new Random();

            if(taken == 9)
                break;
            KeyStroke keyPressed = screen.pollInput();
            if (keyPressed != null){
                switch (keyPressed.getKeyType()){
                    case EOF:
                        screen.readInput();
                        screen.stopScreen();
                        return 3;
                    case ArrowDown:
                        y = (y+1)%3;
                        System.out.println(x + " "+y);
                        break;
                    case ArrowUp:
                        y = (y+5)%3;
                        System.out.println(x + " "+y);
                        break;
                    case ArrowLeft:
                        x = (x+5)%3;
                        System.out.println(x + " "+y);
                        break;
                    case ArrowRight:
                        x = (x+1)%3;
                        System.out.println(x + " "+y);
                        break;
                    case Enter:
                        board[x][y] = 'X';
                        a = 'X';
                        DrawSign(x,y,a,screen,tg);
                        taken++;
                        x = 0;
                        y = 0;
                        won = haveWon(board);
                        if(!won){
                            while(free){
                                x1 = random.nextInt(3);
                                y1 = random.nextInt(3);
                                if (board[x1][y1] != 'O' && board[x1][y1] != 'X'){
                                    board[x1][y1] = 'O';
                                    a = 'O';
                                    DrawSign(x1,y1,a,screen,tg);
                                    taken++;
                                    free = false;
                                }
                            }
                            won = haveWon(board);
                        }
                        break;
                }

            }

        }
        tg.putString(10,3, "  _______         ______              ______         ");
        tg.putString(10,4," /_  __(_)____   /_  __/___ ______   /_  __/___  ___ ");
        tg.putString(10,5,"  / / / / ___/    / / / __ `/ ___/    / / / __ \\/ _ \\");
        tg.putString(10,6," / / / / /__     / / / /_/ / /__     / / / /_/ /  __/");
        tg.putString(10,7,"/_/ /_/\\___/    /_/  \\__,_/\\___/    /_/  \\____/\\___/ ");
        tg.putString(10,9,"(Wish You luck)");

        tg.putString(12,47, "KONIEC GRY     KONIEC GRY     KONIEC GRY");
        tg.putString(7,56, "Enter-> Aby wrócić do menu / Escape -> Aby wyjść z aplikacji");
        screen.refresh();
        while(true){
            KeyStroke keyPressed = screen.pollInput();
            if(keyPressed == null)
                continue;
            else if(keyPressed.getKeyType() == KeyType.Enter){
                return 2;
            } else if (keyPressed.getKeyType() == KeyType.Escape ||keyPressed.getKeyType() == KeyType.EOF ) {
                screen.refresh();
                screen.readInput();
                screen.stopScreen();
                return 3;
            }
        }

    }
    public static boolean haveWon(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] ==  board[row][1] &&  board[row][0] == board[row][2]) {
                return true;
            }
        }

        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == board[1][col] && board[2][col] == board[0][col]) {
                return true;
            }
        }

        if (board[0][0] ==  board[1][1] && board[2][2] == board[0][0]) {
            return true;
        }

        if (board[0][2] == board[1][1]&& board[2][0] == board[1][1]) {
            return true;
        }
        return false;
    }
    public static void DrawSign(int x, int y, char a, Screen screen, TextGraphics tg) throws IOException {
        if(a == 'X'){
            TerminalPosition tp= new TerminalPosition(5,12);
            tg.putString(tp.withRelativeRow(2+8*y).withRelativeColumn(7+20*x), "___  _");
            tg.putString(tp.withRelativeRow(3+8*y).withRelativeColumn(7+20*x), "\\  \\//");
            tg.putString(tp.withRelativeRow(4+8*y).withRelativeColumn(7+20*x), " \\  / ");
            tg.putString(tp.withRelativeRow(5+8*y).withRelativeColumn(7+20*x), " /  \\ ");
            tg.putString(tp.withRelativeRow(6+8*y).withRelativeColumn(7+20*x), "/__/\\\\");
            screen.refresh();
        }else{
            TerminalPosition tp= new TerminalPosition(5,12);
            tg.putString(tp.withRelativeRow(2+8*y).withRelativeColumn(7+20*x), " ____ ");
            tg.putString(tp.withRelativeRow(3+8*y).withRelativeColumn(7+20*x), "/  _ \\");
            tg.putString(tp.withRelativeRow(4+8*y).withRelativeColumn(7+20*x), "| / \\|");
            tg.putString(tp.withRelativeRow(5+8*y).withRelativeColumn(7+20*x), "| \\_/|");
            tg.putString(tp.withRelativeRow(6+8*y).withRelativeColumn(7+20*x), "\\____/");
            screen.refresh();
        }

    }

}