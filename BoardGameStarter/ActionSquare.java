import java.util.Random;

import javax.swing.JOptionPane;

public class ActionSquare extends Square{
	private String label;
	private Random rand;
	
	public ActionSquare(String label) {
		super();
		this.label = label;
	}

	

	@Override
	public void doAction() {
		if(label.equals("Roll Again")){
			JOptionPane.showMessageDialog(null, "It's your turn to roll again!");
		}
		else if(label.equals("Go Back")){
			board.goBack();
			board.next();
		}
		else if(label.equals("Shuffle")){
			board.shuffle();
			board.next();
		}
		else if(label.contains("Save Point")){
			String number = label.substring(label.length() - 1);
			board.savePoint(this);
			board.next();
		}
		else if(label.equals("Finish")){
			JOptionPane.showMessageDialog(null, "*Celine* wins!");
			System.exit(0);
		}
	}

	@Override
	public String getLabel() {
		return label;
	}

}
