import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    private JPanel capturedPiecesPanel;
    private Game game;

    public SidePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create turn number label
        turnNumberLabel = new JLabel("Turn Number: 1");
        turnNumberLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnNumberLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(turnNumberLabel, BorderLayout.NORTH);

        // Create turn label
        turnLabel = new JLabel("Player 1 Yellow's Turn");
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(turnLabel, BorderLayout.CENTER);

        // Create captured pieces label
        capturedPiecesLabel = new JLabel("Captured Pieces:");
        capturedPiecesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        capturedPiecesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(capturedPiecesLabel, BorderLayout.SOUTH);

        // Create captured pieces panel
        capturedPiecesPanel = new JPanel();
        capturedPiecesPanel.setLayout(new GridLayout(0, 2));
        add(capturedPiecesPanel, BorderLayout.SOUTH);
    }
    public void updateTurn() {
        int turn = (game.getTurn()+1)/2;
        turnNumberLabel.setText("Turn Number: " + turn);
        if(game.getTurn()%2 ==1){
            turnLabel.setText("Player 1 Yellow's Turn");
        } else{
            turnLabel.setText("Player 2 Red's Turn ");
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
}

