package student_player;

import tablut.TablutBoardState;

import tablut.TablutMove;

import java.util.List;
import java.util.HashSet;

import coordinates.Coord;
import coordinates.Coordinates;

public class MyTools {
	
	public static final int SWEDE = 1;
	public static final int MUSCOVITE = 0;
	public static final double OPP1DISTPENALTY = 0.5;
	public static final double OPP2DISTPENALTY = 0;
	public static final double OPP3DISTPENALTY = 0;
	public static final double ADJACENTKINGPENALTY = 5;
	public static final int MAX_DEPTH = 2;
	
    public static double getSomething() {
        return Math.random();
    }
    
    /*Used to get the utility value for Muscovites of a certain TablutBoardState
     * Can be used before and after to compare utility values*/
    
    public static double getUtility(TablutBoardState boardstate) {
    	//Things that affect utility or Muscovites(Black): lower utility = better
    	//- Number of white pieces total (negatively): each piece worth 2 points, king worth 50
    	//- Number of black pieces total (positively): each piece worth 2 points
    	//- Closeness of king to corner (negatively): starts at 8, closer->higher
    	//- Closeness of king to black pieces (positively): start at 0, closer->lower
    	double totalUtility = 0;
    	int swedeCount = 0;
    	int muscoviteCount = 0;
    	int kingElim = 1000;
    	int kingSafe = -1000;
    	int kingCorner = 0;
    	Coord kingsCoord= boardstate.getKingPosition();
    	int kingInSightOfCorner = 0;
    	int adjacentBlackPieces = 0;
    	
    	
    	/*Get number of white pieces*/
    	/*Start of game: 8+king*/
    	swedeCount = boardstate.getNumberPlayerPieces(SWEDE);
    	totalUtility -= swedeCount;
    	
    	
    	/*Get number of black pieces total*/
    	/*Start of game: 16*/
    	muscoviteCount = boardstate.getNumberPlayerPieces(MUSCOVITE);
    	totalUtility += muscoviteCount;
    	
    	
    	/*Check if MUSCOVITES win: king is captured*/
    	if(boardstate.getWinner()==MUSCOVITE) {
    		totalUtility += kingElim;
    	}else if(boardstate.getWinner()==SWEDE){
    		totalUtility -= kingElim;
    	}
    	
    	
//    	/*Check if SWEDES win: king is on a corner*/
//    	if(Coordinates.isCorner(boardstate.getKingPosition())) {
//    		totalUtility -= kingSafe;
//    	}
//    	
//    	
//    	/*Get distance to closest corner for king*/
//    	kingCorner = Coordinates.distanceToClosestCorner(kingsCoord);
//    	totalUtility += kingCorner;
    	
    	
    	/*Check for check condition*/
    	
    	
//    	/*Get proximity of king to corners*/
//    	/*Start of game: 4Horizontal + 4Vertical*/
//    	kingsCoordX = kingsCoord.x;
//    	kingsCoordY = kingsCoord.y;
//    	
//    	//Corner 0,0
//    	int distKingCorner1 = (kingsCoord.x - 0) + (kingsCoord.y - 0);
//    	
//    	//Corner 0,8
//    	int distKingCorner2 = (kingsCoord.x - 0) + (8 - kingsCoord.y);
//    	
//    	//Corner 8,0
//    	int distKingsCorner3 = (8-kingsCoord.x) + (kingsCoord.y - 0);
//    	
//    	//Corner 8,8
//    	int distKingsCorner4 = (8 - kingsCoord.x) + (8 - kingsCoord.y);
//    	
//    	//Smallest dist to corner
//    	kingCorner = Math.min(distKingCorner1, Math.min(distKingCorner2, Math.min(distKingsCorner3, distKingsCorner4)));
//    	totalUtility += kingCorner;
//    	
//    	
//    	/*Get proximity of black pieces to king*/
//    	HashSet<Coord> opponentPieces = boardstate.getPlayerPieceCoordinates();
//    	for(Coord piece: opponentPieces) {
//    		//Opponent piece one tile away from king piece
//    		if(kingsCoordX-1<=piece.x&&piece.x<=kingsCoordX+1 && kingsCoordY-1<=piece.y&&piece.y<=kingsCoordY+1) {
//    			kingSafety += OPP1DISTPENALTY;
//    		}
//    		//Opponent piece two tiles away from king piece
//    		else if(kingsCoordX-2<=piece.x&&piece.x<=kingsCoordX+2 && kingsCoordY-2<=piece.y&&piece.y<=kingsCoordY+2) {
//    			kingSafety += OPP2DISTPENALTY;
//    		}
//    		else if(kingsCoordX-3<=piece.x&&piece.x<=kingsCoordX+3 && kingsCoordY-3<=piece.y&&piece.y<=kingsCoordY+3) {
//    			kingSafety += OPP3DISTPENALTY;
//    		}
//    	}
//    	totalUtility += kingSafety;
//    	
//    	
//    	/*Check if the king is in same row of Muscovite*/
//    	double closestXNegative = -10;
//    	List<Coord> adjacentCoordinatesXNeg = kingsCoord.getCoordsBetween(Coordinates.get(kingsCoordX, 0));
//    	for(Coord coord: adjacentCoordinatesXNeg) {
//    		if(boardstate.isOpponentPieceAt(coord) && coord.x>closestXNegative) {
//    			closestXNegative = coord.x;
//    		}
//    	}
//    	System.out.println(closestXNegative);
//    	adjacentBlackPieces += (1/closestXNegative) * ADJACENTKINGPENALTY;
//    	
//    	double closestXPositive = 10;
//    	List<Coord> adjacentCoordinatesXPos = kingsCoord.getCoordsBetween(Coordinates.get(kingsCoordX, 8));
//    	for(Coord coord: adjacentCoordinatesXPos) {
//    		if(boardstate.isOpponentPieceAt(coord) && coord.x<closestXPositive) {
//    			closestXPositive = coord.x;
//    		}
//    	}
//    	System.out.println(closestXPositive);
//    	adjacentBlackPieces += (1/closestXPositive) * ADJACENTKINGPENALTY;
//    	
//    	double closestYNegative = -10;
//    	List<Coord> adjacentCoordinatesYNeg = kingsCoord.getCoordsBetween(Coordinates.get(0, kingsCoordY));
//    	for(Coord coord: adjacentCoordinatesYNeg) {
//    		if(boardstate.isOpponentPieceAt(coord) && coord.y>closestYNegative) {
//    			closestYNegative = coord.y;
//    		}
//    	}
//    	System.out.println(closestYNegative);
//    	adjacentBlackPieces += (1/closestYNegative) * ADJACENTKINGPENALTY;
//    	
//    	double closestYPositive = 10;
//    	List<Coord> adjacentCoordinatesYPos = kingsCoord.getCoordsBetween(Coordinates.get(8, kingsCoordY));
//    	for(Coord coord: adjacentCoordinatesYPos) {
//    		if(boardstate.isOpponentPieceAt(coord) && coord.y<closestYPositive) {
//    			closestYPositive = coord.y;
//    		}
//    	}
//    	System.out.println(closestYPositive);
//    	adjacentBlackPieces += (1/closestYPositive) * ADJACENTKINGPENALTY;
//    	
//    	totalUtility += adjacentBlackPieces;
//    	
    	return totalUtility;
    }
    
