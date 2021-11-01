import java.awt.Graphics;
import java.util.ArrayList;

public class Room {
	public int blockSize = 40;
	public int worldWidth = 12;
	public int worldHeight = 8;
	
//	public ArrayList<Block> block = new ArrayList<Block>();
	
	public Block[][] block;
	public int mapX = 50, mapY = 40;

	public Room() {
		define();
	}

	public void define() {
		block = new Block[worldHeight][worldWidth];

		for (int y = 0; y < worldHeight; y++) {
			for (int x = 0; x < worldWidth; x++) {
				block[y][x] = new Block(x*blockSize + mapX,y*blockSize+mapY,Value.block,Value.air);
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int y = 0; y<block.length; y++) {
			for(int x=0;x<block[0].length;x++) {
				block[y][x].draw(g);
			}
		}
	}
}
