import javax.swing.*;

import java.awt.*;

public class BoardPanel extends JPanel {
    private JPanel[][] squares;
    private final int ROWS = 9;
    private final int COLS = 7;
    private Board board;
    private Spot[][] spots;
    
    public BoardPanel(Board board) {
        this.board = board;
        this.spots = board.getSpots();
        this.setLayout(new GridLayout(ROWS, COLS));
        squares = new JPanel[ROWS][COLS];
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
                    squares[row][col].add(new JLabel(this.spots[row][col].getPiece().getName()));
                }
                this.add(squares[row][col]);
            }
        }
    }
}