    public static boolean isCorner(Coord c, TablutBoardState bs) {
    	int x = c.x;
    	int y = c.y;
    	int BOARDSIZE = bs.BOARD_SIZE - 1;
    	if(x==0 && y==0) {
    		return true;
    	}else if(x==0 && y==BOARDSIZE) {
    		return true;
    	}else if(x==BOARDSIZE && y==0) {
    		return true;
    	}else if(x==BOARDSIZE && y==BOARDSIZE) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
    public static BestUtilityMove miniMax(TablutBoardState tbs, int level, TablutMove lastMove) {
    	
    	BestUtilityMove currentMoveAndUtility;
    	double currentUtility = getUtility(tbs);
    	
    	//Max depth of minimax
    	if(level==MAX_DEPTH) {
    		return new BestUtilityMove(lastMove, currentUtility);
    	//Check if gameover
    		/*CAN BE CACHED*/
    	}else if(tbs.getKingPosition()==null||Coordinates.isCorner(tbs.getKingPosition())) {
    		return new BestUtilityMove(lastMove, currentUtility);
    	}

    	List<TablutMove> options = tbs.getAllLegalMoves();
    	
    	//Muscovites turn: highest utility possible
    	if(tbs.getTurnPlayer()==MUSCOVITE) {
    
    		BestUtilityMove bestMoveAndUtility = new BestUtilityMove(null, -1000);
    		//Iterate through each possible move from this Board State
    		for(TablutMove move: options) {
    			
    			//Create cloned Board State to execute child move
    			TablutBoardState cloneBS = (TablutBoardState) tbs.clone();
    			
    			//Process step in cloned Board State
    			cloneBS.processMove(move);
    			
    			//Calculate utility of processed move
    			currentMoveAndUtility = miniMax(cloneBS, level+1, move);

    			//Return the MAXIMIZED utility for Muscovites
    			if(currentMoveAndUtility.bestUtility > bestMoveAndUtility.bestUtility) {
    				bestMoveAndUtility.bestUtility = currentMoveAndUtility.bestUtility;
    				bestMoveAndUtility.bestMove = move;
    			}
    		}
    		return bestMoveAndUtility;
    		
    	//Swedes turn: lowest utility possible
    	}else{
    		
    		BestUtilityMove bestMoveAndUtility = new BestUtilityMove(null, 1000);
    		//Iterate through each possible move from this Board State
    		for(TablutMove move: options) {
    			
    			//Create cloned Board State to execute child move
    			TablutBoardState cloneBS = (TablutBoardState) tbs.clone();
    			
    			//Process step in cloned Board State
    			cloneBS.processMove(move);
    			
    			//Calculate utility of processed move
    			currentMoveAndUtility = miniMax(cloneBS, level+1, move);
    			
    			//Return the MAXIMIZED utility for Swedes
    			if(currentMoveAndUtility.bestUtility < bestMoveAndUtility.bestUtility) {
    				bestMoveAndUtility.bestUtility = currentMoveAndUtility.bestUtility;
    				bestMoveAndUtility.bestMove = move;
    			}
    		}
    		return bestMoveAndUtility;
    	} 
    }
    
}
