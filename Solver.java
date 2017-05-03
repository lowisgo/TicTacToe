package tictactoe;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Solver implements GameSettings{
	public int winner;
	private int m;
	private boolean turnFirst;

	// returns max value among two int
	public int max(int a, int b){
		return (a > b ? a : b);
	}

	// returns min value among two int
	public int min(int a, int b){
		return (a > b ? b : a);
	}

	// [ (0, 0) (0, 1) (0, 2)
	//	 (1, 0) (1, 1) (1, 2)
	//	 (2, 0) (2, 1) (2, 2) ]
	// return yung possible moves -- point (0, 0)
	public ArrayList<Point> action(State currentState){
		ArrayList<Point> toBeReturned = new ArrayList<Point>();
		Point coordinatesAction;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(currentState.config[i][j] == 0){
					toBeReturned.add(new Point(i, j));
					System.out.print(i + "," + j + " ");
				}
				else{
					System.out.print("-,- ");					
				}
			}
			System.out.println();
		}

		return toBeReturned;
	}

	public State result(State currentState, Point coordinates){
		State nextState = new State();

		// copying int[][] config of the current state to the next state
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				nextState.config[i][j] = currentState.config[i][j];
			}
		}
		nextState.turnPlayer = !currentState.turnPlayer;
		nextState.config[(int)coordinates.getX()][(int)coordinates.getY()] = (nextState.turnPlayer == true ? 1 : 2);
		System.out.println("anak na state");
		printState(nextState.config);

		return nextState;
	}

	public int value(State currentState){
		if(checkIfUtility(currentState)){
			System.out.println("^UTILITY\n");
			return utility(currentState);
		}
		if(currentState.minmax == 1){
			System.out.println("^MAX NODE\n");
			return maxValue(currentState);
		}
		if(currentState.minmax == 2){
			System.out.println("^MIN NODE\n");
			return minValue(currentState);
		}
		return utility(currentState);
	}

	public int maxValue(State currentState){
		int m = -1000000, v = 0;
		ArrayList<Point> coordinates = new ArrayList<Point>();
		State state = new State(2); // kasi iba na yung type nung anak niya

		coordinates = action(currentState);
		for(Point point : coordinates){
			state = result(currentState, point);
			currentState.child.add(state);
			v = value(state);
			System.out.println("value!!!" + v);
			m = max(m,v);	
			state.m = m;
			System.out.println("player?: " + state.turnPlayer + " m : " + m);
		}
		return m;
	}

	public int minValue(State currentState){
		System.out.println("MAGULANG:");
		printState(currentState.config);
		System.out.println("---------\n");

		int m = 1000000, v = 0;
		ArrayList<Point> coordinates = new ArrayList<Point>();
		State state = new State(1); // kasi iba na yung type nung anak niya

		coordinates = action(currentState);
		for(Point point : coordinates){
			state = result(currentState, point);
			currentState.child.add(state);
			v = value(state);
			System.out.println("value!!!" + v);
			m = min(m,v);
			state.m = m;
			System.out.println("player?: " + state.turnPlayer + " m : " + m);
		}
		return m;
	}

	public void printState(int [][] grid){
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				System.out.print(grid[i][j] + " ");
			}	
			System.out.println();
		}
	}

	public Point minMaxAlgo(int[][] config){
		// laging min yung una kay AI
		State state = new State(config); // root sa tree
		
		// tracing
		// int[][] multi = new int[][]{
		// 	{ 0, 1, 0 },
		// 	{ 2, 2, 1 },
		// 	{ 1, 0, 0 },
		// };
		// State state = new State(multi); // root sa tree
		
		state.turnPlayer = true; // katatapos lang tumira ni player dito	
		int value = minValue(state);

		printState(state.config);
		System.out.println("point: " + compareBestNode(state, value));
		return compareBestNode(state, value);
	}

	//finds the bestnode
	public State getBestNode(State parent, int value){

		for(State children : parent.child){
			for(int i = 0; i < GameSettings.BOARD_SIZE_X; i++){
				for(int j = 0; j < GameSettings.BOARD_SIZE_Y; j++){
					if(parent.config[i][j] == 0 && value == children.m){
						System.out.println("wew: " + parent.config[i][j]);
						return children;
					}
				}
			}
		}

		return null;
	}
	
	//gets the coordinates to change (bestnode vs oldnode)
	public Point compareBestNode(State parent, int value){
		State bestChild = getBestNode(parent, value);
		Point point = new Point(0, 0);

		for(int i = 0; i < GameSettings.BOARD_SIZE_X; i++){
			for(int j = 0; j < GameSettings.BOARD_SIZE_Y; j++){
				if(parent.config[i][j] != bestChild.config[i][j]){
					point = new Point(i, j);
				}
			}
		}

		return point;
	}

	//if magkakaparehas ng output, check alin yung kukunin
	// public void checkNode(State currentState){
	// 	if(currentState.child.m == currentState.m){
	// 		//ignore lang
	// 	}
	// }
	//update ends here----------------------------------------------------------

	public boolean checkIfUtility(State currentState){		
		if(horizontalWin(currentState) || verticalWin(currentState) || diagonalUpWin(currentState) || diagonalDownWin(currentState) || drawCheck(currentState)){
			return true;
		}
		return false;
	}

	public int utility(State currentState){
		if(GameFrame.turnFirst == false){ // false == 2nd player == AI
			if(horizontalWin(currentState) || verticalWin(currentState) || diagonalUpWin(currentState) || diagonalDownWin(currentState)){
				return 1; // win
			}
			else if(drawCheck(currentState)){
				return 0; // draw
			}
		}
		return -1; // lose
	}

	public boolean horizontalWin(State currentState){
		int horizontalBlue = 0;
		int horizontalRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(currentState.config[i][j] == 1) horizontalBlue++;
				else if(currentState.config[i][j] == 2) horizontalRed++;

				if(horizontalRed == 3 || horizontalBlue == 3){
					if(horizontalRed == 3) winner = 1;
					else if(horizontalBlue == 3) winner = 2;
					System.out.println("horizontalWin");
					return true;
				}
			}			
			horizontalBlue = 0;
			horizontalRed = 0;
		}
		return false;
	}

	public boolean verticalWin(State currentState){
		int verticalBlue = 0;
		int verticalRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(currentState.config[j][i] == 1){
					verticalBlue++;
				}
				else if(currentState.config[j][i] == 2){
					verticalRed++;
				}

				if(verticalRed == 3 || verticalBlue == 3){
					if(verticalRed == 3) winner = 1;
					else if(verticalBlue == 3) winner = 2;
					System.out.println("verticalWin");
					return true;
				}
			}			
			verticalBlue = 0;
			verticalRed = 0;
		}

		return false;
	}

	public boolean diagonalDownWin(State currentState){	
		int diagonalDownBlue = 0;
		int diagonalDownRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(i == j){
					if(currentState.config[i][i] == 1){
						diagonalDownBlue++;
					}
					else if(currentState.config[i][i] == 2){
						diagonalDownRed++;
					}

					if(diagonalDownRed == 3 || diagonalDownBlue == 3){
						if(diagonalDownRed == 3) winner = 1;
						else if(diagonalDownBlue == 3) winner = 2;
						System.out.println("diagonalDownWin");
						return true;
					}
				}

			}			
		}

		return false;
	}

	public boolean diagonalUpWin(State currentState){	
		int diagonalUpRed = 0;
		int diagonalUpBlue = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if((i + j == 2) || (Math.abs(i - j) == 2)){
					if(currentState.config[i][j] == 1){
						diagonalUpBlue++;
					}
					else if(currentState.config[i][j] == 2){
						diagonalUpRed++;
					}

					if(diagonalUpRed == 3 || diagonalUpBlue == 3){
						if(diagonalUpRed == 3) winner = 1;
						else if(diagonalUpBlue == 3) winner = 2;
						System.out.println("diagonalUpWin");
						return true;
					}
				}
			}			
		}
		return false;
	}

	public boolean drawCheck(State currentState){
		boolean flag = true;
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(currentState.config[i][j] == 0){
					return false;
				}
			}
		}

		System.out.println("DRAW!!!!!");
		return true;
	}
}
