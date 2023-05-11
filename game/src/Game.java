import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Game extends JFrame implements ActionListener, BoardPanel.BoardChangeListener {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private int turn;
    private int row;
    private int col;
    private int selectedRow;
    private int selectedCol;
    private Spot selectedSpot = null;
    private Move[] moves;

    public Game() {
        board = new Board();
        player1 = new Player("Player 1", Piece.Color.YELLOW);
        player2 = new Player("Player 2", Piece.Color.RED);
        currentPlayer = player1;
        gameOver = false;
        turn = 0;

    }

    public void setVisible(boolean b) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }

    public Board getBoard() {
        return board;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public boolean getGameOver(){
        return gameOver;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public void incrementTurn(){
        turn = turn + 1;
        System.out.println(turn);
    }
    @Override
    public void onBoardChanged(BoardChangeEvent e) {
        System.out.println("row: " + e.getRow() + " col: " + e.getCol());
        this.row = e.getRow();
        this.col = e.getCol();
        if(!gameOver){
            if(selectedSpot == null){
                // check if there is a piece on the square
                if (board.getSpots()[row][col].getPiece() != null) {
                    // select the piece
                    selectedSpot = board.getSpots()[row][col];
                    selectedRow = row;
                    selectedCol = col;
                    //squares[row][col].setBackground(Color.yellow);
    
    
                }
            } else{
                Move move = new Move(getCurrentPlayer(), board.getSpots()[selectedRow][selectedCol], board.getSpots()[row][col]);
                board.movePiece(move);
                incrementTurn();
                selectedSpot = null;
            }
        }
        updateGame();
    }
    public void updateGame(){
        if(board.getSpots()[0][3].getPiece() != null || board.getSpots()[8][3].getPiece() !=null){
            gameOver = true;
            System.out.println("GAMEOVER!");
        }
    }

}
