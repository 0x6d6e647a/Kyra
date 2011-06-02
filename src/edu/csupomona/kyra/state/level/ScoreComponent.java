package edu.csupomona.kyra.state.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.Component;

public class ScoreComponent extends Component {
	
	long score;

	public ScoreComponent(String id) {
		super(id);
		score = 0;
	}
	
	public long getScore() {
		return score;
	}
	
	public void addToScore(long score) {
		this.score += score;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {

	}

}
