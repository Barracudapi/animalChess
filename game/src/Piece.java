public abstract class Piece {

    public enum Color{
        RED, YELLOW;
    }
    private Color color;
    private String name;
    private int animalPower;

    public Piece(Color color, String name, int animalPower){
        this.color = color;
        this.name = name;
        this.animalPower = animalPower;
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
