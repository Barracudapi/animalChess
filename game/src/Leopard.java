public class Leopard extends Piece{

    public Leopard(Color color, String name, int animalPower){
        super(color, name, animalPower);
    }
    @Override
    public boolean canMove(Spot[][] board, Spot start, Spot end) {
        if(end.getSpotType() == Spot.Type.BASERED && start.getPiece().getColor() == Color.RED){
            return false;
        }
        else if(end.getSpotType() == Spot.Type.BASEYELLOW && start.getPiece().getColor() == Color.YELLOW){
            return false;
        }
        else if(end.getSpotType() == Spot.Type.WATER){
            return false;
        }
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