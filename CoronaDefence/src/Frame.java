import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame() {
		setTitle("코로나디펜스");
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(new Screen());
	}
}
