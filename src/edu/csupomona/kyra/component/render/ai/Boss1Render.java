package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.ImageRender;

public class Boss1Render extends ImageRender {
	Image bossRight, bossLeft;

	public Boss1Render(String id) {
		super(id);
		try {
			bossRight = new Image("img/boss-1-right.png");
			bossLeft = new Image("img/boss-1-left.png");
		}
		catch (SlickException e) {
			System.out.println("Unable to load boss 1 images!");
			System.exit(1);
		}
		super.setImage(bossLeft);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
//		for (String action : owner.getAIComponent().getActions()) {
//			if (action.equals("right"))
//				setImage(bossRight);
//			if (action.equals("left"))
//				setImage(bossLeft);
//			else {
//				Direction direction = owner.getXDirection();
//				if (direction.equals(Direction.RIGHT))
//					setImage(bossRight);
//				else
//					setImage(bossLeft);
//			}
//		}
	}

}
