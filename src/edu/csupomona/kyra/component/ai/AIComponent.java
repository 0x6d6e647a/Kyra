package edu.csupomona.kyra.component.ai;

import java.util.ArrayList;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.physics.objects.Block;
import edu.csupomona.kyra.component.physics.objects.BlockMap;
import edu.csupomona.kyra.entity.Entity;

public abstract class AIComponent extends Component {
	Entity player;
	BlockMap map;
	ArrayList<String> actions;

	public AIComponent(String id, Entity player, TiledMap map) {
		super.id = id;
		this.player = player;
		this.map = new BlockMap(map);
	}
	
	protected Line getLineToPlayer() {
		return new Line(owner.getPosition(), player.getPosition());
	}
		
	protected boolean clearPathToPlayer() {
		Line lineToPlayer = getLineToPlayer();
		for (Block block : map.getBlocks()) {
			if (lineToPlayer.intersects(block.getPolygon()))
				return false;
		}
		return true;
	}
	
	public ArrayList<String> getActions() {
		return actions;
	}
}
