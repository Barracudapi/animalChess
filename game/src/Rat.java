public class Rat extends Piece {
    private static final String imagePathRed = "game/resources/ratred.jpg";
    private static final String imagePathBlue = "game/resources/ratblue.jfif";
    private static final int value = 5;
    private static final int animalPower = 1;
    private static final String name = "RAT";
    public Rat(Color color){
        super(color, name, animalPower, value, imagePathRed, imagePathBlue);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
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
