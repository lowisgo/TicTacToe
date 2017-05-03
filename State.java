package tictactoe;

public class State{
	public int[][] config;
	public int[][] toggled;
	public int minmax;
	public State parent;
	public State child;

	// initial creation
	public State(){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.toggled = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.minmax = 2; // pero 1 (max) or 2 (min) siya
		this.parent = null;
	}

	// initial creation
	public State(int[][] config){
		for(int i = 0; i < GameSettings.BOARD_SIZE_X; i++){
			for(int j = 0; j < GameSettings.BOARD_SIZE_X; j++){
				this.config[i][j] = config[i][j];
			}
		}
		this.toggled = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.minmax = 2; // pero 1 (max) or 2 (min) siya
		this.parent = null;
	}

	// if na-form siya sa loob ng maxValue/minValue
	public State(int minmax){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.toggled = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.minmax = minmax;
		this.parent = null;
	}
}