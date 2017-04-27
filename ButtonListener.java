/* Author: 		Abasolo, John Jourish DC.
 * Section: 	U-6L
 * Exer #1: 	UI for Lights Out Game
 * Filename: 	surname_exer1.zip
 */

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ButtonListener implements MouseListener, GameSettings{
	private JButton[][] btngrid;
	private int[][] finalGrid;
	private int row;
	private int col;
	private int winner;

	public ButtonListener(JButton[][] btngrid, int row, int col) {
		this.btngrid = btngrid;
		this.finalGrid = new int[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];
		this.row = row;
		this.col = col;
		// this.GameFrame.turnFirst = GameFrame.turnFirst;
	}

	public void changeColor(JButton btn, Color clr){
		if(btn.getBackground() == Color.WHITE){
			btn.setBackground(clr);
			if(GameFrame.turnFirst){
				GameFrame.turnFirst = false;
			}
			else{
				GameFrame.turnFirst = true;
			}
		}
	}

	// Detect the winning condition (2 pts.)
	public boolean lightCheck(){
		int horizontalBlue = 0;
		int horizontalRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				// if(this.btngrid[i][j].getBackground() == Color.BLUE){
				// 	return true; // the game isn't finished yet
				// }

				// horizontal | i = row
				if(this.btngrid[i][j].getBackground() == Color.BLUE){
					horizontalBlue++;
				}
				else if(this.btngrid[i][j].getBackground() == Color.RED){
					horizontalRed++;
				}

				// todo - sino yung panalo
				if(horizontalRed == 3 || horizontalBlue == 3){
					if(horizontalRed == 3) winner = 1;
					else if(horizontalBlue == 3) winner = 2;
					return false;
				}
			}			
			horizontalBlue = 0;
			horizontalRed = 0;
		}

		int verticalBlue = 0;
		int verticalRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				// if(this.btngrid[i][j].getBackground() == Color.BLUE){
				// 	return true; // the game isn't finished yet
				// }

				// horizontal | i = row
				if(this.btngrid[j][i].getBackground() == Color.BLUE){
					verticalBlue++;
				}
				else if(this.btngrid[j][i].getBackground() == Color.RED){
					verticalRed++;
				}

				// todo - sino yung panalo
				if(verticalRed == 3 || verticalBlue == 3){
					if(verticalRed == 3) winner = 1;
					else if(verticalBlue == 3) winner = 2;
					return false;
				}
			}			
			verticalBlue = 0;
			verticalRed = 0;
		}
	
		int diagonalDownBlue = 0;
		int diagonalDownRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				// if(this.btngrid[i][j].getBackground() == Color.BLUE){
				// 	return true; // the game isn't finished yet
				// }

				// horizontal | i = row
				if(i == j){
					if(this.btngrid[i][i].getBackground() == Color.BLUE){
						diagonalDownBlue++;
					}
					else if(this.btngrid[i][i].getBackground() == Color.RED){
						diagonalDownRed++;
					}

					// todo - sino yung panalo
					if(diagonalDownRed == 3 || diagonalDownBlue == 3){
						if(diagonalDownRed == 3) winner = 1;
						else if(diagonalDownBlue == 3) winner = 2;
						return false;
					}
				}

			}			
		}

		int diagonalUpRed = 0;
		int diagonalUpBlue = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if((i + j == 2) || (Math.abs(i - j) == 2)){
					System.out.println(i+"asdf"+j);
					if(this.btngrid[i][j].getBackground() == Color.BLUE){
						diagonalUpBlue++;
					}
					else if(this.btngrid[i][j].getBackground() == Color.RED){
						diagonalUpRed++;
					}

					// todo - sino yung panalo
					if(diagonalUpRed == 3 || diagonalUpBlue == 3){
						if(diagonalUpRed == 3) winner = 1;
						else if(diagonalUpBlue == 3) winner = 2;
						return false;
					}
				}
			}			
		}
		return true; // all lights are out
		
	}


	// Detect the winning condition (2 pts.)
	public boolean drawCheck(){
		boolean flag = true;
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(this.btngrid[i][j].getBackground() == Color.WHITE){
					flag = false;
				}
			}
		}
		if(flag){
			return false;
		}
		return true;
	}


	// Store the current state of the game properly (2 pts.)
	public void saveState(){
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(this.btngrid[i][j].getBackground() == Color.BLUE){
					this.finalGrid[i][j] = 1;
				}
				else if(this.btngrid[i][j].getBackground() == Color.RED){
					this.finalGrid[i][j] = 2;
				}
				else{
					this.finalGrid[i][j] = 0;
				}
			}			
		}
	}

	public void printState(){
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(this.finalGrid[i][j] == 1){
					System.out.print("blue ");
				}
				else if(this.finalGrid[i][j] == 2){
					System.out.print("red_ ");
				}
				else{
					System.out.print("whit ");					
				}
			}	
			System.out.println();
		}
		System.out.println();
	}

	public int[][] getButtonGrid(){
		return this.finalGrid;
	}

	// Correctly toggle lights upon pressing a cell (4 pts.)
	public void mouseClicked(MouseEvent e){
		// button clicked
		System.out.println(GameFrame.turnFirst + "asdf");

		if(GameFrame.turnFirst){
			changeColor(this.btngrid[this.row][this.col], Color.BLUE);
			// GameFrame.turnFirst = false;
		}
		else{
			changeColor(this.btngrid[this.row][this.col], Color.RED);
			// GameFrame.turnFirst = true;
		}

		// check if all lights are out
		if(!this.lightCheck()){
			if(winner == 1){
				JOptionPane.showMessageDialog(
					null,
					new JLabel("Red wins!", JLabel.CENTER),
					"Game Over",
					JOptionPane.PLAIN_MESSAGE
				);
			}
			else if(winner == 2){
				JOptionPane.showMessageDialog(
					null,
					new JLabel("Blue wins!", JLabel.CENTER),
					"Game Over",
					JOptionPane.PLAIN_MESSAGE
				);
			}
			// GameOverFrame gameOver = new GameOverFrame();
		}

		// check if all lights are out
		if(!this.drawCheck()){
			JOptionPane.showMessageDialog(
				null,
				new JLabel("DRAW", JLabel.CENTER),
				"Game Over",
				JOptionPane.PLAIN_MESSAGE
			);
			// GameOverFrame gameOver = new GameOverFrame();
		}

		this.saveState();
		
		// tracing
		this.printState();
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}