package tic.tac.toe.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class TicTacToe {
    int whoseTurn = 1;
    JButton[] buttons = new JButton[9];
    JButton restart = new JButton("Restart");
    JButton whoWon = new JButton("X starts");
    JButton playAI = new JButton("Play vs AI");
    boolean gameInProgress = true;
    String winner;
    boolean AI_O_playing = false;
    //experimental not working (therefore is final)
    final boolean AI_training = false;
    ///////////////////////



// main function launching tic-tac-toe game
    public static void main(String[] args){
        new TicTacToe();

    }

    //constructor
    public TicTacToe() {
        JFrame guiFrame = new JFrame();

//make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Tic Tac Toe");
        guiFrame.setSize(400, 400);

//This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setLayout(new GridLayout(4, 3));

        //add AI
        AI_TicTacToe AI = new AI_TicTacToe(this);

        //fill buttons array with tic-tac-toe buttons and add ActionListener to them to turn button to show "X" or "O"
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
        }
        // create action listener for each button and add each button to JFrame
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                // for 2 human player
                public void actionPerformed(ActionEvent e) {
                    if (gameInProgress) {
                        if (!AI_O_playing) {
                            if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                                //X's turn
                                if (is_X_Turn()) {
                                    button.setText("X");
                                    whoseTurn++;
                                    whoWon.setText("O turn");
                                    findWhoWins();
                                //O's turn
                                } else {
                                    button.setText("O");
                                    whoseTurn++;
                                    whoWon.setText("X turn");
                                    findWhoWins();
                                }
                            }
                        // for AI
                        } else if(AI_O_playing && !(AI_training)){
                            // if button not already clicked
                            if(!(button.getText().equals("X") || button.getText().equals("O"))){
                                if(is_X_Turn()){
                                    button.setText("X");
                                    whoseTurn++;
                                    whoWon.setText("O turn");
                                    findWhoWins();
                                    AI.calculateMove();
                              // if button clicked
                                }else{
                                    button.setText("O");
                                    whoseTurn++;
                                    whoWon.setText("X turn");
                                    findWhoWins();
                                }
                            }
                        }
                        // experimental not working
//                        else if (AI_O_playing && AI_training){
//                            if(!(button.getText().equals("X") || button.getText().equals("O"))){
//                                if(is_X_Turn()){
//                                    button.setText("X");
//                                    whoseTurn++;
//                                    whoWon.setText("O turn");
//                                    findWhoWins();
//                                    AI.calculateMove();
//                                    // if button clicked
//                                }else{
//                                    button.setText("O");
//                                    whoseTurn++;
//                                    whoWon.setText("X turn");
//                                    findWhoWins();
//                                    AI.uncalculatedMove();
//                                }
//                            }
//                        }
                        ////////////////////////////////////////////////////

                        }
                    }
                });

            // add JButton to guiFrame
            guiFrame.add(button);
        }


//  add ActionListener to restart button and playAI button
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AI.clear();
                whoWon.setText("");
                whoseTurn = 1;
                gameInProgress = true;
                for(JButton button : buttons) {
                    button.setText("");
                }
            }
        });

        playAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!AI_O_playing){
                    AI_O_playing = true;
                    playAI.setText("AI playing");
                }
                //experimental not working
//                else if (AI_O_playing){
//                    AI_training = true;
//                    playAI.setText("AI training");
//                    AI.uncalculatedMove();
//                }
                ///////////////////////////
            }
        });
 //add buttons to guiFrame
        guiFrame.add(restart);
        guiFrame.add(whoWon);
        guiFrame.add(playAI);


        //make sure the JFrame is visible
        guiFrame.setVisible(true);

    }

    boolean is_X_Turn(){
        return whoseTurn % 2 != 0;
    }


// function to find who wins tic-tac-toe game
    void findWhoWins(){
        boolean topRowX =        buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X");
        boolean middleRowX =     buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X");
        boolean bottomRowX =     buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X");
        boolean firstColumnX =   buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X");
        boolean secondColumnX =  buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X");
        boolean thirdColumnX =   buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X");
        boolean leftDiagonalX =  buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X");
        boolean rightDiagonalX = buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X");

        boolean topRowO = buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O");
        boolean middleRowO = buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O");
        boolean bottomRowO = buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O");
        boolean firstColumnO =   buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O");
        boolean secondColumnO =  buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O");
        boolean thirdColumnO =   buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O");
        boolean leftDiagonalO = buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O");
        boolean rightDiagonalO = buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O");


        if (topRowX || middleRowX || bottomRowX || firstColumnX || secondColumnX || thirdColumnX || leftDiagonalX || rightDiagonalX) {
            winner = "X";;
            whoWon.setText("X wins");
            gameInProgress = false;
            }
        else if (topRowO || middleRowO || bottomRowO || firstColumnO || secondColumnO || thirdColumnO || leftDiagonalO || rightDiagonalO) {
            winner ="O";
            whoWon.setText("O wins");
            gameInProgress = false;
        }
        else if(whoseTurn == 10){
            winner = "Draw";
            whoWon.setText("Draw");
            gameInProgress = false;
        }

    }


}


