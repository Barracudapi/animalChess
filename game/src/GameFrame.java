import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    private BoardPanel boardPanel;
    private SidePanel sidePanel;
    private Game game;

    public GameFrame(Game game) {
        super("Jungle Chess");
        this.game = game;
        setTitle("Jungle Chess Game");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel(this.game.getBoard());
        sidePanel = new SidePanel(this.game);
        setLayout(new BorderLayout());

        add(boardPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.LINE_END);
    }
}
