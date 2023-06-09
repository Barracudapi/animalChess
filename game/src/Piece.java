public abstract class Piece {

    public enum Color{
        RED, BLUE;
    }
    private Color color;
    private String name;
    private int animalPower;
    private int value;
    private String imagePathRed;
    private String imagePathBlue;
    

    public Piece(Color color, String name, int animalPower, int value, String imagePathRed, String imagePathBlue){
        this.color = color;
        this.name = name;
        this.animalPower = animalPower;
        this.imagePathRed = imagePathRed;
        this.imagePathBlue = imagePathBlue;
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

    public abstract boolean canMove(Board board, Spot start, Spot end);


    public boolean isValidMove(Move move, Board board){
        return canMove(board, move.getStart(), move.getEnd());
    }

    public void move(Move move) {};

    public String getImagePathRed(){
        return imagePathRed;
    }
    public String getImagePathBlue(){
        return imagePathBlue;
    }

    public int getValue() {
        return value;
    }
}
