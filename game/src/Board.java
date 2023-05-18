import java.util.*;

public class Board {
    private static final int COLUMN_SIZE = 7;
    private static final int ROW_SIZE = 9;
    private static final String[] COLUMN_LABEL = {"1", "2", "3", "4", "5", "6", "7"};
    private static final String[] ROW_LABEL = {"9", "8", "7", "6", "5", "4", "3", "2", "1"};

    private static final Spot[][] INITIAL_SPOTS = {
            {new Spot(1,1, new Lion(Piece.Color.RED), Spot.Type.LAND), new Spot(1,2, null, Spot.Type.LAND), new Spot(1,3, null, Spot.Type.TRAPRED), new Spot(1,4, null, Spot.Type.BASERED), new Spot(1,5, null, Spot.Type.TRAPRED), new Spot(1,6, null, Spot.Type.LAND), new Spot(1,7, new Tiger(Piece.Color.RED), Spot.Type.LAND)},
            {new Spot(2,1, null, Spot.Type.LAND), new Spot(2,2, new Dog(Piece.Color.RED), Spot.Type.LAND), new Spot(2,3, null, Spot.Type.LAND), new Spot(2,4, null, Spot.Type.TRAPRED), new Spot(2,5, null, Spot.Type.LAND), new Spot(2,6, new Cat(Piece.Color.RED), Spot.Type.LAND), new Spot(2,7, null, Spot.Type.LAND)},
            {new Spot(3,1, new Rat(Piece.Color.RED), Spot.Type.LAND), new Spot(3,2, null, Spot.Type.LAND), new Spot(3,3, new Leopard(Piece.Color.RED), Spot.Type.LAND), new Spot(3,4, null, Spot.Type.LAND), new Spot(3,5, new Wolf(Piece.Color.RED), Spot.Type.LAND), new Spot(3,6, null, Spot.Type.LAND), new Spot(3,7, new Elephant(Piece.Color.RED), Spot.Type.LAND)},
            {new Spot(4,1, null, Spot.Type.LAND), new Spot(4,2, null, Spot.Type.WATER), new Spot(4,3, null, Spot.Type.WATER), new Spot(4,4, null, Spot.Type.LAND), new Spot(4,5, null, Spot.Type.WATER), new Spot(4,6, null, Spot.Type.WATER), new Spot(4,7, null, Spot.Type.LAND)},
            {new Spot(5,1, null, Spot.Type.LAND), new Spot(5,2, null, Spot.Type.WATER), new Spot(5,3, null, Spot.Type.WATER), new Spot(5,4, null, Spot.Type.LAND), new Spot(5,5, null, Spot.Type.WATER), new Spot(5,6, null, Spot.Type.WATER), new Spot(5,7, null, Spot.Type.LAND)},
            {new Spot(6,1, null, Spot.Type.LAND), new Spot(6,2, null, Spot.Type.WATER), new Spot(6,3, null, Spot.Type.WATER), new Spot(6,4, null, Spot.Type.LAND), new Spot(6,5, null, Spot.Type.WATER), new Spot(6,6, null, Spot.Type.WATER), new Spot(6,7, null, Spot.Type.LAND)},
            {new Spot(7,1, new Elephant(Piece.Color.YELLOW), Spot.Type.LAND), new Spot(7,2, null, Spot.Type.LAND), new Spot(7,3, new Wolf(Piece.Color.YELLOW), Spot.Type.LAND), new Spot(7,4, null, Spot.Type.LAND), new Spot(7,5, new Leopard(Piece.Color.YELLOW), Spot.Type.LAND), new Spot(7,6, null, Spot.Type.LAND), new Spot(7,7, new Rat(Piece.Color.YELLOW), Spot.Type.LAND)},
            {new Spot(8,1, null, Spot.Type.LAND), new Spot(8,2, new Cat(Piece.Color.YELLOW), Spot.Type.LAND), new Spot(8,3, null, Spot.Type.LAND), new Spot(8,4, null, Spot.Type.TRAPYELLOW), new Spot(8,5, null, Spot.Type.LAND), new Spot(8,6, new Dog(Piece.Color.YELLOW), Spot.Type.LAND), new Spot(8,7, null, Spot.Type.LAND)},
            {new Spot(9,1, new Tiger(Piece.Color.YELLOW), Spot.Type.LAND), new Spot(9,2, null, Spot.Type.LAND), new Spot(9,3, null, Spot.Type.TRAPYELLOW), new Spot(9,4, null, Spot.Type.BASEYELLOW), new Spot(9,5, null, Spot.Type.TRAPYELLOW), new Spot(9,6, null, Spot.Type.LAND), new Spot(9,7, new Lion(Piece.Color.YELLOW), Spot.Type.LAND)},
    };

    private Spot[][] Spots;
    private List<Piece> capturedPieces;
    private ArrayList<String> pgn;

