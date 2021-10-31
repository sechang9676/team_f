import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Screen extends JPanel {
	
	public static Point mse = new Point(0,0);
	public static int myWidth, myHeight;
	public boolean isFirst = true;
	
	public static Room room;
	
	
	public Screen(Frame frame) {
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		
		
	}
	
	public void define() {
		room = new Room();
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
		
		room.draw(g);
	}
	
	
}
