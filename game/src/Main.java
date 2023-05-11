import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Player player1 = new Player("Player1", Piece.Color.YELLOW);
        Spot spot1 = new Spot(1, 2, null, Spot.Type.LAND);
        spot1.setPiece(b.getSpot(spot1).getPiece());
        Spot spot2 = new Spot(1, 3, null, Spot.Type.TRAPRED);
        spot2.setPiece(b.getSpot(spot2).getPiece());

        Move move = new Move(player1, spot1, spot2);
        b.movePiece(move);

        b.printBoard();
    }
}