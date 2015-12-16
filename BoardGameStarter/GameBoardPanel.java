import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class is responsible for displaying the grid of squares. You do not need to modify this code to
 * get the basic game working (you may want to change it for some of the extras)
 * @author jburge
 *
 */
public class GameBoardPanel extends JPanel {


	private ArrayList<SquarePanel> tiles = new ArrayList<SquarePanel>();
	
	private ArrayList<SquarePanel> emptyPanels = new ArrayList<SquarePanel>();

	/**
	 * Create the board and display the squares. 
	 * @param b the board
	 */
	public GameBoardPanel(Board b) {

		this.setLayout(new GridLayout(Board.BOARDSIZE,Board.BOARDSIZE));
		Square[][] squares = b.createBoard();
		for (int y=Board.BOARDSIZE-1; y>=0; --y) 
		{
			for (int x=0; x<Board.BOARDSIZE; ++x) 
			{
				if (squares[x][y]!=null) 
				{
					SquarePanel sp = new SquarePanel(squares[x][y]);
					tiles.add(sp);
					this.add(sp);
				}
				else 
				{
					this.add(new JPanel());
					//this.setBackground(Color.BLUE);
				}
			}
		}

	}
	

	/**
	 * Updates the board as players move around
	 */
	public void update() {
		for (SquarePanel p: tiles) {
			p.update();
		}
		validate();
		repaint();
	}
	
}
