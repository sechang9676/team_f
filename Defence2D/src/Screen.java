import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.InputStream;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	public Thread thread = new Thread(this);
	
	public static Point mse = new Point(0,0);
	public static int myWidth, myHeight;
	public boolean isFirst = true;
	
	public static int killCount = 0;


	public static Room room;
	public static Save save;
	public static Store store;
	public static Enemy[] enemys = new Enemy[100];
	
	public int enemyNumber = Screen.level * 3;
	public int enemySpawned = 0;
	
	public static int coinage = 25, health = 100;
	
	public static int level = 1;
	
	public Screen() {
		this.addMouseListener(new KeyHandel());
		this.addMouseMotionListener(new KeyHandel());
		
		thread.start();
	}
	
	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();
		
		
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("Mission");
		
		save.loadSave(stream);
		
		for(int i = 0; i < enemys.length; i++) {
			enemys[i] = new Enemy();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		if(isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();
			
			isFirst = false;
		} 
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0,0,0));
		//---------------------------------
		
		room.draw(g); // draw map
		
		for(int i = 0; i < enemys.length; i++) {
			if(enemys[i].inGame) {
				enemys[i].draw(g);
			}
		}
		
		store.draw(g);
		
		//------------Game Over--------------------------
		if(health < 1) {
			g.setColor(new Color(240,20,20));
			g.fillRect(0,0,myWidth,myHeight);
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("Arial",Font.BOLD,14));
			g.drawString("Game Over!", 10, 20); 
		}
		//-----------------------------------------------
	}
	public static int fpsFrame = 0, fps = 1000000;
	public int spawnTime = 2000, spawnFrame = 0;
	
	public void enemySpawner() {
		if(enemySpawned <= enemyNumber) {
			if(spawnFrame >= spawnTime) {
				for(int i = 0; i < enemys.length; i++) {
					if(!enemys[i].inGame) {
						if(enemySpawned == enemyNumber) {
							enemys[enemyNumber].towerDamage = 5;
							enemys[enemyNumber].spawnEnemy(Value.enemyG, 1000*level, 22*level, 10, (25/Screen.level));
							System.out.println("Boss Spawned! "+"Health: "+enemys[enemyNumber].health+" Tower Damage: "+ enemys[i].towerDamage+" Count: "+enemySpawned+" At Speed: "+enemys[i].walkSpeed);
						}
						else {
							enemys[enemyNumber].towerDamage = 15;
							enemys[enemyNumber].spawnEnemy(Value.enemyG, 1000*level, 22*level, 5, (25/Screen.level));
							System.out.println("Normal Spawned! "+"Health: "+enemys[enemyNumber].health+" Tower Damage: "+ enemys[i].towerDamage+" Count: "+enemySpawned+" At Speed: "+enemys[i].walkSpeed);
						}
						enemySpawned += 1;
						break;
					}
				}
				spawnFrame = 0;
			}
			else {
				spawnFrame += 1;
			}
		}
	}
	
	public void run() {
		while(true) {
			if(!isFirst && health > 0) {
				room.physic();
				enemySpawner();
				for(int i = 0; i < enemys.length; i++) {
					if(enemys[i].inGame) {
						enemys[i].physic();
					}
				}
				if(killCount == enemyNumber+1) {
					level += 1;
					killCount = 0;
					enemySpawned = 0;
				}
			}
			repaint();
			
			try {
				Thread.sleep(1);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
