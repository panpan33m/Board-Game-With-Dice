import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Parent class for the squares on the board
 * @author jburge, apham, ctao
 *
 */
public abstract class Square extends JPanel{
	

	protected Board board;
	
	private ArrayList<String> players;

	public Square() {
		players = new ArrayList<String>();
	}
	
	/**
	 * add player to square
	 * @param name
	 */
	public void addPlayer(String name){
		players.add(name);
		
	}
	
	/**
	 * return player on the square
	 * @param n
	 * @return
	 */
	public String getPlayer(int n){
		return players.get(n);
	}
	
	/**
	 * remove player from square
	 * @param n
	 */
	public void removePlayer(int n){
		players.remove(n);
	}
	
	/**
	 * return size of players (number of players) on the square
	 * @return
	 */
	public int sizePlayer(){
		return players.size();
	}
	
	public void setBoard(Board b) {
		board = b;
	}
	
	
	public abstract void doAction();
	
	public abstract String getLabel();
	
}
