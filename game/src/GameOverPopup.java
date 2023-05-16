import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPopup extends JDialog {
    
    public GameOverPopup() {
        super(null, Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Game Over");
        setPreferredSize(new Dimension(300, 150));

        JPanel messagePanel = new JPanel(new FlowLayout());
        JLabel messageLabel = new JLabel("Game Over!");
        messagePanel.add(messageLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
                dispose();
            }
        });
        buttonPanel.add(restartButton);

        setLayout(new BorderLayout());
        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    private void restartGame() {
        Game game = new Game(true);
        GameFrame gameFrame = new GameFrame(game);
        game.setGameFrame(gameFrame);
        gameFrame.setVisible(true);

    }
        StartingPage page = new StartingPage();

}
