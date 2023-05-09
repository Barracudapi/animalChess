import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    private BoardPanel boardPanel;
    private Game game;

    public GameFrame(Game game) {
        this.game = game;
        setTitle("Jungle Chess Game");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel(this.game.getBoard());
        add(boardPanel);
    }
}
