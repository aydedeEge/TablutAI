package student_player;

import boardgame.*;
import tablut.TablutMove;

public class BestUtilityMove {

	public TablutMove bestMove;
	public double bestUtility = 0;
	
	public BestUtilityMove(TablutMove bestMove, double bestUtility) {
		this.bestMove = bestMove;
		this.bestUtility = bestUtility;
	}
}
