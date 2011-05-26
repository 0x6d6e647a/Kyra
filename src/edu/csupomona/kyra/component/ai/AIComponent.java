package edu.csupomona.kyra.component.ai;

import java.util.ArrayList;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.physics.objects.Block;
import edu.csupomona.kyra.component.physics.objects.BlockMap;
import edu.csupomona.kyra.entity.Entity;

public abstract class AIComponent extends Component {
	Entity player1;
	Entity player2;
	BlockMap map;
	ArrayList<String> actions;

	public AIComponent(String id, Entity player1, Entity player2, TiledMap map) {
		super(id);
		this.player1 = player1;
		this.player2 = player2;
		this.map = new BlockMap(map);
	}
	
	protected Line getLineToPlayer(Entity player) {
		return new Line(owner.getPosition(), player.getPosition());
	}
		
	protected boolean clearPathToPlayer(Line lineToPlayer) {
		for (Block block : map.getBlocks()) {
			if (lineToPlayer.intersects(block.getPolygon()))
				return false;
		}
		return true;
	}
	
	public Line getLineToTarget() {
		Line p1Line = getLineToPlayer(player1);
		if (Kyra.vs) {
			Line p2Line = getLineToPlayer(player2);
			if (clearPathToPlayer(p1Line) && !clearPathToPlayer(p2Line))
				return p1Line;
			else if (!clearPathToPlayer(p1Line) && clearPathToPlayer(p2Line))
				return p2Line;
			else if (clearPathToPlayer(p1Line) && clearPathToPlayer(p2Line)) {
				if (p1Line.length() < p2Line.length())
					return p1Line;
				else
					return p2Line;
			}
			else
				return null;
			
		}
		if (clearPathToPlayer(p1Line))
			return p1Line;
		else
			return null;
	}
	
	public ArrayList<String> getActions() {
		return actions;
	}
}
