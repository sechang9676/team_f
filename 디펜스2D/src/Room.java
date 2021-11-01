import java.awt.*;

public class Room {
	public int worldWidth = 12;
	public int worldHeight = 8;
	public int blockSize = 52;

	public Block[][] block;
	public int mapX = 85, mapY = 60;
	
	public Room() {
		define();
	}

	public void define() {
		block = new Block[worldHeight][worldWidth];

		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
//				block[y][x] = new Block((Screen.myWidth / 2) - ((worldWidth * blockSize) / 2) + (x * blockSize),
//						y * blockSize, blockSize, blockSize, Value.groundBlock, Value.airAir);
				block[y][x] = new Block(x*blockSize + mapX,y*blockSize+mapY,blockSize,blockSize,Value.groundBlock,Value.airAir);

			}

		}
	}

	public void physic() {
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].physic();
			}
		}

	}

	public void draw(Graphics g) {
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].draw(g);
			}
		}
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].fight(g);
			}
		}

	}
}
