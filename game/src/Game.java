import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.*;


public class Game extends JFrame implements ActionListener, BoardPanel.BoardChangeListener {
    private Board board;
    private GameFrame gameFrame;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private int turn;
    private int row;
    private int col;
    private Spot selectedSpot = null;
    private ArrayList<Move> moves;
    private String pgn;


    public Game() {
        board = new Board();
        player1 = new Player("Player 1", Piece.Color.YELLOW);
        player2 = new Player("Player 2", Piece.Color.RED);
        currentPlayer = player1;
        gameOver = false;
        turn = 1;
        moves = new ArrayList<Move>();

    }
    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
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
    public Spot getSelectedSpot() {
        return selectedSpot;
    }
    public void incrementTurn(){
        turn = turn + 1;
        System.out.println(turn);
    }
    @Override
    public void onBoardChanged(Spot e) {
        if(!gameOver){
            if(selectedSpot == null){
                if(e.getPiece()!=null){
                    if(e.getPiece().getColor() == currentPlayer.getPieceColor()){
                        selectedSpot = e;
                    }
                }
            } else{
                if(selectedSpot.getPiece() == e.getPiece()){
                    selectedSpot = null;
                }else {

                    Move move = new Move(currentPlayer, selectedSpot, board.getSpots()[e.getX()][e.getY()]);
                    if(selectedSpot.getPiece().getColor() == currentPlayer.getPieceColor()){
                        if(board.movePiece(move)){
                            moves.add(move);
                            if(board.getSpots()[selectedSpot.getX()][selectedSpot.getY()].getPiece()==null) incrementTurn();
                            switchPlayer();
                            System.out.println("Turn: " + turn);
                            selectedSpot = null;
                        } else{
                            selectedSpot = e;
                        }
                    }
                }
            }
        }
        gameFrame.updateGame();
        updateGame();
    }
    public void updateGame(){
        if(board.getSpots()[0][3].getPiece() != null || board.getSpots()[8][3].getPiece() !=null){
            gameOver = true;
            System.out.println("GAMEOVER!");
            saveGame("default", moves);
            gameFrame.gameover();
            gameFrame.getSidePanel().setGameOver(true);
        }
    }
    public void saveGame(String filename, ArrayList<Move> moves){
        String s = "game/saved-games/" + filename + ".txt";
        try {
            writeToPosition(s, board.getPgn(), 0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void writeToPosition(String filename, String pgn, long position) 
        throws IOException {
            // RandomAccessFile writer = new RandomAccessFile(filename, "rw");
            // writer.seek(position);
            // writer.writeUTF(pgn);
            // writer.close();
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.write(pgn);
            fileWriter.close();
    }

    public void switchPlayer(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        } else{
            currentPlayer = player1;
        }
    }
}
