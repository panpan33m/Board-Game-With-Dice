import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Parent class for the squares on the board
 * @author jburge
 *
 */
public abstract class Square {
	

	protected Board board;
	
	public Square() {
	}
	
	public void setBoard(Board b) {
		board = b;
	}
	
	public abstract void doAction();
	
	public abstract String getLabel();
	
}
