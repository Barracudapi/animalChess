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

    @Override
    public String toString(){
        String string = this.name;
        return string;
    }

    public Color getColor(){
        return color;
    }

    public String getName(){
        return name;
    }
    public int getAnimalPower() {
        return animalPower;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end);


    public boolean isValidMove(Move move, Board board){
        return canMove(board, move.getStart(), move.getEnd());
    }

    public void move(Move move) {};
}
