import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	private Graphics g;

	public SquarePanel(Square s) {
		super();
		model = s;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();

		Font font = new Font("Courier New", Font.BOLD, 13);

		JLabel newLabel = new JLabel(s.getLabel());
		newLabel.setFont(font);

		topPanel.add(newLabel);

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
			Font font2 = new Font("Garamond", Font.PLAIN, 17);

			name = new JLabel(model.getPlayer(i));
			int j = model.board.getPlayerNum(model.getPlayer(i));
			switch (j){
			case 0:
				Image Pic1 = null;
				ImageIcon Icon1 = new ImageIcon("cat2.png");
				Pic1 = Icon1.getImage();
				Pic1 = Pic1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				JLabel picLabel1 = new JLabel(new ImageIcon(Pic1));
				subPanel.add(picLabel1);
				break;
			case 1:
				Image Pic2 = null;
				ImageIcon Icon2 = new ImageIcon("duck.png");
				Pic2 = Icon2.getImage();
				Pic2 = Pic2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				JLabel picLabel2 = new JLabel(new ImageIcon(Pic2));
				subPanel.add(picLabel2);
				break;
			case 2:
				Image Pic3 = null;
				ImageIcon Icon3 = new ImageIcon("sheep.png");
				Pic3 = Icon3.getImage();
				Pic3 = Pic3.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				JLabel picLabel3 = new JLabel(new ImageIcon(Pic3));
				subPanel.add(picLabel3);
				break;
			case 3:
				Image Pic4 = null;
				ImageIcon Icon4 = new ImageIcon("watermelon.png");
				Pic4 = Icon4.getImage();
				Pic4 = Pic4.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				JLabel picLabel4 = new JLabel(new ImageIcon(Pic4));
				subPanel.add(picLabel4);
				break;
			}
			name.setFont(font2);
			//subPanel.add(name);
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
