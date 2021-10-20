package Design_;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


class drag_and_drop extends JFrame implements MouseListener{
	
	
	JPanel p = new JPanel();
	int x=0, y=0;
	
	public drag_and_drop(){
		setLayout(null);
		p.setBounds(x, y, 50, 50);
		p.setBackground(Color.RED);
		addMouseListener(this);
		add(p);
		
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// 마우스 버튼을 클릭했을 때의 동작 정의
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	// 마우스 포인터가 컨테이너/컴포넌트 영역 안에 있을 때의 동작 정의
	@Override
	public void mouseEntered(MouseEvent e) {
		p.setBounds(x, y, 50, 50);
		p.setBackground(Color.RED);
	}
	
	// 마우스 포인터가 컨테이너/컴포넌트 영역 밖에 있을 때의 동작 정의
	@Override
	public void mouseExited(MouseEvent e) {
		p.setBounds((600-200)/2, (600-200)/2, 200, 200);
		p.setBackground(Color.blue);
	}
	
	// 마우스 버튼을 누르고 있을 때의 동작 정의
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();	// 마우스 포인터의 X좌표
		y = e.getY();	// 마우스 포인터의 Y좌표
	
		p.setLocation(x,y);
		p.setBackground(Color.green);
	}

	// 마우스 버튼을 눌렀다가 떼었을 때의 동작 정의
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
