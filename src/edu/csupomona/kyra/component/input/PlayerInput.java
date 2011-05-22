package edu.csupomona.kyra.component.input;

import java.util.Enumeration;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;

public class PlayerInput extends InputComponent{
	public PlayerInput(String id) {
		super(id);		
		buttonMappings.put("up", Input.KEY_UP);
		//buttonMappings.put("down", Input.KEY_DOWN);
		buttonMappings.put("left", Input.KEY_A);
		buttonMappings.put("right", Input.KEY_S);
		//buttonMappings.put("action", Input.KEY_X);
		buttonMappings.put("jump", Input.KEY_D);
		buttonMappings.put("pause", Input.KEY_ENTER);
		buttonMappings.put("attack", Input.KEY_F);
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
