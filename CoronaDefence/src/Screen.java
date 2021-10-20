import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel {
	
	public int[][] map = {{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0}};
	
	private int mapX = 50, mapY = 40;
	
	public Block block;
	public Tower tower;
	public Store store;
	public Screen() {
	}
	
	public void define() {
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		block = new Block();
		tower = new Tower();
		store = new Store();
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				block.setPosition(x*block.blockSize + mapX, y*block.blockSize + mapY);
				block.draw(g, this, map[y][x]);
			}
		}
		tower.draw(g, this);
		store.draw(g, this);
	}
	
	
}
