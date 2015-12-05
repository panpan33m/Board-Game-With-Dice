
/**
 * This defines the class for a plain (blank) square
 * @author jburge
 *
 */
public class PlainSquare extends Square {

	public PlainSquare() {
	}

	/**
	 * Nothing happens when someone lands on a plain square - you just go to the next
	 * player's turn
	 */
	public void doAction() {
		board.next();
	}
	
	public String getLabel() {
		return ""; //plain squares are blank
	}
	
	
	
}
