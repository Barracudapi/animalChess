import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartingPage extends JFrame implements ActionListener {

    private JButton startButton;
    private JButton exitButton;
    private BoardPanel boardPanel;

    public StartingPage() {
        setTitle("Jungle Chess Game");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel label = new JLabel("Welcome to Jungle Chess Game!");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        panel.add(startButton);
        
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
        }
    }

    public static void main(String[] args) {
        StartingPage page = new StartingPage();
    }
}
