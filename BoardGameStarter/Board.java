import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class defines your game board
 * @author jburge, apham, ctao
 *
 */
public class Board
{

	public static final int BOARDSIZE = 9;
	
	private int TURN_INDEX = 0;
	
	/**
	 * ArrayList to keep track of save points for each player
	 */
	private ArrayList<ArrayList<Square>> SavePoints = new ArrayList<ArrayList<Square>>();
	
	/**
	 * An array list of all the squares on our board
	 */
	private ArrayList<Square> board = new ArrayList<Square>();
	
	/**
	 * The people playing the game
	 */
	private String[] playerNames;
	
	/**
	 * The person whose turn it is
	 */
	public String turn()
	{
		return playerNames[TURN_INDEX];
	}
	
	/**
	 * returns turn index
	 * @return turn index
	 */
	public int turnIndex()
	{
		return TURN_INDEX;
	}

	/**
	 * Constructor for the board game
	 * @param playerNames
	 */
	public Board(String[] playerNames)
	{
		this.playerNames = playerNames;
		for(int i = 0; i<playerNames.length; i++)
			SavePoints.add(new ArrayList<Square>());
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
		for(int i=0; i<playerNames.length; i++){
			if(turn().equals(playerNames[i]))
				SavePoints.get(i).add(s);
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
		for(int a=0; a<playerNames.length; a++){
			if(turn().equals(playerNames[a])){
				int location = 0;
				int player = 0;
				for(int i = 0; i < board.size(); i++)
				{
					if(board.get(i).sizePlayer() > 0)
					{
						for(int j = 0; j < board.get(i).sizePlayer(); j++)
						{
							if(board.get(i).getPlayer(j).contains(playerNames[a]))
							{
								location = i;
								player = j;
							}
						}
					}
				}
				board.get(location).removePlayer(player);
				if(SavePoints.get(a).isEmpty())
				{
					board.get(0).addPlayer(playerNames[a]);
				}
				else
				{
					for(int i = 0; i < board.size(); i++)
					{
						if(board.get(i).equals(SavePoints.get(a).get(SavePoints.get(a).size()-1)))
						{
							board.get(i).addPlayer(playerNames[a]);
						}
					}
				}
				break;
			}
		}	
	}

	/**
	 * return the player number
	 * @param name
	 * @return
	 */
	public int getPlayerNum(String name){
		for(int i=0; i<playerNames.length;i++)
		{
			if(playerNames[i].equals(name))
				return i;
		}
		return -1;
	}


	/**
	 * Add a new square to the board.If it's the first one, add the players to it
	 * @param newSquare - the square being added
	 */
	public void addSquare(Square newSquare) {
		//TODO: need to add the players if this is the first square
		if(board.isEmpty())
		{
			for(int i=0; i<playerNames.length; i++){
				newSquare.addPlayer(playerNames[i]);
			}
		}
		board.add(newSquare);
		newSquare.setBoard(this);

	}
	
	/**
	 * Boolean returns if a player finished the game
	 * @return
	 */
	public boolean hitFinish(){
		if(board.get(board.size()-1).sizePlayer()>0)
			return true;
		return false;
	}
	

	/**
	 * Creates our game board
	 * @return - a 2-D array of all the squares on the board
	 */
	public Square[][] createBoard()
	{
		Square[][] result = new Square[BOARDSIZE][BOARDSIZE];
		for (int x=0; x<BOARDSIZE; ++x) 
		{
			for (int y=0; y<BOARDSIZE; ++y) 
			{
				int position = mapSquareToPosition(x,y);
				result[x][y]=null;
				
				if ((position!=-1)&&(position<board.size())) 
				{
					result[x][y] = board.get(position);
				}
			}
		}
		return result;
	}

	public void doAction(){
		for(int a=0; a<playerNames.length; a++)
		{

			if(turn().equals(playerNames[a]))
			{
				for(int i = 0; i < board.size(); i++)
				{
					if(board.get(i).sizePlayer() > 0)
					{
						for(int j = 0; j < board.get(i).sizePlayer(); j++)
						{
							if(board.get(i).getPlayer(j).equals(playerNames[a]))
							{
								board.get(i).doAction();
								return;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Move the player to their new position based on the dice roll
	 * @param value - the dice roll
	 */
	public void doMove(int value)
	{
		for(int a=0; a<playerNames.length; a++)
		{
			int location = 0;
			int player = 0;
			if(turn().equals(playerNames[a]))
			{
				for(int i = 0; i < board.size(); i++)
				{
					if(board.get(i).sizePlayer() > 0)
					{
						for(int j = 0; j < board.get(i).sizePlayer(); j++)
						{
							if(board.get(i).getPlayer(j).contains(playerNames[a]))
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
					board.get(location+value).addPlayer(playerNames[a]);
					//board.get(location+value).doAction();
				}
				else
				{
					board.get(board.size()-1).addPlayer(playerNames[a]);
					//board.get(board.size()-1).doAction();
				}
				break;
			}
		}
		
	}

	/**
	 * Go to the next player's turn
	 */
	public void next()
	{
		if(TURN_INDEX == playerNames.length-1)
		{
			TURN_INDEX = 0;
		}
		else
		{
			TURN_INDEX = TURN_INDEX+1;
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
