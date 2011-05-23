package edu.csupomona.kyra.component.input;

import java.util.Enumeration;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;

public class PlayerInput extends InputComponent{
	public PlayerInput(String id) {
		super(id);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Input input = gc.getInput();
		
		for (Enumeration<String> e = buttonMappings.keys(); e.hasMoreElements(); ) {
			String buttonName = e.nextElement();
			int buttonCode = buttonMappings.get(buttonName);
			if (input.isKeyDown(buttonCode)) 
				buttonStates.put(buttonName, true);
			else
				buttonStates.put(buttonName, false);
			
		}
		
	}
}
