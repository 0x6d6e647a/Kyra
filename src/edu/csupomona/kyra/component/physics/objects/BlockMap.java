package edu.csupomona.kyra.component.physics.objects;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
 
public class BlockMap {
	TiledMap tmap;
	int mapWidth;
	int mapHeight;
	int square[] = {1,1,31,1,31,31,1,31}; //square shaped tile
	ArrayList<Block> blocks;
 
	public BlockMap(TiledMap map) throws SlickException {
		blocks = new ArrayList<Block>();
		tmap = map;
		mapWidth = tmap.getWidth() * tmap.getTileWidth();
		mapHeight = tmap.getHeight() * tmap.getTileHeight();
 
		for (int x = 0; x < tmap.getWidth(); x++) {
			for (int y = 0; y < tmap.getHeight(); y++) {
				int tileID = tmap.getTileId(x, y, 0);
				if (tileID == 1) {
					blocks.add(new Block(x * 32, y * 32, square, "square"));
				}
			}
		}
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
}
