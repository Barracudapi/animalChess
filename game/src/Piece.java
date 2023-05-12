public abstract class Piece {

    public enum Color{
        RED, YELLOW;
    }
    private Color color;
    private String name;
    private int animalPower;
    private int value;
    private String imagePath;
    

    public Piece(Color color, String name, int animalPower, int value, String imagePath){
        this.color = color;
        this.name = name;
        this.animalPower = animalPower;
        this.imagePath = imagePath;
        this.value = value;
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

    public abstract boolean canMove(Spot[][] board, Spot start, Spot end);


    public boolean isValidMove(Move move, Spot[][] board){
        return canMove(board, move.getStart(), move.getEnd());
    }

    public void move(Move move) {};

    public String getImagePath(){
        return imagePath;
    }
}
