/* Author: 		Abasolo, John Jourish DC.
 * Section: 	U-6L
 * Exer #1: 	UI for Lights Out Game
 * Filename: 	surname_exer1.zip
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class GameOverFrame extends JFrame{
	private JFrame gameOverFrame;
	private Container gameOverContainer;
	private JPanel centerPanel;
	private JLabel yaaayText;
	private JButton yaaayButton;

	private final static String WINDOW_NAME = "GAME OVER";
	private final static String WINDOW_TEXT_CONTENT = "YAAAY";
	private final static String WINDOW_BUTTON = "Ok!";

	private final static int WINDOW_SIZE_X = 250;
	private final static int WINDOW_SIZE_Y = 250;	

	public GameOverFrame(){
		this.gameOverFrame = new JFrame(GameOverFrame.WINDOW_NAME);

		this.setFrame();
		this.setFrameContents();
		this.packFrame();
	}

	private void setFrame(){
		this.gameOverFrame.setPreferredSize(new Dimension(GameOverFrame.WINDOW_SIZE_X, GameOverFrame.WINDOW_SIZE_Y));
		this.gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setFrameContents(){
		this.gameOverContainer = this.gameOverFrame.getContentPane();
		this.gameOverContainer.setLayout(new BorderLayout());

		this.centerPanel = new JPanel();

		this.yaaayText = new JLabel(GameOverFrame.WINDOW_TEXT_CONTENT);
		this.yaaayButton = new JButton(GameOverFrame.WINDOW_BUTTON);

		this.centerPanel.add(this.yaaayText);
		this.centerPanel.add(this.yaaayButton);

		this.gameOverContainer.add(this.centerPanel, BorderLayout.CENTER);
	}

	private void packFrame(){
		this.gameOverFrame.pack();
		this.gameOverFrame.setVisible(true);
	}
}