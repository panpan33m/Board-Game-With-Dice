import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The Square class is a data class. The SquarePanel class is a boundary class
 * that is responsible for displaying the appropriate informaiton about each Square
 * @author jburge
 *
 */
public class SquarePanel extends JPanel {
	
	private Square model;
	private JPanel subPanel;
	private JLabel name;
	
	public SquarePanel(Square s) {
		super();
		model = s;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		
		topPanel.add(new JLabel(s.getLabel()));
		this.add(topPanel,BorderLayout.NORTH);
		
		subPanel = new JPanel();
		this.add(subPanel,BorderLayout.CENTER);
		update();
		
	}

	/**
	 * Updates all the squares so the player labels are in the right spot
	 */
	public void update() {
		subPanel.removeAll();
		//TODO: you will need to update all the player labels here so the
		subPanel.setLayout(new FlowLayout());
		for(int i=0; i<model.sizePlayer(); i++)
		{
			name = new JLabel(model.getPlayer(i));
			int j = model.board.getPlayerNum(model.getPlayer(i));
			switch (j){
				case 0:
					name.setForeground(Color.RED);
					break;
				case 1:
					name.setForeground(Color.BLUE);
					break;
				case 2:
					name.setForeground(Color.YELLOW);
					break;
				case 3:
					name.setForeground(Color.GREEN);
					break;
			}
			subPanel.add(name);
		}
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * This method is just for testing.
	 * @param args
	 */
	public static void main(String [] args) {
		JFrame frame = new JFrame("Hello");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200,200);
		frame.setLayout(new GridLayout(2,1));
		frame.add(new SquarePanel(new PlainSquare()));
		frame.add(new SquarePanel(new PlainSquare()));
		frame.setVisible(true);
	}

}
