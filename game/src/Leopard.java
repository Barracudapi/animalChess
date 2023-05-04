public class Leopard extends Piece{
    public Leopard(Color color, String name, int animalPower){
        super(color, name, animalPower);
    }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(end.getSpotType().toString() == "BASERED" && start.getPiece().getColor().toString() == "RED") return false;
        if(end.getSpotType().toString() == "BASEYELLOW" && start.getPiece().getColor().toString() == "YELLOW") return false;
        if(end.getSpotType().toString() == "Water") return false;
        if((start.getX() == end.getX() && Math.abs(start.getY()-end.getY()) == 1) || (start.getY() == end.getY() && Math.abs(start.getX()-end.getX()) == 1)){
            if(end.getPiece() == null){
                return true;
            }
            else if(end.getPiece().getAnimalPower()<= start.getPiece().getAnimalPower()){
                return true;
            }
        }
        return false;
    }
}
