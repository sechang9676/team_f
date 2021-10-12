import javax.swing.JFrame;

public class GameStartFrame extends JFrame {
	public GameStartFrame() {
		setTitle("Corona Defenct");
		setSize(800, 600);
		setVisible(true);
		
		add(new MainScreen());
	}
}
