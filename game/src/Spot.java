public class Spot {

    public enum Type{
        LAND, WATER, TRAPRED, TRAPYELLOW, BASERED, BASEYELLOW
    }

    private int x;
    private int y;
    private Piece piece;
    private Type type;

    public Spot(int row, int column, Piece piece, Type type){
        this.x = row - 1;
        this.y = column - 1;
        this.piece = piece;
        this.type = type;
    }

    @Override
    public String toString(){
        String string;
        if(this.piece == null){
            return "0";
        }
        else{
            string = this.piece.getName();
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
