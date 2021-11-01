import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.InputStream;

import javax.swing.JPanel;

public class Screen extends JPanel {
	
	
	public static Point mse = new Point(0,0);
	public static int myWidth, myHeight;
	public boolean isFirst = true;
	
	public static int killCount = 0;


	public static Room room;
	public static Save save;
	public static Store store;
	
	public static int coinage = 25, health = 100;
	
	public static int level = 1;
	
	public Screen() {
		this.addMouseListener(new KeyHandel());
		this.addMouseMotionListener(new KeyHandel());
	}
	
	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();
		
		
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("Mission");
		
		save.loadSave(stream);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		if(isFirst) {
			myWidth = getWidth();
//			System.out.println(myWidth);
			myHeight = getHeight();
			define();
			
			isFirst = false;
		}
		g.setColor(new Color(60,60,60));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0,0,0));
		room.draw(g);
		
		store.draw(g);
	}
	
	
}
