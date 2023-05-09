public class Cat extends Piece {
    public Cat(Color color, String name, int animalPower){
        super(color, name, animalPower);
    }
    @Override
    public boolean canMove(Spot[][] board, Spot start, Spot end) {
        if(end.getSpotType().toString() == "BASERED" && start.getPiece().getColor().toString() == "RED") return false;
        if(end.getSpotType().toString() == "BASEYELLOW" && start.getPiece().getColor().toString() == "YELLOW") return false;
        if(end.getSpotType().toString() == "Water") return false;
        if((start.getX() == end.getX() && Math.abs(start.getY()-end.getY()) == 1) || (start.getY() == end.getY() && Math.abs(start.getX()-end.getX()) == 1)){
            if(end.getPiece() == null){
                return true;
            }
            else if(end.getPiece().getAnimalPower()<= start.getPiece().getAnimalPower() && start.getPiece().getColor() != end.getPiece().getColor()){
                return true;
            }
        }
        return false;
    }
}
