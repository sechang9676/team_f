import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Block extends Rectangle {
	public Rectangle towerSquare;
	public int towerSquareSize = 130;
	public int x,y; // block position
	public int groundID; // how block? - 1.block 2.road
	public int airID; // item create on the block
	public Image blockImage;
	public int blockSize = 40;
	
	public int loseTime = 100, loseFrame = 0;
	public int shotEnemy = -1;
	public boolean isShooting = false;
	public int towerDamage = 1;

	public Block(int x, int y, int groundID, int airID) {
		setBounds(x,y,blockSize,blockSize);
		towerSquare = new Rectangle(x - (towerSquareSize/2), y- (towerSquareSize/2), width + (towerSquareSize), height + (towerSquareSize));
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
//				System.out.println("block create error!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(Graphics g) {
		imageRoad();
		//draw block
		g.drawImage(blockImage, x, y, blockSize, blockSize, null);
	}
	
	public void physic() {
		if(shotEnemy != -1 && towerSquare.intersects(Screen.enemys[shotEnemy])) {
			isShooting = true;
		}else {
			isShooting = false;
		}
		if(!isShooting) {
			if(airID == Value.immunity || airID == Value.muscle) {
				for(int i = 0; i < Screen.enemys.length; i++) {
					if(Screen.enemys[i].inGame) {
						if(towerSquare.intersects(Screen.enemys[i])) {
							isShooting = true;
							shotEnemy = i;
						}
					}
				}
			}
		}
		if(isShooting) {
			if(loseFrame >= loseTime) {
				if(airID == Value.immunity) {
					Screen.enemys[shotEnemy].slow();
					Screen.enemys[shotEnemy].gettingShot = true;
				}
				else {
					Screen.enemys[shotEnemy].loseHealth();
				}
				loseFrame = 0;
			}
			else {
				loseFrame += 1;
			}
			
			if(Screen.enemys[shotEnemy].isDead()&&!Screen.enemys[shotEnemy].inGame) {
				isShooting = false;
				shotEnemy = -1;
			}
		}
	}
	
	public void fight(Graphics g) {
		if(Screen.store.holdsItem) {
			if(airID == Value.immunity || airID == Value.muscle) {
				g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
			}
		}
		
		if(isShooting) {
			if(airID == Value.muscle) {
				g.setColor(new Color(240,20,20));
				g.drawLine(x + (width/2), y + (height/2), Screen.enemys[shotEnemy].x + (Screen.enemys[shotEnemy].width/2), Screen.enemys[shotEnemy].y + (Screen.enemys[shotEnemy].height/2));
			}else if(airID == Value.immunity) {
				g.setColor(new Color(150, 255, 255));
				g.drawLine(x + (width/2), y + (height/2), Screen.enemys[shotEnemy].x + (Screen.enemys[shotEnemy].width/2), Screen.enemys[shotEnemy].y + (Screen.enemys[shotEnemy].height/2));
			}
		}
	}
}
