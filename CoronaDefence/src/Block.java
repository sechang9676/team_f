import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Block extends Rectangle{
	private int x = 0;
	private int y = 0;
	private Image bodyImage;
	private Image roadImage;
	public static int blockSize = 40;
	public Block() {
		try {
			bodyImage = ImageIO.read(new File("src/block.png"));
			bodyImage = bodyImage.getScaledInstance(blockSize, blockSize, bodyImage.SCALE_SMOOTH);
			roadImage = ImageIO.read(new File("src/none.gif"));
			roadImage = roadImage.getScaledInstance(blockSize, blockSize, roadImage.SCALE_SMOOTH);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, Screen screen, int blockID) {
		
		if(blockID == 1 || blockID == 2) { // 바이러스가 지나가는 길
			g.drawImage(roadImage,x,y,screen);
		}else if(blockID == 0) { 
			g.drawImage(bodyImage, x, y, screen);
		}
		
	}
}
