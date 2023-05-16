import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SidePanel extends JPanel {
    private JLabel turnNumberLabel;
    private JLabel turnLabel;
    private JLabel capturedPiecesLabel;
    private JLabel gameOverLabel;
    private JButton actionButton;
    private JButton saveButton;
    private JButton loadButton;
    private JPanel capturedPiecesPanel;
    private JPanel redCapturedPiecesPanel;
    private JPanel yellowCapturedPiecesPanel;
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

        // Create action button
        actionButton = new JButton("Reverse Move");
        actionButton.setBounds(10, 400, 180, 25);
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getBoard().getCapturedPieces().clear();
                game.reverseMove();
            }
        });
        add(actionButton, BorderLayout.NORTH);

        // Create save button
        actionButton = new JButton("Save Game");
        actionButton.setBounds(10, 450, 180, 25);
        //save game action
        //actionButton.addActionListener(new ActionListener() {
        //});
        add(actionButton, BorderLayout.NORTH);

        // Create load button
        actionButton = new JButton("Load Game");
        actionButton.setBounds(10, 500, 180, 25);
        //load game action
        //actionButton.addActionListener(new ActionListener() {
        //});
        add(actionButton, BorderLayout.NORTH);

        // // Create captured pieces label
        capturedPiecesPanel = new JPanel();
        capturedPiecesLabel = new JLabel("Captured Pieces:");
        JLabel redCapturedPiecesLabel = new JLabel("RED:");
        JLabel yellowCapturedPiecesLabel = new JLabel("YELLOW:");
        capturedPiecesLabel.setFont(new Font("Arial", Font.PLAIN, 23));


        // // Create captured pieces panel
        redCapturedPiecesPanel = new JPanel();
        yellowCapturedPiecesPanel = new JPanel();
        capturedPiecesPanel.setSize(400, 200);
        add(capturedPiecesPanel, BorderLayout.CENTER);
        capturedPiecesPanel.add(capturedPiecesLabel, BorderLayout.CENTER);
        capturedPiecesPanel.add(capturedPiecesLabel, BorderLayout.CENTER);

        redCapturedPiecesPanel.setLayout(new GridLayout(0, 1));
        yellowCapturedPiecesPanel.setLayout(new GridLayout(0, 1));
        redCapturedPiecesPanel.setBorder(new EmptyBorder(0,0,0,25));
        yellowCapturedPiecesLabel.setBorder(new EmptyBorder(0,25,0,0));
        capturedPiecesPanel.add(redCapturedPiecesPanel, BorderLayout.WEST);
        capturedPiecesPanel.add(yellowCapturedPiecesPanel, BorderLayout.EAST);
    }

    public void updateTurn() {
        int turn = (game.getTurn() + 1) / 2;
        if (game.getTurn() % 2 == 1) {
            turnLabel.setText(turn + ": " + game.getCurrentPlayer().getPieceColor());
        } else {
            turnLabel.setText(turn + ": " + game.getCurrentPlayer().getPieceColor());
        }
    }

    public void updateCapturedPieces(List<Piece> pieces) {
        redCapturedPiecesPanel.removeAll();
        yellowCapturedPiecesPanel.removeAll();
        for (Piece piece : pieces) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePath()).getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(imageIcon);
            label.setFont(new Font("Arial", Font.PLAIN, 3));
            if (piece.getColor() == Piece.Color.RED) {
                label.setBorder(BorderFactory.createLineBorder(Color.RED));
                redCapturedPiecesPanel.add(label);
            } else if (piece.getColor() == Piece.Color.YELLOW) {
                label.setBorder(BorderFactory.createLineBorder(Color.CYAN));
                yellowCapturedPiecesPanel.add(label);
            }
        }
        redCapturedPiecesPanel.revalidate();
        yellowCapturedPiecesPanel.revalidate();
        redCapturedPiecesPanel.repaint();
        yellowCapturedPiecesPanel.repaint();
    }

    public void setGameOver(boolean isOver) {
        System.out.println(isOver);
        gameOverLabel.setVisible(isOver);
    }
}

