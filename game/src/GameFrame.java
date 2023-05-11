import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements BoardPanel.BoardChangeListener {
    private BoardPanel boardPanel;
    private SidePanel sidePanel;
    private Game game;

    public GameFrame(Game game) {
        super("Jungle Chess");
        this.game = game;
        setTitle("Jungle Chess Game");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel(this.game);
        sidePanel = new SidePanel(this.game);
        setLayout(new BorderLayout());
        boardPanel.setPreferredSize(boardPanel.getPreferredSize());
        boardPanel.addBoardChangeListener(this);
        boardPanel.addBoardChangeListener(game);

        add(boardPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.LINE_END);
    }
    public void updateGame(){
        sidePanel.updateTurn();
        revalidate();
        repaint();
    }
    @Override
    public void onBoardChanged(Spot event) {
        updateGame();
    }
}
