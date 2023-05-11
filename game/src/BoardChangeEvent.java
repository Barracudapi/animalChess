
public class BoardChangeEvent {
    private int row;
    private int col;
    public BoardChangeEvent(int row, int col){
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
