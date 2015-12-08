import java.util.ArrayList;
import java.util.Random;

/**
 * This class defines your game board
 * @author jburge
 *
 */
public class Board
{

	public static final int BOARDSIZE = 9;
	public int TURN_INDEX = 0;
	
	private String[] playerNames;
	private ArrayList<Integer> location = new ArrayList<Integer>();
	private ArrayList<Square> savePoints = new ArrayList<Square>();

	/**
	 * An array list of all the squares on our board
	 */
	private ArrayList<Square> board = new ArrayList<Square>();
	/**
	 * The people playing the game
	 */
	//public String player1 = playerNames[0];
	//public String player2 = playerNames[1];
	/**
	 * The person whose turn it is
	 */
	public String turn()
	{
		String turn;
		if(TURN_INDEX == 0)
		{
			TURN_INDEX = 1;
			turn = playerNames[0];
		}
		else
		{
			TURN_INDEX = 0;
			turn = playerNames[1];
		}
		return turn;
	}

	/**
	 * Constructor for the board game
	 * @param playerNames
	 */
	public Board(String[] playerNames) 
	{
		this.playerNames = playerNames;
		
	}
	
	/** 
	 * Randomly re-order the squares (except for the start and end, of course)
	 */
	public void shuffle() 
	{
		Square temp;
		int index;
		Random rand = new Random();
		for(int i = board.size()-2; i > 1; i--)
		{
			index = rand.nextInt(i+1);
			temp = board.get(index);
			board.set(index, board.get(i));
			board.set(i, temp);
		}
	}
	
	/**
	 * Move a player back to the last save point square they landed on or to 
	 * the start
	 */
	public void goBack() 
	{
		
	}
	


	/**
	 * Add a new square to the board.If it's the first one, add the players to it
	 * @param newSquare - the square being added
	 */
	public void addSquare(Square newSquare) {
		//TODO: need to add the players if this is the first square
		if(board.isEmpty()){
			newSquare.addPlayer("Celine");
		}
		board.add(newSquare);
		newSquare.setBoard(this);
		
	}

	/**
	 * Creates our game board
	 * @return - a 2-D array of all the squares on the board
	 */
	public Square[][] createBoard() 
	{
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
	public void doMove(int value) 
	{

	}

	/**
	 * Go to the next player's turn
	 */
	public void next() 
	{
		
	}

	/**
	 * Figure out the correspondence between the square number (in the array list)
	 * and its physical location on the 9x9 board
	 * @param x - x-coordinate in the array
	 * @param y - y-coordinate in the array
	 * @return - the index of the square in our array list
	 */
	private int mapSquareToPosition(int x, int y) 
	{
		if ((x<0)||(y<0)||(x>=BOARDSIZE)||(y>=BOARDSIZE))
			throw new IllegalArgumentException("(x,y) invalid");
		//If y=0, we are looking at the bottom row of the grid. 
		//The index in the array corresponds to the X coordinate.
		if (y==0) 
		{
			return x;
		}
		//Lower vertical set of squares - at Max x-coordinate
		else if (y<BOARDSIZE/2) 
		{
			if (x==BOARDSIZE-1) 
			{
				return BOARDSIZE+y-1;
			}
			else return -1;
		}
		//The set that goes across the middle of the board
		else if (y==BOARDSIZE/2) 
		{
			return (2*BOARDSIZE+(BOARDSIZE/2)-x-2);
		}
		//The squares going up the left side
		else if (y<BOARDSIZE-1) 
		{
			if (x==0) 
			{
				return (2*BOARDSIZE+y-2);
			}
		}
		//The squares along the top
		else if (y==BOARDSIZE-1) 
		{
			return (3*BOARDSIZE)-3+x;
		}
		return -1;

	}


	/**
	 * This main method is here for testing purposes only. The game is
	 * run from Game.main. You can run this method to see how mapSquareToPosition works
	 * @param args
	 */
	public static void main(String [] args) 
	{
		Board b = new Board(args);

		for (int y=BOARDSIZE-1; y>=0; --y) {
			for (int x=0; x<BOARDSIZE; ++x) {
				System.out.printf("%2d ",b.mapSquareToPosition(x, y));
			}
			System.out.println("");
		}

	}
}

