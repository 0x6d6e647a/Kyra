/**************************************************************
 *	file:		InputComponent.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for Controls
**************************************************************/
package edu.csupomona.kyra.component.input;

import java.util.Hashtable;
import edu.csupomona.kyra.component.Component;

public abstract class InputComponent extends Component {
	protected Hashtable<String, Integer> buttonMappings = null;
	protected Hashtable<String, Boolean> buttonStates = null;
	
	public InputComponent(String id) {
		super(id);
		buttonMappings = new Hashtable<String, Integer>();
		buttonStates = new Hashtable<String, Boolean>();
	}
	
	public InputComponent(String id, Hashtable<String, Integer> buttonMappings) {
		super(id);
		this.buttonMappings = buttonMappings;
	}
	
	//Adds buttons
	public void addButton(String buttonName, int buttonCode) {
		if(buttonMappings.get(buttonName) == null)
			buttonMappings.put(buttonName, buttonCode);
	}
	
	//Changes the buttons
	public void changebutton(String buttonName, int buttonCode) {
		buttonMappings.put(buttonName, buttonCode);
	}
	
	//Sets the buttonMappings
	public void setButtonMappings(Hashtable<String, Integer> buttonMappings) {
		this.buttonMappings = buttonMappings;
	}
	
	//Returns a hashtable of the buttonstates
	public Hashtable<String, Boolean> getButtonStates() {
		return buttonStates;
	}
	
	//Returns if the button is pressed
	public boolean isPressed(String buttonName) {
		return buttonStates.get(buttonName);
	}
}
