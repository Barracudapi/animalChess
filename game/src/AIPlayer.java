public class AIPlayer {
    private String name;
    private Piece.Color color;
    private AIAlgorithm aiAlgorithm;

    public AIPlayer(String name, Piece.Color color, int maxDepth) {
        this.name = name;
        this.color = color;
        this.aiAlgorithm = new AIAlgorithm(maxDepth);
    }

    public String getName() {
        return name;
    }

    public Piece.Color getColor() {
        return color;
    }

    public Move getNextMove(Board board) {
        return aiAlgorithm.getBestMove(board);
    }
}