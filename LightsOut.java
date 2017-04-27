/* Author: 		Abasolo, John Jourish DC.
 * Section: 	U-6L
 * Exer #1: 	UI for Lights Out Game
 * Filename: 	surname_exer1.zip
 */

public class LightsOut implements GameSettings{
	public static void main(String[] args) {
		int[][] contents = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];

		// Read the initial state correctly and render it on the UI (2 pts.)
		ReadFile lightsOutDotIn = new ReadFile(contents);
		contents = lightsOutDotIn.getContents();

		GameFrame lightsOutGame = new GameFrame(contents);
	}
}