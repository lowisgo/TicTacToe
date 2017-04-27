/* Author: 		Abasolo, John Jourish DC.
 * Section: 	U-6L
 * Exer #1: 	UI for Lights Out Game
 * Filename: 	surname_exer1.zip
 */

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.FileNotFoundException;  
import java.io.IOException;  

public class ReadFile implements GameSettings{
	private int perLine;
	private int[][] contents;

	private BufferedReader lineReader;
	private FileReader fileReader;

	private final static String LOAD_FILE_NAME = "lightsout.in";

	public ReadFile(int[][] contents){
		this.perLine = (GameSettings.BOARD_SIZE_X * 2) - 1;
		this.contents = contents;

		try{
			this.fileReader = new FileReader(ReadFile.LOAD_FILE_NAME);
		}
		catch(FileNotFoundException e) { 
			System.out.println("File lightsout.in not found."); 
		}
		catch(Exception e) { 
			System.out.println(e.getMessage()); 
		} 

		this.lineReader = new BufferedReader(this.fileReader);
		this.readFileGetContents();
	}

	// reads the file lightsout.in per line then extracts the contents
	// of the board then puts it into a 2D array of integers
	public void readFileGetContents(){
		String line; 
		int row = 0, col = 0;

		try {
			while((line=this.lineReader.readLine()) != null) {
				for(int i = 0; i < this.perLine; i += 2){
					// System.out.print(line.charAt(i));
					this.contents[row][col] = Character.getNumericValue(line.charAt(i));
					col++;
				}
				col = 0;
				row++;
			}
		}
		catch(IOException e) {
			System.out.println("Failure or interruption of input/output operation occured.");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		} 
	}

	public int[][] getContents(){
		return this.contents;
	}
}