
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
            Game game = new Game(false, true, 0, 0);
            GameFrame gameFrame = new GameFrame(game);
            game.setGameFrame(gameFrame);
            gameFrame.setVisible(true);
            dispose();
        } else if (e.getSource() == exitButton) {
            dispose();
        } else if (e.getSource() == aiButton){
            JFrame aiLevelFrame = new JFrame("Choose AI difficulty");
            aiLevelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            aiLevelFrame.setSize(1920, 1080);

            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(2, 3));

            JButton easy = new JButton();
            easy.setPreferredSize(new Dimension(300, 600));
            ImageIcon icon1 = new ImageIcon("game/resources/cute-robot.jpg");
            easy.setIcon(icon1);
            easy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game game = new Game(true, true, 3000, 6);
                    System.out.println("THIS GAME IS AGAINST AN AI ON EASY DIFFICULTY");
                    GameFrame gameFrame = new GameFrame(game);
                    game.setGameFrame(gameFrame);
                    gameFrame.setVisible(true);
                    aiLevelFrame.dispose();
                }
            });

            JButton medium = new JButton();
            medium.setPreferredSize(new Dimension(300, 600));
            ImageIcon icon2 = new ImageIcon("game/resources/medium.png");
            medium.setIcon(icon2);
            medium.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game game = new Game(true, true, 7000, 8);
                    System.out.println("THIS GAME IS AGAINST AN AI ON NORMAL DIFFICULTY");
                    GameFrame gameFrame = new GameFrame(game);
                    game.setGameFrame(gameFrame);
                    gameFrame.setVisible(true);
                    aiLevelFrame.dispose();
                }
            });

            JButton hard = new JButton();
            hard.setPreferredSize(new Dimension(300, 600));
            ImageIcon icon3 = new ImageIcon("game/resources/skyweb.png");
            hard.setIcon(icon3);
            hard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game game = new Game(true, true, 12000, 10);
                    System.out.println("THIS GAME IS AGAINST AN AI ON HARD DIFFICULTY");
                    GameFrame gameFrame = new GameFrame(game);
                    game.setGameFrame(gameFrame);
                    gameFrame.setVisible(true);
                    aiLevelFrame.dispose();
                }
            });

            JTextArea text1 = new JTextArea();
            text1.setBackground(new Color(72, 72, 72));
            text1.setBorder(BorderFactory.createLineBorder(Color.black));
            text1.setText("CODE NAME: T.!.N.Y\n\nDIFFICULTY LEVEL: EASY\n\nT.1.N.Y is a adorable and curious A.I created out of love and joy.\nThough optimistic, T.!.N.Y is inexperienced");
            text1.setForeground(Color.white);


            JTextArea text2 = new JTextArea();
            text2.setBackground(new Color(72, 72, 72));
            text2.setBorder(BorderFactory.createLineBorder(Color.black));
            text2.setText("CODE NAME: CHAD_B0T\n\nDIFFICULTY LEVEL: MEDIUM\n\nCHAD-B0T is a self-proclaimed genius at jungle chess.\nCHAD-B0T is calculative on the board but enjoys giving his opponents a chance.");
            text2.setForeground(Color.white);


            JTextArea text3 = new JTextArea();
            text3.setBackground(new Color(72, 72, 72));
            text3.setBorder(BorderFactory.createLineBorder(Color.black));
            text3.setText("CODE NAME: SKYWEB\n\nDIFFICULTY LEVEL: HARD\n\nSKYWEB'S one and only goal is the extermination of the human race along with all other living species.\nCan you stop him before it is too late?");
            text3.setForeground(Color.white);


           panel1.add(easy);
           panel1.add(medium);
           panel1.add(hard);
           panel1.add(text1);
           panel1.add(text2);
           panel1.add(text3);
           aiLevelFrame.add(panel1, BorderLayout.NORTH);
           aiLevelFrame.setVisible(true);

            dispose();
        }
    }

    public static void main(String[] args) {
        StartingPage page = new StartingPage();
    }
}