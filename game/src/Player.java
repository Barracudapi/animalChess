public class Player {
    private String name;
    private Piece.Color pieceColor;

    public Player(String name, Piece.Color pieceColor){
        this.name = name;
        this.pieceColor = pieceColor;
    }

    public String getName(){
        return name;
    }

    public Piece.Color getPieceColor() {
        return pieceColor;
    }

    public Move getMove(){
        return null;
    }
}


