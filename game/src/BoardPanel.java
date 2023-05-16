import javax.swing.*;
import javax.swing.border.LineBorder;

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
                    JLabel label = new JLabel();

                    LineBorder line = new LineBorder(Color.LIGHT_GRAY, 1, true);

                    squares[row][col].setBorder(line);

                    squares[row][col].setBackground(new Color(48, 128, 20));

                }
                else if(this.spots[row][col].getSpotType()== Spot.Type.TRAPRED){
                    JLabel label = new JLabel();

                    LineBorder line = new LineBorder(Color.LIGHT_GRAY, 1, true);

                    squares[row][col].setBorder(line);

                    squares[row][col].setBackground(new Color(100, 0, 0));
                }
                else if(this.spots[row][col].getSpotType() == Spot.Type.TRAPYELLOW){
                    JLabel label = new JLabel();

                    LineBorder line = new LineBorder(Color.LIGHT_GRAY, 1, true);

                    squares[row][col].setBorder(line);

                    squares[row][col].setBackground(new Color(0, 0, 100));
                }
                else if(this.spots[row][col].getSpotType() == Spot.Type.BASERED || this.spots[row][col].getSpotType() == Spot.Type.BASEYELLOW){
                    JLabel label = new JLabel();

                    LineBorder line = new LineBorder(Color.LIGHT_GRAY, 1, true);

                    squares[row][col].setBorder(line);

                    squares[row][col].setBackground(new Color(0, 0, 0));
                }
                else {
                    JLabel label = new JLabel();

                    LineBorder line = new LineBorder(Color.LIGHT_GRAY, 1, true);

                    squares[row][col].setBorder(line);

                    squares[row][col].setBackground(new Color(51, 161, 200));
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
        int row = e.getY()/SQUARE_SIZE_ROW;
        int col = e.getX()/SQUARE_SIZE_COL;
        fireBoardChangeEvent(spots[row][col]);
        updateBoardPanel();
        Piece tempPiece = spots[row][col].getPiece();
        if(game.getSelectedSpot() != null){
            if(game.getSelectedSpot().getPiece()!= null){
                highlightAvailableMoves(game.getSelectedSpot());
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
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
        squares[selectedspot.getX()][selectedspot.getY()].setBackground(new Color(245, 248, 121, 255));
        for(Move move: selectedspot.availableMoves(board)){
            int row = move.getEnd().getX();
            int col = move.getEnd().getY();
            squares[row][col].setBackground(new Color(245, 248, 121, 255));
        }
    }


}
