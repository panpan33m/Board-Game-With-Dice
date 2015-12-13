import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

	private static String[] playerNames;
	private static int PLAYERNUM;

	private static ArrayList<Integer> score = new ArrayList<Integer>();

	private static Game g;

	/**
	 * Constructor for the Game - creates the initial board and its squares; then creates the GameBoardPanel that
	 * will display them
	 */
	public Game() {
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Board Game");
		this.setLayout(new BorderLayout());

		board = new Board(playerNames);

		SquareTypeNumberGUI window = new SquareTypeNumberGUI();
		//window.setLocation(this.getLocation());
		this.setVisible(false);
		window.showDialog(null, "Number of each type of square");
		this.setVisible(true);
		//Add the squares to the board. Don't worry about order (except for start/end)
		board.addSquare(new ActionSquare("Start"));
		//board.getSquare(0).setBackground(Color.RED);
		
		int count = 2;
		for (int i=0; i<window.getNumber(0); ++i) {
			board.addSquare(new ActionSquare("Roll Again"));
			
			count++;
		}
		for (int i=0; i<window.getNumber(1); ++i) {
			board.addSquare(new ActionSquare("Go Back"));
			count++;
		}
		for (int i=0; i<window.getNumber(2); ++i) {
			board.addSquare(new ActionSquare("Shuffle"));
			count++;
		}
		for (int i=0, j=1; i<window.getNumber(3); ++i, ++j) {
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
		while(true){
			try{
				String playerNum = JOptionPane.showInputDialog("Please enter the number of players(2-4): ");
				if(playerNum == null)
					System.exit(0);
				PLAYERNUM = Integer.parseInt(playerNum);
				if(PLAYERNUM<2 || PLAYERNUM>4)
					throw new Exception();
				break;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 4.");
			}
		}
		playerNames = new String[PLAYERNUM];
		for(int i=0, num=1; i<PLAYERNUM; i++,num++){
			String ans;
			while(true){
				ans = JOptionPane.showInputDialog(null, "Please enter the user name for player "+num);
				if(ans == null){
					System.exit(0);
				}
				else if(ans.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid name");
					continue;
				}
				else if(isIn(playerNames,i,ans)){
					JOptionPane.showMessageDialog(null, "Name already taken!");
					continue;
				}
				break;
			}
			playerNames[i] = ans;
		}
		for(int i = 0; i < PLAYERNUM; i++)
		{
			score.add(0);
		}
			
		g = new Game();
		g.setVisible(true);
	}

	private static boolean isIn(String[] playerNames2, int i, String ans) {
		for(int j=0; j<i; j++){
			if(playerNames2[j].equals(ans))
				return true;
		}
		return false;
	}

	/**
	 * This method will respond to the Roll button. You will need to write this code so your version
	 * works like the one in the example.
	 *
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("Roll")){
			rand = new Random();
			int diceNumber = rand.nextInt(6)+1;
			JOptionPane.showMessageDialog(this, board.turn() + " rolled "+diceNumber);
			board.doMove(diceNumber);
			
			if(board.hitFinish()){
				score.set(board.turnIndex(), score.get(board.turnIndex()) + 1);
				
				int replay = JOptionPane.showConfirmDialog(this, "Do you want to play another game?" + System.lineSeparator() 
				+ updateScores(), "Play Again?", 
						JOptionPane.YES_NO_OPTION);
				if(replay == JOptionPane.YES_OPTION){
					g.dispose();
					g.setVisible(false);
					g = new Game();
					g.setVisible(true);
				}
				else{
					System.exit(0);
				}
			}
			
			this.remove(gbp);
			gbp = new GameBoardPanel(board);
			this.add(gbp,BorderLayout.CENTER);
			this.add(makeControl(),BorderLayout.SOUTH);
			gbp.update();
			this.update(this.getGraphics());
		}
		//take care of moving the player around and updating the board!
		//this.update(this.getGraphics());
	}
	
	/**
	 * method to print out the scores
	 * @return
	 */
	public String updateScores()
	{
		String s = "";
		for(int i = 0; i < score.size(); i++)
		{
			s += playerNames[i] + " has won " + score.get(i) + " game(s)" + System.lineSeparator();
		}
		return s;
	}

}
