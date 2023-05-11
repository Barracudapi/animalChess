import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Game extends JFrame implements ActionListener {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private int turn;

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
}
