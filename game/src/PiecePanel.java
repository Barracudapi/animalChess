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
        setPreferredSize(new Dimension(80, 80));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());

        // create a new JLabel with the ImageIcon and add it to the center of the panel
        JLabel label = new JLabel(piece.getName());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
    }

    // public String getImagePath() {
    //     return imagePath;
    // }
}

