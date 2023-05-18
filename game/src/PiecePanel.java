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
        if(piece.getColor()==Piece.Color.RED){
            ImageIcon Lion = new ImageIcon(piece.getImagePathRed());
            JLabel lionLabel = new JLabel(Lion);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePathRed()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            lionLabel.setIcon(imageIcon);
            lionLabel.setHorizontalAlignment(JLabel.CENTER);
            lionLabel.setVerticalAlignment(JLabel.TOP);
            lionLabel.setBorder(BorderFactory.createLineBorder(new Color(100 ,0, 0), 3, false));
            add(lionLabel, BorderLayout.CENTER);
        } else{
            ImageIcon Lion = new ImageIcon(piece.getImagePathBlue());
            JLabel lionLabel = new JLabel(Lion);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePathBlue()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            lionLabel.setIcon(imageIcon);
            lionLabel.setHorizontalAlignment(JLabel.CENTER);
            lionLabel.setVerticalAlignment(JLabel.TOP);
            lionLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 100), 3, false));
            add(lionLabel, BorderLayout.CENTER);
        }
    }

    // public String getImagePath() {
    //     return imagePath;
    // }
}

