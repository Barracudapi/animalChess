public class Rat extends Piece {

    public Rat(Color color, String name, int animalPower){
        super(color, name, animalPower);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){
        boolean canMove = false;
        if((start.getX() == end.getX() && Math.abs(start.getY()-end.getY()) == 1) || (start.getY() == end.getY() && Math.abs(start.getX()-end.getX()) == 1)){
            if(end.getPiece() == null){
                canMove = true;
            }
            else if((end.getPiece().getName() == "ELEPHANT" || end.getPiece().getName() == "RAT") && end.getPiece().getColor() != start.getPiece().getColor() && start.getSpotType() != Spot.SpotType.WATER){
                canMove = true;
            }
        }
        return canMove;
    }

}
