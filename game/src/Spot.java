public class Spot {

    public enum SpotType{
        LAND, WATER, TRAPRED, TRAPYELLOW, BASERED, BASEYELLOW
    }

    private int x;
    private int y;
    private Piece piece;
    private SpotType type;

    public Spot(int x, int y){
        this.x = x;
        this.y = y;
        this.piece = null;
        this.type = type;
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

    public SpotType getSpotType(){
        return type;
    }
}
