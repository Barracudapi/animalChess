public class Rat extends Piece {

    public Rat(Color color, String name, int animalPower){
        super(color, name, animalPower);
    }

    @Override
    public boolean canMove(Spot[][] board, Spot start, Spot end){
        if((start.getX() == end.getX() && Math.abs(start.getY()-end.getY()) == 1) || (start.getY() == end.getY() && Math.abs(start.getX()-end.getX()) == 1)){
            if(end.getPiece() == null){
                return true;
            }
            else if((end.getPiece().getName() == "ELEPHANT" || end.getPiece().getName() == "RAT") && end.getPiece().getColor() != start.getPiece().getColor() && start.getSpotType() == end.getSpotType()){
                return true;
            }
            else if((start.getPiece().getColor() == Color.RED && end.getSpotType() == Spot.Type.BASERED) ||(start.getPiece().getColor() == Color.YELLOW && end.getSpotType() == Spot.Type.BASEYELLOW) ){
                return false;
            }
        }
        return false;
    }

}
