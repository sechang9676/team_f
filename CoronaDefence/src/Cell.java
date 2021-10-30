import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Cell {
	public Image cellImage;
	public String cellID;
	public int hp;
	public int level;
	public int attackForce;
	public int attackSpeed;
	public String name;
	public int posX,posY;
	

	public Cell(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public void setImage() {
		try {
			if(cellID == "immunityCell") {
				cellImage = ImageIO.read(new File("src/immunityCell.gif"));
				cellImage = cellImage.getScaledInstance(Block.blockSize, Block.blockSize, cellImage.SCALE_SMOOTH);
			}else if(cellID=="muscleCell") {
				cellImage = ImageIO.read(new File("src/immunityCell.gif"));
				cellImage = cellImage.getScaledInstance(Block.blockSize, Block.blockSize, cellImage.SCALE_SMOOTH);
			}else {
				System.out.println("cellID error!!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g,Screen screen) {
		g.drawImage(cellImage,posX,posY,screen);
	}
}
