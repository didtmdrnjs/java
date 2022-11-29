package Main;

import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.Date;

public class controller extends KeyAdapter implements Runnable{
	
	boolean keyUp;
	boolean keyDown;
	boolean keyLeft;
	boolean keyRight;
	boolean c;
	boolean running = true;
	boolean h = false;
	
	int to = 0, wh = 0, st = 0, sh = 0, co = 0, eg = 0, mi = 0, ho = 0;
	static int to_sub = 0, wh_sub = 0, st_sub = 0, sh_sub = 0, co_sub = 0, eg_sub = 0, mi_sub = 0, ho_sub = 0;
	static int chicken_n[] = new int[3];
	static int cow_n[] = new int[3];
	static int honey_house_n[] = new int[3];
	static int chicken_x[] = new int[2];
	static int cow_x[] = new int[2];
	static int honey_house_x[] = new int[2];
	static int chicken_y[] = new int[2];
	static int cow_y[] = new int[2];
	static int honey_house_y[] = new int[2];
	static Date chicken_t[] = new Date[2];
	static Date cow_t[] = new Date[2];
	static Date honey_house_t[] = new Date[2];
	LocalDateTime now = LocalDateTime.now();
	
	String formatedNow;
	Start repaint = new Start("");
	File clone = new File("textfile/clone.txt");

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keyUp = true;
			Start.player_lotation = "up";
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDown = true;
			Start.player_lotation = "down";
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyLeft = true;
			if (Start.player_lotation.equals("up-right") || Start.player_lotation.equals("up") || Start.player_lotation.equals("up-left")) {
				Start.player_lotation = "up-left";
			}
			else if (Start.player_lotation.equals("down-right") || Start.player_lotation.equals("down") || Start.player_lotation.equals("down-left")) {
				Start.player_lotation = "down-left";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyRight = true;
			if (Start.player_lotation.equals("up-right") || Start.player_lotation.equals("up") || Start.player_lotation.equals("up-left")) {
				Start.player_lotation = "up-right";
			}
			else if (Start.player_lotation.equals("down-right") || Start.player_lotation.equals("down") || Start.player_lotation.equals("down-left")) {
				Start.player_lotation = "down-right";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			Start.possible = true;
			c = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_H) {
			
			String str = "";
			String crop;
			String date;
			long time_sub = 0;
			try {
				BufferedReader read = new BufferedReader(new FileReader(file_data.text));
				BufferedWriter write = new BufferedWriter(new FileWriter(clone));
				LocalDateTime now = LocalDateTime.now();
				
				while((str = read.readLine()) != null) {
		    		if (str.substring(0, 4).equals("crop")) {
		    			formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
		    			date = str.substring(14, 33);
		    			Date format1 = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(date);
		    			Date format2 = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formatedNow);
		    			time_sub = (format2.getTime() - format1.getTime()) / 1000;
		    			crop = str.substring(str.length() - 2);
		    			switch(crop) {
		    			case "to":
			    			if ((80 > (Math.abs(Start.pos[0] - Integer.valueOf(str.substring(5, 9))) + Math.abs(Start.pos[1] - Integer.valueOf(str.substring(10, 13))))) && time_sub > 120) {
			    				h = true;
			    				to++;
			    				break;
			    			}
			    			h = false;
			    			break;
		    			case "wh":
		    				if ((80 > (Math.abs(Start.pos[0] - Integer.valueOf(str.substring(5, 9))) + Math.abs(Start.pos[1] - Integer.valueOf(str.substring(10, 13))))) && time_sub > 120) {
		    					h = true;
		    					wh++;
		    					break;
			    			}
		    				h = false;
			    			break;
		    			case "st":
		    				if ((80 > (Math.abs(Start.pos[0] - Integer.valueOf(str.substring(5, 9))) + Math.abs(Start.pos[1] - Integer.valueOf(str.substring(10, 13))))) && time_sub > 120) {
		    					h = true;
		    					st++;
		    					break;
			    			}
		    				h = false;
			    			break;
		    			case "sh":
		    				if ((80 > (Math.abs(Start.pos[0] - Integer.valueOf(str.substring(5, 9))) + Math.abs(Start.pos[1] - Integer.valueOf(str.substring(10, 13))))) && time_sub > 120) {
		    					h = true;
		    					sh++;
		    					break;
			    			}
		    				h = false;
			    			break;
		    			case "co":
		    				if ((80 > (Math.abs(Start.pos[0] - Integer.valueOf(str.substring(5, 9))) + Math.abs(Start.pos[1] - Integer.valueOf(str.substring(10, 13))))) && time_sub > 120) {
		    					h = true;
		    					co++;
		    					break;
			    			}
		    				h = false;
			    			break;
		    			}
		    			if (h == true) {
		    				break;
		    			}
		    		}
		    		else if (str.substring(0, 2).equals("eg") || str.substring(0, 2).equals("mi") || str.substring(0, 2).equals("ho")) {
		    			switch(str.substring(0, 2)) {
			    		case "eg":
		    				if (80 > (Math.abs(Start.pos[0] - chicken_x[0]) + Math.abs(Start.pos[1] - chicken_y[0])) && chicken_n[1] > 0) {
		    					chicken_n[1]--;
		    					eg++;
		    				}
		    				else if (80 > (Math.abs(Start.pos[0] - chicken_x[1]) + Math.abs(Start.pos[1] - chicken_y[1])) && chicken_n[2] > 0) {
		    					chicken_n[2]--;
		    					eg++;
		    				}
		    				break;
		    			case "mi":
		    				if (80 > (Math.abs(Start.pos[0] - cow_x[0]) + Math.abs(Start.pos[1] - cow_y[0])) && cow_n[1] > 0) {
		    					cow_n[1]--;
		    					mi++;
		    				}
		    				else if (80 > (Math.abs(Start.pos[0] - cow_x[1]) + Math.abs(Start.pos[1] - cow_y[1])) && cow_n[2] > 0) {
		    					cow_n[2]--;
		    					mi++;
		    				}
		    				break;
		    			case "ho":
		    				if (80 > (Math.abs(Start.pos[0] - honey_house_x[0]) + Math.abs(Start.pos[1] - honey_house_y[0])) && honey_house_n[1] > 0) {
		    					honey_house_n[1]--;
		    					ho++;
		    				}
		    				else if (80 > (Math.abs(Start.pos[0] - honey_house_x[1]) + Math.abs(Start.pos[1] - honey_house_y[1])) && honey_house_n[2] > 0) {
		    					honey_house_n[2]--;
		    					ho++;
		    				}
		    				break;
		    			}
		    		}
		    		write.write(str + "\n");
		    	}
				
				while((str = read.readLine()) != null) {
					write.write(str + "\n");
				}
				read.close();
				write.close();
				read = new BufferedReader(new FileReader(clone));
				write = new BufferedWriter(new FileWriter(file_data.text));
				while((str = read.readLine()) != null) {
		    		write.write(str + "\n");
		    	}
				read.close();
				write.close();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_N) {
			Start.crops_index++;
			if (Start.crops_index == 8) {
				Start.crops_index = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_B) {
			Start.crops_index--;
			if (Start.crops_index == -1) {
				Start.crops_index = 7;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			new store_tab();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Start.possible = false;
			c = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String xpos = null, ypos = null, formated = null;
			if (Start.possible == true) {
				String crop = Start.crops_path[Start.crops_index].substring(6, 8);
				LocalDateTime now = LocalDateTime.now();
				try {
					BufferedWriter crops = new BufferedWriter(new FileWriter(file_data.text, true));
//					if (Start.pos[0] < 1290 || Start.pos[1] > 243) {
						switch(Start.player_lotation) {
			    		case "up":
			    			switch(crop) {
			    			case "to":
			    				if ((Start.pos[1] - 71 > 243 || Start.pos[0] + 163 < 1290) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] - 27);
					    			ypos = String.valueOf(Start.pos[1] - 71);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "wh":
			    				if ((Start.pos[1] - 87 > 243 || Start.pos[0] + 109 < 1290) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0]);
					    			ypos = String.valueOf(Start.pos[1] - 87);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "st":
			    				if ((Start.pos[1] - 31 > 243 || Start.pos[0] + 133 < 1290) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] - 12);
					    			ypos = String.valueOf(Start.pos[1] - 31);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "sh":
			    				if ((Start.pos[1] - 30 > 243 || Start.pos[0] + 153 < 1290) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] - 22);
					    			ypos = String.valueOf(Start.pos[1] - 71);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "co":
			    				if ((Start.pos[1] - 84 > 243 || Start.pos[0] + 109 < 1290) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0]);
				    				ypos = String.valueOf(Start.pos[1] - 84);
				    				Start.gold -= 15;
			    				}
			    				break;
			    			case "eg":
			    				if ((Start.pos[1] - 85 > 243 || Start.pos[0] + 173 < 1290 && chicken_n[0] < 2) && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] - 32);
				    				ypos = String.valueOf(Start.pos[1] - 85);
				    				chicken_n[0]++;
				    				chicken_x[chicken_n[0] - 1] = Start.pos[0] - 32;
				    				chicken_y[chicken_n[0] - 1] = Start.pos[1] - 85;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				chicken_t[chicken_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "mi":
			    				if ((Start.pos[1] - 60 > 243 || Start.pos[0] + 109 < 1290 && cow_n[0] < 2) && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] - 47);
				    				ypos = String.valueOf(Start.pos[1] - 60);
				    				cow_n[0]++;
				    				cow_x[cow_n[0] - 1] = Start.pos[0] - 47;
				    				cow_y[cow_n[0] - 1] = Start.pos[1] - 60;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				cow_t[cow_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "ho":
			    				if ((Start.pos[1] - 60 > 243 ||  Start.pos[0] + 109 < 1290 && honey_house_n[0] < 2) && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] - 36);
				    				ypos = String.valueOf(Start.pos[1] - 60);
				    				honey_house_n[0]++;
				    				honey_house_x[honey_house_n[0] - 1] = Start.pos[0] - 36;
				    				honey_house_y[honey_house_n[0] - 1] = Start.pos[1] - 60;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				honey_house_t[honey_house_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			}
			    			break;
			    		case "down":
			    			switch(crop) {
			    			case "to":
			    				if ((Start.pos[1] + 171 < 624 || Start.pos[0] - 104 > 200) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 27);
					    			ypos = String.valueOf(Start.pos[1] + 50);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "wh":
			    				if ((Start.pos[1] + 187 < 624 || Start.pos[0] - 50 > 200) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0]);
					    			ypos = String.valueOf(Start.pos[1] + 50);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "st":
			    				if ((Start.pos[1] + 131 < 624 || Start.pos[0] - 74 > 200) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] - 12);
					    			ypos = String.valueOf(Start.pos[1] + 50);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "sh":
			    				if ((Start.pos[1] + 130 < 624 || Start.pos[0] - 94 > 200) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] - 22);
					    			ypos = String.valueOf(Start.pos[1] + 50);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "co":
			    				if ((Start.pos[1] + 184 < 624 || Start.pos[0] - 50 > 200) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0]);
				    				ypos = String.valueOf(Start.pos[1] + 50);
				    				Start.gold -= 15;
			    				}
			    				break;
			    			case "eg":
			    				if ((Start.pos[1] + 185 < 624 || Start.pos[0] - 114 > 200 && chicken_n[0] < 2) && Start.gold - 60 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 32);
				    				ypos = String.valueOf(Start.pos[1] + 50);
				    				chicken_n[0]++;
				    				chicken_x[chicken_n[0] - 1] = Start.pos[0] - 32;
				    				chicken_y[chicken_n[0] - 1] = Start.pos[1] + 50;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				chicken_t[chicken_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "mi":
			    				if ((Start.pos[1] + 160 < 624 || Start.pos[0] - 145 > 200 && cow_n[0] < 2) && Start.gold - 60 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 47);
				    				ypos = String.valueOf(Start.pos[1] + 50);
				    				cow_n[0]++;
				    				cow_x[cow_n[0] - 1] = Start.pos[0] - 47;
				    				cow_y[cow_n[0] - 1] = Start.pos[1] + 50;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				cow_t[cow_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "ho":
			    				if ((Start.pos[1] + 160 < 624 || Start.pos[0] - 122 > 200) && honey_house_n[0] < 2 && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] - 36);
			    					ypos = String.valueOf(Start.pos[1] + 50);
			    					honey_house_n[0]++;
			    					honey_house_x[honey_house_n[0] - 1] = Start.pos[0] - 36;
				    				honey_house_y[honey_house_n[0] - 1] = Start.pos[1] + 50;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				honey_house_t[honey_house_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			}
			    			break;
			    		case "up-left":
			    		case "down-left":
			    			switch(crop) {
			    			case "to":
			    				if ((Start.pos[1] + 171 < 624 || Start.pos[0] - 104 > 200) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 104);
					    			ypos = String.valueOf(Start.pos[1] - 20);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "wh":
			    				if ((Start.pos[1] + 187 < 624 || Start.pos[0] - 50 > 200) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] - 50);
					    			ypos = String.valueOf(Start.pos[1] - 28);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "st":
			    				if ((Start.pos[1] + 131 < 624 || Start.pos[0] - 74 > 200) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 74);
					    			ypos = String.valueOf(Start.pos[1] - 1);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "sh":
			    				if ((Start.pos[1] + 130 < 624 || Start.pos[0] - 94 > 200) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 94);
					    			ypos = String.valueOf(Start.pos[1]);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "co":
			    				if ((Start.pos[1] + 184 < 624 || Start.pos[0] - 50 > 200) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 50);
				    				ypos = String.valueOf(Start.pos[1] - 27);
				    				Start.gold -= 15;
				    			}
			    				break;
			    			case "eg":
			    				if ((Start.pos[1] + 185 < 624 || Start.pos[0] - 114 > 200) && chicken_n[0] < 2 && Start.gold - 60 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 114);
				    				ypos = String.valueOf(Start.pos[1] - 22);
				    				chicken_n[0]++;
				    				chicken_x[chicken_n[0] - 1] = Start.pos[0] - 114;
				    				chicken_y[chicken_n[0] - 1] = Start.pos[1] - 22;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				chicken_t[chicken_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "mi":
			    				if ((Start.pos[1] + 160 < 624 || Start.pos[0] - 145 > 200) && cow_n[0] < 2 && Start.gold - 60 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 145);
				    				ypos = String.valueOf(Start.pos[1] - 15);
				    				cow_n[0]++;
				    				cow_x[cow_n[0] - 1] = Start.pos[0] - 145;
				    				cow_y[cow_n[0] - 1] = Start.pos[1] - 15;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				cow_t[cow_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "ho":
			    				if ((Start.pos[1] + 160 < 624 || Start.pos[0] - 122 > 200) && honey_house_n[0] < 2 && Start.gold - 60 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] - 122);
				    				ypos = String.valueOf(Start.pos[1] - 15);
				    				honey_house_n[0]++;
				    				honey_house_x[honey_house_n[0] - 1] = Start.pos[0] - 122;
				    				honey_house_y[honey_house_n[0] - 1] = Start.pos[1] - 15;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				honey_house_t[honey_house_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			}
			    			break;
			    		case "up-right":
			    		case "down-right":
			    			switch(crop) {
			    			case "to":
			    				if ((Start.pos[1] - 71 > 243 || Start.pos[0] + 163 < 1290) && Start.gold - 15 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] + 59);
				    				ypos = String.valueOf(Start.pos[1] - 20);
				    				Start.gold -= 15;
			    				}
				    			break;
			    			case "wh":
			    				if ((Start.pos[1] - 87 > 243 || Start.pos[0] + 109 < 1290) && Start.gold - 15 >= 0) {
				    				xpos = String.valueOf(Start.pos[0] + 59);
					    			ypos = String.valueOf(Start.pos[1] - 28);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "st":
			    				if ((Start.pos[1] - 31 > 243 || Start.pos[0] + 133 < 1290) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] + 59);
					    			ypos = String.valueOf(Start.pos[1] - 1);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "sh":
			    				if ((Start.pos[1] - 30 > 243 || Start.pos[0] + 153 < 1290) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] + 59);
					    			ypos = String.valueOf(Start.pos[1]);
					    			Start.gold -= 15;
			    				}
				    			break;
			    			case "co":
			    				if ((Start.pos[1] - 84 > 243 || Start.pos[0] + 109 < 1290) && Start.gold - 15 >= 0) {
					    			xpos = String.valueOf(Start.pos[0] + 59);
				    				ypos = String.valueOf(Start.pos[1] - 27);
				    				Start.gold -= 15;
			    				}
			    				break;
			    			case "eg":
			    				if ((Start.pos[1] - 85 > 243 || Start.pos[0] + 173 < 1290) && chicken_n[0] < 2 && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] + 59);
				    				ypos = String.valueOf(Start.pos[1] - 22);
				    				chicken_n[0]++;
				    				chicken_x[chicken_n[0] - 1] = Start.pos[0] + 59;
				    				chicken_y[chicken_n[0] - 1] = Start.pos[1] - 22;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				chicken_t[chicken_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "mi":
			    				if ((Start.pos[1] - 60 > 243 || Start.pos[0] + 109 < 1290) && cow_n[0] < 2 && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] + 59);
				    				ypos = String.valueOf(Start.pos[1] - 15);
				    				cow_n[0]++;
				    				cow_x[cow_n[0] - 1] = Start.pos[0] + 59;
				    				cow_y[cow_n[0] - 1] = Start.pos[1] - 15;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				cow_t[cow_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			case "ho":
			    				if ((Start.pos[1] - 60 > 243 ||  Start.pos[0] + 109 < 1290) && honey_house_n[0] < 2 && Start.gold - 60 >= 0) {
			    					xpos = String.valueOf(Start.pos[0] + 59);
				    				ypos = String.valueOf(Start.pos[1] - 15);
				    				honey_house_n[0]++;
				    				honey_house_x[honey_house_n[0] - 1] = Start.pos[0] + 59;
				    				honey_house_y[honey_house_n[0] - 1] = Start.pos[1] - 15;
				    				formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
				    				honey_house_t[honey_house_n[0] - 1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated);
				    				Start.gold -= 60;
			    				}
			    				break;
			    			}
			    			break;
			    		}
		    			if (xpos != null) {
		    				if (crop.equals("eg") || crop.equals("mi") || crop.equals("ho")) {
		    					crops.write(crop);
		    					switch(crop) {
		    					case "eg":
		    						crops.write(String.valueOf(chicken_n[0] - 1));
			    					for (int i = 0; i < 4 - String.valueOf(chicken_x[chicken_n[0] - 1]).length(); i++) {
					    				crops.write("0");
					    			}
					    			crops.write(String.valueOf(chicken_x[chicken_n[0] - 1]) + " ");
					    			for (int i = 0; i < 3 - String.valueOf(chicken_y[chicken_n[0] - 1]).length(); i++) {
					    				crops.write("0");
					    			}
					    			crops.write(String.valueOf(chicken_y[chicken_n[0] - 1]));
					    			break;
		    					case "mi":
		    						crops.write(String.valueOf(cow_n[0] - 1));
			    					for (int i = 0; i < 4 - String.valueOf(cow_x[cow_n[0] - 1]).length(); i++) {
					    				crops.write("0");
					    			}
					    			crops.write(String.valueOf(cow_x[cow_n[0] - 1]) + " ");
					    			for (int i = 0; i < 3 - String.valueOf(cow_y[cow_n[0] - 1]).length(); i++) {
					    				crops.write("0");
					    			}
					    			crops.write(String.valueOf(cow_y[cow_n[0] - 1]));
					    			break;
		    					case "ho":
		    						crops.write(String.valueOf(honey_house_n[0] - 1));
			    					for (int i = 0; i < 4 - String.valueOf(honey_house_x[honey_house_n[0] - 1]).length(); i++) {
					    				crops.write("0");
					    			}
					    			crops.write(String.valueOf(honey_house_x[honey_house_n[0] - 1]) + " ");
					    			for (int i = 0; i < 3 - String.valueOf(honey_house_y[honey_house_n[0] - 1]).length(); i++) {
					    				crops.write("0");
					    			}
					    			crops.write(String.valueOf(honey_house_y[honey_house_n[0] - 1]));
					    			break;
		    					}
		    					crops.write("\n");
		    				}
		    				else {
				    			crops.write("crop "); 
				    			for (int i = 0; i < 4 - xpos.length(); i++) {
				    				crops.write("0");
				    			}
				    			crops.write(xpos + " ");
				    			for (int i = 0; i < 3 - ypos.length(); i++) {
				    				crops.write("0");
				    			}
				    			formatedNow = now.format(DateTimeFormatter.ofPattern(" yyyy MM dd HH mm ss "));
				    			crops.write(ypos + formatedNow);
								crops.write(crop + "\n");
		    				}
		    			}
						crops.close();
					}