    public Board() {
        this.Spots = new Spot[ROW_SIZE][COLUMN_SIZE];
        reinitialize();
        capturedPieces = new ArrayList<>();
    }
    public Board(Board board){
        this.Spots = new Spot[9][7];
        this.pgn = new ArrayList<String>();
        for(String s: board.getPgn()){
            this.pgn.add(s);
        }
        reinitialize(board.getSpots());
        capturedPieces = new ArrayList<>();
        // System.out.print("NEW BOARD PGN: ");
        // printPgn();
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
        Move newMove = new Move(getSpot(move.getStart()),getSpot(move.getEnd()));
        // System.out.println(newMove);
        Piece piece = newMove.getStart().getPiece();
        if(piece != null){
            if(piece.isValidMove(newMove, this)){
                if(newMove.isCapture()){
                    capturedPieces.add(getSpot(move.getEnd()).getPiece());
                }
                this.pgn.add(newMove.toString());
                // System.out.println("NEW PGN ADDED: " + newMove.toString());
                setPieceAtSpot(newMove.getEnd(), piece);
                setPieceAtSpot(newMove.getStart(), null);
                return true;
            } else{
                System.out.println("INVALIDT MOVE: " + move.toString() + move.getStart().getPiece());
            }
        } else{
            // System.out.println("INVALID MOVE: " + move.toString());
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
        return this.Spots[spot.getX()][spot.getY()];
    }

    public void setPieceAtSpot(Spot spot, Piece piece){
        this.Spots[spot.getX()][spot.getY()].setPiece(piece);
    }
    public Spot[][] getSpots() {
        return this.Spots;
    }
    public ArrayList<String> getPgn() {
        return pgn;
    }
    public void reinitialize(){
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; j < COLUMN_SIZE; j++){
                Spots[i][j] = new Spot(INITIAL_SPOTS[i][j]);
            }
        }
        pgn = new ArrayList<String>();
    }
    public void reinitialize(Spot[][] Spots){
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; j < COLUMN_SIZE; j++){
                this.Spots[i][j] = new Spot(Spots[i][j]);
            }
        }
        capturedPieces = new ArrayList<Piece>();
    }
    public ArrayList<Move> pgnToMoves(ArrayList<String> pgn){
        ArrayList<Move> moves = new ArrayList<Move>();
        Piece.Color pieceColor;
        Piece piece;
        for(int i = 0; i<pgn.size(); i++){
            Move move = new Move(new Spot(Spots[Character.getNumericValue(pgn.get(i).charAt(2))][Character.getNumericValue(pgn.get(i).charAt((3)))]),  new Spot(Spots[Character.getNumericValue(pgn.get(i).charAt(5))][Character.getNumericValue(pgn.get(i).charAt((6)))]));
            if(i%2==0){
                pieceColor = Piece.Color.YELLOW;
            } else{
                pieceColor = Piece.Color.RED;
            }
            piece = new Elephant(pieceColor);
            switch(pgn.get(i).charAt(0)){
                case 'R':
                    piece = new Rat(pieceColor);
                    break;
                case 'C': 
                    piece = new Cat(pieceColor);
                    break;
                case 'D':
                    piece = new Dog(pieceColor);
                    break;
                case 'W': 
                    piece = new Wolf(pieceColor);
                    break;
                case 'P':
                    piece = new Leopard(pieceColor);
                    break;
                case 'T': 
                    piece = new Tiger(pieceColor);
                    break;
                case 'L':
                    piece = new Lion(pieceColor);
                    break;
                case 'E': 
                    piece = new Elephant(pieceColor);
                    break;
        }
            move.getStart().setPiece(piece);
            move.getEnd().setPiece(null);
            moves.add(move);
        }
        return moves;
    }
    public void reducePgn(){
        printPgn();
        
        if(pgn.size()-1>=0){
            pgn.remove(pgn.size()-1);
        }

    }
    public void printPgn(){
        System.out.print("Pgn contents: ");
        for(String pgn: pgn){
            System.out.print(pgn+'\t');
        }
    }
    public List<Move> getAllAvailableMoves(){
        List<Move> allAvailableMoves = new ArrayList<Move>();
        for(Spot[] spots: Spots){
            for(Spot spot: spots){
                for(Move move: spot.availableMoves(this)){
                    allAvailableMoves.add(move);
                }
            }
        }
        Collections.sort(allAvailableMoves, new MoveComparator());
        return allAvailableMoves;
    }
    public boolean isCaptureAvailable(){
        for(Move m: getAllAvailableMoves()){
            if(m.getEnd().getPiece()!=null) return true;
        }
        return false;
    }
    public Board clone(){
        return new Board(this);
    }
    public boolean isGameOver(){
        if(Spots[0][3].getPiece() != null || Spots[8][3].getPiece() !=null){
            return true;
        }
        return false;
    }
    public ArrayList<Piece> getAllPieces(){
        ArrayList<Piece> allPieces = new ArrayList<Piece>();
        for(Spot[] spots: Spots){
            for(Spot spot: spots){
                if(spot.getPiece()!=null){
                    allPieces.add(spot.getPiece());
                }
            }
        }
        return allPieces;
    }
    public Move convertMove(Move other){
        return new Move(Spots[other.getStart().getX()][other.getStart().getY()],Spots[other.getEnd().getX()][other.getEnd().getY()]);
    }

    public List<Piece> getCapturedPieces(){
        return capturedPieces;
    }
}