package student_player;


import java.util.List;

import boardgame.Move;
import tablut.TablutBoardState;
import tablut.TablutPlayer;
import tablut.TablutMove;

/** A player file submitted by a student. */
public class StudentPlayer extends TablutPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
	
	public static final int SWEDE = 1;
	public static final int MUSCOVITE = 0;
	
    public StudentPlayer() {
        super("xxxxxxxxx");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(TablutBoardState boardState) {
        
        // Start by getting all possible moves
        List<TablutMove> options = boardState.getAllLegalMoves();
        double minUtility = 1000;
        double maxUtility = -1000;
        double currentUtility;
        //Initial Value
        TablutMove bestMove = (TablutMove) boardState.getRandomMove();
        
        if(boardState.getTurnPlayer()==SWEDE) {
            for(TablutMove move: options) {
            	// To evaluate a move, clone the boardState so that we can do modifications on
                // it.
                TablutBoardState cloneBS = (TablutBoardState) boardState.clone();

                //Process that move, as if we actually made it happen.
                cloneBS.processMove(move);
                currentUtility = MyTools.miniMax(cloneBS, 0);

            	if(currentUtility<minUtility) {
            		minUtility = currentUtility;
            		bestMove = move;
            	}
            }
        }
        
        if(boardState.getTurnPlayer()==MUSCOVITE) {
            for(TablutMove move: options) {
            	// To evaluate a move, clone the boardState so that we can do modifications on
                // it.
                TablutBoardState cloneBS = (TablutBoardState) boardState.clone();

                // Process that move, as if we actually made it happen.
                cloneBS.processMove(move);
                currentUtility = MyTools.miniMax(cloneBS, 0);

            	if(currentUtility>maxUtility) {
            		maxUtility = currentUtility;
            		bestMove = move;
            	}
            }
        }
        

        // Return your move to be processed by the server.
        return bestMove;
    }
}