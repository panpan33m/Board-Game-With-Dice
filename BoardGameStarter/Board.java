import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

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
	private ArrayList<Square> p1SavePoints = new ArrayList<Square>();
	private ArrayList<Square> p2SavePoints = new ArrayList<Square>();

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
		return playerNames[TURN_INDEX];
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
		Collections.shuffle(board.subList(1, board.size()-1));
	}
	
	/**
	 * Keeps track of savePoints for player, to be used in goBack method
	 */
	public void savePoint(Square s)
	{
		if(turn() == playerNames[0])
		{
			p1SavePoints.add(s);
		}
		else if(turn() == playerNames[1])
		{
			p2SavePoints.add(s);
		}
	}
	

	/**
	 * Move a player back to the last save point square they landed on or to
	 * the start
	 */
	public void goBack()
	{
		//Check to see if have any savePoints saved, then move to last savePoint
		//If no savePoints, move to start
		if(turn().equals(playerNames[0]))
		{
			int location = 0;
			int player = 0;
			for(int i = 0; i < board.size(); i++)
			{
				if(board.get(i).sizePlayer() > 0)
				{
					for(int j = 0; j < board.get(i).sizePlayer(); j++)
					{
						if(board.get(i).getPlayer(j).contains(playerNames[0]))
						{
							location = i;
							player = j;
						}
					}
				}
			}
			board.get(location).removePlayer(player);
			if(p1SavePoints.isEmpty())
			{
				board.get(0).addPlayer(playerNames[0]);
			}
			else
			{
				for(int i = 0; i < board.size(); i++)
				{
					if(board.get(i).equals(p1SavePoints.get(p1SavePoints.size()-1)))
					{
						board.get(i).addPlayer(playerNames[0]);
					}
				}
			}
		}
		else if(turn().equals(playerNames[1]))
		{
			int location = 0;
			int player = 0;
			for(int i = 0; i < board.size(); i++)
			{
				if(board.get(i).sizePlayer() > 0)
				{
					for(int j = 0; j < board.get(i).sizePlayer(); j++)
					{
						if(board.get(i).getPlayer(j).contains(playerNames[1]))
						{
							location = i;
							player = j;
						}
					}
				}
			}
			board.get(location).removePlayer(player);
			if(p2SavePoints.isEmpty())
			{
				board.get(0).addPlayer(playerNames[1]);
			}
			else
			{
				for(int i = 0; i < board.size(); i++)
				{
					if(board.get(i).equals(p2SavePoints.get(p2SavePoints.size()-1)))
					{
						board.get(i).addPlayer(playerNames[1]);
					}
				}
			}
			
		}
	}



	/**
	 * Add a new square to the board.If it's the first one, add the players to it
	 * @param newSquare - the square being added
	 */
	public void addSquare(Square newSquare) {
		//TODO: need to add the players if this is the first square
		if(board.isEmpty())
		{
			newSquare.addPlayer(playerNames[0]);
			newSquare.addPlayer(playerNames[1]);
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
		int location = 0;
		int player = 0;
		if(turn().equals(playerNames[0]))
		{
			for(int i = 0; i < board.size(); i++)
			{
				if(board.get(i).sizePlayer() > 0)
				{
					for(int j = 0; j < board.get(i).sizePlayer(); j++)
					{
						if(board.get(i).getPlayer(j).contains(playerNames[0]))
						{
							location = i;
							player = j;
						}
					}
				}
			}
			
			board.get(location).removePlayer(player);
			if((location+value) <= (board.size()-1))
			{
				board.get(location+value).addPlayer(playerNames[0]);
				board.get(location+value).doAction();
			}
			else
			{
				board.get(board.size()-1).addPlayer(playerNames[0]);
				board.get(board.size()-1).doAction();
			}
		}
		else if(turn().equals(playerNames[1]))
		{
			for(int i = 0; i < board.size(); i++)
			{
				if(board.get(i).sizePlayer() > 0)
				{
					for(int j = 0; j < board.get(i).sizePlayer(); j++)
					{
						if(board.get(i).getPlayer(j).contains(playerNames[1]))
						{
							location = i;
							player = j;
						}
					}
				}
			}
			
			board.get(location).removePlayer(player);
			if((location+value) <= (board.size()-1))
			{
				board.get(location+value).addPlayer(playerNames[1]);
				board.get(location+value).doAction();
			}
			else
			{
				board.get(board.size()-1).addPlayer(playerNames[1]);
				board.get(board.size()-1).doAction();
			}
		}
	}

	/**
	 * Go to the next player's turn
	 */
	 public void next()
	 {
		 if(TURN_INDEX == 0)
		 {
			 TURN_INDEX = 1;
		 }
		 else
		 {
			 TURN_INDEX = 0;
		 }
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
