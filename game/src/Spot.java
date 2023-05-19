import java.util.ArrayList;

public class Spot {

    public boolean canMove;

    public enum Type{
        LAND, WATER, TRAPRED, TRAPYELLOW, BASERED, BASEYELLOW
    }

    private int x;
    private int y;
    private Piece piece;
    private Type type;

    public Spot(int x, int y, Piece piece, Type type){
        this.x = x - 1;
        this.y = y - 1;
        this.piece = piece;
        this.type = type;
    }
    public Spot(Spot other) {
        this.x = other.x;
        this.y = other.y;
        this.piece = other.piece;
        this.type = other.type;
    }

    @Override
    public String toString(){
        String string;
        if(this.piece == null && this.getSpotType() == Type.LAND){
            return "L ";
        }
        else if(this.piece == null && this.getSpotType() == Type.WATER){
            return "W  ";
        }
        else if(this.piece == null && (this.getSpotType() == Type.BASERED || this.getSpotType() == Type.BASEYELLOW)){
            return "B  ";
        }
        else if(this.piece == null && (this.getSpotType() == Type.TRAPRED || this.getSpotType() == Type.TRAPYELLOW)){
            return "T  ";
        }
        else{
            string = this.piece.getName() + "  ";
        }
        return string;
    }

    public ArrayList<Move> availableMoves(Board board){
        ArrayList<Move> array = new ArrayList<>();
        Spot start = new Spot(x + 1, y + 1, piece, type);
        for(int i = x-1; i < x+2; i++){
            for(int j = y-1; j < y+2; j++){
                if(i>-1 && i<9 && j>-1 && j<7){
                    if(start.getPiece() != null){
                        Move move = new Move(start, board.getSpots()[i][j]);
                        if(start.getPiece().isValidMove(move, board)){
                            array.add(move);
                        }
                    }
                }
            }
        }
        return array;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Type getSpotType(){
        return type;
    }
    
}
