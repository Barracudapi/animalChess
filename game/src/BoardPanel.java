import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {
    private JPanel[][] squares;
    private static final int ROWS = 9;
    private static final int COLS = 7;
    private static final int BOARD_SIZE = 600;
    private Game game;
    private Board board;
    private Spot[][] spots;
    private static final int SQUARE_SIZE_ROW = BOARD_SIZE/ROWS;
    private static final int SQUARE_SIZE_COL = BOARD_SIZE/COLS;
    private ArrayList<BoardChangeListener> boardChangeListeners = new ArrayList<>();
    
    public BoardPanel(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.spots = this.board.getSpots();
        this.setLayout(new GridLayout(ROWS, COLS));
        squares = new JPanel[ROWS][COLS];
        addMouseListener(this);
        addMouseMotionListener(this);
        updateBoardPanel(); 
    }
    public void updateBoardPanel(){
        removeAll();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                squares[row][col] = new JPanel();
                if(this.spots[row][col].getSpotType()==Spot.Type.LAND) {
                    squares[row][col].setBackground(Color.GREEN);
                }
                else if(this.spots[row][col].getSpotType()== Spot.Type.TRAPRED){
                    squares[row][col].setBackground(Color.RED);
                }
                else if(this.spots[row][col].getSpotType() == Spot.Type.TRAPYELLOW){
                    squares[row][col].setBackground(Color.yellow);
                }
                else if(this.spots[row][col].getSpotType() == Spot.Type.BASERED || this.spots[row][col].getSpotType() == Spot.Type.BASEYELLOW){
                    squares[row][col].setBackground(Color.BLACK);
                }
                else {
                    squares[row][col].setBackground(Color.BLUE);
                }
                if(this.spots[row][col].getPiece() != null){
                    squares[row][col].add(new PiecePanel(this.spots[row][col].getPiece()));
                }
                this.add(squares[row][col]);
            }
        }
        
        revalidate(); // Revalidate the panel to update its components
        repaint();
    }
    public JPanel[][] getSquares() {
        return squares;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY()/SQUARE_SIZE_ROW;
        int col = e.getX()/SQUARE_SIZE_COL;
        boolean hasPiece = (spots[row][col].getPiece()!=null);
        fireBoardChangeEvent(spots[row][col]);
        updateBoardPanel();
        if(hasPiece){
            highlightAvailableMoves(spots[row][col]);
        }
        // if(selectedSpot == null){
        //     int row = e.getY() / SQUARE_SIZE_ROW;
        //     int col = e.getX() / SQUARE_SIZE_COL;
        //     // check if there is a piece on the square
        //     if (this.spots[row][col].getPiece() != null) {
        //         // select the piece
        //         selectedSpot = spots[row][col];
        //         selectedRow = row;
        //         selectedCol = col;
        //         offsetX = e.getX() - col * SQUARE_SIZE_COL;
        //         offsetY = e.getY() - row * SQUARE_SIZE_ROW;
        //         squares[row][col].setBackground(Color.yellow);


        //     }
        // } else{
        //     // drop the selected piece onto the new square
        //     int row = e.getY() / SQUARE_SIZE_ROW;
        //     int col = e.getX() / SQUARE_SIZE_COL;
        //     Move move = new Move(game.getCurrentPlayer(), spots[selectedRow][selectedCol], spots[row][col]);
        //     board.movePiece(move);
        //     game.incrementTurn();
        //     selectedSpot = null;
        //     fireBoardChangeEvent(null);
        //     updateBoardPanel();
        // }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }
    public interface BoardChangeListener {
        void onBoardChanged(Spot event);
    }
    public void addBoardChangeListener(BoardChangeListener listener) {
        boardChangeListeners.add(listener);
        //System.out.println(listener);
    }

    private void fireBoardChangeEvent(Spot event) {
        for (BoardChangeListener listener : boardChangeListeners) {
            listener.onBoardChanged(event);
        }
    }
    private void highlightAvailableMoves(Spot selectedspot){
        squares[selectedspot.getX()][selectedspot.getY()].setBackground(Color.pink);
        for(Move move: selectedspot.availableMoves()){
            int row = move.getEnd().getX();
            int col = move.getEnd().getY();
            squares[row][col].setBackground(Color.PINK);
        }
    }
    
}
