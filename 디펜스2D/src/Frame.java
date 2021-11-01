import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
//	public Screen screen = new Screen(this);
	public MenuScreen menu_screen = new MenuScreen(this);
	
	public Frame() {
		setTitle(title);
		setSize(size);

		setResizable(false); // ������ ���� �Ұ�
		setLocationRelativeTo(null); // �������� ����� �������� ��ġ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X ��ư�� ������ ������ �� ����

		menu_screen.setLayout(null);
		
		init(); // �����ӿ� �г��� �����ϴ� �Լ�
	}

	public static String title = "���潺2D";
	public static Dimension size = new Dimension(800, 650);

	public void init() {

//		setLayout(new GridLayout(1, 1, 0, 0)); // �׸��� ���̾ƿ����� ����

//		Screen screen = new Screen(this); // �г��� ����
		add(menu_screen); // �����ӿ� �г� �߰�

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
