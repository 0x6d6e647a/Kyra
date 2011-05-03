package edu.csupomona.kyra.component.input;

import java.util.Hashtable;
import edu.csupomona.kyra.component.Component;

public abstract class InputComponent extends Component {
	protected Hashtable<String, Integer> buttonMappings = null;
	protected Hashtable<String, Boolean> buttonStates = null;
	
	public InputComponent(String id) {
		super.id = id;
		buttonMappings = new Hashtable<String, Integer>();
		buttonStates = new Hashtable<String, Boolean>();
	}
	
	public InputComponent(String id, Hashtable<String, Integer> buttonMappings) {
		super.id = id;
		this.buttonMappings = buttonMappings;
	}
	
	public void addButton(String buttonName, int buttonCode) {
		if(buttonMappings.get(buttonName) == null)
			buttonMappings.put(buttonName, buttonCode);
	}
	
	public void changebutton(String buttonName, int buttonCode) {
		buttonMappings.put(buttonName, buttonCode);
	}
	
	public void setButtonMappings(Hashtable<String, Integer> buttonMappings) {
		this.buttonMappings = buttonMappings;
	}
	
	public Hashtable<String, Boolean> getButtonStates() {
		return buttonStates;
	}
	
	public boolean isPressed(String buttonName) {
		return buttonStates.get(buttonName);
	}
}
