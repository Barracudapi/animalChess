public abstract class Piece {

    public enum Color{
        RED, YELLOW;
    }
    private Color color;
    private String name;

    public Piece(Color color, String name){
        this.color = color;
        this.name = name;
    }

    public Piece(Color color2){
    }

    public Color getColor(){
        return color;
    }

    public String getName(){
        return name;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end);

    public boolean isValidMove(Move move, Board board){
        return canMove(board, move.getStartPosition(), move.getEndPosition());
    }

    public void move(Move move) {};
}
