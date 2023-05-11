import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

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
    private int selectedRow;
    private int selectedCol;
    private int offsetX;
    private int offsetY;
    private Spot selectedSpot = null;
    
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
    @Override
    public void mouseDragged(MouseEvent e) {
        // if (selectedRow != -1 && selectedCol != -1) {
        //     // move the selected piece
        //     int x = e.getX() - offsetX;
        //     int y = e.getY() - offsetY;
        //     Piece piece = spots[selectedRow][selectedCol].getPiece();
        //     //piece.setLocation(x, y);
        //     repaint();
        // }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // if (selectedRow != -1 && selectedCol != -1) {
        //     // drop the selected piece onto the new square
        //     int row = e.getY() / SQUARE_SIZE_ROW;
        //     int col = e.getX() / SQUARE_SIZE_COL;
        //     Move move = new Move(game.getCurrentPlayer(), spots[selectedRow][selectedCol], spots[row][col]);
        //     this.board.movePiece(move);
        //     selectedRow = -1;
        //     selectedCol = -1;
        // }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.printf("x: %d, y: %d", e.getX(), e.getY());
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // int row = e.getY() / SQUARE_SIZE_ROW;
        // int col = e.getX() / SQUARE_SIZE_COL;
        // // check if there is a piece on the square
        // if (this.spots[row][col] != null) {
        //     // select the piece
        //     selectedRow = row;
        //     selectedCol = col;
        //     offsetX = e.getX() - col * SQUARE_SIZE_COL;
        //     offsetY = e.getY() - row * SQUARE_SIZE_ROW;
        // }
        // System.out.printf("x: %d, y: %d", e.getX(), e.getY());
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.printf("x: %d, y: %d", e.getX(), e.getY());
    }
    @Override
    public void mouseExited(MouseEvent e) {
        
        // System.out.printf("x: %d, y: %d", e.getX(), e.getY());
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(selectedSpot == null){
            int row = e.getY() / SQUARE_SIZE_ROW;
            int col = e.getX() / SQUARE_SIZE_COL;
            // check if there is a piece on the square
            if (this.spots[row][col].getPiece() != null) {
                // select the piece
                selectedSpot = spots[row][col];
                System.out.println(selectedSpot.getPiece().getName());
                selectedRow = row;
                selectedCol = col;
                offsetX = e.getX() - col * SQUARE_SIZE_COL;
                offsetY = e.getY() - row * SQUARE_SIZE_ROW;
                squares[row][col].setBackground(Color.yellow);
                System.out.printf("selectedRow: %d, selectedCol: %d\n", selectedRow,selectedCol);


            }
        } else{
            // drop the selected piece onto the new square
            int row = e.getY() / SQUARE_SIZE_ROW;
            int col = e.getX() / SQUARE_SIZE_COL;
            System.out.println(selectedSpot.getPiece().getName());
            System.out.printf("selectedRow: %d, selectedCol: %d\n", selectedRow,selectedCol);
            Move move = new Move(game.getCurrentPlayer(), spots[selectedRow][selectedCol], spots[row][col]);
            board.movePiece(move);
            board.printBoard();
            selectedSpot = null;
            updateBoardPanel();
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }
}

