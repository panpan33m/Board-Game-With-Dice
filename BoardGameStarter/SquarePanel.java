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
 * that is responsible for displaying the appropriate information about each Square
 * @author jburge
 *
 */
public class SquarePanel extends JPanel {
	
	private Square model;
	private JPanel subPanel;
	private JLabel name;
	private Color color;
	
	public SquarePanel(Square s) {
		super();
		model = s;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		
		topPanel.add(new JLabel(s.getLabel()));
		
		subPanel = new JPanel();
		
		if(s.getLabel().equals("Start"))
		{
			topPanel.setBackground(Color.GREEN);
			subPanel.setBackground(Color.GREEN);
			
		}
		else if(s.getLabel().equals("Roll Again"))
		{
			topPanel.setBackground(Color.PINK);
			subPanel.setBackground(Color.PINK);		
		}
		else if(s.getLabel().equals("Go Back")){
			topPanel.setBackground(Color.RED);
			subPanel.setBackground(Color.RED);	
		}
		else if(s.getLabel().equals("Shuffle")){
			topPanel.setBackground(Color.ORANGE);
			subPanel.setBackground(Color.ORANGE);	
		}
		else if(s.getLabel().contains("Save Point")){
			topPanel.setBackground(Color.CYAN);
			subPanel.setBackground(Color.CYAN);	
		}
		else if(s.getLabel().equals("Finish")){
			topPanel.setBackground(Color.YELLOW);
			subPanel.setBackground(Color.YELLOW);		
		}
		else
		{
			topPanel.setBackground(Color.WHITE);
			subPanel.setBackground(Color.WHITE);
		}
		
		this.add(topPanel,BorderLayout.NORTH);
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
