import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("1", Piece.Color.YELLOW);
        Board b = new Board();
        Spot spot1 = new Spot(1, 1, new Lion(Piece.Color.RED), Spot.Type.LAND);
        spot1.setPiece(b.getSpot(spot1).getPiece());
        ArrayList<Move> array = spot1.availableMoves(b);
        for(int i = 0; i < array.size(); i++){
            System.out.print(array.get(i));
        }
        spot1.availableMoves(b);
        // TranspositionTable trans = new TranspositionTable();
        // int[] transKey = trans.createKey(b, true);
        // trans.store(transKey, 3);
        // trans.printTable(transKey);
        // System.out.println(trans.lookupexists(transKey));
        // System.out.println(trans.lookup(transKey));
    }
}