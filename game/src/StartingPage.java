

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartingPage extends JFrame implements ActionListener {

    private JButton startButton;
    private JButton exitButton;
    private JButton aiButton;
    private JFrame frame;

    public StartingPage() {
        setTitle("Jungle Chess Game");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        startButton.setSize(550, 50);
        startButton.setLocation(480, 330);
        add(startButton);

        aiButton = new JButton("Play Against AI");
        aiButton.addActionListener(this);
        aiButton.setSize(550, 50);
        aiButton.setLocation(480, 430);
        add(aiButton);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setSize(550, 50);
        exitButton.setLocation(480, 530);
        add(exitButton);

        Image image = Toolkit.getDefaultToolkit().getImage("game/resources/backgroundpixeledited.jpg");
        ImagePanel imagePanel = new ImagePanel(image);
        add(imagePanel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Open the chess game window
            Game game = new Game(false, true);
            GameFrame gameFrame = new GameFrame(game);
            game.setGameFrame(gameFrame);
            gameFrame.setVisible(true);
            dispose(); // close the starting page window
        } else if (e.getSource() == exitButton) {
            // Exit the game
            dispose();
        } else if (e.getSource() == aiButton){
            // Open the chess game window
            Game game = new Game(true, false);
            System.out.println("THIS GAME IS AGAINST AN AI");
            GameFrame gameFrame = new GameFrame(game);
            game.setGameFrame(gameFrame);
            gameFrame.setVisible(true);
            dispose(); // close the starting page window
        }
    }

    public static void main(String[] args) {
        new StartingPage();
    }
}
