public class AIPlayer extends Player {
    private String name;
    private Piece.Color color;
    private AIAlgorithm aiAlgorithm;

    public AIPlayer(String name, Piece.Color color, int maxRunTime, int maxDepth) {
        super(name, color);
        System.out.println(name+maxDepth);
        this.aiAlgorithm = new AIAlgorithm(maxRunTime, maxDepth);
    }
    public Move getMove(Board board) {
        System.out.println("THE GAME EVALUATION IS " + aiAlgorithm.evaluateBoard(board, true));
        return aiAlgorithm.getBestMove(board);
    }
    
}