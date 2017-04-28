package tictactoe;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Solver implements GameSettings{
	public int winner;

	// returns max value among two int
	public int max(int a, int b){
		return (a > b ? a : b);
	}

	// returns min value among two int
	public int min(int a, int b){
		return (a > b ? b : a);
	}

	// [ (0, 0) (0, 1) (0, 2)
	//   (1, 0) (1, 1) (1, 2)
	//   (2, 0) (2, 1) (2, 2) ]
	// return yung possible moves -- point (0, 0)
	public ArrayList<Point> action(State currentState){
		ArrayList<Point> toBeReturned = new ArrayList<Point>();
		Point coordinatesAction;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(currentState.toggled[i][j] == 0){
					toBeReturned.add(new Point(i, j));
					// System.out.print(i + "," + j + " ");
				}
			}
			// System.out.println();	
		}

		return toBeReturned;
	}

	// ----------------lois ito----------------
	public int maxValue(State currentState){
		int m = 1000000, v = 0;
		ArrayList<Point> coordinates = new ArrayList<Point>();
		State state = new State();

		coordinates = action(currentState);

		for(int i = 0; i < coordinates.size(); i++){
			for(Point point : coordinates.get(i)){
				state = result(currentState, point);
				v = value(state);
				m = max(m,v);	
			}
			return m;
		}


		//traverse through the arraylist 
/*		while(coordinates.size() != 0){
			removed = coordinates.remove();

			//for a, s’ in successors(s)
			state = result(currentState, removed);
			v = value(state);
			m = max(m,v);
		}
*/
	}

	public int minValue(State currentState){
		int m = -1000000, v = 0;
		ArrayList<Point> coordinates = new ArrayList<Point>();
		Point removed;
		State state = new State();

		coordinates = action(currentState);

		for(int i = 0; i < coordinates.size(); i++){
			for(Point point : coordinates.get(i)){
				state = result(currentState, point);
				v = value(state);
				m = max(m,v);	
			}
			return m;
		}

/*		//traverse through the arraylist 
		while(coordinates.size() != 0){
			removed = coordinates.remove();

			//for a, s’ in successors(s)
			state = result(currentState, removed);
			v = value(state);
			m = min(m,v);
		}*/
		
	}
	// ----------------lois ito----------------

	public State result(State currentState, Point coordinates){
		State nextState = new State();

		// copying int[][] config of the current state to the next state
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				nextState.config[i][j] = currentState.config[i][j];
			}
		}

		// copying int[][] toggled of the current state to the next state
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				nextState.toggled[i][j] = currentState.toggled[i][j];
			}
		}

		int row = (int)coordinates.getX(), col = (int)coordinates.getY();
		nextState.toggled[row][col] = 1;

		nextState.config[row][col] = (GameFrame.turnFirst == true && nextState.config[row][col] == 0 ? 1 : 2);
		return nextState;
	}

	public int value(State currentState){
		if(checkIfUtility(currentState)){
			return utility(currentState);
		}
		if(currentState.minmax == 1){
			return maxValue(currentState);
		}
		if(currentState.minmax == 2){
			return minValue(currentState);
		}
		return -10000000;
	}

	public int maxValue(State currentState){
		int m = 1000000, v = 0;
		ArrayList<Point> coordinates = new ArrayList<Point>();
		State state = new State(2); // kasi iba na yung type nung anak niya

		coordinates = action(currentState);
		for(int i = 0; i < coordinates.size(); i++){
			for(Point point : coordinates){
				state = result(currentState, point);
				state.parent = currentState;
				v = value(state);
				m = max(m,v);	
			}
		}
		return m;
	}

	public int minValue(State currentState){
		int m = -1000000, v = 0;
		ArrayList<Point> coordinates = new ArrayList<Point>();
		State state = new State(1); // kasi iba na yung type nung anak niya

		coordinates = action(currentState);
		for(int i = 0; i < coordinates.size(); i++){
			for(Point point : coordinates){
				state = result(currentState, point);
				state.parent = currentState;
				v = value(state);
				m = max(m,v);
			}
		}
		return m;
	}

	public void minMaxAlgo(int[][] config){
		// laging min yung una kay AI
		State state = new State(config); // root sa tree
		int value = minValue(state);
		// anung gagawin dun sa m?
		// paano idedetect na siya yung best move
		if()		
	}

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

				if(horizontalRed == 3 || horizontalBlue == 3) return false;
			}			
			horizontalBlue = 0;
			horizontalRed = 0;
		}
		return true;
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
					return false;
				}
			}			
			verticalBlue = 0;
			verticalRed = 0;
		}

		return true;
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
						return false;
					}
				}

			}			
		}

		return true;
	}

	public boolean diagonalUpWin(State currentState){	
		int diagonalUpRed = 0;
		int diagonalUpBlue = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if((i + j == 2) || (Math.abs(i - j) == 2)){
					System.out.println(i+"asdf"+j);
					if(currentState.config[i][j] == 1){
						diagonalUpBlue++;
					}
					else if(currentState.config[i][j] == 2){
						diagonalUpRed++;
					}

					if(diagonalUpRed == 3 || diagonalUpBlue == 3){
						if(diagonalUpRed == 3) winner = 1;
						else if(diagonalUpBlue == 3) winner = 2;
						return false;
					}
				}
			}			
		}
		return true;
	}

	public boolean drawCheck(State currentState){
		boolean flag = true;
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(currentState.config[i][j] == 0){
					flag = false;
				}
			}
		}
		if(flag){
			return false;
		}
		return true;
	}
}
