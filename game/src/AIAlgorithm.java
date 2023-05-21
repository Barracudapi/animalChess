import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIAlgorithm {
    private int currentDepth;
    private long startTime;
    private long maxRunTime;
    private TranspositionTable trans;
    private int numEvaluations;
    private int numLookups;
    private ArrayList<int[]> visitStates;
    private int[] transKey;

    public AIAlgorithm(long maxRunTime) {
        this.maxRunTime = maxRunTime;
        visitStates = new ArrayList<>();
        trans = new TranspositionTable(100000);
    }
    
    public Move getBestMove(Board board) {
        if(board.isGameOver()) return null;
        currentDepth = 0;
        int maxDepth = 8;
        numLookups = 0;
        startTime = System.currentTimeMillis();
        double bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        numEvaluations = 0;
        List<Move> allAvailableMoves = board.getAllAvailableMoves();
        while((!isTerminate()&&currentDepth<maxDepth)||bestMove==null){
            currentDepth++;
            List<Move> newMoveList = new ArrayList<Move>();
            System.out.println("The AI IS SEARCHING AT A DEPTH OF " + currentDepth + ", number of available moves: " + allAvailableMoves.size() + ", elapsed time: " + (System.currentTimeMillis()-startTime));
            for (Move move : allAvailableMoves) {
                // System.out.println(move.toString());
                if(isTerminate())break;
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    transKey = trans.createKey(board, false);
                    newBoard.movePiece(move);
                    double score = minimax(newBoard, currentDepth, transKey, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    trans.store(transKey, score);
                    move.setValue(score);
                    newMoveList.add(move);
                    if(currentDepth>5){
                        if (score > bestScore) {
                            if(!visitStates.contains(transKey)){
                                System.out.println("The AI IS SEARCHING AT A DEPTH OF " + currentDepth + ", number of available moves: " + allAvailableMoves.size() + ", elapsed time: " + (System.currentTimeMillis()-startTime));
                                System.out.println("THE AI IS HIGHLY CONSIDERING " + move.toString() + "because score = " + score);
                                bestMove = move;
                                bestScore = score;
                            }
                        }
                    }
                }
            }
            Collections.sort(newMoveList, (a, b)->Double.compare(b.getValue(), a.getValue()));
            allAvailableMoves = newMoveList;
        }
        System.out.println("Number of transpositions: " + trans.getSize());
        System.out.println("Number of evaluations: " + numEvaluations);
        System.out.println("Number of lookups: " + numLookups);
        visitStates.add(transKey);
        if(visitStates.size()>3) visitStates.remove(0);
        return bestMove;
    }

    private double minimax(Board board, int depth, int[] transKey, double alpha, double beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver() || isTerminate()) {
            // if(board.isCaptureAvailable()) return minimaxCapture(board, 3, alpha, beta, isMaximizingPlayer);
            if(trans.lookupexists(transKey)){
                numLookups++;
                return trans.lookup(transKey);
            }
            return evaluateBoard(board, isMaximizingPlayer);
        }

        if (isMaximizingPlayer) {
            double maxScore = Integer.MIN_VALUE;
            for (Move move : board.getAllAvailableMoves()) {
                
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    double score;
                    if(move.getEnd().getPiece()!=null){
                        newBoard.movePiece(move);
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        score = minimax(newBoard, depth, transKey, alpha, beta, false);
                    } else{
                        newBoard.movePiece(move);
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        score = minimax(newBoard, depth-1, transKey, alpha, beta, false);

                    }
                    if(currentDepth>4) trans.store(transKey, score);
                    maxScore = Math.max(maxScore, score);
                    alpha = Math.max(alpha, score);
                    if (beta <= alpha) {
                        break; // Beta cutoff
                    }
                }
            }

            return maxScore;
        } else {
            double minScore = Integer.MAX_VALUE;
            for (Move move : board.getAllAvailableMoves()) {
                if (move.getStart().getPiece().getColor() == Piece.Color.YELLOW) {
                    Board newBoard = new Board(board);
                    double score;
                    if(move.getEnd().getPiece()!=null){
                        newBoard.movePiece(move);
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        score = minimax(newBoard, depth, transKey, alpha, beta, true);
                    } else{
                        newBoard.movePiece(move);
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        score = minimax(newBoard, depth-1, transKey, alpha, beta, true);

                    }
                    if(currentDepth>4) trans.store(transKey, score);
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

    public double evaluateBoard(Board board, boolean isMaximizingPlayer) {
        double score = 0;
        numEvaluations++;
        for (Spot[] spots: board.getSpots()) {
            for(Spot spot: spots){
                if(spot.getPiece()!=null){
                    if (spot.getPiece().getColor() == Piece.Color.RED) {
                        score += spot.getPiece().getValue();
                        for(Move move: spot.availableMoves(board)){
                            if(move.getEnd().getPiece()!=null){
                                if(isMaximizingPlayer){
                                    score+=move.getEnd().getPiece().getValue()*0.9;
                                }else{
                                    score+=move.getEnd().getPiece().getValue()*0.5;
                                }
                            }
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPRED && spot.getPiece().getValue()<5) score+=0.5;
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPYELLOW) score+=1;
                            if(move.getEnd().getSpotType() == Spot.Type.BASEYELLOW) score+=2;
                        }
                        if(spot.getX()<8){
                            if(spot.getX()<2 || spot.getPiece().getValue()>4){
                                score += (spot.getX())*(spot.getPiece().getValue())*0.1;
                            }
                        }
                        score += (3 - Math.abs(3-spot.getY()))*spot.getPiece().getValue()*0.02;
                    } else {
                        score -= spot.getPiece().getValue();
                        for(Move move: spot.availableMoves(board)){
                            if(move.getEnd().getPiece()!=null){
                                if(!isMaximizingPlayer){
                                    score-=move.getEnd().getPiece().getValue()*2.9;
                                }else{
                                    score-=move.getEnd().getPiece().getValue()*0.5;
                                }
                            }
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPYELLOW) score-=0.5;
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPRED) score-=1;
                            if(move.getEnd().getSpotType() == Spot.Type.BASERED) score-=2;
                            
                        }
                        if(spot.getX()>0){
                            if(spot.getX()<6 || spot.getPiece().getValue()>4){
                                score -= (8-spot.getX())*(spot.getPiece().getValue())*0.1;
                            }
                        } 
                        score -= (3 - Math.abs(3-spot.getY()))*spot.getPiece().getValue()*0.02;

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
            // System.out.println("ELAPSED TIME: " + (System.currentTimeMillis()-startTime));
            return true;
        }
        return false;
    }
}

