import java.util.Comparator;

public class MoveComparator implements Comparator<Move> {
    @Override
    public int compare(Move move1, Move move2) {
        if(move1.getEnd().getPiece()!=null || move2.getEnd().getPiece()!=null){
            if(move1.getEnd().getPiece()==null) return 1;
            if(move2.getEnd().getPiece()==null) return -1;
            return Integer.compare(move2.getEnd().getPiece().getValue(), move1.getEnd().getPiece().getValue());
        }else {
            return Integer.compare(move2.getStart().getPiece().getValue(), move1.getStart().getPiece().getValue()); 
        }
    }
}
