public class AIAlgorithm {
    private int maxDepth;

    public AIAlgorithm(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Move getBestMove(Board board) {
        float bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (Move move : board.getAllAvailableMoves()) {
            if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                Board newBoard = new Board(board);
                newBoard.movePiece(move);
                float score = minimax(newBoard, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                if (score > bestScore || (Math.random()>0.9 && score == bestScore)) {
                    System.out.println("THE AI IS HIGHLY CONSIDERING " + move.toString() + "because score = " + score);
                    bestMove = move;
                    bestScore = score;
                }
                if(score == bestScore){
                    
                }
            }
        }

        return bestMove;
    }

    private float minimax(Board board, int depth, float alpha, float beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board);
        }

        if (isMaximizingPlayer) {
            float maxScore = Integer.MIN_VALUE;

            for (Move move : board.getAllAvailableMoves()) {
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    float score = minimax(newBoard, depth - 1, alpha, beta, false);
                    maxScore = Math.max(maxScore, score);
                    alpha = Math.max(alpha, score);
                    if (beta <= alpha) {
                        break; // Beta cutoff
                    }
                }
            }

            return maxScore;
        } else {
            float minScore = Integer.MAX_VALUE;

            for (Move move : board.getAllAvailableMoves()) {
                if (move.getStart().getPiece().getColor() == Piece.Color.YELLOW) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    float score = minimax(newBoard, depth - 1, alpha, beta, true);
                    minScore = Math.min(minScore, score);
                    beta = Math.min(beta, score);
                    if (beta <= alpha) {
                        break; // Alpha cutoff
                    }
                }
            }

            return minScore;
        }
    }

    private int evaluateBoard(Board board) {
        int score = 0;
        for (Spot[] spots: board.getSpots()) {
            for(Spot spot: spots){
                if(spot.getPiece()!=null){
                    if (spot.getPiece().getColor() == Piece.Color.RED) {
                        score += spot.getPiece().getValue();
                        score += spot.availableMoves(board).size()*spot.getPiece().getValue()*0.05;
                        score += spot.getX()*(spot.getPiece().getValue()*3)*0.1;
                    } else {
                        score -= spot.getPiece().getValue();
                        score -= spot.availableMoves(board).size()*spot.getPiece().getValue()*0.05;
                        score -= (9-spot.getX())*(spot.getPiece().getValue()*3)*0.1;

                    }
                }
            }
        }
        if(board.getSpots()[0][3].getPiece()!=null) score = Integer.MIN_VALUE;
        if(board.getSpots()[8][3].getPiece()!=null) score = Integer.MAX_VALUE;
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

