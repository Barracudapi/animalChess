import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class PiecePanel extends JPanel {
    // private final String imagePath;
    private Piece piece;

    public PiecePanel(Piece piece) {
        this.piece = piece;

        // set the layout to BorderLayout
        setPreferredSize(new Dimension(60, 60));
        
        setLayout(new BorderLayout());
        
        // create a new JLabel with the ImageIcon and add it to the center of the panel
        ImageIcon Lion = new ImageIcon(piece.getImagePath());
        JLabel lionLabel = new JLabel(Lion);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePath()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lionLabel.setIcon(imageIcon);
        if(piece.getColor()==Piece.Color.RED){
            // label.setForeground(Color.RED);
            setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        } else{
            // label.setForeground(Color.YELLOW);
            setBorder(BorderFactory.createLineBorder(Color.cyan, 5));
        }
        lionLabel.setHorizontalAlignment(JLabel.CENTER);
        lionLabel.setVerticalAlignment(JLabel.CENTER);
        add(lionLabel, BorderLayout.CENTER);
    }

    // public String getImagePath() {
    //     return imagePath;
    // }
}

