import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuScreen extends JPanel{

	public JButton start_btn = new JButton("Start");
	public JButton end_btn = new JButton("End");
	public MenuScreen(Frame frame) {
		
		start_btn.setSize(300,50);
		start_btn.setLocation(250, 250);
		add(start_btn);
		start_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.change("screen");
			}
		});
		end_btn.setSize(300,50);
		end_btn.setLocation(250, 350);
		add(end_btn);
		end_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	
	
	
	
	
}
