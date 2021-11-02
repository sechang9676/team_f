import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	public Thread thread = new Thread(this);

	public static Image[] tileset_ground = new Image[100];
	public static Image[] tileset_air = new Image[100];
	public static Image[] tileset_res = new Image[100];
	public static Image[] tileset_mob = new Image[100];
	public static Image[] tileset_boss = new Image[100];

	public static int myWidth, myHeight;
	public static boolean isFirst = true;
	public static boolean isPlay = false;
	public static int killCount = 0;
	public static int score = 0;

	public static Room room;
	public static Save save;
	public static Point mse = new Point(0, 0); // current mouse point
	public static Store store;
	public static Mob[] mobs = new Mob[100];

	public int mobNumber = Screen.level * 3; 
	public int mobSpawned = 0;

	public static int coinage = 25, health = 10;

	public static int level = 1;
	public static int lastRound = 3;
	
	public Random randMob = new Random(System.currentTimeMillis()); // create random number of mob random spawn 
	
	public JButton back_btn = new JButton();
	public Image back_img;
	
	public File audioFile;
	
	public Screen(Frame frame) {
		this.setLayout(null);
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		
		//--------------back button-----------------
		//TODO setImage at button
		back_btn.setSize(50,30);
		back_btn.setLocation(50, 25);
		back_btn.setBorderPainted(false);
		back_btn.setContentAreaFilled(false);
		URL back_btnLoc = getClass().getResource("Textures/back_btn.png");
		back_btn.setIcon(new ImageIcon(back_btnLoc));
		add(back_btn);
		back_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.change("menu_screen");
				thread.suspend();
			}
		});
		//--------------------------------------------
		thread.start();
	}

	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();

		// --------tileset_ground---------------------------------------
		URL groundBlockLoc = getClass().getResource("Textures/block.png");
		tileset_ground[0] = new ImageIcon(groundBlockLoc).getImage();
		URL groundRoadLoc = getClass().getResource("Textures/road.png");
		tileset_ground[1] = new ImageIcon(groundRoadLoc).getImage();

		// --------tileset_air--------------------------------------
		URL airHome = getClass().getResource("Textures/home.png");
		tileset_air[0] = new ImageIcon(airHome).getImage();
		URL airTrash = getClass().getResource("Textures/trash.png");
		tileset_air[1] = new ImageIcon(airTrash).getImage();
		URL airCellA = getClass().getResource("Textures/cellA.png");
		tileset_air[2] = new ImageIcon(airCellA).getImage();
		URL airCellB = getClass().getResource("Textures/cellB.png");
		tileset_air[3] = new ImageIcon(airCellB).getImage();

		// ----------tileset_res----------------------------------
		URL resCell = getClass().getResource("Textures/Cell.png");
		tileset_res[0] = new ImageIcon(resCell).getImage();
		URL resCoin = getClass().getResource("Textures/coin.png");
		tileset_res[1] = new ImageIcon(resCoin).getImage();
		URL resHeart = getClass().getResource("Textures/heart.png");
		tileset_res[2] = new ImageIcon(resHeart).getImage();

		// ----------tileset_mob-----------------------------------
		URL mobA = getClass().getResource("Textures/enemyA.png");
		tileset_mob[0] = new ImageIcon(mobA).getImage();
		URL mobB = getClass().getResource("Textures/enemyB.png");
		tileset_mob[1] = new ImageIcon(mobB).getImage();
		URL mobG = getClass().getResource("Textures/enemyG.png");
		tileset_mob[2] = new ImageIcon(mobG).getImage();
		URL mobD = getClass().getResource("Textures/enemyD.png");
//		System.out.println(mobD.toString());
		tileset_mob[3] = new ImageIcon(mobD).getImage();
		//---------------Mission Load-----------------------------
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("Mission");
		save.loadSave(stream);
		//--------------------------------------------------------
		
		for (int i = 0; i < mobs.length; i++) {
			mobs[i] = new Mob();
		}
	}

	public void paintComponent(Graphics g) {
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();

			isFirst = false;

		}
		g.setColor(new Color(60, 60, 60));
		g.fillRect(0, 0, getWidth(), getHeight());
