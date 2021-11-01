import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
//	public Screen screen = new Screen(this);
	public MenuScreen menu_screen = new MenuScreen(this);
	
	public Frame() {
		setTitle(title);
		setSize(size);

		setResizable(false); // 사이즈 조절 불가
		setLocationRelativeTo(null); // 윈도우의 가운데에 프레임을 배치함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼을 눌러서 종료할 수 있음

		menu_screen.setLayout(null);
		
		init(); // 프레임에 패널을 설정하는 함수
	}

	public static String title = "디펜스2D";
	public static Dimension size = new Dimension(800, 650);

	public void init() {

//		setLayout(new GridLayout(1, 1, 0, 0)); // 그리드 레이아웃으로 설정

//		Screen screen = new Screen(this); // 패널을 생성
		add(menu_screen); // 프레임에 패널 추가

		setVisible(true);
	}
	
	public void change(String panelName) {
		if(panelName.equals("screen")) {
			getContentPane().removeAll();
			getContentPane().add(new Screen(this));
			revalidate();
			repaint();
		}else if(panelName.equals("menu_screen")){
			getContentPane().removeAll();
			getContentPane().add(menu_screen);
			revalidate();
			repaint();
		}
	}

	public static void main(String[] agrs) {
		new Frame();
	}
	
}
