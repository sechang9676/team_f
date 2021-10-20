import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;

public class Store {
	private int shopWidth = 8;
	private int storeX = 130 , storeY = 410;
	private int boxSize = 40;
	public JButton[] storeButton = new JButton[shopWidth];
	
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public Store() {
		
	}
	
	public void draw(Graphics g, Screen screen) {
		
		for(int i = 0; i < storeButton.length; i++) {
			storeButton[i] = new JButton();
			storeButton[i].setBounds(storeX+(i*boxSize), storeY, boxSize, boxSize);
			screen.add(storeButton[i]);
		}
		
	}
	
	
}
