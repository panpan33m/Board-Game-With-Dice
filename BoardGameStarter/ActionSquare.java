import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Square class that holds action squares
 * @author apham, ctao
 *
 */
public class ActionSquare extends Square
{
	private String label;
	private Random rand;
	
	public ActionSquare(String label) 
	{
		super();
		this.label = label;
	}

	/**
	 * method to do action if square is landed on
	 */
	@Override
	public void doAction() {
		//roll again
		if(label.equals("Roll Again"))
		{
			JOptionPane.showMessageDialog(null, "It's your turn to roll again!");
		}
		//use goBack method, then change turns
		else if(label.equals("Go Back")){
			board.goBack();
			board.next();
		}
		//shuffle the board, then change turns
		else if(label.equals("Shuffle")){
			board.shuffle();
			board.next();
		}
		//save "save point" to list of savePoints and change turns
		else if(label.contains("Save Point")){
			board.savePoint(this);
			board.next();
		}
		//Print out the winner
		else if(label.equals("Finish")){
			JOptionPane.showMessageDialog(null, board.turn()+" wins!");		
		}
	}

	/**
	 * get label of this action square
	 */
	@Override
	public String getLabel() {
		return label;
	}

}
