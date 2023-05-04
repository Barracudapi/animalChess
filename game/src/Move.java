public class Move {
    private Player player;
    private Spot start;
    private Spot end;

    public Move(Player player, Spot start, Spot end){
        this.player = player;
        this.start = start;
        this.end = end;
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

    public Spot getStartPosition(){
        return null;
    }

    public Spot getEndPosition() {
        return null;
    }
}
