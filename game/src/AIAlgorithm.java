public class AIAlgorithm {
    private int maxDepth;

    public AIAlgorithm(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Move getBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (Move move : board.getAllAvailableMoves()) {
            Board newBoard = board.clone();
            newBoard.movePiece(move);
            int score = minimax(newBoard, maxDepth, false);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board);
        }

        if (isMaximizingPlayer) {
            int maxScore = Integer.MIN_VALUE;

            for (Move move : board.getAllAvailableMoves()) {
                Board newBoard = board.clone();
                newBoard.movePiece(move);
                int score = minimax(newBoard, depth - 1, false);
                maxScore = Math.max(maxScore, score);
            }

            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;

            for (Move move : board.getAllAvailableMoves()) {
                Board newBoard = board.clone();
                newBoard.movePiece(move);
                int score = minimax(newBoard, depth - 1, true);
                minScore = Math.min(minScore, score);
            }

            return minScore;
        }
    }

    private int evaluateBoard(Board board) {
        int score = 0;
        for (Piece piece : board.getAllPieces()) {
            if (piece.getColor() == Piece.Color.RED) {
                score += piece.getAnimalPower();
            } else {
                score -= piece.getAnimalPower();
            }
        }

        // rat 5??
        // cat 1?
        // dog 2?
        // wolf 3?
        // leopard 4?
        // tiger 7?
        // lion 9?
        // elephant 9?
        return score;
    }
}
