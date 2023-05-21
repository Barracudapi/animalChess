import javax.swing.*;
import java.awt.*;
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
        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame saveFrame = new JFrame("Save");
                saveFrame.setLayout(new BorderLayout());
                saveFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                saveFrame.setLocationRelativeTo(messagePanel);
                saveFrame.setSize(300, 200);

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

                JButton saveButton2 = new JButton("save");
                panel2.add(saveButton2);
                saveFrame.setVisible(true);

                if(saveFrame.isVisible()){
                    saveFrame.setAlwaysOnTop(true);
                }
            }
        });
        buttonPanel.add(saveButton);


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
        Game game = new Game(true, true);
        GameFrame gameFrame = new GameFrame(game);
        game.setGameFrame(gameFrame);
        gameFrame.setVisible(true);

    }

    StartingPage page = new StartingPage();

}
