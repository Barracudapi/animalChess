import java.util.Objects;

public class Rat extends Piece {
    private static final String imagePath = "game/resources/rat.jpg";
    public Rat(Color color, String name, int animalPower){
        super(color, name, animalPower, imagePath);
    }

    @Override
    public boolean canMove(Spot[][] board, Spot start, Spot end) {
        if(end.getSpotType() == Spot.Type.BASERED && start.getPiece().getColor() == Color.RED){
            return false;
        }
        else if(end.getSpotType() == Spot.Type.BASEYELLOW && start.getPiece().getColor() == Color.YELLOW){
            return false;
        }
        if(end.getPiece() != null){
            if(start.getPiece().getColor() == end.getPiece().getColor()){
                return false;
            }
        }
        if((start.getX() == end.getX() && Math.abs(start.getY()-end.getY()) == 1) || (start.getY() == end.getY() && Math.abs(start.getX()-end.getX()) == 1)){
            if(end.getPiece() == null){
                return true;
            }
            if(end.getSpotType() == Spot.Type.TRAPRED && end.getPiece().getColor() == Color.YELLOW){
                return true;
            }
            if(end.getSpotType() == Spot.Type.TRAPYELLOW && end.getPiece().getColor() == Color.RED) {
                return true;
            }
            if((end.getPiece().getAnimalPower()<= start.getPiece().getAnimalPower() || end.getPiece().getName() == "ELEPHANT") && (start.getSpotType() != Spot.Type.WATER && end.getSpotType() == Spot.Type.LAND)){
                return true;
            }
            if(start.getSpotType() == Spot.Type.WATER && end.getSpotType() == Spot.Type.WATER){
                return true;
            }
        }
        return false;
    }

}
