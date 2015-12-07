import java.util.ArrayList;

/**
 * This class defines your game board
 * @author jburge
 *
 */
public class Board {

	public static final int BOARDSIZE=9;

	/**
	 * An array list of all the squares on our board
	 */
	private ArrayList<Square> board = new ArrayList<Square>();
	/**
	 * The people playing the game
	 */
	//TODO - define!
	/**
	 * The person whose turn it is
	 */
	//TODO: define!

	/**
	 * Constructor for the board game
	 * @param playerNames
	 */
	public Board(String[] playerNames) {
	
	}
	
	/** 
	 * Randomly re-order the squares (except for the start and end, of course)
	 */
	public void shuffle() {

	}
	
	/**
	 * Move a player back to the last save point square they landed on or to 
	 * the start
	 */
	public void goBack() {

	}
	


	/**
	 * Add a new square to the board.If it's the first one, add the players to it
	 * @param newSquare - the square being added
	 */
	public void addSquare(Square newSquare) {
		board.add(newSquare);
		newSquare.setBoard(this);
		//TODO: need to add the players if this is the first square
		
	}

	/**
	 * Creates our game board
	 * @return - a 2-D array of all the squares on the board
	 */
	public Square[][] createBoard() {
		Square[][] result = new Square[BOARDSIZE][BOARDSIZE];
		for (int x=0; x<BOARDSIZE; ++x) {
			for (int y=0; y<BOARDSIZE; ++y) {
				int position = mapSquareToPosition(x,y);
				result[x][y]=null;
				if ((position!=-1)&&(position<board.size())) {
					result[x][y] = board.get(position);
				}
			}
		}
		return result;
	}

	/**
	 * Move the player to their new position based on the dice roll
	 * @param value - the dice roll
	 */
	public void doMove(int value) {

	}

	/**
	 * Go to the next player's turn
	 */
	public void next() {

	}

	/**
	 * Figure out the correspondence between the square number (in the array list)
	 * and its physical location on the 9x9 board
	 * @param x - x-coordinate in the array
	 * @param y - y-coordinate in the array
	 * @return - the index of the square in our array list
	 */
	private int mapSquareToPosition(int x, int y) {
		if ((x<0)||(y<0)||(x>=BOARDSIZE)||(y>=BOARDSIZE))
			throw new IllegalArgumentException("(x,y) invalid");
		//If y=0, we are looking at the bottom row of the grid. 
		//The index in the array corresponds to the X coordinate.
		if (y==0) {
			return x;
		}
		//Lower vertical set of squares - at Max x-coordinate
		else if (y<BOARDSIZE/2) {
			if (x==BOARDSIZE-1) {
				return BOARDSIZE+y-1;
			}
			else return -1;
		}
		//The set that goes across the middle of the board
		else if (y==BOARDSIZE/2) {
			return (2*BOARDSIZE+(BOARDSIZE/2)-x-2);
		}
		//The squares going up the left side
		else if (y<BOARDSIZE-1) {
			if (x==0) {
				return (2*BOARDSIZE+y-2);
			}
		}
		//The squares along the top
		else if (y==BOARDSIZE-1) {
			return (3*BOARDSIZE)-3+x;
		}
		return -1;

	}


	/**
	 * This main method is here for testing purposes only. The game is
	 * run from Game.main. You can run this method to see how mapSquareToPosition works
	 * @param args
	 */
	public static void main(String [] args) {
		Board b = new Board(args);

		for (int y=BOARDSIZE-1; y>=0; --y) {
			for (int x=0; x<BOARDSIZE; ++x) {
				System.out.printf("%2d ",b.mapSquareToPosition(x, y));
			}
			System.out.println("");
		}

	}
}
