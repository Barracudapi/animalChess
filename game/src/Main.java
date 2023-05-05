public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Player player1 = new Player("P1", Piece.Color.RED);
        Player player2 = new Player("P2", Piece.Color.YELLOW);
        b.printBoard();


    }
}