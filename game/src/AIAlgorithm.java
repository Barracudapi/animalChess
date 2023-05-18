import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIAlgorithm {
    private int currentDepth;
    private long startTime;
    private long maxRunTime;

    public AIAlgorithm(long maxRunTime) {
        this.maxRunTime = maxRunTime;
    }
    
    public Move getBestMove(Board board) {
        currentDepth = 0;
        startTime = System.currentTimeMillis();
        float bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        List<Move> allAvailableMoves = board.getAllAvailableMoves();
        // System.out.println(allAvailableMoves);
        while(!isTerminate()){
            currentDepth++;
            List<Move> newMoveList = new ArrayList<Move>();
            System.out.println("The AI IS SEARCHING AT A DEPTH OF " + currentDepth + ", number of available moves: " + allAvailableMoves.size() + ", elapsed time: " + (System.currentTimeMillis()-startTime));
            for (Move move : allAvailableMoves) {
                // System.out.println(move.toString());
                if(isTerminate())break;
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    float score = minimax(newBoard, currentDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    newMoveList.add(move);
                    if (score > bestScore || (Math.random()>0.9 && score == bestScore)) {
                        System.out.println("The AI IS SEARCHING AT A DEPTH OF " + currentDepth + ", number of available moves: " + allAvailableMoves.size() + ", elapsed time: " + (System.currentTimeMillis()-startTime));
                        System.out.println("THE AI IS HIGHLY CONSIDERING " + move.toString() + "because score = " + score);
                        bestMove = move;
                        bestScore = score;
                    }
                }
            }
            Collections.sort(newMoveList, (a, b)->Double.compare(b.getValue(), a.getValue()));
            allAvailableMoves = newMoveList;
        }

        return bestMove;
    }

    private float minimax(Board board, int depth, float alpha, float beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            if(board.isCaptureAvailable()) return minimaxCapture(board, 3, alpha, beta, isMaximizingPlayer);
            return evaluateBoard(board);
        }

        if (isMaximizingPlayer) {
            float maxScore = Integer.MIN_VALUE;
            for (Move move : board.getAllAvailableMoves()) {
                
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    // if(newBoard.isCaptureAvailable()) depth++;
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
            List<Move> allAvailableMoves= board.getAllAvailableMoves();
            Collections.sort(allAvailableMoves, new MoveComparator());
            for (Move move : allAvailableMoves) {
                if (move.getStart().getPiece().getColor() == Piece.Color.YELLOW) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    // if(newBoard.isCaptureAvailable()) depth++;
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
    private float minimaxCapture(Board board, int depth, float alpha, float beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board);
        }

        if (isMaximizingPlayer) {
            float maxScore = Integer.MIN_VALUE;
            for (Move move : board.getAllAvailableMoves()) {
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    // if(newBoard.isCaptureAvailable()) depth++;
                    float score = minimaxCapture(newBoard, depth - 1, alpha, beta, false);
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
            List<Move> allAvailableMoves= board.getAllAvailableMoves();
            Collections.sort(allAvailableMoves, new MoveComparator());
            for (Move move : allAvailableMoves) {
                if (move.getStart().getPiece().getColor() == Piece.Color.YELLOW) {
                    Board newBoard = new Board(board);
                    newBoard.movePiece(move);
                    // if(newBoard.isCaptureAvailable()) depth++;
                    float score = minimaxCapture(newBoard, depth - 1, alpha, beta, true);
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

    public int evaluateBoard(Board board) {
        int score = 0;
        for (Spot[] spots: board.getSpots()) {
            for(Spot spot: spots){
                if(spot.getPiece()!=null){
                    if (spot.getPiece().getColor() == Piece.Color.RED) {
                        score += spot.getPiece().getValue();
                        // score += spot.availableMoves(board).size()*spot.getPiece().getValue()*0.05;
                        if(spot.getX()<8){
                            if(spot.getX()<2 || spot.getPiece().getValue()>5){
                                score += (spot.getX())*(spot.getPiece().getValue())*0.1;
                            }
                        } 
                    } else {
                        score -= spot.getPiece().getValue();
                        // score -= spot.availableMoves(board).size()*spot.getPiece().getValue()*0.05;
                        if(spot.getX()>0){
                            if(spot.getX()<6 || spot.getPiece().getValue()>5){
                                score -= (8-spot.getX())*(spot.getPiece().getValue())*0.1;
                            }
                        } 

                    }
                }
            }
        }
        if(board.getSpots()[0][3].getPiece()!=null) score = Integer.MIN_VALUE;
        if(board.getSpots()[8][3].getPiece()!=null) score = Integer.MAX_VALUE;
        

        return score;
    }
    public boolean isTerminate(){

        if(System.currentTimeMillis()-startTime>maxRunTime){
            System.out.println("ELAPSED TIME: " + (System.currentTimeMillis()-startTime));
            return true;
        }
        return false;
    }
}

