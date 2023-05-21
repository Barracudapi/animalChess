
public class Move {
    private Spot start;
    private Spot end;
    public double value;

    public Move(Spot start, Spot end){
        this.start = start;
        this.end = end;
        value = 0;
    }
    public Move(Move other){
        this.start = new Spot(other.start);
        this.end = new Spot(other.end);
    }
    public Move(String pgn){
        
    }
    @Override
    public String toString(){
        if(start.getPiece()!=null){

            char c = '_';
            switch(start.getPiece().getName()){
                case "RAT":
                    c = 'R';
                    break;
                case "CAT": 
                    c = 'C';
                    break;
                case "DOG":
                    c = 'D';
                    break;
                case "WOLF": 
                    c = 'W';
                    break;
                case "LEOPARD":
                    c = 'P';
                    break;
                case "TIGER": 
                    c = 'T';
                    break;
                case "LION":
                    c = 'L';
                    break;
                case "ELEPHANT": 
                    c = 'E';
                    break;
    
            }
            return String.format("%s-%d%d-%d%d ", c, start.getX(), start.getY(), end.getX(), end.getY());
        }
        return String.format(start.toString() + end.toString());
    }

    public boolean isCapture(){
        if(end.getPiece() == null){
            return false;
        }
        return true;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }
    public Move pgnToMove(String pngFrag, Piece.Color pieceColor){
        
        // return new Move(new Spot(pngFrag.charAt(2)+0, pngFrag.charAt(3)+0, piece, ))
        return null;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
}
