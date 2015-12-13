import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Parent class for the squares on the board
 * @author jburge
 *
 */
public abstract class Square extends JPanel{
	

	protected Board board;
	
	private ArrayList<String> players;
	
	public Square() {
		players = new ArrayList<String>();
	}
	
	public void addPlayer(String name){
		players.add(name);
		
	}
	
	public String getPlayer(int n){
		return players.get(n);
	}
	
	public void removePlayer(int n){
		players.remove(n);
	}
	
	public int sizePlayer(){
		return players.size();
	}
	
	public void setBoard(Board b) {
		board = b;
	}
	
	
	public abstract void doAction();
	
	public abstract String getLabel();
	
}
