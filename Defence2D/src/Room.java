import java.awt.Graphics;
import java.util.ArrayList;

public class Room {
	public int blockSize = 40;
	public int[][] map = {{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0}};
	public ArrayList<Block> block = new ArrayList<Block>();
	
	public int mapX = 50, mapY = 40;

	public Room() {
		define();
	}

	public void define() {

		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				if(map[y][x] == 0) {
					block.add(new Block(x*blockSize + mapX,y*blockSize+mapY,Value.block,Value.air));	
				}else if(map[y][x] == 1) {
					block.add(new Block(x*blockSize + mapX,y*blockSize+mapY,Value.road,Value.air));
				}else {
					
				}
				

			}
		}
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < block.size();i++) {
			block.get(i).draw(g);
		}
	}
}
