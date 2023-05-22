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
    private Player firstPlayer;



    public Game(boolean isAI, boolean isFirstPlayer, int maxRunTime, int maxDepth) {
        this.isAI = isAI;
        this.isFirstPlayer = isFirstPlayer;
        System.out.println(isAI);
        board = new Board();
        player1 = new Player("Player 1", Piece.Color.BLUE);
        if(isAI){
            aiPlayer = new AIPlayer("AI Player", Piece.Color.RED, maxRunTime, maxDepth);
            System.out.println(aiPlayer.getName());
        } else{
            player2 = new Player("Player 2", Piece.Color.RED);
        }
        currentPlayer = player1;
        firstPlayer = player1;
        gameOver = false;
        turn = 1;
        moves = new ArrayList<Move>();
        
        if(!isFirstPlayer){
            firstPlayer = aiPlayer;
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
                    saveGame(gameFrame.getSidePanel().getSaveFileName()+"-finished");
                    gameFrame.gameover();
                    gameFrame.getSidePanel().setGameOver(true);
                }
                board.printPgn();
                SwingUtilities.invokeLater(() -> {
                    switchPlayer();
                    playSound();
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
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
    public Player getFirstPlayer(){
        return firstPlayer;
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
                        boolean isCapture = move.getEnd().getPiece()!=null;
                        if(board.movePiece(move)){
                            board.printBoard();
                            if(board.getSpots()[selectedSpot.getX()][selectedSpot.getY()].getPiece()==null) {
                                successfulMove();
                                 if(isCapture){
                                     captSound();
                                 }
                                 else{
                                     playSound();
                                 }

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
    public void successfulMove(){
        System.out.println("player 1 has moved!");
        printMoves();
        incrementTurn();
        switchPlayer();
        selectedSpot = null;
        updateGame();
    }
    public void playSound(){
        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.loadSound("game/resources/move-self.wav");
        soundPlayer.playSound();
    }

    public void captSound(){
        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.loadSound("game/resources/capture.wav");
        soundPlayer.playSound();
    }
    public void updateGame(){
        gameFrame.updateGame();
        if(checkGameOver()){
            gameOver = true;
            printMoves();
            saveGame(gameFrame.getSidePanel().getSaveFileName()+"-finished");
            gameFrame.gameover();
            gameFrame.getSidePanel().setGameOver(true);
        }
        System.out.println(currentPlayer.getName());
        if(currentPlayer==aiPlayer){
            System.out.println("It is the AI's turn");
            aiTurn();
        }
    }
    public boolean checkGameOver(){
        if(board.getSpots()[0][3].getPiece() != null || board.getSpots()[8][3].getPiece() !=null) return true;
        for(Move m: board.getAllAvailableMoves()){
            if(m.getStart().getPiece().getColor() == currentPlayer.getPieceColor()) return false;
        }
        return true;
    }
    public void saveGame(String filename){
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
        updateGame();
    }
    public void loadGame(ArrayList<String> newPgn){
        System.out.println("is against AI: " + isAI);
        player1 = new Player("Player 1", Piece.Color.BLUE);
        if(isAI){
            aiPlayer = new AIPlayer("AI Player", Piece.Color.RED, 10000, 10);
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
        board.setPgn(newPgn);
        moves = board.pgnToMoves(board.getPgn());
        board.reinitialize();
        for(int i = 0; i<moves.size(); i++){
            board.movePiece(moves.get(i));
            successfulMove();
        }

        updateGame();
    }

    public void disposeGame(){
        gameFrame.dispose();
        dispose();
    }
}
