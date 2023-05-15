import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("1", Piece.Color.YELLOW);
        Board b = new Board();
        Spot spot1 = new Spot(1, 1, null, Spot.Type.LAND);
        spot1.setPiece(b.getSpot(spot1).getPiece());
        ArrayList<Move> array = spot1.availableMoves(b);
        for(int i = 0; i < array.size(); i++){
            System.out.print(array.get(i));
        }
    }
}