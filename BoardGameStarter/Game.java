import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 * Main frame for the Board Game.
 * @author jburge, apham, ctao
 *
 */
public class Game extends JFrame implements ActionListener {

	private Board board;

	private Random rand; //used to roll our dice
	private GameBoardPanel gbp;

	private JPanel bottom;

	private JButton roll;

	private JLabel turnLabel;

	private JPanel top;

	private JLabel player1;

	private JLabel player2;

	private JLabel player3;

	private JLabel player4;

	private JPanel makeCtrl;

	private JPanel matching;

	private JPanel totalPanel;

	private static String[] playerNames;
	
	private static int PLAYERNUM;
	
	/**
	 * ArrayList to keep track of scores
	 */
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

		//Asking user to input the number of each action square they would like to have on the board
		SquareTypeNumberGUI window = new SquareTypeNumberGUI();
		this.setVisible(false);
		window.showDialog(null, "Number of each type of square");
		this.setVisible(true);

		//Add the squares to the board according to the number entered by user
		board.addSquare(new ActionSquare("Start"));

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
		//add bottom panel
		makeCtrl = makeControl();
		matching = matching();
		this.add(gbp,BorderLayout.CENTER);
		this.add(makeCtrl,BorderLayout.SOUTH);
		this.add(matching, BorderLayout.NORTH);
		rand = new Random(); //used when rolling dice
		
		//play music in game background
	}
	
	/**
	 * Add icons to the game in place of names
	 * Player names with Icons legend at the top of the Game panel
	 * @return
	 */
	private JPanel matching(){
		top = new JPanel();
		top.setLayout(new GridLayout(1,8));

		//first player is a cat
		Image Pic1 = null;
		ImageIcon Icon1 = new ImageIcon("cat2.png");
		Pic1 = Icon1.getImage();
		Pic1 = Pic1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JLabel picLabel1 = new JLabel(new ImageIcon(Pic1));
		
		//second player is a duck
		Image Pic2 = null;
		ImageIcon Icon2 = new ImageIcon("duck.png");
		Pic2 = Icon2.getImage();
		Pic2 = Pic2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JLabel picLabel2 = new JLabel(new ImageIcon(Pic2));
		
		//third player is a sheep
		Image Pic3 = null;
		ImageIcon Icon3 = new ImageIcon("sheep.png");
		Pic3 = Icon3.getImage();
		Pic3 = Pic3.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JLabel picLabel3 = new JLabel(new ImageIcon(Pic3));
		
		//fourth player is a watermelon
		Image Pic4 = null;
		ImageIcon Icon4 = new ImageIcon("watermelon.png");
		Pic4 = Icon4.getImage();
		Pic4 = Pic4.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JLabel picLabel4 = new JLabel(new ImageIcon(Pic4));
		
		//Show players w/icons legend at top
		if(0<playerNames.length){
			player1 = new JLabel(playerNames[0]+": ");
			top.add(player1);
			top.add(picLabel1);
		}
		if(1<playerNames.length){
			player2 = new JLabel(playerNames[1]+": ");
			top.add(player2);
			top.add(picLabel2);
		}
		if(2<playerNames.length){
			player3 = new JLabel(playerNames[2]+": ");
			top.add(player3);
			top.add(picLabel3);
		}
		if(3<playerNames.length){
			player4 = new JLabel(playerNames[3]+": ");
			top.add(player4);
			top.add(picLabel4);
		}
		return top;
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

	/**
	 * main method that plays the game
	 * @param args
	 */
	public static void main(String [] args) {
		
		//Ask the user for number of players (2-4)
		try{
			String playerNum = JOptionPane.showInputDialog("Please enter the number of players(2-4): ");
			if(playerNum == null)
				System.exit(0);
			PLAYERNUM = Integer.parseInt(playerNum);
			if(PLAYERNUM<2 || PLAYERNUM>4)
				throw new Exception();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 4.");
		}

		playerNames = new String[PLAYERNUM];
		//Ask user to input names for each player
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
		//Begin each player with a score of 0
		for(int i = 0; i < PLAYERNUM; i++)
		{
			score.add(0);
		}

		//Begin the game
		music();
		g = new Game();
		g.setVisible(true);
		
	}

	/**
	 * boolean returns if a name is already taken by another player
	 * @param playerNames2
	 * @param i
	 * @param ans
	 * @return
	 */
	private static boolean isIn(String[] playerNames2, int i, String ans) 
	{
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

			//Move player one tile at a time
			for(int i=0; i<diceNumber; i++){
				board.doMove(1);
				gbp.update();
				this.repaint();
				this.update(this.getGraphics());
				try {
				    Thread.sleep(200);
				} 
				catch (InterruptedException ex) {
				    ex.printStackTrace();
				}
			}
			//do action of that square it finally lands on
			board.doAction();

			//if player lands on finish, update scores and ask if they want to play again, printing out scores too
			//if yes, restart game
			//if no, close game
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
			this.remove(matching);
			this.remove(makeCtrl);
			this.remove(gbp);
			gbp = new GameBoardPanel(board);
			makeCtrl = makeControl();
			matching = matching();
			this.add(gbp,BorderLayout.CENTER);
			this.add(makeCtrl,BorderLayout.SOUTH);
			this.add(matching, BorderLayout.NORTH);
			gbp.update();
			this.revalidate();
			this.update(this.getGraphics());
		}

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
	
	/**
	 * plays music in GUI
	 */
	public static void music() 
	{       
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		//AudioData MD;
		
		ContinuousAudioDataStream loop = null;

		//play a song! Frozen, for winter
		try
		{
			InputStream test = new FileInputStream("Let It Go.wav");
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
		}
		catch(FileNotFoundException e){
			System.out.print(e.toString());
		}
		catch(IOException error)
		{
			System.out.print(error.toString());
		}
		MGP.start(loop);
	}

}
