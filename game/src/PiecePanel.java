import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class PiecePanel extends JPanel {
    // private final String imagePath;
    private Piece piece;

    public PiecePanel(Piece piece) {
        this.piece = piece;

        // set the layout to BorderLayout
        setPreferredSize(new Dimension(60, 60));
        
        setLayout(new BorderLayout());
        
        // create a new JLabel with the ImageIcon and add it to the center of the panel
        JLabel label = new JLabel(piece.getName());
        if(piece.getColor()==Piece.Color.RED){
            // label.setForeground(Color.RED);
            setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        } else{
            // label.setForeground(Color.YELLOW);
            setBorder(BorderFactory.createLineBorder(Color.cyan, 5));
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
    }

    // public String getImagePath() {
    //     return imagePath;
    // }
}

