import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame() {
		setTitle("�ڷγ����潺");
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(new Screen());
	}
}
