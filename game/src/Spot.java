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

    @Override
    public String toString(){
        String string;
        if(this.piece == null && this.getSpotType() == Type.LAND){
            return "L  ";
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
