package personalProjects;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new TicTacToe();
    }

    Panel1 pl1;
    Panel2 pl2;
    Boolean isPlayer1Turn = true;


    public TicTacToe() {

        pl1 = new Panel1();
        add(pl1, BorderLayout.NORTH);
        pl2 = new Panel2(this);
        add(pl2, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(350, 350);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton b = (JButton) source;
            if (isPlayer1Turn) b.setLabel("X");
            else b.setLabel("O");
            b.setEnabled(false);
            isPlayer1Turn = !isPlayer1Turn;
            checkWin();
            JTextArea textArea = pl1.getTextArea();
            if (checkWin()&&isPlayer1Turn){
                textArea.setText("Player 0  wins the game" );
            }if (checkWin()&&!isPlayer1Turn){
                textArea.setText("Player X wins the game" );
            }else textArea.setText("Draw");
        }
    }

    private boolean checkWin() {
        JButton[][] b = pl2.getButtons();
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (b[i][0].getText().equals(b[i][1].getText()) &&
                    b[i][1].getText().equals(b[i][2].getText()) &&
                    !b[i][0].getText().equals(" ")) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (b[0][j].getText().equals(b[1][j].getText()) &&
                    b[1][j].getText().equals(b[2][j].getText()) &&
                    !b[0][j].getText().equals(" ")) {
                return true;
            }
        }

        // Check diagonals
        if (b[0][0].getText().equals(b[1][1].getText()) &&
                b[1][1].getText().equals(b[2][2].getText()) &&
                !b[0][0].getText().equals(" ")) {
            return true;
        }
        if (b[0][2].getText().equals(b[1][1].getText()) &&
                b[1][1].getText().equals(b[2][0].getText()) &&
                !b[0][2].getText().equals(" ")) {
            return true;
        }

        // If no winning conditions are met
        return false;
    }

}

class Panel1 extends JPanel{
    JTextArea ta1;
    
    public Panel1(){
        add(ta1 = new JTextArea(3,30));
        ta1.setEditable(false);
    }

    public JTextArea getTextArea(){
        return ta1;
    }
}

class Panel2 extends JPanel{
    JButton b[][];
    
    public Panel2(ActionListener listener){
        setLayout(new GridLayout(3,3));
        b = new JButton[3][3];
        for (int i = 0; i < 3; i++){
            for ( int j = 0; j < 3; j++){
                b[i][j] = new JButton(" ");
                b[i][j].addActionListener(listener);
                add(b[i][j]);
            }
        }
    }
    public JButton[][] getButtons() {
        return b;
    }
}
