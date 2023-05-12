import java.util.ArrayList;

public class Move {
    private Player player;
    private Spot start;
    private Spot end;

    public Move(Player player, Spot start, Spot end){
        this.player = player;
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString(){
        char c = '_';
        switch(start.getPiece().getName()){
            case "RAT":
                c = 'R';
                break;
            case "CAT": 
                c = 'C';
                break;
            case "DOG":
                c = 'D';
                break;
            case "WOLF": 
                c = 'W';
                break;
            case "LEOPARD":
                c = 'P';
                break;
            case "TIGER": 
                c = 'T';
                break;
            case "LION":
                c = 'L';
                break;
            case "ELEPHANT": 
                c = 'E';
                break;

        }
        return String.format("%s%d%d ", c, end.getX(), end.getY());
    }

    public boolean isCapture(){
        if(end.getPiece() == null){
            return false;
        }
        return true;
    }



    public Player getPlayer() {
        return player;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }
}
