import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main frame for the Board Game. 
 * @author jburge
 *
 */
public class Game extends JFrame implements ActionListener {
	
	private Board board;

	private Random rand; //used to roll our dice
	private GameBoardPanel gbp;

/**
 * Constructor for the Game - creates the initial board and its squares; then creates the GameBoardPanel that
 * will display them	
 */
	public Game() {
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Board Game");
		this.setLayout(new BorderLayout());
		board = new Board(new String[]{"Alice","Bob"});
		//Add the squares to the board. Don't worry about order (except for start/end)
		board.addSquare(new PlainSquare()); //you will need to change this
		for (int i=0; i<4*Board.BOARDSIZE; ++i) {
			board.addSquare(new PlainSquare());
		}

		//Shuffle our board so the order is random
		board.shuffle();
		gbp = new GameBoardPanel(board);
		this.add(gbp,BorderLayout.CENTER);
		this.add(makeControl(),BorderLayout.SOUTH);
		rand = new Random(); //used when rolling dice
	}
	
	/**
	 * Sets up the panel at the bottom that shows whose turn it is and lets the player roll the dice
	 * @return
	 */
	private JPanel makeControl() {
		//set up your bottom control panel here
		return new JPanel(); //change - this code is here so the starter code will compile
		
	}
	
	public static void main(String [] args) {
		Game g = new Game();
		g.setVisible(true);
	}

	/**
	 * This method will respond to the Roll button. You will need to write this code so your version
	 * works like the one in the example.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//take care of moving the player around and updating the board!
		this.update(this.getGraphics());
	}

}
