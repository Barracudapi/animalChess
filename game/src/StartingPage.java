
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartingPage extends JFrame implements ActionListener {

    private JButton startButton;
    private JButton exitButton;
    private JButton aiButton;
    private JFrame frame;
    private JPanel panel;
    private static boolean isFrameCreated = false;

    public StartingPage() {
        if(!isFrameCreated){
            frame = new JFrame();
            setTitle("Jungle Chess Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1920, 1080);

            JPanel panel = new JPanel();

            JLabel label = new JLabel("Jungle!");
            label.setFont(new Font("Serif", Font.PLAIN, 30));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);


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

            Image image = Toolkit.getDefaultToolkit().getImage("game/resources/background.jpg");
            ImagePanel imagePanel = new ImagePanel(image);
            add(imagePanel);
            pack();

            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = environment.getDefaultScreenDevice();
            device.setFullScreenWindow(this);

            isFrameCreated = true;
        }
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
           dispose();
        }
    }

    public static void main(String[] args) {
        StartingPage page = new StartingPage();
    }
}