//		g.setColor(new Color(0, 0, 0));
		g.drawLine(room.block[0][0].x - 1, 0, room.block[0][0].x - 1,
				room.block[room.worldHeight - 1][0].y + room.blockSize);// Drawing Left Line
		g.drawLine(room.block[0][room.worldWidth - 1].x + room.blockSize, 0,
				room.block[0][room.worldWidth - 1].x + room.blockSize,
				room.block[room.worldHeight - 1][0].y + room.blockSize);// Drawing Right Line
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight - 1][0].y + room.blockSize,
				room.block[0][room.worldWidth - 1].x + room.blockSize,
				room.block[room.worldHeight - 1][0].y + room.blockSize);// Drawing Bottom Line

		room.draw(g);// Draw the Room(MAP)

		for (int i = 0; i < mobs.length; i++) {
			if (mobs[i].inGame) {
				mobs[i].draw(g);
			}

			store.draw(g);// Draw the Store

			if (health < 1) {
				g.setColor(new Color(0, 0, 0));
				g.fillRect(0, 0, myWidth, myHeight);
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Game Over!!", 300, getHeight()/2); // Set Game over text to be in the middle of the Window!
			}else {
				//-----------score draw--------------
				g.setFont(new Font("Arial", Font.BOLD, 14));
				g.drawString("Score: " + Screen.score, 700,40);
			}
			
			//-----------game clear draw---------------------
			if(level > lastRound) {
				g.setColor(new Color(0, 0, 0));
				g.fillRect(0, 0, myWidth, myHeight);
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Game Clear!!", 300, getHeight()/2);
			}
			//----------------------------------------------
		}
		
		
	}

	public static int fpsFrame = 0, fps = 1000000;

	public int spawnTime = 2000, spawnFrame = 0;

	//---------------mob Spawner-----------------------------------------------------------
	public void mobSpawner() {
		if (mobSpawned <= mobNumber) {
			if (spawnFrame >= spawnTime) {
				for (int i = 0; i < mobs.length; i++) {
					if (!mobs[i].inGame) {
//						if (mobSpawned == mobNumber) {
//							mobs[mobNumber].towerDamage = 5;// Bosses Damage to our health from getting home
//							mobs[mobNumber].spawnMob(Value.mobB, 1000 * level, 22 * level, 10, (25 / Screen.level));
//							System.out.println("Corona-Beta Spawned! " + "Health: " + mobs[mobNumber].health
//									+ " Tower Damage: " + mobs[mobNumber].towerDamage + " Count: " + mobSpawned
//									+ " At Speed " + mobs[mobNumber].walkSpeed);
//
//						} else {
//							mobs[i].towerDamage = 15;// Normal creep Damage to our health from getting home
//							mobs[i].spawnMob(randMob.nextInt(4), 1000 * level, 22 * level, 5, (25 / Screen.level));
//							System.out.println("Corona-Alpha Spawned! " + "Health: " + mobs[i].health + " Tower Damage: "
//									+ mobs[i].towerDamage + " Count: " + mobSpawned + " At Speed " + mobs[i].walkSpeed);
//						}
						
						//------------------monster random spawn-------------------------------------------
						mobs[i].towerDamage = 10;
						mobs[i].spawnMob(randMob.nextInt(4), 1000*level, 22*level, 5, (25/level));
						//---------------------------------------------------------------------------------
						mobSpawned += 1;
						break;
					}
				}

				spawnFrame = 0;
			} else {
				spawnFrame += 1;
			}
		}
	}
//----------------------mob Spawner end-------------------------------------------------------------------------------------------
	

	public void run() {
		while (true) {
			if (!isFirst && health > 0) {
				room.physic();
				mobSpawner();
				for (int i = 0; i < mobs.length; i++) {
					if (mobs[i].inGame) {
						mobs[i].physic();
					}
				}
				if (killCount == mobNumber+1 ) {
					level += 1;
					killCount = 0;
					mobSpawned = 0;
				}
			}
			
			//-------game stop---------------
			if(level > lastRound || health <= 0) {
				thread.stop();
			}
			//-------------------------------

			repaint();

			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}

	}
}
