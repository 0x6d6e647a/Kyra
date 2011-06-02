package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.ImageRender;

public class Boss2Render extends ImageRender {
	
	Image standRight, standLeft, jumpRight, jumpLeft;
	
	public Boss2Render(String id) {
		super(id);
		try {
			standRight = new Image("img/boss-2-move-right_001.png");
			standLeft = new Image("img/boss-2-move-left_001.png");
			jumpRight = new Image("img/boss-2-fly-right.png");
			jumpLeft = new Image("img/boss-2-fly-left.png");
		}
		catch (SlickException e) {
			System.out.println("Unable to load boss 2 images!");
			System.exit(1);
		}
		super.setImage(standLeft);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Direction dir = owner.getXDirection();
		boolean onFloor = owner.getPhysicsComponent().onFloor;
		if (dir.equals(Direction.RIGHT)) {
			if (onFloor)
				setImage(standRight);
			else
				setImage(jumpRight);
		}
		else {
			if (onFloor)
				setImage(standLeft);
			else
				setImage(jumpLeft);
		}
	}

}
