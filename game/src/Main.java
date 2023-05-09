import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Player player1 = new Player("P1", Piece.Color.RED);
        Player player2 = new Player("P2", Piece.Color.YELLOW);
        Spot spot = new Spot(3, 3, null, null);
        spot.setPiece(b.getSpot(spot).getPiece());
        Spot spot2 = new Spot(3, 4, null, Spot.Type.LAND);
        spot2.setPiece(b.getSpot(spot2).getPiece());
        Spot spot3 = new Spot(2, 4, null, Spot.Type.LAND);
        spot3.setPiece(b.getSpot(spot3).getPiece());
        Piece piece = new Cat(Piece.Color.RED, "CAT", 2);

        Move move1 = new Move(player1, spot, spot2);
        b.movePiece(move1);
        b.printBoard();
    }
}