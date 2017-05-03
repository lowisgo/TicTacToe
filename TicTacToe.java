package tictactoe;

import java.awt.Point;

public class TicTacToe implements GameSettings{
	public static void main(String[] args){
		int[][] multi = new int[][]{
			{ 2, 0, 0 },
			{ 0, 0, 0 },
			{ 1, 0, 0 },
		};
		State asdf = new State(multi, 0);
		// State asdf = new State();
		GameFrame lightsOutGame = new GameFrame(asdf);
	}
} 