package edu.georgiasouthern.cr04956.architecturemidterm;

import android.util.Log;

import static edu.georgiasouthern.cr04956.architecturemidterm.TicTacToeBoard.TicTacToePiece.NO_PIECE;
import static edu.georgiasouthern.cr04956.architecturemidterm.TicTacToeBoard.TicTacToePiece.O_PIECE;
import static edu.georgiasouthern.cr04956.architecturemidterm.TicTacToeBoard.TicTacToePiece.X_PIECE;

/**
 * Created by Cameron Rhodes on 2/26/2017.
 */

public class TicTacToeBoard {

    private TicTacToePiece[][] board;
    private boolean isXTurn;
    private TicTacToePiece theWinner;

    public TicTacToeBoard() {
        board = new TicTacToePiece[3][3];
//        isXTurn = true;
        initializeBoard();
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    /**
     * Attempts to place board piece. If successful, changes player turn
     * @param id the identifier associated with the gui board segment
     */
    public void tryToPlacePiece(int id) {
        int row = id/3;
        int column = id%3;
        if(board[row][column] == NO_PIECE) {

            TicTacToePiece placePiece;

            if(isXTurn) {
                placePiece = X_PIECE;
            } else {
                placePiece = O_PIECE;
            }

            board[row][column] = placePiece;
            isXTurn = !isXTurn;
        } //only places and changes turn if valid


    }

    public TicTacToePiece getPiece(int id) {
        int row = id/3;
        int column = id%3;
        Log.i("getPiece", "id: " + id + " (row: " + row + ", column: " + column + ")");
        return board[row][column];
    }

    public boolean hasWinner() {
        //check rows, then columns, then diagonals
        if(checkColumnsForWinner() || checkRowsForWinner() || checkDiagonalsForWinner()) {
            if(theWinner != NO_PIECE) {

                return true;
            }
        } else if(checkForTie()) {
            theWinner = NO_PIECE;
            return true;
        }
        return false;
    }

    private boolean checkForTie() {
        for(int row = 0; row < board.length; row++) {
            for(int column = 0; column < board[row].length; column++) {
                if(board[row][column] == NO_PIECE)
                    return false;
            }
        }
        return true;
    }

    private boolean checkRowsForWinner() {
        for(int row = 0; row < board.length; row++) {
            if(board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
               if(board[row][0] != NO_PIECE) {
                   theWinner = board[row][0];
                   return true;
               }
            }
        }
        return false;
    }

    private boolean checkColumnsForWinner() {
        for(int column = 0; column < board[0].length; column++) {
            if(board[0][column] == board[1][column] && board[1][column] == board[2][column]) {
                if(board[0][column] != NO_PIECE) {
                theWinner = board[0][column];
                return true;
            }
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWinner() {
        //check top-left to bottom-right
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if(board[0][0] != NO_PIECE) {
                theWinner = board[0][0];
                return true;
            }
        }

        //check bottom-left to top-right
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            if(board[2][0] != NO_PIECE) {
                theWinner = board[2][0];
                return true;
            }
        }

        return false;
    }

    public TicTacToePiece getWinner() {
        return theWinner;
    }

    public void initializeBoard() {
        isXTurn = true;
        theWinner = NO_PIECE;

        for(int row = 0; row < board.length; row++) {
            for(int column = 0; column < board[row].length; column++) {
                board[row][column] = NO_PIECE;
            }
        }
    }

    public enum TicTacToePiece {
        X_PIECE,O_PIECE,NO_PIECE;
    }
}
