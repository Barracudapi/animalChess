import java.util.*;

public class Board {
    private static final int COLUMN_SIZE = 7;
    private static final int ROW_SIZE = 9;
    private static final String[] COLUMN_LABEL = {"1", "2", "3", "4", "5", "6", "7"};
    private static final String[] ROW_LABEL = {"9", "8", "7", "6", "5", "4", "3", "2", "1"};

    private static final Piece[][] INITIAL_SPOTS = {
            {null, null, null, null, null, null, null},
            {null, new Dog(Piece.Color.RED, "DOG", 3), null, null, null, new Cat(Piece.Color.RED, "CAT", 2), null},
            {new Rat(Piece.Color.RED, "RAT", 1), null, new Leopard(Piece.Color.RED, "LEOPARD", 5), null, new Wolf(Piece.Color.RED, "WOLF", 4), null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, new Wolf(Piece.Color.YELLOW, "WOLF", 4), null, new Leopard(Piece.Color.YELLOW, "LEOPARD", 5), null, new Rat(Piece.Color.YELLOW, "RAT", 1)},
            {null, new Cat(Piece.Color.YELLOW, "CAT", 2), null, null, null, new Dog(Piece.Color.YELLOW, "DOG", 3), null},
            {null, null, null, null, null, null, null}
    };

    private static Piece[][] Spots;
    private List<Piece> capturedPieces;

    public Board() {
        Spots = new Piece[ROW_SIZE][COLUMN_SIZE];
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; i < COLUMN_SIZE; j++){
                Spots[i][j] = INITIAL_SPOTS[i][j];
            }
        }
        capturedPieces = new ArrayList<>();
    }

    public static void main(String[] args) {
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; i < COLUMN_SIZE; j++){
                if(Spots[i][j] == null){
                    System.out.print("_");
                }
                else{
                    System.out.print(Spots[i][j]);
                }
            }
            System.out.print("\n");
        }
    }
}
