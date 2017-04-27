package tictactoe;

public class State{
	public int[][] config;
	public int[][] toggled;

	public State(){
		this.config = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.toggled = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
	}
}