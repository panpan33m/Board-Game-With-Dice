
public class ActionSquare extends Square{
	private String label;
	
	public ActionSquare(String label) {
		super();
		this.label = label;
	}

	

	@Override
	public void doAction() {
		if(label.equals("Roll Again")){
			
		}
		if(label.equals("Go Back")){
			
		}
		if(label.equals("Shuffle")){
			
		}
		if(label.contains("Save Point")){
			
		}
		if(label.equals("Finish")){
			
		}
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

}
