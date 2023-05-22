import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIAlgorithm {
    private int currentDepth;
    private long startTime;
    private long maxRunTime;
    private int maxDepth;
    private TranspositionTable trans;
    private int numEvaluations;
    private int numLookups;
    private ArrayList<int[]> visitStates;
    private int[] transKey;

    public AIAlgorithm(long maxRunTime, int maxDepth) {
        this.maxRunTime = maxRunTime;
        this.maxDepth = maxDepth;
        visitStates = new ArrayList<>();
        trans = new TranspositionTable(100000);
    }
    
    public Move getBestMove(Board board) {
        if(board.isGameOver()) return null;
        currentDepth = 0;
        numLookups = 0;
        startTime = System.currentTimeMillis();
        double bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        numEvaluations = 0;
        List<Move> allAvailableMoves = board.getAllAvailableMoves();
        while((!isTerminate()&&currentDepth<maxDepth)){
            currentDepth++;
            List<Move> newMoveList = new ArrayList<Move>();
            System.out.println("The AI IS SEARCHING AT A DEPTH OF " + currentDepth + ", number of available moves: " + allAvailableMoves.size() + ", elapsed time: " + (System.currentTimeMillis()-startTime));
            for (Move move : allAvailableMoves) {
                if(isTerminate())break;
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    transKey = trans.createKey(board, false);
                    newBoard.movePiece(move);
                    double score = minimax(newBoard, currentDepth, transKey, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    move.setValue(score);
                    newMoveList.add(move);
                    if(currentDepth>maxDepth-4){
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
            trans.store(transKey, bestScore);
            Collections.sort(newMoveList, (a, b)->Double.compare(b.getValue(), a.getValue()));
            allAvailableMoves = newMoveList;
        }
        if(bestMove == null) bestMove = allAvailableMoves.get(0);
        System.out.println("Number of transpositions: " + trans.getSize());
        System.out.println("Number of evaluations: " + numEvaluations);
        System.out.println("Number of lookups: " + numLookups);
        visitStates.add(transKey);
        if(visitStates.size()>3) visitStates.remove(0);
        return bestMove;
    }

    private double minimax(Board board, int depth, int[] transKey, double alpha, double beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver() || isTerminate()) {
            return evaluateBoard(board, isMaximizingPlayer);
        }
        if(trans.lookupexists(transKey) && depth<2){
            numLookups++;
            return trans.lookup(transKey);
        }

        if (isMaximizingPlayer) {
            double maxScore = Integer.MIN_VALUE;
            for (Move move : board.getAllAvailableMoves()) {
                
                if (move.getStart().getPiece().getColor() == Piece.Color.RED) {
                    Board newBoard = new Board(board);
                    double score;
                    if(move.getEnd().getPiece()!=null || move.getEnd().getSpotType() == Spot.Type.BASEYELLOW || move.getEnd().getSpotType() == Spot.Type.TRAPYELLOW){
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        newBoard.movePiece(move);
                        score = minimax(newBoard, depth, transKey, alpha, beta, !isMaximizingPlayer);
                    } else{
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        newBoard.movePiece(move);
                        score = minimax(newBoard, depth-1, transKey, alpha, beta, !isMaximizingPlayer);

                    }
                    maxScore = Math.max(maxScore, score);
                    alpha = Math.max(alpha, score);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            if(currentDepth>maxDepth-5) trans.store(transKey, maxScore);

            return maxScore;
        } else {
            double minScore = Integer.MAX_VALUE;
            for (Move move : board.getAllAvailableMoves()) {
                if (move.getStart().getPiece().getColor() == Piece.Color.BLUE) {
                    Board newBoard = new Board(board);
                    double score;
                    if(move.getEnd().getPiece()!=null || move.getEnd().getSpotType()==Spot.Type.BASERED || move.getEnd().getSpotType() == Spot.Type.BASERED){
                        newBoard.movePiece(move);
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        score = minimax(newBoard, depth, transKey, alpha, beta, !isMaximizingPlayer);
                    } else{
                        newBoard.movePiece(move);
                        transKey = trans.incrementKey(transKey, move, currentDepth);
                        score = minimax(newBoard, depth-1, transKey, alpha, beta, !isMaximizingPlayer);

                    }
                    minScore = Math.min(minScore, score);
                    beta = Math.min(beta, score);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            if(currentDepth>maxDepth-5) trans.store(transKey, minScore);

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
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPRED && spot.getPiece().getValue()<5) score+=1;
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPYELLOW) score+=2;
                            if(move.getEnd().getSpotType() == Spot.Type.BASEYELLOW) score+=3;
                        }
                        if(spot.getX()<8){
                            if(spot.getX()>2 || spot.getPiece().getValue()>4){
                                score += (spot.getX())*(spot.getPiece().getValue())*0.1;
                            }
                        }
                        if(spot.getPiece().getAnimalPower() == 7 || spot.getPiece().getAnimalPower() == 6){
                            score += spot.getX();
                        }
                        score += (3 - Math.abs(3-spot.getY()))*spot.getPiece().getValue()*0.08;
                    } else {
                        score -= spot.getPiece().getValue();
                        for(Move move: spot.availableMoves(board)){
                            if(move.getEnd().getPiece()!=null){
                                if(!isMaximizingPlayer){
                                    score-=move.getEnd().getPiece().getValue()*0.9;
                                }else{
                                    score-=move.getEnd().getPiece().getValue()*0.5;
                                }
                            }
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPYELLOW) score-=1;
                            if(move.getEnd().getSpotType() == Spot.Type.TRAPRED) score-=2;
                            if(move.getEnd().getSpotType() == Spot.Type.BASERED) score-=3;
                            
                        }
                        if(spot.getX()>0){
                            if(spot.getX()<6 || spot.getPiece().getValue()>4){
                                score -= (8-spot.getX())*(spot.getPiece().getValue())*0.1;
                            }
                        } 
                        if(spot.getPiece().getAnimalPower() == 7 || spot.getPiece().getAnimalPower() == 6){
                            score -= (8-spot.getX());
                        }
                        score -= (3 - Math.abs(3-spot.getY()))*spot.getPiece().getValue()*0.08;

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
            return true;
        }
        return false;
    }
}