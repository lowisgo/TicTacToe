/* Author: 		Abasolo, John Jourish DC.
 * Section: 	U-6L
 * Exer #1: 	UI for Lights Out Game
 * Filename: 	surname_exer1.zip
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements GameSettings{
	private int[][] contents;

	private JFrame lightsOutFrame;
	private Container lightsOutContainer;
	private JButton[][] buttons2D;
	private JPanel rows[];

	public static boolean turnFirst;

	private final static String WINDOW_NAME = "Lights Out";

	private final static int WINDOW_SIZE_X = 500;
	private final static int WINDOW_SIZE_Y = 500;

	public GameFrame(int[][] contents){
		this.contents = contents;
		this.turnFirst = true;
		this.lightsOutFrame = new JFrame(GameFrame.WINDOW_NAME);

		this.setFrame();
		this.setFrameContents();
		this.packFrame();
	}

	private void setFrame(){
		this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE_X, GameFrame.WINDOW_SIZE_Y));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	private void setFrameContents(){
		this.lightsOutContainer = this.getContentPane();
		this.lightsOutContainer.setLayout(new GridLayout(GameSettings.BOARD_SIZE_X, 0));

		// row panels
		this.rows = new JPanel[GameSettings.BOARD_SIZE_X];		

		// 2d array of buttons - the grid
		this.buttons2D = new JButton[GameSettings.BOARD_SIZE_X][GameSettings.BOARD_SIZE_Y];

		for(int i = 0; i < GameSettings.BOARD_SIZE_X; i++) {
			// create panels
			this.rows[i] = new JPanel();
			this.rows[i].setLayout(new GridLayout(0,GameSettings.BOARD_SIZE_Y));
				
			for(int j = 0; j < GameSettings.BOARD_SIZE_Y; j++) {
				this.buttons2D[i][j] = new JButton();
				this.buttons2D[i][j].setBackground(Color.WHITE);
				this.rows[i].add(this.buttons2D[i][j]);

				// add a button listener to change the buttons' color
		        this.buttons2D[i][j].addMouseListener(new ButtonListener(this.buttons2D, i, j));
			}
			this.lightsOutContainer.add(this.rows[i]);
		}	
	}

	public void access(){
		System.out.println("Okay");
	}

	private void packFrame(){
		this.pack();
		this.setVisible(true);		
	}
}