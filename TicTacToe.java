package tictactoe;

import java.awt.Point;

public class TicTacToe implements GameSettings{
	public static void main(String[] args){

		State asdf = new State();
		Solver solve = new Solver();
		solve.action(asdf);
		solve.result(asdf, new Point(0, 0));

		GameFrame lightsOutGame = new GameFrame(asdf);
	}
}