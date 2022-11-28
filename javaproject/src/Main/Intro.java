package Main;

import java.sql.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Intro extends JFrame implements Runnable {
	
	ImageIcon gif = new ImageIcon("image/Intro.gif");
	
	public Intro() {
		JLabel Intro_label = new JLabel();
			
		setTitle("Intro");//프레임 제목
		setSize(gif.getIconWidth(),gif.getIconHeight());
		setResizable(false);//프레임 크기 변경x
		setVisible(true);//프레임 보이기
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Intro_label.setIcon(gif);
		
		add(Intro_label);
		setLocationRelativeTo(null);

		Thread Intro = new Thread(this);
		Intro.setDaemon(true);
		Intro.start();
	}
	
	@Override
	public void run() {
		int n = 0;
		boolean running = true;
		while(running) {
			try {
				Thread.sleep(1000);
				n++;
				if (n == 2) {
					dispose();
					running = false;
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			new show_Screen();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}