public class Spot {
    private int x;
    private int y;
    private Piece piece;

    public Spot(int x, int y){
        this.x = x;
        this.y = y;
        this.piece = null;
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
}
