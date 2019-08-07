package com.udacity;

import java.util.Arrays;

public class Game {

    private char turn; // who's turn is it, 'x' or 'o' ? x always starts
    private boolean twoPlayer; // true if this is a 2 player game, false if AI playing
    private char [][] grid; // a 2D array of chars representing the game grid
    private int freeSpots; // counts the number of empty spots remaining on the board (starts from 9  and counts down)
    private static GameUI gui;


    public Game() {
        newGame(false);
    }


    public void newGame(boolean twoPlayer){
        //sets a game to one or two player
        this.twoPlayer = twoPlayer;

        // initialize all chars in 3x3 game grid to '-'
        grid = new char[3][3];
        //fill all empty slots with -
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                grid[i][j] = '-';
            }
        }
        //start with 9 free spots and decrement by one every time a spot is taken
        freeSpots = 9;
        //x always starts
        turn = 'x';
    }



    public char gridAt(int i, int j){
        if(i>=3||j>=3||i<0||j<0)
            return '!';
        return grid[i][j];
    }

    public boolean playAt(int i, int j){
        //check for index boundries
        if(i>=3||j>=3||i<0||j<0)
            return false;
        //check if this position is available
        if(grid[i][j] != '-'){
            return false; //bail out if not available
        }
        //update grid with new play based on who's turn it is
        grid[i][j] = turn;
        //update free spots
        freeSpots--;
        return true;
    }


    /**
     * Override
     * @return string format for 2D array values
     */
    public String toString(){
        return Arrays.deepToString(this.grid);
    }

    /**
     * Performs the winner chack and displayes a message if game is over
     * @return true if game is over to start a new game
     */
    public boolean doChecks() {
        //check if there's a winner or tie ?
        String winnerMessage = checkGameWinner(grid);
        if (!winnerMessage.equals("None")) {
            gui.gameOver(winnerMessage);
            newGame(false);
            return true;
        }
        return false;
    }


    public void nextTurn(){
        //check if single player game, then let computer play turn
        if(!twoPlayer){
            if(freeSpots == 0){
                return ; //bail out if no more free spots
            }
            int ai_i, ai_j;
            do {
                //randomly pick a position (ai_i,ai_j)
                ai_i = (int) (Math.random() * 3);
                ai_j = (int) (Math.random() * 3);
            }while(grid[ai_i][ai_j] != '-'); //keep trying if this spot was taken
            //update grid with new play, computer is always o
            grid[ai_i][ai_j] = 'o';
            //update free spots
            freeSpots--;
        }
        else{
            //change turns
            if(turn == 'x'){
                turn = 'o';
            }
            else{
                turn = 'x';
            }
        }
        return;
    }


  
    public String checkGameWinner(char [][]grid){
        String result = "None";
        boolean win = false;

        if(grid [0][0] == 'x' && grid[0][1] == 'x' && grid[0][2] == 'x' || grid[1][0] == 'x' && grid[1][1]=='x' && grid[1][2]== 'x' || grid[2][0]=='x' && grid[2][1] =='x' && grid[2][2] == 'x')
        {
            result = "X wins";
            win = true;
        }
        if (grid [0][0] == 'x' && grid[1][0] == 'x' && grid[2][0] == 'x' || grid [0][1] == 'x' && grid[1][1] == 'x' && grid[2][1] == 'x' || grid [0][2] == 'x' && grid[1][2] == 'x' && grid[2][2] == 'x')
        {
            result = "X wins";
            win = true;
        }
        if (grid[0][0] == 'x' && grid[1][1] == 'x' && grid[2][2] == 'x' || grid[2][0] == 'x' && grid[1][1] == 'x' && grid[0][2]=='x') {
        result = "X wins";
        win = true;
        }
        if (grid [0][0] == 'o' && grid[0][1] == 'o' && grid[0][2] == 'o' || grid[1][0] == 'o' && grid[1][1]=='o' && grid[1][2]== 'o' || grid[2][0]=='o' && grid[2][1] =='o' && grid[2][2] == 'o')
        {
            result = "O wins";
            win = true;
        }
        if (grid [0][0] == 'o' && grid[1][0] == 'o' && grid[2][0] == 'o' || grid [0][1] == 'o' && grid[1][1] == 'o' && grid[2][1] == 'o' || grid [0][2] == 'o' && grid[1][2] == 'o' && grid[2][2] == 'o')
        {
            result = "O wins";
            win = true;
        }
        if (grid[0][0] == 'o' && grid[1][1] == 'o' && grid[2][2] == 'o' || grid[2][0] == 'o' && grid[1][1] == 'o' && grid[0][2]=='o') {
            result = "O wins";
            win = true;
        }
        if(freeSpots == 0 && !win){
            result = "Tie";
        }

        //Student code goes here ...
        return result;
    }

    /**
     * Main function
     * @param args command line arguments
     */
    public static void main(String args[]){
        Game game = new Game();
        gui = new GameUI(game);
    }

}
