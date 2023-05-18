import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.security.KeyPair;
import java.util.List;
import java.awt.event.WindowFocusListener;
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
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame saveFrame = new JFrame("Save");
                saveFrame.setLayout(new BorderLayout());

                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();
                JPanel panel3 = new JPanel();

                JLabel label = new JLabel("Save game?");
                saveFrame.setLayout(new GridLayout(0, 1));

                JTextField textField = new JTextField(20);
                textField.setText("savedGame_?");
                panel3.add(textField, BorderLayout.CENTER);

                panel1.add(label);
                saveFrame.add(panel1, BorderLayout.NORTH);
                saveFrame.add(panel3, BorderLayout.CENTER);
                saveFrame.add(panel2, BorderLayout.SOUTH);

                JButton saveButton = new JButton("save");
                panel2.add(saveButton);
                saveButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String saveFileName = textField.getText();
                        saveFrame.dispose();
                    }
                });

                JButton noButton = new JButton("back");
                panel2.add(noButton);
                noButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveFrame.dispose();
                    }
                });

                saveFrame.addWindowFocusListener(new WindowFocusListener() {
                    @Override
                    public void windowGainedFocus(WindowEvent e) {

                    }

                    @Override
                    public void windowLostFocus(WindowEvent e) {
                        saveFrame.dispose();
                    }
                });

                saveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                saveFrame.setSize(400, 200);
                saveFrame.setLocation(600, 350);
                saveFrame.setVisible(true);
            }
        });
        add(actionButton, BorderLayout.NORTH);

        // Create load button
        actionButton = new JButton("Load Game");
        actionButton.setBounds(10, 500, 180, 25);
        //load game action
        //actionButton.addActionListener(new ActionListener() {
        //});
        add(actionButton, BorderLayout.NORTH);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(10, 550, 180, 25);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame exitWarning = new JFrame("WARNING!");
                exitWarning.setLayout(new BorderLayout());
                exitWarning.setUndecorated(true);
                JPanel warning1 = new JPanel();
                JPanel warning2 = new JPanel();
                JLabel label = new JLabel("Are you sure?");
                exitWarning.setLayout(new GridLayout(0, 1));

                warning1.add(label);
                exitWarning.add(warning1, BorderLayout.NORTH);
                exitWarning.add(warning2, BorderLayout.SOUTH);

                JButton yesButton = new JButton("yes");
                warning2.add(yesButton);
                yesButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                    }
                });

                JButton noButton = new JButton("no");
                warning2.add(noButton);
               noButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exitWarning.dispose();
                    }
                });

               exitWarning.addWindowFocusListener(new WindowFocusListener() {
                   @Override
                   public void windowGainedFocus(WindowEvent e) {

                   }

                   @Override
                   public void windowLostFocus(WindowEvent e) {
                       exitWarning.dispose();
                   }
               });

                exitWarning.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                exitWarning.setSize(150, 100);
                exitWarning.setLocation(600, 350);
                exitWarning.setVisible(true);
            }
        });
        add(exitButton, BorderLayout.NORTH);

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
            if (piece.getColor() == Piece.Color.RED) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePathRed()).getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT));
                JLabel label = new JLabel(imageIcon);
                label.setFont(new Font("Arial", Font.PLAIN, 3));
                label.setBorder(BorderFactory.createLineBorder(Color.RED));
                redCapturedPiecesPanel.add(label);
            } else if (piece.getColor() == Piece.Color.YELLOW) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePathBlue()).getImage().getScaledInstance(37, 37, Image.SCALE_DEFAULT));
                JLabel label = new JLabel(imageIcon);
                label.setFont(new Font("Arial", Font.PLAIN, 3));
                yellowCapturedPiecesPanel.add((label));
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

