import java.util.Comparator;

public class MoveComparator implements Comparator<Move> {
    @Override
    public int compare(Move move1, Move move2) {
        return Integer.compare(move2.getStart().getPiece().getValue(), move1.getStart().getPiece().getValue()); 
    }
}
