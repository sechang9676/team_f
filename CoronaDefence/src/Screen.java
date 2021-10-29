import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Screen extends JPanel implements MouseMotionListener, MouseListener, ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource().toString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i<cellSpot.size();i++) {
			if(cellSpot.get(i).x < e.getX() && e.getX() < cellSpot.get(i).x+block.blockSize) {
				if(cellSpot.get(i).y < e.getY() && e.getY() < cellSpot.get(i).y+block.blockSize) {
//					System.out.println(cellSpot.get(i).toString());	
					cell.add(new ImmunityCell());
					
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int[][] map = {{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0},
			{-1,-1,1,1,1,1,1,1,1,1,1,1,2},
			{-1,-1,0,0,0,0,0,0,0,0,0,0,0}};
	public ArrayList<Rectangle> cellSpot = new ArrayList<Rectangle>();
	public ArrayList<Cell> cell = new ArrayList<>();
	
	private int mapX = 50, mapY = 40;
	
	public Block block;
	public Tower tower;
	public Store store;
	public Screen() {
		addMouseListener(this);
	}
	
	public void define() {
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		block = new Block();
		tower = new Tower();
		store = new Store();
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				block.setPosition(x*block.blockSize + mapX, y*block.blockSize + mapY);
				block.draw(g, this, map[y][x]);
				if(map[y][x] == 1) {
					cellSpot.add(new Rectangle(x*block.blockSize+mapX,y*block.blockSize+mapY,block.blockSize,block.blockSize)); // 몬스터가 나오는 위치정보를 cellSpot에 저장
				}
			}
		}
		System.out.println(cellSpot.get(0).toString());
		tower.draw(g, this);
		store.draw(g, this);
	}
	
	
}
