import java.util.LinkedHashMap;
import java.util.Map;

public class TranspositionTable {
    private final int tableSize;
    private Map<int[], Double> table;


    public TranspositionTable(int tableSize) {
        this.tableSize = tableSize;
        this.table = new LinkedHashMap<int[], Double>(tableSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<int[], Double> eldest) {
                return size() > TranspositionTable.this.tableSize;
            }
        };
    }

    public void store(int[] key, double score) {
        table.put(key, score);
    }

    public double lookup(int[] key) {
        return table.get(key);
    }
    public boolean lookupexists(int[] key){
        return table.containsKey(key);
    }
    public int[] createKey(Board board, boolean isPlayer1){
        int[] key = new int[65];
        key[0] = 1; //1 for first player turn
        if(!isPlayer1) key[0] = 2; //2 for second player turn
        for(int i = 0; i<9; i++){
            for(int j = 0; j<7; j++){
                if(board.getSpots()[i][j].getPiece()!=null){
                    if(board.getSpots()[i][j].getPiece().getColor()==Piece.Color.RED){
                        key[i*7+j+1] = board.getSpots()[i][j].getPiece().getAnimalPower();
                    } else{
                        key[i*7+j+1] = -board.getSpots()[i][j].getPiece().getAnimalPower();
                    }
                }
            }
        }
        return key; 
    }
    public int[] incrementKey(int[] key, Move move, int depth){
        if(key[0] == 1) {
            key[0] = 2;
        } else{
            key[0] = 1;
        }
        key[move.getStart().getX()*7+move.getStart().getY()+1] = 0;
        key[move.getEnd().getX()*7+move.getEnd().getY()+1] = move.getStart().getPiece().getAnimalPower();
        return key;
    }
    public long getSize(){
        return table.size();
    }
    public void printTable(int[] key){
        for(int i: key){
            System.out.print(i+" ");
        }
        System.out.print("\n");
    }
    public void clearTable(){
        table.clear();
    }
}

