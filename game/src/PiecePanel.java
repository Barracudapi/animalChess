import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class PiecePanel extends JPanel {
    private Piece piece;

    public PiecePanel(Piece piece) {
        this.piece = piece;

        setPreferredSize(new Dimension(60, 60));
        
        setLayout(new BorderLayout());

        if(piece.getColor()==Piece.Color.RED){
            ImageIcon image = new ImageIcon(piece.getImagePathRed());
            JLabel label = new JLabel(image);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePathRed()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            label.setIcon(imageIcon);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            label.setBorder(BorderFactory.createLineBorder(new Color(100 ,0, 0), 3, false));
            add(label, BorderLayout.CENTER);
        } else{
            ImageIcon image = new ImageIcon(piece.getImagePathBlue());
            JLabel label = new JLabel(image);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(piece.getImagePathBlue()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            label.setIcon(imageIcon);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            label.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 100), 3, false));
            label.setBackground(Color.gray);
            add(label, BorderLayout.CENTER);
        }
    }
}

