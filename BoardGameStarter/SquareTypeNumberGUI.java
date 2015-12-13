import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class SquareTypeNumberGUI extends JPanel implements ActionListener {

	private boolean ok;
	private JDialog dialog = null;
	private JPanel centerPanel;
	private JPanel southPanel;
	private ArrayList<Integer> number;
	private JTextField raNum;
	private JTextField gbNum;
	private JTextField sNum;
	private JTextField spNum;
	private JButton OK;
	private int raNumInt;
	private int gbNumInt;
	private int sNumInt;
	private int spNumInt;

	public SquareTypeNumberGUI() {
		super();
		this.setLayout(new BorderLayout());
		int borderWidth = 5;
		this.setPreferredSize(new Dimension(450,180));

		number = new ArrayList<Integer>();
		for(int i=0; i<4;i++)
			number.add(0);

		centerPanel = new JPanel();		
		centerPanel.setLayout(new GridLayout(4,2));	
		centerPanel.add(new JLabel("Number of 'Roll Again' square: "));
		raNum = new JTextField();
		centerPanel.add(raNum);
		centerPanel.add(new JLabel("Number of 'Go Back' square: "));
		gbNum = new JTextField();
		centerPanel.add(gbNum);
		centerPanel.add(new JLabel("Number of 'Shuffle' square: "));
		sNum = new JTextField();
		centerPanel.add(sNum);
		centerPanel.add(new JLabel("Number of Save Points: "));
		spNum = new JTextField();
		centerPanel.add(spNum);
		this.add(centerPanel,BorderLayout.CENTER);

		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		OK = new JButton("OK");
		OK.setActionCommand("OK");
		OK.addActionListener(this);
		southPanel.add(OK);
		this.add(southPanel,BorderLayout.SOUTH);

		Border empty;
		empty = BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth);
		this.setBorder(empty);
	}

	public boolean showDialog(Component parent, String title)
	{
		ok = false;
		//find the owner
		Frame owner = null;
		if (parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		if (dialog == null || dialog.getOwner() != owner)
		{
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.setLocationRelativeTo(null);
			//	dialog.getRootPane().setDefaultButton(cancelButton);
			dialog.pack();
		}
		//set the title and show it
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == OK){
			try{
				raNumInt = Integer.parseInt(raNum.getText());				
				gbNumInt = Integer.parseInt(gbNum.getText());				
				sNumInt = Integer.parseInt(sNum.getText());				
				spNumInt = Integer.parseInt(spNum.getText());				
				if(raNumInt+gbNumInt+sNumInt+spNumInt>30)
					throw new Exception();
				else if(raNumInt<0 || gbNumInt<0 || sNumInt<0 || spNumInt<0)
					throw new NumberFormatException();
				else{
					number.set(0,raNumInt);
					number.set(1,gbNumInt);
					number.set(2,sNumInt);
					number.set(3,spNumInt);
					dialog.setVisible(false);
				}				
			}
			catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(this, "Invalid number.");
			}
			catch (Exception ex){
				JOptionPane.showMessageDialog(this, "The total number of squares can not exceed 30.");
			}
		}

	}

	public int getNumber(int n){
		return number.get(n);
	}

}
