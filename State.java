package tictactoe;

import java.util.ArrayList;

public class State{
	public int[][] config;
	public int minmax;
	public int m;
	public ArrayList<State> child;
	public boolean turnPlayer; // false == AI || true == player

	// initial creation - empty board
	public State(){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.minmax = 2; // pero 1 (max) or 2 (min) siya
		this.m = 0;
		this.child = new ArrayList<State>();
		this.turnPlayer = false;
	}

	public State(int [][]config, int m){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		for(int i = 0; i < GameSettings.BOARD_SIZE_X; i++){
			for(int j = 0; j < GameSettings.BOARD_SIZE_X; j++){
				this.config[i][j] = config[i][j];
			}
		}
		this.minmax = 2; // pero 1 (max) or 2 (min) siya
		this.m = m;
		this.child = new ArrayList<State>();
		this.turnPlayer = false;
	}


	// initial creation - for the AI turn @ minMaxAlgo
	public State(int[][] config){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		for(int i = 0; i < GameSettings.BOARD_SIZE_X; i++){
			for(int j = 0; j < GameSettings.BOARD_SIZE_X; j++){
				this.config[i][j] = config[i][j];
			}
		}
		this.minmax = 2; // pero 1 (max) or 2 (min) siya
		this.m = 0;
		this.child = new ArrayList<State>();
		this.turnPlayer = true;
	}

	// if na-form siya sa loob ng maxValue/minValue
	public State(int minmax){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.minmax = minmax;
		this.m = 0;
		this.child = new ArrayList<State>();
		this.turnPlayer = false;
	}
}