import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements BoardPanel.BoardChangeListener {
    private BoardPanel boardPanel;
    private SidePanel sidePanel;
    private GameOverPopup gameOverPopup;
    private Game game;

    public GameFrame(Game game) {
        super("Jungle Chess");
        this.game = game;
        setTitle("Jungle Chess Game");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel(this.game);
        sidePanel = new SidePanel(this.game);
        gameOverPopup = new GameOverPopup(this.game);
        setLayout(new BorderLayout());
        boardPanel.setPreferredSize(boardPanel.getPreferredSize());
        boardPanel.addBoardChangeListener(this);
        boardPanel.addBoardChangeListener(game);

        add(boardPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.LINE_END);
    }
    public void updateGame(){
        sidePanel.updateTurn();
        boardPanel.updateBoardPanel();
        sidePanel.updateCapturedPieces(game.getBoard().getCapturedPieces());
        revalidate();
        repaint();
    }
    @Override
    public void onBoardChanged(Spot event) {
        updateGame();
    }
    public SidePanel getSidePanel() {
        return sidePanel;
    }
    public void gameover(){
        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.loadSound("game/resources/vicroy.wav");
        soundPlayer.playSound();
        gameOverPopup.setVisible(true);
        dispose();
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
