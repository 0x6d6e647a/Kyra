package edu.csupomona.kyra.component.physics.objects;

import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;
 
public class BlockMap {
	TiledMap tiledMap;
	int mapWidth, mapHeight;
	int square[] = {1,1,31,1,31,31,1,31}; //square shaped tile
	ArrayList<Block> blocks;
 
	public BlockMap(TiledMap tiledMap) {
		blocks = new ArrayList<Block>();
		this.tiledMap = tiledMap;
		mapWidth = tiledMap.getWidth() * tiledMap.getTileWidth();
		mapHeight = tiledMap.getHeight() * tiledMap.getTileHeight();
 
		for (int x = 0; x < tiledMap.getWidth(); x++) {
			for (int y = 0; y < tiledMap.getHeight(); y++) {
				int tileID = tiledMap.getTileId(x, y, 0);
				if (tileID == 1) {
					blocks.add(new Block(x * 32, y * 32, square, "square"));
				}
			}
		}
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public TiledMap getTiledMap() {
		return tiledMap;
	}
}
