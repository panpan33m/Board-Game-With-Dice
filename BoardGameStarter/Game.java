import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Main frame for the Board Game.
 * @author jburge
 *
 */
public class Game extends JFrame implements ActionListener {

	private Board board;

	private Random rand; //used to roll our dice
	private GameBoardPanel gbp;

	private JPanel bottom;

	private JButton roll;

	private JLabel turnLabel;

	private JDialog dialog = null;

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
		board.addSquare(new ActionSquare("Start"));
		int count = 2;
		for (int i=0; i<3; ++i) {
			board.addSquare(new ActionSquare("Roll Again"));
			count++;
		}
		for (int i=0; i<5; ++i) {
			board.addSquare(new ActionSquare("Go Back"));
			count++;
		}
		for (int i=0; i<2; ++i) {
			board.addSquare(new ActionSquare("Shuffle"));
			count++;
		}
		for (int i=0, j=1; i<6; ++i, ++j) {
			board.addSquare(new ActionSquare("Save Point"+j));
			count++;
		}
		for (int i=0; i<4*(board.BOARDSIZE-1)+1-count; ++i) {
			board.addSquare(new PlainSquare());
		}
		board.addSquare(new ActionSquare("Finish"));

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
		bottom = new JPanel();
		roll = new JButton("Roll");
		roll.addActionListener(this);
		bottom.setLayout(new BorderLayout());
		bottom.add(roll, BorderLayout.WEST);
		turnLabel = new JLabel("It's " + board.turn() +"'s turn", SwingConstants.CENTER);
		bottom.add(turnLabel, BorderLayout.CENTER);
		return bottom; //change - this code is here so the starter code will compile

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
		if (e.getActionCommand().equals("Roll")){
			rand = new Random();
			int diceNumber = rand.nextInt(6)+1;
			JOptionPane.showMessageDialog(this, board.turn() + " rolled "+diceNumber);
			board.doMove(diceNumber);
			gbp.update();
			this.update(this.getGraphics());
		}
		//take care of moving the player around and updating the board!
		//this.update(this.getGraphics());
	}

}
