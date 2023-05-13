import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class SidePanel extends JPanel {
    private JLabel turnNumberLabel;
    private JLabel turnLabel;
    private JLabel capturedPiecesLabel;
    private JLabel gameOverLabel;
    private JButton actionButton;
    private JButton saveButton;
    private JButton loadButton;
    private JPanel capturedPiecesPanel;
    private Game game;

    public SidePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create turn number label
        turnLabel = new JLabel("1: " + game.getCurrentPlayer().getPieceColor());
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(turnLabel, BorderLayout.PAGE_START);

        //Create gameover label
        gameOverLabel = new JLabel("GameOVER");
        gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gameOverLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(gameOverLabel, BorderLayout.NORTH);
        gameOverLabel.setVisible(false);

        // // Create captured pieces label
        capturedPiecesLabel = new JLabel("Captured Pieces:");
        capturedPiecesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        capturedPiecesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        capturedPiecesLabel.setLocation(10, 300);
        add(capturedPiecesLabel, BorderLayout.CENTER);

        // // Create captured pieces panel
        capturedPiecesPanel = new JPanel();
        capturedPiecesPanel.setLayout(new GridLayout(0, 1));
        add(capturedPiecesPanel, BorderLayout.SOUTH);

        // Create action button
        actionButton = new JButton("Reverse Move");
        actionButton.setBounds(10, 100, 180, 25);
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.reverseMove();
            }
        });
        add(actionButton, BorderLayout.NORTH);

        // Create save button
        actionButton = new JButton("Save Game");
        actionButton.setBounds(10, 150, 180, 25);
        //save game action
        //actionButton.addActionListener(new ActionListener() {
        //});
        add(actionButton, BorderLayout.NORTH);

        // Create load button
        actionButton = new JButton("Load Game");
        actionButton.setBounds(10, 200, 180, 25);
        //load game action
        //actionButton.addActionListener(new ActionListener() {
        //});
        add(actionButton, BorderLayout.NORTH);

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
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePath()).getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
            JLabel label = new JLabel(imageIcon);
            label.setFont(new Font("Arial", Font.PLAIN, 5));
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            if(piece.getColor() == Piece.Color.RED){
                capturedPiecesPanel.add(label, BorderLayout.EAST);
            }
            else if(piece.getColor() == Piece.Color.YELLOW){
                capturedPiecesPanel.add(label, BorderLayout.WEST);
            }
        }
        capturedPiecesPanel.revalidate();
        capturedPiecesPanel.repaint();
    }

    public void setGameOver(boolean isOver){
        System.out.println(isOver);
        gameOverLabel.setVisible(isOver);
    }
}

