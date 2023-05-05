public class Move {
    private Player player;
    private Spot start;
    private Spot end;

    public Move(Player player, Spot start, Spot end){
        this.player = player;
        this.start = start;
        this.end = end;
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
