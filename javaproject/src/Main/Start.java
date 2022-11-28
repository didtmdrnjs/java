package Main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class Start extends JFrame {
	
	public static int[] pos = new int[] {0, 0};
	public static String info = "", name = "", str = "";
	static String player_lotation = "";
	String xypos;
	int cnt = 0;
	static int num = 0;
	static int gold = 0, week = 0;
	static String[][][] mission = {{{"image/sh.png", "image/to.png"}}, {{"image/sh.png", "image/to.png"}, {"image/co.png", "image/honey.png"}}, {{"image/co.png", "image/honey.png"}, {"image/wh.png", "image/milk_submit.png"}}, {{"image/wh.png", "image/milk_submit.png"}, {"image/st.png", "image/egg_submit.png"}}};
	static int[][][] mission_num = {{{5, 5}}, {{7, 4}, {6, 2}}, {{10, 6}, {13, 9}}, {{24, 7}, {18, 12}}};
	static int[][][] mission_sub = {{{0, 0}}, {{0, 0}, {0, 0}}, {{0, 0}, {0, 0}}, {{0, 0}, {0, 0}}};
	static boolean possible = false;
	
	static JLabel login_start = new JLabel("화면을 클릭해서 로그인");
	static JLabel backgroundL = new JLabel();
	static controller controller = new controller();
	static Thread player_move = new Thread((Runnable)controller);
	present_login_userData user_ID = new present_login_userData();
	
	static Toolkit imageTool = Toolkit.getDefaultToolkit();
	static String[] crops_path = {"image/to.png", "image/wh.png", "image/st.png", "image/sh.png", "image/co.png", "image/egg_submit.png", "image/milk_submit.png", "image/honey.png"};
	static Image crops;
	static int crops_index = 0;
	static Image player_front = imageTool.getImage("image/front.png");
	static Image player_back = imageTool.getImage("image/back.png");
	static Image player_front_left = imageTool.getImage("image/front-left.png");
	static Image player_back_left = imageTool.getImage("image/back-left.png");
	static Image player_front_right = imageTool.getImage("image/front-right.png");
	static Image player_back_right = imageTool.getImage("image/back-right.png");
	static Image background = imageTool.getImage("image/ground.png");
	static Image house = imageTool.getImage("image/house.png"); // 232 243
	static Image backString = imageTool.getImage("image/panel_string.png");
	static Image panel = imageTool.getImage("image/panel.png"); // 200 200
	static Image gold_I = imageTool.getImage("image/gold.png");
	static Image tomato1 = imageTool.getImage("image/tomato1.png");
	static Image tomato2 = imageTool.getImage("image/tomato2.png");
	static Image tomato3 = imageTool.getImage("image/tomato3.png"); // 104 121
	static Image wheat1 = imageTool.getImage("image/wheat1.png");
	static Image wheat2 = imageTool.getImage("image/wheat2.png");
	static Image wheat3 = imageTool.getImage("image/wheat3.png"); // 50 137
	static Image strawberry1 = imageTool.getImage("image/strawberry1.png");
	static Image strawberry2 = imageTool.getImage("image/strawberry2.png");
	static Image strawberry3 = imageTool.getImage("image/strawberry3.png"); // 74 81
	static Image shamrock1 = imageTool.getImage("image/shamrock1.png");
	static Image shamrock2 = imageTool.getImage("image/shamrock2.png");
	static Image shamrock3 = imageTool.getImage("image/shamrock3.png"); // 94 80
	static Image corn1 = imageTool.getImage("image/corn1.png");
	static Image corn2 = imageTool.getImage("image/corn2.png");
	static Image corn3 = imageTool.getImage("image/corn3.png"); // 50 134
	static Image chicken = imageTool.getImage("image/chicken.png"); // 114 135
	static Image chicken1 = imageTool.getImage("image/chicken1.png");
	static Image chicken2 = imageTool.getImage("image/chicken2.png");
	static Image chicken3 = imageTool.getImage("image/chicken3.png");
	static Image egg = imageTool.getImage("image/egg.png"); // 44
	static Image cow = imageTool.getImage("image/cow.png"); // 145 110
	static Image cow1 = imageTool.getImage("image/cow1.png");
	static Image cow2= imageTool.getImage("image/cow2.png");
	static Image cow3 = imageTool.getImage("image/cow3.png");
	static Image milk = imageTool.getImage("image/milk.png"); // 32
	static Image honey_house = imageTool.getImage("image/honey_house.png");
	static Image honey_house1 = imageTool.getImage("image/honey_house1.png");
	static Image honey_house2 = imageTool.getImage("image/honey_house2.png");
	static Image honey_house3 = imageTool.getImage("image/honey_house3.png"); // 122 110
	
	static Image buffImg;
	static Graphics buffG;
	
	static JPanel wating_login = new JPanel();
	static JPanel start_game = new JPanel() {
		@Override
	    public void paint(Graphics g) {
	        buffImg = createImage(getWidth(),getHeight());
	        buffG = buffImg.getGraphics();
	        update(g);
	    }

	    @SuppressWarnings("static-access")
		@Override
	    public void update(Graphics g) {
	    	String crop = Start.crops_path[Start.crops_index].substring(6, 8);
	    	buffG.drawImage(background, 0, 0, this);
	    	buffG.drawImage(house, 1290, 0, this);
	    	buffG.drawImage(panel, 0, 624, this);
	    	switch(player_lotation) {
    		case "up":
    			buffG.drawImage(player_back, pos[0], pos[1], this);
    			break;
    		case "down":
    			buffG.drawImage(player_front, pos[0], pos[1], this);
    			break;
    		case "up-left":
    			buffG.drawImage(player_back_left, pos[0], pos[1], this);
    			break;
    		case "up-right":
    			buffG.drawImage(player_back_right, pos[0], pos[1], this);
    			break;
    		case "down-left":
    			buffG.drawImage(player_front_left, pos[0], pos[1], this);
    			break;
    		case "down-right":
    			buffG.drawImage(player_front_right, pos[0], pos[1], this);
    			break;
    		}
	    	if (possible) {
		    	switch(crop) {
		    	case "to":
		    		switch(player_lotation) {
			    	case "up":
			    		buffG.drawImage(tomato1, pos[0] - 27, pos[1] - 71, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(tomato1, pos[0] - 27, pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(tomato1, pos[0] - 104, pos[1] - 20, this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(tomato1, pos[0] + 59, pos[1] - 20, this);
			    		break;
			    	case "down-left":
			    		buffG.drawImage(tomato1, pos[0] - 104, pos[1] - 20, this);
			    		break;
			    	case "down-right":
			    		buffG.drawImage(tomato1, pos[0] + 59, pos[1] - 20, this);
			    		break;
			    	}
		    		break;
		    	case "wh":
		    		switch(player_lotation) {
			    	case "up":
			    		buffG.drawImage(wheat1, pos[0], pos[1] - 87, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(wheat1, pos[0], pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(wheat1, pos[0] - 50, pos[1] - 28, this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(wheat1, pos[0] + 59, pos[1] - 28, this);
			    		break;
			    	case "down-left":
			    		buffG.drawImage(wheat1, pos[0] - 50, pos[1] - 28, this);
			    		break;
			    	case "down-right":
			    		buffG.drawImage(wheat1, pos[0] + 59, pos[1] - 28, this);
			    		break;
			    	}
		    		break;
		    	case "st": 
		    		switch(player_lotation) {
			   		case "up":
			   			buffG.drawImage(strawberry1, pos[0] - 12, pos[1] - 31, this);
			   			break;
			   		case "down":
			   			buffG.drawImage(strawberry1, pos[0] - 12, pos[1] + 50, this);
			   			break;
			   		case "up-left":
			   			buffG.drawImage(strawberry1, pos[0] - 74, pos[1] - 1, this);
			   			break;
			   		case "up-right":
			   			buffG.drawImage(strawberry1, pos[0] + 59, pos[1] - 1, this);
			   			break;
			   		case "down-left":
			   			buffG.drawImage(strawberry1, pos[0] - 74, pos[1] - 1, this);
			   			break;
			   		case "down-right":
			   			buffG.drawImage(strawberry1, pos[0] + 59, pos[1] - 1, this);
			   			break;
			   		}
		   			break;
		    	case "sh":
		    		switch(player_lotation) {
			    	case "up":
			    		buffG.drawImage(shamrock1, pos[0] - 22, pos[1] - 71, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(shamrock1, pos[0] - 22, pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(shamrock1, pos[0] - 94, pos[1], this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(shamrock1, pos[0] + 59, pos[1], this);
			    		break;
			   		case "down-left":
			   			buffG.drawImage(shamrock1, pos[0] - 94, pos[1], this);
			   			break;
			   		case "down-right":
			   			buffG.drawImage(shamrock1, pos[0] + 59, pos[1], this);
			   			break;
			   		}
		    		break;
		    	case "co":
		    		switch(player_lotation) {
		    		case "up":
			    		buffG.drawImage(corn1, pos[0], pos[1] - 84, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(corn1, pos[0], pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(corn1, pos[0] - 50, pos[1] - 27, this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(corn1, pos[0] + 59, pos[1] - 27, this);
			    		break;
			    	case "down-left":
			    		buffG.drawImage(corn1, pos[0] - 50, pos[1] - 27, this);
			    		break;
			    	case "down-right":
			    		buffG.drawImage(corn1, pos[0] + 59, pos[1] - 27, this);
			    		break;
		    		}
		    		break;
		    	case "eg":
		    		switch(player_lotation) {
		    		case "up":
			    		buffG.drawImage(chicken, pos[0] - 32, pos[1] - 85, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(chicken, pos[0] - 32, pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(chicken, pos[0] - 114, pos[1] - 22, this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(chicken, pos[0] + 59, pos[1] - 22, this);
			    		break;
			    	case "down-left":
			    		buffG.drawImage(chicken, pos[0] - 114, pos[1] - 22, this);
			    		break;
			    	case "down-right":
			    		buffG.drawImage(chicken, pos[0] + 59, pos[1] - 22, this);
			    		break;
		    		}
		    		break;
		    	case "mi":
		    		switch(player_lotation) {
		    		case "up":
			    		buffG.drawImage(cow, pos[0] - 47, pos[1] - 60, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(cow, pos[0] - 47, pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(cow, pos[0] - 145, pos[1] - 15, this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(cow, pos[0] + 59, pos[1] - 15, this);
			    		break;
			    	case "down-left":
			    		buffG.drawImage(cow, pos[0] - 145, pos[1] - 15, this);
			    		break;
			    	case "down-right":
			    		buffG.drawImage(cow, pos[0] + 59, pos[1] - 15, this);
			    		break;
		    		}
		    		break;
		    	case "ho":
		    		switch(player_lotation) {
		    		case "up":
			    		buffG.drawImage(honey_house, pos[0] - 36, pos[1] - 60, this);
			    		break;
			    	case "down":
			    		buffG.drawImage(honey_house, pos[0] - 36, pos[1] + 50, this);
			    		break;
			    	case "up-left":
			    		buffG.drawImage(honey_house, pos[0] - 122, pos[1] - 15, this);
			    		break;
			    	case "up-right":
			    		buffG.drawImage(honey_house, pos[0] + 59, pos[1] - 15, this);
			    		break;
			    	case "down-left":
			    		buffG.drawImage(honey_house, pos[0] - 122, pos[1] - 15, this);
			    		break;
			    	case "down-right":
			    		buffG.drawImage(honey_house, pos[0] + 59, pos[1] - 15, this);
			    		break;
		    		}
		    		break;
		   		}
	    	}
	    	try {
		    	BufferedReader crops_read = new BufferedReader(new FileReader(file_data.text));
		    	Date date;
		    	while((str = crops_read.readLine()) != null) {
		    		if (str.substring(0, 4).equals("crop")) {
		    			date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str.substring(14, 33));
		    			switch(str.substring(str.length() - 2)) {
		    			case "to":
		    				buffG.drawImage(t.get_grow_percent(str, date, "to"), Integer.valueOf(str.substring(5, 9)), Integer.valueOf(str.substring(10, 13)), null);
		    				break;
		    			case "wh":
		    				buffG.drawImage(t.get_grow_percent(str, date, "wh"), Integer.valueOf(str.substring(5, 9)), Integer.valueOf(str.substring(10, 13)), null);
		    				break;
						case "st":
							buffG.drawImage(t.get_grow_percent(str, date, "st"), Integer.valueOf(str.substring(5, 9)), Integer.valueOf(str.substring(10, 13)), null);
		    				break;
						case "sh":
							buffG.drawImage(t.get_grow_percent(str, date, "sh"), Integer.valueOf(str.substring(5, 9)), Integer.valueOf(str.substring(10, 13)), null);
		    				break;
						case "co":
							buffG.drawImage(t.get_grow_percent(str, date, "co"), Integer.valueOf(str.substring(5, 9)), Integer.valueOf(str.substring(10, 13)), null);
		    				break;
		    			}
		    		}
		    		switch(str.substring(0, 2)) {
		    		case "eg":
		    			num = Integer.valueOf(str.substring(2, 3));
							switch(controller.chicken_n[num + 1]) {
							case 0:
								buffG.drawImage(chicken, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 1:
								buffG.drawImage(chicken1, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 2:
								buffG.drawImage(chicken2, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 3:
								buffG.drawImage(chicken3, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							}
						break;
					case "mi":
						num = Integer.valueOf(str.substring(2, 3));
							switch(controller.cow_n[num + 1]) {
							case 0:
								buffG.drawImage(cow, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 1:
								buffG.drawImage(cow1, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 2:
								buffG.drawImage(cow2, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 3:
								buffG.drawImage(cow3, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							}
						break;
					case "ho":
						num = Integer.valueOf(str.substring(2, 3));
							switch(controller.honey_house_n[num + 1]) {
							case 0:
								buffG.drawImage(honey_house, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 1:
								buffG.drawImage(honey_house1, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 2:
								buffG.drawImage(honey_house2, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							case 3:
								buffG.drawImage(honey_house3, Integer.valueOf(str.substring(3, 7)), Integer.valueOf(str.substring(8, 11)), null);
								break;
							}
						break;
		    		}
		    	}
		    	crops_read.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	buffG.drawImage(backString, 0, 0, this);
	        buffG.drawImage(imageTool.getImage(crops_path[crops_index]), 900, 0, this);
	        g.drawImage(buffImg, 0, 0, this);
	        g.drawImage(gold_I, 90, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[0]), 180, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[1]), 270, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[2]), 355, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[3]), 445, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[4]), 535, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[5]), 620, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[6]), 710, 0, this);
	        g.drawImage(imageTool.getImage(crops_path[7]), 800, 0, this);
	        g.setFont(new Font("굴림", Font.BOLD, 15));
	        if (week != 4) {
		        for (int i = 0; i < mission[week].length; i++) {
		        	g.drawImage(imageTool.getImage(mission[week][i][0]), 10, 664 + (i * 50), this);
		        	g.drawImage(imageTool.getImage(mission[week][i][1]), 95, 664 + (i * 50), this);
		        	g.drawString("(" + mission_sub[week][i][0] + "/" + mission_num[week][i][0] + ")", 60, 694 + (i * 50));
		        	g.drawString("(" + mission_sub[week][i][1] + "/" + mission_num[week][i][1] + ")", 145, 694 + (i * 50));
		        }
	        }
	        else {
	        	g.setFont(new Font("굴림", Font.BOLD, 20));
	        	g.drawString("모든 미션 클리어", 21, 704);
	        }
	        g.setFont(new Font("굴림", Font.BOLD, 20));
	        g.drawString(info, 30, 30);
	        g.drawString(String.valueOf(gold), 140, 30);
	        g.drawString(String.valueOf(controller.to_sub), 230, 30);
	        g.drawString(String.valueOf(controller.wh_sub), 315, 30);
	        g.drawString(String.valueOf(controller.st_sub), 405, 30);
	        g.drawString(String.valueOf(controller.sh_sub), 495, 30);
	        g.drawString(String.valueOf(controller.co_sub), 585, 30);
	        g.drawString(String.valueOf(controller.eg_sub), 670, 30);
	        g.drawString(String.valueOf(controller.mi_sub), 760, 30);
	        g.drawString(String.valueOf(controller.ho_sub), 850, 30);
	        g.setFont(new Font("굴림", Font.BOLD, 18));
	        g.drawString("미션", 78, 654);
	        repaint();
	    }
	};
	
	public Start() {
		player_lotation = "down";
		
		setTitle("GamPlex");
		setSize(1540, 824);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(controller);
		
		wating_login.setBounds(0, 0, 1540, 825);
		start_game.setBounds(0, 0, 1540, 825);
		
		wating_login.setLayout(null);
		start_game.setLayout(null);
		start_game.setVisible(false);
		
		login_start.setFont(new Font("굴림", Font.BOLD, 30));
		login_start.setBounds(603, 600, 334, 32);
		
		add(wating_login);
		add(start_game);
		wating_login.add(login_start);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				new all_update();
			}
		});
		
		player_move.setDaemon(true);
		
		wating_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Login();
			}
		});
	}
	
	public Start(String s) {}
}

class t {
		
	static Image get_grow_percent(String str, Date date, String s) {
		LocalDateTime now = LocalDateTime.now();
		String formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
		try {
			Date nowdate = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
			if ((nowdate.getTime() - date.getTime()) / 1000 > 120) {
				switch(s) {
				case "to":
					return Start.tomato3;
				case "wh":
					return Start.wheat3;
				case "st":
					return Start.strawberry3;
				case "sh":
					return Start.shamrock3;
				case "co":
					return Start.corn3;
				}
			}
			else if ((nowdate.getTime() - date.getTime()) / 1000 > 60) {
				switch(s) {
				case "to":
					return Start.tomato2;
				case "wh":
					return Start.wheat2;
				case "st":
					return Start.strawberry2;
				case "sh":
					return Start.shamrock2;
				case "co":
					return Start.corn2;
				}
			}
			else {
				switch(s) {
				case "to":
					return Start.tomato1;
				case "wh":
					return Start.wheat1;
				case "st":
					return Start.strawberry1;
				case "sh":
					return Start.shamrock1;
				case "co":
					return Start.corn1;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}

@SuppressWarnings("serial")
class store_tab extends JFrame implements ActionListener {
	
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JScrollPane scroll = new JScrollPane();
	ImageIcon tomato_i = new ImageIcon("image/to.png");
	ImageIcon wheat_i = new ImageIcon("image/wh.png");
	ImageIcon strawberry_i = new ImageIcon("image/st.png");
	ImageIcon shamrock_i = new ImageIcon("image/sh.png");
	ImageIcon corn_i = new ImageIcon("image/co.png");
	ImageIcon egg_i = new ImageIcon("image/egg_submit.png");
	ImageIcon milk_i = new ImageIcon("image/milk_submit.png");
	ImageIcon honey_i = new ImageIcon("image/honey.png");
	JLabel tomato = new JLabel();
	JLabel wheat = new JLabel();
	JLabel strawberry = new JLabel();
	JLabel shamrock = new JLabel();
	JLabel corn = new JLabel();
	JLabel egg = new JLabel();
	JLabel milk = new JLabel();
	JLabel honey = new JLabel();
	JButton to_sell = new JButton("판매");
	JButton wh_sell = new JButton("판매");
	JButton st_sell = new JButton("판매");
	JButton sh_sell = new JButton("판매");
	JButton co_sell = new JButton("판매");
	JButton eg_sell = new JButton("판매");
	JButton mi_sell = new JButton("판매");
	JButton ho_sell = new JButton("판매");
	
	public store_tab() {

		setTitle("상점");
		setLocation(1100, 30);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setLayout(null);
		setSize(200,120);
		setVisible(true);
		  
		panel.setLayout(null);
		panel.setBounds(0, 0, 200, 120);
		panel1.setLayout(new GridLayout(2, 8, 30, 0)); 
		panel1.setBounds(0, 0, 200, 120);
		scroll.setViewportView(panel1);
		scroll.setBounds(0, 0, 200, 120);
		
		tomato.setIcon(tomato_i);
		wheat.setIcon(wheat_i);
		strawberry.setIcon(strawberry_i);
		shamrock.setIcon(shamrock_i);
		corn.setIcon(corn_i);
		egg.setIcon(egg_i);
		milk.setIcon(milk_i);
		honey.setIcon(honey_i);
		
		to_sell.setBorderPainted(false);
		to_sell.setContentAreaFilled(false);
		to_sell.setFocusPainted(false);
		to_sell.addActionListener(this);
		wh_sell.setBorderPainted(false);
		wh_sell.setContentAreaFilled(false);
		wh_sell.setFocusPainted(false);
		wh_sell.addActionListener(this);
		st_sell.setBorderPainted(false);
		st_sell.setContentAreaFilled(false);
		st_sell.setFocusPainted(false);
		st_sell.addActionListener(this);
		sh_sell.setBorderPainted(false);
		sh_sell.setContentAreaFilled(false);
		sh_sell.setFocusPainted(false);
		sh_sell.addActionListener(this);
		co_sell.setBorderPainted(false);
		co_sell.setContentAreaFilled(false);
		co_sell.setFocusPainted(false);
		co_sell.addActionListener(this);
		eg_sell.setBorderPainted(false);
		eg_sell.setContentAreaFilled(false);
		eg_sell.setFocusPainted(false);
		eg_sell.addActionListener(this);
		mi_sell.setBorderPainted(false);
		mi_sell.setContentAreaFilled(false);
		mi_sell.setFocusPainted(false);
		mi_sell.addActionListener(this);
		ho_sell.setBorderPainted(false);
		ho_sell.setContentAreaFilled(false);
		ho_sell.setFocusPainted(false);
		ho_sell.addActionListener(this);
		
		panel1.add(tomato);
		panel1.add(wheat);
		panel1.add(strawberry);
		panel1.add(shamrock);
		panel1.add(corn);
		panel1.add(egg);
		panel1.add(milk);
		panel1.add(honey);
		panel1.add(to_sell);
		panel1.add(wh_sell);
		panel1.add(st_sell);
		panel1.add(sh_sell);
		panel1.add(co_sell);
		panel1.add(eg_sell);
		panel1.add(mi_sell);
		panel1.add(ho_sell);
		
		panel.add(scroll);
		add(panel);  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		present_login_userData ID = new present_login_userData();
		
		Connection con;
		PreparedStatement pstmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		@SuppressWarnings("static-access")
		String sql = "UPDATE user_info SET to_sub = ?, wh_sub = ?, st_sub = ?, sh_sub = ?, co_sub = ?, eg_sub = ?, mi_sub = ?, ho_sub = ? WHERE user_ID = '" + ID.ID + "'";
		
		if (e.getSource() == to_sell && controller.to_sub > 0) {
			controller.to_sub--;
			Start.gold += 18;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == wh_sell && controller.wh_sub > 0) {
			controller.wh_sub--;
			Start.gold += 18;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == st_sell && controller.st_sub > 0) {
			controller.st_sub--;
			Start.gold += 18;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == sh_sell && controller.sh_sub > 0) {
			controller.sh_sub--;
			Start.gold += 18;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == co_sell && controller.co_sub > 0) {
			controller.co_sub--;
			Start.gold += 18;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == eg_sell && controller.eg_sub > 0) {
			controller.eg_sub--;
			Start.gold += 10;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == mi_sell && controller.mi_sub > 0) {
			controller.mi_sub--;
			Start.gold += 10;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == ho_sell && controller.ho_sub > 0) {
			controller.ho_sub--;
			Start.gold += 10;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, controller.to_sub);
				pstmt.setInt(2, controller.wh_sub);
				pstmt.setInt(3, controller.st_sub);
				pstmt.setInt(4, controller.sh_sub);
				pstmt.setInt(5, controller.co_sub);
				pstmt.setInt(6, controller.eg_sub);
				pstmt.setInt(7, controller.mi_sub);
				pstmt.setInt(8, controller.ho_sub);
				pstmt.executeUpdate();
				try {
					pstmt.close();
					con.close();
				}
				catch(SQLException e1) {}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
