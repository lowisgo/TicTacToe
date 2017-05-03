package tictactoe;

import java.awt.Color;
import java.awt.Point;
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

	public boolean horizontalWin(){
		int horizontalBlue = 0;
		int horizontalRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(this.btngrid[i][j].getBackground() == Color.BLUE){
					horizontalBlue++;
				}
				else if(this.btngrid[i][j].getBackground() == Color.RED){
					horizontalRed++;
				}

				if(horizontalRed == 3 || horizontalBlue == 3){
					if(horizontalRed == 3) winner = 1;
					else if(horizontalBlue == 3) winner = 2;
					return false;
				}
			}			
			horizontalBlue = 0;
			horizontalRed = 0;
		}
		
		return true;
	}

	public boolean verticalWin(){
		int verticalBlue = 0;
		int verticalRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(this.btngrid[j][i].getBackground() == Color.BLUE){
					verticalBlue++;
				}
				else if(this.btngrid[j][i].getBackground() == Color.RED){
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

	public boolean diagonalDownWin(){	
		int diagonalDownBlue = 0;
		int diagonalDownRed = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if(i == j){
					if(this.btngrid[i][i].getBackground() == Color.BLUE){
						diagonalDownBlue++;
					}
					else if(this.btngrid[i][i].getBackground() == Color.RED){
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

	public boolean diagonalUpWin(){	
		int diagonalUpRed = 0;
		int diagonalUpBlue = 0;

		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				if((i + j == 2) || (Math.abs(i - j) == 2)){
					if(this.btngrid[i][j].getBackground() == Color.BLUE){
						diagonalUpBlue++;
					}
					else if(this.btngrid[i][j].getBackground() == Color.RED){
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

	public void printStateNumber(int [][] grid){
		for (int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			for (int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				System.out.print(grid[i][j] + " ");
			}	
			System.out.println();
		}
	}

	public void popUpMessage(){
		if(!this.horizontalWin() || !this.verticalWin() || !this.diagonalDownWin() || !this.diagonalUpWin()){
			if(winner == 1){
				JOptionPane.showMessageDialog(
					null,
					new JLabel("Red wins!", JLabel.CENTER),
					"Game Over",
					JOptionPane.PLAIN_MESSAGE
				);
				System.exit(0);
			}
			else if(winner == 2){
				JOptionPane.showMessageDialog(
					null,
					new JLabel("Blue wins!", JLabel.CENTER),
					"Game Over",
					JOptionPane.PLAIN_MESSAGE
				);
				System.exit(0);
			}
		}

		if(!this.drawCheck()){
			JOptionPane.showMessageDialog(
				null,
				new JLabel("Draw!", JLabel.CENTER),
				"Game Over",
				JOptionPane.PLAIN_MESSAGE
			);
			System.exit(0);
		}		
	}

	public void mouseClicked(MouseEvent e){
		// turn ni player
		changeColor(this.btngrid[this.row][this.col], Color.BLUE);
		this.popUpMessage();

		// pagupdate nung board
		this.saveState();
		this.printState();

		// turn ni AI
		this.turnAI();
		this.popUpMessage();
	}

	public void turnAI(){
		Solver solve = new Solver();
		printStateNumber(this.finalGrid);
		Point point = solve.minMaxAlgo(this.finalGrid);
		changeColor(this.btngrid[(int)point.getX()][(int)point.getY()], Color.RED);
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}