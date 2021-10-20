import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Tower {
	private Image towerImage;
	private int towerX = 0, towerY = 40;
	public int towerWidth = 130, towerHeight = 360;
	
	public Tower() {
		try {
			towerImage = ImageIO.read(new File("src/tower.gif"));
			towerImage = towerImage.getScaledInstance(towerWidth, towerHeight, towerImage.SCALE_SMOOTH);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, Screen screen) {
		g.drawImage(towerImage, towerX,towerY,screen);
	}
}
