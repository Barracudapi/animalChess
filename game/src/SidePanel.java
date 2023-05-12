import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
    private JLabel turnNumberLabel;
    private JLabel turnLabel;
    private JLabel capturedPiecesLabel;
    private JLabel gameOverLabel;
    private JPanel capturedPiecesPanel;
    private Game game;

    public SidePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create turn number label
        turnLabel = new JLabel("1 " + game.getCurrentPlayer().getPieceColor());
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(turnLabel, BorderLayout.PAGE_START);

        //Create gameover label
        gameOverLabel = new JLabel("GameOVER");
        gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gameOverLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(gameOverLabel, BorderLayout.NORTH);
        gameOverLabel.setVisible(false);

        // Create captured pieces label
        capturedPiecesLabel = new JLabel("Captured Pieces:");
        capturedPiecesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        capturedPiecesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(capturedPiecesLabel, BorderLayout.CENTER);

        // Create captured pieces panel
        capturedPiecesPanel = new JPanel();
        capturedPiecesPanel.setLayout(new GridLayout(0, 2));
        add(capturedPiecesPanel, BorderLayout.SOUTH);
    }
    public void updateTurn() {
        int turn = (game.getTurn()+1)/2;
        if(game.getTurn()%2 ==1){
            turnLabel.setText(turn + ": " + game.getCurrentPlayer().getPieceColor());
        } else{
            turnLabel.setText(turn + ": " + game.getCurrentPlayer().getPieceColor());
        }
    }

    public void updateCapturedPieces(List<Piece> pieces) {
        capturedPiecesPanel.removeAll();
        for (Piece piece : pieces) {
            JLabel label = new JLabel(piece.getName());
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            capturedPiecesPanel.add(label);
        }
        capturedPiecesPanel.revalidate();
        capturedPiecesPanel.repaint();
    }

    public void setGameOver(boolean isOver){
        System.out.println(isOver);
        gameOverLabel.setVisible(isOver);
    }
}

