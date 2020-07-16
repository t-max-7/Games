package tic.tac.toe.app;

import java.util.Arrays;
import java.util.Random;

public class AI_TicTacToe {
    //tic-tac-toe app AI is connected to:
    TicTacToe game;

    //object to store historical data for game:
    GameData gameData = new GameData(9);

    // I believe this is necessary if one wants AI to play AI but right now not needed
    boolean playingO = true;
    //////////////////////////

    /*stores the moves that have been made in array with moves numbered by position on the board like this
     0 1 2
     3 4 5
     6 7 8
     in order moves were made e.g. [3, 1, 0, 7, 6]

     IMPORTANT: Buttons are also stored in that order in the button array of the TicTacToe game! That is very important
     to understanding how to figure out which button AI should click
     */
    int[] gameBoard = new int[9];

    // stores whether a move has been made at position on board:
    boolean[] moveMade = new boolean[]{false,false,false,false,false,false,false,false,false};

//constructor
    AI_TicTacToe(TicTacToe game) {
        this.game = game;
    }
 //calculates move AI should make
    public void calculateMove(){
        if (playingO) {
            if(!game.gameInProgress){
                storeEndGameData();
                clear();
            } else if (game.whoseTurn % 2 == 0) {

                addXsMoveToGameBoard();
                makeMove(gameBoard);

                }
            }
    }
// method for AI to calculate move and click correct button
    void makeMove(int[] gameBoard){
//????????? is this redundant. can't figure out why I put it twice????????
        if(!game.gameInProgress){
            storeEndGameData();
            clear();

        } else{
            // past moves need to be stored in array 1 size smaller than current gameBoard array
            int[] movesThatHaveBeenMade = new int[game.whoseTurn-1];
            int whichMoveToMake;


            for(int i = 0; i < movesThatHaveBeenMade.length; i++){
                movesThatHaveBeenMade[i] = gameBoard[i];
            }
            //

            System.out.println("movesThatHaveBeenMade: " + Arrays.toString(movesThatHaveBeenMade));

            //calculate which move to make based on branch with highest points
            whichMoveToMake = gameData.getBranchWithHighestPoints(movesThatHaveBeenMade);

            /*click button that is same number as whichMoveToMake
            complicated because number is based on current board position e.g
            0 O 1
            X X 2
            O O 3
            therefore only increment j if X or O move not made there

            i is the index of button array
            0 1 2
            3 4 5
            6 7 8
             */
            int j = -1;
            for(int i = 0; i < moveMade.length; i++){

                if(!moveMade[i]){
                    j++;
                }
                if(j == whichMoveToMake){
                    game.buttons[i].doClick(50);
                    addOsMoveToGameBoard();
                    break;
                }
            }
        }

    }
    // adds true to moveMade array after X's turn
    void addXsMoveToGameBoard(){
        int j = -1;
        for (int i = 0; i < moveMade.length; i++) {
            if (!moveMade[i]) {
                if (game.buttons[i].getText().equals("X")) {
                    j++;
                    moveMade[i] = true;
                    break;
                } else{
                    j++;
                }
            }
        }

        gameBoard[game.whoseTurn - 2] = j;
    }

    // adds true to moveMade array after O's turn
    void addOsMoveToGameBoard(){
        int j = -1;
        for (int i = 0; i < moveMade.length; i++) {
            if (!moveMade[i]) {
                if (game.buttons[i].getText().equals("O")) {
                    j++;
                    moveMade[i] = true;
                    break;
                } else{
                    j++;
                }
            }
        }
        gameBoard[game.whoseTurn - 2] = j;
    }


// stores end game data into gameBoard object based on point system, for future AI calculations
    void storeEndGameData(){

        int points = 0;

        if(game.winner.equals("X")){
            addXsMoveToGameBoard();
            //
            if(game.whoseTurn < 7){
                points = -5;
            }else{
                points = -1;
            }

        }else if(game.winner.equals("O")){
            addOsMoveToGameBoard();
            points = 20;
        }else if(game.winner.equals("Draw")){
            addXsMoveToGameBoard();
            points = 10;
        }

        int[] movesThatHaveBeenMade = new int[game.whoseTurn-1];
        for(int i = 0; i < movesThatHaveBeenMade.length; i++){
            movesThatHaveBeenMade[i] = gameBoard[i];
        }

        System.out.println("ending board: " +Arrays.toString(movesThatHaveBeenMade) +" points to add: "+ points);

        gameData.addPoints(movesThatHaveBeenMade, points);
    }

// clears gameBoard and moveMade so they can be used for next game
    void clear(){
        for(int i = 0; i < gameBoard.length; i++){
            gameBoard[i] = 0;
        }
        for(int i = 0; i < moveMade.length; i++){
            moveMade[i] = false;
        }

    }
    ////// experimental
//    void trainAI(){
//        int numberOfGames = 2;
//        while(numberOfGames > 0){
//
//        }
//    }
//
//    void uncalculatedMove(){
//        int sizeOfGameBoard = 9;
//        for(int i = 0; i < sizeOfGameBoard;){
//            if(!(game.buttons[i].getText().equals("X") || game.buttons[i].getText().equals("O"))){
//                game.buttons[i].doClick(200);
//                break;
//            }
//        }
//    }
    /////////experimental


}