//				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

    // 키가 눌렀다 때졌을 때.
    @Override
    public void keyReleased(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_UP) {
			keyUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDown = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyRight = false;
		}
    }
    
	public void keyProcess() {
		String crop = Start.crops_path[Start.crops_index].substring(6, 8);
		switch(crop) {
		case "to":
	        if (c == true) {
	        	if (Start.pos[0] - 104 < 0 || Start.pos[0] > 1377 || Start.pos[1] - 71 < 0 || Start.pos[1] > 653) {
	        		Start.possible = false;
	        	}
	        	else {
	        		Start.possible = true;
	        	}
	    	}
	        break;
		case "wh":
			if (c == true) {
	        	if (Start.pos[0] - 50 < 0 || Start.pos[0] > 1431 || Start.pos[1] - 87 < 0 || Start.pos[1] > 637) {
	        		Start.possible = false;
	        	}
	        	else {
	        		Start.possible = true;
	        	}
	    	}
	        break;
		case "st":
			if (c == true) {
	        	if (Start.pos[0] - 74 < 0 || Start.pos[0] > 1407 || Start.pos[1] - 31 < 0 || Start.pos[1] > 693) {
	        		Start.possible = false;
	        	}
	        	else {
	        		Start.possible = true;
	        	}
	    	}
	        break;
		case "sh":
			if (c == true) {
	        	if (Start.pos[0] - 94 < 0 || Start.pos[0] > 1387 || Start.pos[1] - 30 < 0 || Start.pos[1] > 694) {
	        		Start.possible = false;
	        	}
	        	else {
	        		Start.possible = true;
	        	}
	    	}
	        break;
		case "co":
			if (c == true) {
	        	if (Start.pos[0] - 50 < 0 || Start.pos[0] > 1431 || Start.pos[1] - 84 < 0 || Start.pos[1] > 640) {
	        		Start.possible = false;
	        	}
	        	else {
	        		Start.possible = true;
	        	}
	    	}
	        break;
		}
        if(keyUp){
            if (Start.pos[1] >= 50) {
            	Start.pos[1] -= 2;
            }
            else if (Start.pos[1] < 50) {
            	Start.pos[1] = 50;
            }
        }
        if(keyDown){
        	if (Start.pos[1] <= 684) {
        		Start.pos[1] += 2;
            }
            else if (Start.pos[1] > 684) {
            	Start.pos[1] = 684;
            }
        }
        if(keyLeft){
        	if (Start.pos[0] >= 0) {
        		Start.pos[0] -= 2;
            }
        	else if(Start.pos[0] < 0) {
        		Start.pos[0] = 0;
        	}
        }
        if(keyRight){
        	if (Start.pos[0] <= 1475) {
        		Start.pos[0] += 2;
            }
        	else if(Start.pos[0] > 1475) {
        		Start.pos[0] = 1475;
        	}
        }
    }
    int count = 0;
    @Override
	public void run() {
		while(running) {
			try {
				keyProcess();
				repaint.repaint();
				repaint.revalidate();
				new update_time();
				if (count == 6000) {
					new all_update();
					count = 0;
				}
				switch(Start.week) {
				case 0:
					while(sh_sub > 0) {
						if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0]) {
							break;
						}
						sh_sub--;
						Start.mission_sub[Start.week][0][0]++;
					}
					while(to_sub > 0) {
						if (Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1]) {
							break;
						}
						to_sub--;
						Start.mission_sub[Start.week][0][1]++;
					}
					if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0] && Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1]) {
						Start.week++;
					}
				case 1:
					while(sh_sub > 0) {
						if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0]) {
							break;
						}
						sh_sub--;
						Start.mission_sub[Start.week][0][0]++;
					}
					while(to_sub > 0) {
						if (Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1]) {
							break;
						}
						to_sub--;
						Start.mission_sub[Start.week][0][1]++;
					}
					while(co_sub > 0) {
						if (Start.mission_sub[Start.week][1][0] == Start.mission_num[Start.week][1][0]) {
							break;
						}
						co_sub--;
						Start.mission_sub[Start.week][1][0]++;
					}
					while(ho_sub > 0) {
						if (Start.mission_sub[Start.week][1][1] == Start.mission_num[Start.week][1][1]) {
							break;
						}
						ho_sub--;
						Start.mission_sub[Start.week][1][1]++;
					}
					if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0] && Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1] && Start.mission_sub[Start.week][1][0] == Start.mission_num[Start.week][1][0] && Start.mission_sub[Start.week][1][1] == Start.mission_num[Start.week][1][1]) {
						Start.week++;
					}
					break;
				case 2:
					while(co_sub > 0) {
						if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0]) {
							break;
						}
						co_sub--;
						Start.mission_sub[Start.week][0][0]++;
					}
					while(ho_sub > 0) {
						if (Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1]) {
							break;
						}
						ho_sub--;
						Start.mission_sub[Start.week][0][1]++;
					}
					while(wh_sub > 0) {
						if (Start.mission_sub[Start.week][1][0] == Start.mission_num[Start.week][1][0]) {
							break;
						}
						wh_sub--;
						Start.mission_sub[Start.week][1][0]++;
					}
					while(mi_sub > 0) {
						if (Start.mission_sub[Start.week][1][1] == Start.mission_num[Start.week][1][1]) {
							break;
						}
						mi_sub--;
						Start.mission_sub[Start.week][1][1]++;
					}
					if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0] && Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1] && Start.mission_sub[Start.week][1][0] == Start.mission_num[Start.week][1][0] && Start.mission_sub[Start.week][1][1] == Start.mission_num[Start.week][1][1]) {
						Start.week++;
					}
					break;
				case 3:
					while(wh_sub > 0) {
						if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0]) {
							break;
						}
						wh_sub--;
						Start.mission_sub[Start.week][0][0]++;
					}
					while(mi_sub > 0) {
						if (Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1]) {
							break;
						}
						mi_sub--;
						Start.mission_sub[Start.week][0][1]++;
					}
					while(st_sub > 0) {
						if (Start.mission_sub[Start.week][1][0] == Start.mission_num[Start.week][1][0]) {
							break;
						}
						st_sub--;
						Start.mission_sub[Start.week][1][0]++;
					}
					while(eg_sub > 0) {
						if (Start.mission_sub[Start.week][1][1] == Start.mission_num[Start.week][1][1]) {
							break;
						}
						eg_sub--;
						Start.mission_sub[Start.week][1][1]++;
					}
					if (Start.mission_sub[Start.week][0][0] == Start.mission_num[Start.week][0][0] && Start.mission_sub[Start.week][0][1] == Start.mission_num[Start.week][0][1] && Start.mission_sub[Start.week][1][0] == Start.mission_num[Start.week][1][0] && Start.mission_sub[Start.week][1][1] == Start.mission_num[Start.week][1][1]) {
						Start.week++;
					}
					break;
				}
				if (Start.pos[0] > 1290 && Start.pos[1] < 243) {
					if (to != 0 || wh != 0 || st != 0 || sh != 0 || co != 0 || eg != 0 || mi != 0 || ho != 0) {
						to_sub += to;
						wh_sub += wh;
						st_sub += st;
						sh_sub += sh;
						co_sub += co;
						eg_sub += eg;
						mi_sub += mi;
						ho_sub += ho;
						to = 0;
						wh = 0;
						st = 0;
						sh = 0;
						co = 0;
						eg = 0;
						mi = 0;
						ho = 0;
					}
				}
				Thread.sleep(3);
				count++;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class all_update {
	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
	present_login_userData ID = new present_login_userData();
	
	private Connection con;
	private PreparedStatement pstmt = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	@SuppressWarnings("static-access")
	String sql = "UPDATE user_info SET to_sub = ?, wh_sub = ?, st_sub = ?, sh_sub = ?, co_sub = ?, eg_sub = ?, mi_sub = ?, ho_sub = ?, xpos = ?, ypos = ?, gold = ?, chicken0 = ?, chicken1 = ?, cow0 = ?, cow1 = ?, honey_house0 = ?, honey_house1 = ?, chicken_n0 = ?, chicken_n1 = ?, cow_n0 = ?, cow_n1 = ?, honey_house_n0 = ?, honey_house_n1 = ?, week = ?, mission1 = ?, mission2 = ?, mission3 = ?, mission4 = ? WHERE user_ID = '" + ID.ID + "'";
	public all_update() {
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
			pstmt.setString(9, String.valueOf(Start.pos[0]));
			pstmt.setString(10, String.valueOf(Start.pos[1]));
			pstmt.setInt(11, Start.gold);
			if (controller.chicken_t[0] != null) {
				pstmt.setString(12, String.valueOf(transFormat.format(controller.chicken_t[0])));
			}
			else {
				pstmt.setString(12, "");
			}
			if (controller.chicken_t[1] != null) {
				pstmt.setString(13, String.valueOf(transFormat.format(controller.chicken_t[1])));
			}
			else {
				pstmt.setString(13, "");
			}
			if (controller.cow_t[0] != null) {	
				pstmt.setString(14, String.valueOf(transFormat.format(controller.cow_t[0])));
			}
			else {
				pstmt.setString(14, "");
			}
			if (controller.cow_t[1] != null) {		
				pstmt.setString(15, String.valueOf(transFormat.format(controller.cow_t[1])));
			}
			else {
				pstmt.setString(15, "");
			}
			if (controller.honey_house_t[0] != null) {
				pstmt.setString(16, String.valueOf(transFormat.format(controller.honey_house_t[0])));
			}
			else {
				pstmt.setString(16, "");
			}
			if (controller.honey_house_t[1] != null) {
				pstmt.setString(17, String.valueOf(transFormat.format(controller.honey_house_t[1])));
			}
			else {
				pstmt.setString(17, "");
			}
			pstmt.setInt(18, controller.chicken_n[1]);
			pstmt.setInt(19, controller.chicken_n[2]);
			pstmt.setInt(20, controller.cow_n[1]);
			pstmt.setInt(21, controller.cow_n[2]);
			pstmt.setInt(22, controller.honey_house_n[1]);
			pstmt.setInt(23, controller.honey_house_n[2]);
			pstmt.setInt(24, Start.week);
			if (Start.week != 4) {
				if (Start.week == 0) {
					pstmt.setInt(25, Start.mission_sub[Start.week][0][0]);
					pstmt.setInt(26, Start.mission_sub[Start.week][0][1]);
					pstmt.setInt(27, 0);
					pstmt.setInt(28, 0);
				}
				else {
					pstmt.setInt(25, Start.mission_sub[Start.week][0][0]);
					pstmt.setInt(26, Start.mission_sub[Start.week][0][1]);
					pstmt.setInt(27, Start.mission_sub[Start.week][1][0]);
					pstmt.setInt(28, Start.mission_sub[Start.week][1][1]);
				}
			}
			else {
				pstmt.setInt(25, 0);
				pstmt.setInt(26, 0);
				pstmt.setInt(27, 0);
				pstmt.setInt(28, 0);
			}
			pstmt.executeUpdate();
			try {
				pstmt.close();
				con.close();
			}
			catch(SQLException e) {}
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

class update_time {

	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
	
	present_login_userData ID = new present_login_userData();
	
	private Connection con;
	private PreparedStatement pstmt = null;
	static String blank = "	            ";
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	LocalDateTime now = LocalDateTime.now();
	String formated;
	Date date;
	
	@SuppressWarnings("static-access")
	public update_time() {
		formated = now.format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss"));
		try {
			if (controller.chicken_n[0] > 0) {
				if (controller.chicken_t[0] != null && controller.chicken_n[1] < 3 && ((date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated)).getTime() - controller.chicken_t[0].getTime()) / 1000 > 120) {
					controller.chicken_n[1]++;
					controller.chicken_t[0] = date;
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "system", "1234");
					String sql = "UPDATE user_info SET chicken0 = ? WHERE user_ID = '" + ID.ID + "'";
					String sql1 = "UPDATE user_info SET chicken_n0 = ? WHERE user_ID = '" + ID.ID + "'";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, String.valueOf(transFormat.format(controller.chicken_t[0])));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, controller.chicken_n[1]);
					pstmt.executeUpdate();
					try {
						pstmt.close();
						con.close();
					}
					catch(SQLException e) {}
				}
				if (controller.chicken_t[1] != null && controller.chicken_n[2] < 3 && ((date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated)).getTime() - controller.chicken_t[1].getTime()) / 1000 > 120) {
					controller.chicken_n[2]++;
					controller.chicken_t[1] = date;
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "system", "1234");
					String sql = "UPDATE user_info SET chicken1 = ? WHERE user_ID = '" + ID.ID + "'";
					String sql1 = "UPDATE user_info SET chicken_n1 = ? WHERE user_ID = '" + ID.ID + "'";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, String.valueOf(transFormat.format(controller.chicken_t[1])));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, controller.chicken_n[2]);
					pstmt.executeUpdate();
					try {
						pstmt.close();
						con.close();
					}
					catch(SQLException e) {}
				}
			}
			if (controller.cow_n[0] > 0) {
				if (controller.cow_t[0] != null && controller.cow_n[1] < 3 && ((date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated)).getTime() - controller.cow_t[0].getTime()) / 1000 > 120) {
					controller.cow_n[1]++;
					controller.cow_t[0] = date;
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "system", "1234");
					String sql = "UPDATE user_info SET cow0 = ? WHERE user_ID = '" + ID.ID + "'";
					String sql1 = "UPDATE user_info SET cow_n0 = ? WHERE user_ID = '" + ID.ID + "'";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, String.valueOf(transFormat.format(controller.cow_t[0])));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, controller.cow_n[0]);
					pstmt.executeUpdate();
					try {
						pstmt.close();
						con.close();
					}
					catch(SQLException e) {}
				}
				if (controller.cow_t[1] != null && controller.cow_n[2] < 3 && ((date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated)).getTime() - controller.cow_t[1].getTime()) / 1000 > 120) {
					controller.cow_n[2]++;
					controller.cow_t[1] = date;
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "system", "1234");
					String sql = "UPDATE user_info SET cow1 = ? WHERE user_ID = '" + ID.ID + "'";
					String sql1 = "UPDATE user_info SET cow_n1 = ? WHERE user_ID = '" + ID.ID + "'";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, String.valueOf(transFormat.format(controller.cow_t[1])));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, controller.cow_n[1]);
					pstmt.executeUpdate();
					try {
						pstmt.close();
						con.close();
					}
					catch(SQLException e) {}
				}
			}
			if (controller.honey_house_n[0] > 0) {
				if (controller.honey_house_t[0] != null && controller.honey_house_n[1] < 3 && ((date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated)).getTime() - controller.honey_house_t[0].getTime()) / 1000 > 120) {
					controller.honey_house_n[1]++;
					controller.honey_house_t[0] = date;
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "system", "1234");
					String sql = "UPDATE user_info SET honey_house0 = ? WHERE user_ID = '" + ID.ID + "'";
					String sql1 = "UPDATE user_info SET honey_house_n0 = ? WHERE user_ID = '" + ID.ID + "'";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, String.valueOf(transFormat.format(controller.honey_house_t[0])));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, controller.honey_house_n[0]);
					pstmt.executeUpdate();
					try {
						pstmt.close();
						con.close();
					}
					catch(SQLException e) {}
				}
				if (controller.honey_house_t[1] != null && controller.honey_house_n[2] < 3 && ((date = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(formated)).getTime() - controller.honey_house_t[1].getTime()) / 1000 > 120) {
					controller.honey_house_n[2]++;
					controller.honey_house_t[1] = date;
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "system", "1234");
					String sql = "UPDATE user_info SET honey_house1 = ? WHERE user_ID = '" + ID.ID + "'";
					String sql1 = "UPDATE user_info SET honey_house_n1 = ? WHERE user_ID = '" + ID.ID + "'";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, String.valueOf(transFormat.format(controller.honey_house_t[1])));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, controller.honey_house_n[1]);
					pstmt.executeUpdate();
					try {
						pstmt.close();
						con.close();
					}
					catch(SQLException e) {}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}