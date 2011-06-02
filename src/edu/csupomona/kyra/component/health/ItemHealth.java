package edu.csupomona.kyra.component.health;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class ItemHealth extends HealthComponent {
	
	public ItemHealth(String id) {
		super(id, 1);
	}
	
	public void useItem() {
		currHealth = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {

	}

}
