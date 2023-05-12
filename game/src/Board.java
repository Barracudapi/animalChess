import java.util.*;

public class Board {
    private static final int COLUMN_SIZE = 7;
    private static final int ROW_SIZE = 9;
    private static final String[] COLUMN_LABEL = {"1", "2", "3", "4", "5", "6", "7"};
    private static final String[] ROW_LABEL = {"9", "8", "7", "6", "5", "4", "3", "2", "1"};

    public static final Spot[][] INITIAL_SPOTS = {
            {new Spot(1,1, new Lion(Piece.Color.RED, "LION", 8), Spot.Type.LAND), new Spot(1,2, null, Spot.Type.LAND), new Spot(1,3, null, Spot.Type.TRAPRED), new Spot(1,4, null, Spot.Type.BASERED), new Spot(1,5, null, Spot.Type.TRAPRED), new Spot(1,6, null, Spot.Type.LAND), new Spot(1,7, new Tiger(Piece.Color.RED, "TIGER", 7), Spot.Type.LAND)},
            {new Spot(2,1, null, Spot.Type.LAND), new Spot(2,2, new Dog(Piece.Color.RED, "DOG", 3), Spot.Type.LAND), new Spot(2,3, null, Spot.Type.LAND), new Spot(2,4, null, Spot.Type.TRAPRED), new Spot(2,5, null, Spot.Type.LAND), new Spot(2,6, new Cat(Piece.Color.RED, "CAT", 2), Spot.Type.LAND), new Spot(2,7, null, Spot.Type.LAND)},
            {new Spot(3,1, new Rat(Piece.Color.RED, "RAT", 1), Spot.Type.LAND), new Spot(3,2, null, Spot.Type.LAND), new Spot(3,3, new Leopard(Piece.Color.RED, "LEOPARD",5 ), Spot.Type.LAND), new Spot(3,4, null, Spot.Type.LAND), new Spot(3,5, new Wolf(Piece.Color.RED, "WOLF", 4), Spot.Type.LAND), new Spot(3,6, null, Spot.Type.LAND), new Spot(3,7, new Elephant(Piece.Color.RED, "ELEPHANT", 9), Spot.Type.LAND)},
            {new Spot(4,1, null, Spot.Type.LAND), new Spot(4,2, null, Spot.Type.WATER), new Spot(4,3, null, Spot.Type.WATER), new Spot(4,4, null, Spot.Type.LAND), new Spot(4,5, null, Spot.Type.WATER), new Spot(4,6, null, Spot.Type.WATER), new Spot(4,7, null, Spot.Type.LAND)},
            {new Spot(5,1, null, Spot.Type.LAND), new Spot(5,2, null, Spot.Type.WATER), new Spot(5,3, null, Spot.Type.WATER), new Spot(5,4, null, Spot.Type.LAND), new Spot(5,5, null, Spot.Type.WATER), new Spot(5,6, null, Spot.Type.WATER), new Spot(5,7, null, Spot.Type.LAND)},
            {new Spot(6,1, null, Spot.Type.LAND), new Spot(6,2, null, Spot.Type.WATER), new Spot(6,3, null, Spot.Type.WATER), new Spot(6,4, null, Spot.Type.LAND), new Spot(6,5, null, Spot.Type.WATER), new Spot(6,6, null, Spot.Type.WATER), new Spot(6,7, null, Spot.Type.LAND)},
            {new Spot(7,1, new Elephant(Piece.Color.YELLOW, "ELEPHANT", 9), Spot.Type.LAND), new Spot(7,2, null, Spot.Type.LAND), new Spot(7,3, new Wolf(Piece.Color.YELLOW, "WOLF",4 ), Spot.Type.LAND), new Spot(7,4, null, Spot.Type.LAND), new Spot(7,5, new Leopard(Piece.Color.YELLOW, "LEOPARD", 5), Spot.Type.LAND), new Spot(7,6, null, Spot.Type.LAND), new Spot(7,7, new Rat(Piece.Color.YELLOW, "RAT", 1), Spot.Type.LAND)},
            {new Spot(8,1, null, Spot.Type.LAND), new Spot(8,2, new Cat(Piece.Color.YELLOW, "CAT", 2), Spot.Type.LAND), new Spot(8,3, null, Spot.Type.LAND), new Spot(8,4, null, Spot.Type.TRAPYELLOW), new Spot(8,5, null, Spot.Type.LAND), new Spot(8,6, new Dog(Piece.Color.YELLOW, "DOG", 3), Spot.Type.LAND), new Spot(8,7, null, Spot.Type.LAND)},
            {new Spot(9,1, new Tiger(Piece.Color.YELLOW, "TIGER", 7), Spot.Type.LAND), new Spot(9,2, null, Spot.Type.LAND), new Spot(9,3, null, Spot.Type.TRAPYELLOW), new Spot(9,4, null, Spot.Type.BASEYELLOW), new Spot(9,5, null, Spot.Type.TRAPYELLOW), new Spot(9,6, null, Spot.Type.LAND), new Spot(9,7, new Lion(Piece.Color.YELLOW, "LION", 8), Spot.Type.LAND)},
    };

    public static Spot[][] Spots;
    private List<Piece> capturedPieces;

    public Board() {
        Spots = new Spot[ROW_SIZE][COLUMN_SIZE];
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; j < COLUMN_SIZE; j++){
                Spots[i][j] = new Spot(INITIAL_SPOTS[i][j]);
            }
        }
        capturedPieces = new ArrayList<>();
    }

    public void printBoard() {
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; j < COLUMN_SIZE; j++){
                if(Spots[i][j] == null){
                    System.out.print("0  ");
                }
                else{
                    System.out.print(Spots[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    public boolean movePiece(Move move){
        Piece piece = getSpot(move.getStart()).getPiece();
        if(piece != null){
            if(piece.isValidMove(move, Spots)){
                if(move.isCapture()){
                    capturedPieces.add(getSpot(move.getEnd()).getPiece());
                }
                System.out.println(move.toString());
                setPieceAtSpot(move.getEnd(), piece);
                setPieceAtSpot(move.getStart(), null);
                return true;
            }
        }
        return false;
    }

    public void printCapturedPieces(){
        for(Piece p: capturedPieces){
            System.out.print(p);
        }
        System.out.print("\n");
    }

    public Spot getSpot(Spot spot){
        return Spots[spot.getX()][spot.getY()];
    }

    public void setPieceAtSpot(Spot spot, Piece piece){
        Spots[spot.getX()][spot.getY()].setPiece(piece);
    }
    public Spot[][] getSpots() {
        return Spots;
    }
}