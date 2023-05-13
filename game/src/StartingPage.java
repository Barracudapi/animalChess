

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class StartingPage extends JFrame implements ActionListener {

    private JButton startButton;
    private JButton exitButton;
    private JButton aiButton;

    public StartingPage() {
        setTitle("Jungle Chess Game");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setSize(50, 50);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        panel.setBackground(Color.PINK);

        JLabel label = new JLabel("Jungle!");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);


        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        panel.add(startButton);

        aiButton = new JButton("Play Against AI");
        aiButton.addActionListener(this);
        panel.add(aiButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        panel.add(exitButton);


        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Open the chess game window
            Game game = new Game();
            GameFrame gameFrame = new GameFrame(game);
            game.setGameFrame(gameFrame);
            gameFrame.setVisible(true);
            dispose(); // close the starting page window
        } else if (e.getSource() == exitButton) {
            // Exit the game
            dispose();
        } else if (e.getSource() == aiButton){

        }
    }

    public static void main(String[] args) {
        StartingPage page = new StartingPage();
    }
}
