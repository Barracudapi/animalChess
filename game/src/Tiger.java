public class Tiger extends Piece {
    private static final String imagePath = "game/resources/tiger.jfif";
    public Tiger(Piece.Color color, String name, int animalPower){
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
                return true;
            }
        }
        if(start.getY() == end.getY() && Math.abs(end.getX() - start.getX()) == 4 && ((start.getX() == 2 || start.getX() == 6) && (start.getY() == 1 || start.getY() == 2 || start.getY() == 4 || start.getY() == 5))){
            if(end.getPiece() != null && end.getPiece().getAnimalPower() >= start.getPiece().getAnimalPower()){
                return false;
            }
            for(int i = 3; i < 6; i++){
                if(Board.Spots[i][start.getY()].getPiece() != null){
                    return false;
                }
            }
            return true;
        }
        if(start.getX() == end.getX() && Math.abs(end.getY() - start.getY()) == 3 && ((start.getX() == 3 || start.getX() == 4 || start.getX() == 5) && (start.getY() == 0 || start.getY() == 3 || start.getY() == 6))){
            if(end.getPiece() != null && end.getPiece().getAnimalPower() >= start.getPiece().getAnimalPower()){
                return false;
            }
            if(start.getY() == 0){
                for(int i = 1; i < 3; i++){
                    if(Board.Spots[start.getX()][i].getPiece() != null){
                        return false;
                    }
                }
            }
            else if(start.getY() == 3 && end.getY() == 0){
                for(int i = 1; i < 3; i++){
                    if(Board.Spots[start.getX()][i].getPiece() != null){
                        return false;
                    }
                }
            }
            else if(start.getY() == 3 && end.getY() == 6){
                for(int i = 4; i < 6; i++){
                    if(Board.Spots[start.getX()][i].getPiece() != null){
                        return false;
                    }
                }
            }
            else if(start.getY() == 6){
                for(int i = 4; i < 6; i++){
                    if(Board.Spots[start.getX()][i].getPiece() != null){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
