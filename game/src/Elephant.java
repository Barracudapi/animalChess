import java.util.Objects;

public class Elephant extends Piece{
    private static final String imagePathRed = "game/resources/elephantred.jpg";
    private static final String imagePathBlue = "game/resources/elephantblue.jpg";
        private static final String imagePath = "game/resources/elephant.jfif";
        private static final int value = 11;
        private static final int animalPower = 8;
        private static final String name = "ELEPHANT";
        public Elephant(Color color){
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
            else if(end.getSpotType() == Spot.Type.WATER){
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
                if(end.getPiece().getAnimalPower()<= start.getPiece().getAnimalPower()){
                    if(end.getPiece().getName() == "RAT"){
                        return false;
                    }
                    else{
                     return true;
                    }
                }
            }
            return false;
        }

}
