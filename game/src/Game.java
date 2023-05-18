import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class Game extends JFrame implements ActionListener, BoardPanel.BoardChangeListener {
    private Board board;
    private GameFrame gameFrame;
    private Player player1;
    private Player player2;
    private AIPlayer aiPlayer;
    private Player currentPlayer;
    private boolean isAI;
    private boolean isFirstPlayer;
    private boolean gameOver;
    private int turn;
    private Spot selectedSpot = null;
    private ArrayList<Move> moves;


    public Game(boolean isAI, boolean isFirstPlayer) {
        this.isAI = isAI;
        this.isFirstPlayer = isFirstPlayer;
        System.out.println(isAI);
        board = new Board();
        player1 = new Player("Player 1", Piece.Color.YELLOW);
        if(isAI){
            aiPlayer = new AIPlayer("AI Player", Piece.Color.RED, 5);
            System.out.println(aiPlayer.getName());
        } else{
            player2 = new Player("Player 2", Piece.Color.RED);
        }
        currentPlayer = player1;
        gameOver = false;
        turn = 1;
        moves = new ArrayList<Move>();
        if(!isFirstPlayer){
            currentPlayer = aiPlayer;
            aiTurn();
        }
    }
    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }
    public void aiTurn() {
        Thread aiThread = new Thread(() -> {
            if(currentPlayer == aiPlayer){
        
                System.out.println("AI'S TURN IS LOADING");
                board.printBoard();
                Move moveTemp = aiPlayer.getMove(board);
                Move move = board.convertMove(moveTemp);
                if(board.movePiece(move)){
                    System.out.println("AI HAS MADE A MOVE");
                    System.out.println("THE AI HAS DECIDED TO MOVE THE " + board.getSpot(move.getEnd()).getPiece() + " : --- " + move.toString());
                } else{
                    System.out.println("THE AI IS UNABLE TO MOVE");
                    gameOver = true;
                    printMoves();
                    saveGame("default", moves);
                    gameFrame.gameover();
                    gameFrame.getSidePanel().setGameOver(true);
                }
                board.printPgn();
                SwingUtilities.invokeLater(() -> {
                    switchPlayer();
                    updateGame();
                });
            }

        });
        aiThread.start();
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
    }
    public void addToMoves(Move move) {
        moves.add(move);
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
                }else if(selectedSpot.getPiece()!=null) {

                    Move move = new Move(selectedSpot, board.getSpots()[e.getX()][e.getY()]);
                    if(selectedSpot.getPiece().getColor() == currentPlayer.getPieceColor()){
                        if(board.movePiece(move)){
                            board.printBoard();
                            if(board.getSpots()[selectedSpot.getX()][selectedSpot.getY()].getPiece()==null) {
                                System.out.println("player 1 has moved!");
                                printMoves();
                                incrementTurn();
                                switchPlayer();
                                selectedSpot = null;
                                updateGame();
                            }
                        } else{
                            if(e.getPiece() != null){
                                if(e.getPiece().getColor() == currentPlayer.getPieceColor()){
                                    selectedSpot = e;
                                } else{
                                    selectedSpot = null;
                                }
                            } else{
                                selectedSpot = null;
                            }
                            
                        }
                    }
                }
            }
        }
    }
    public void updateGame(){
        gameFrame.updateGame();
        if(board.getSpots()[0][3].getPiece() != null || board.getSpots()[8][3].getPiece() !=null){
            gameOver = true;
            printMoves();
            saveGame("default", moves);
            gameFrame.gameover();
            gameFrame.getSidePanel().setGameOver(true);
        }
        System.out.println(currentPlayer.getName());
        if(currentPlayer==aiPlayer){
            System.out.println("It is the AI's turn");
            aiTurn();
        }
    }
    public void saveGame(String filename, ArrayList<Move> moves){
        String s = "game/saved-games/" + filename + ".txt";
        String pgnString = "";
        for(String pgnFrag: board.getPgn()){
            pgnString += pgnFrag + " ";
        }
        try {
            writeToPosition(s, pgnString, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeToPosition(String filename, String pgn, long position) 
        throws IOException {
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.write(pgn);
            fileWriter.close();
    }

    public void switchPlayer(){
        if(currentPlayer == player1){
            if(isAI){
                currentPlayer = aiPlayer;
            } else{
                currentPlayer = player2;
            }
        } else{
            currentPlayer = player1;
        }
    }
    public void printMoves(){
        for(Move move: moves){
            if(move.getStart().getPiece()!=null){
                System.out.println(move.toString());
            }
        }
    }
    public void reverseMove(){
        moves = board.pgnToMoves(board.getPgn());
        board.reinitialize();
        for(int i = 0; i<moves.size()-1; i++){
            board.movePiece(moves.get(i));
        }
        if(moves.size()>0){
            setTurn(turn-1);
            switchPlayer();
        }
        gameFrame.updateGame();
    }

}
