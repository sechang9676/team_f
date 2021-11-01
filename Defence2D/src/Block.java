import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Block extends Rectangle {
	public int x,y; // block position
	public int groundID; // how block? - 1.block 2.road
	public int airID; // item create on the block
	public Image blockImage;
	public int blockSize = 40;

	public Block(int x, int y, int groundID, int airID) {
		setBounds(x,y,blockSize,blockSize);
		this.groundID = groundID;
		this.airID = airID;
		this.x = x;
		this.y = y;
	}
	
	public void imageRoad() {
		try {
			if(groundID == Value.road) {
				blockImage = ImageIO.read(new File("src/road.gif"));
				blockImage = blockImage.getScaledInstance(blockSize, blockSize, blockImage.SCALE_SMOOTH);
			}else if(groundID == Value.block) {
				blockImage = ImageIO.read(new File("src/block.png"));
				blockImage = blockImage.getScaledInstance(blockSize, blockSize, blockImage.SCALE_SMOOTH);
			}else {
				System.out.println("block create error!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void define() {
	}
	
	public void draw(Graphics g) {
		imageRoad();
		//draw block
		g.drawImage(blockImage, x, y, blockSize, blockSize, null);
	}
}
