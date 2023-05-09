import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Player player1 = new Player("P1", Piece.Color.RED);
        Player player2 = new Player("P2", Piece.Color.YELLOW);
        Spot spot1 = new Spot(3, 1, null, Spot.Type.LAND);
        spot1.setPiece(b.getSpot(spot1).getPiece());
        Spot spot2 = new Spot(4, 1, null, Spot.Type.LAND);
        spot2.setPiece(b.getSpot(spot2).getPiece());
        Move move = new Move(player1, spot2, spot1);
        b.movePiece(move);
        b.printBoard();
    }
}