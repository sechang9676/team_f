import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuScreen extends JPanel{

	//-------------menu button image setting----------------------------------
	public ImageIcon startNormalIcon = new ImageIcon("./Resource/Textures/playbutton.gif");
	public ImageIcon startRollOverIcon = new ImageIcon("./Resource/Textures/playbutton_mouseOn.gif");
	public ImageIcon endNormalIcon = new ImageIcon("./Resource/Textures/endbutton.gif");
	public ImageIcon endRollOverIcon = new ImageIcon("./Resource/Textures/endbutton_mouseOn.gif");
	//---------------------------------------------------------------------------------
	public JButton start_btn = new JButton("",startNormalIcon);
	public JButton end_btn = new JButton("",endNormalIcon);
	public JLabel gameName = new JLabel();
	public MenuScreen(Frame frame) {
		gameName.setText("Corona Defence-2D");
		gameName.setSize(400, 50);
		gameName.setLocation(250, 90);
		gameName.setFont(new Font("Arial",Font.BOLD,30));
		add(gameName);
		start_btn.setSize(350,160);
		start_btn.setLocation(220, 150);
		start_btn.setBorderPainted(false);
		start_btn.setContentAreaFilled(false);
		start_btn.setFocusPainted(false);
		start_btn.setRolloverIcon(startRollOverIcon);
		add(start_btn);
		start_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.change("screen");
			}
		});
		end_btn.setSize(350,160);
		end_btn.setLocation(220, 350);
		end_btn.setBorderPainted(false);
		end_btn.setContentAreaFilled(false);
		end_btn.setFocusPainted(false);
		end_btn.setRolloverIcon(endRollOverIcon);
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
