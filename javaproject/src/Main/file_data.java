package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class file_data extends JFrame implements ActionListener {
	
	private Connection con;
	private PreparedStatement pstmt = null;
	static present_login_userData user_ID = new present_login_userData();
	private JTextField NAME = new JTextField(20);
	@SuppressWarnings("static-access")
	static File text = new File("textfile/" + user_ID.ID + ".txt");
	private JButton check = new JButton("확인");
	String search;
	String str;
	
	@SuppressWarnings("static-access")
	file_data() {
		try {
			if(!text.exists())
				text.createNewFile();
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "SELECT xpos, ypos, to_sub, wh_sub, st_sub, sh_sub, co_sub, eg_sub, mi_sub, ho_sub, chicken0, chicken1, cow0, cow1, honey_house0, honey_house1, chicken_n0, chicken_n1, cow_n0, cow_n1, honey_house_n0, honey_house_n1, gold, week, mission1, mission2, mission3, mission4 FROM user_info WHERE user_ID = '" + user_ID.ID + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "system", "1234");
			
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Start.pos[0] = Integer.valueOf(rs.getString(1));
				Start.pos[1] = Integer.valueOf(rs.getString(2));
				controller.to_sub = rs.getInt(3);
				controller.wh_sub = rs.getInt(4);
				controller.st_sub = rs.getInt(5);
				controller.sh_sub = rs.getInt(6);
				controller.co_sub = rs.getInt(7);
				controller.eg_sub = rs.getInt(8);
				controller.mi_sub = rs.getInt(9);
				controller.ho_sub = rs.getInt(10);
				if (rs.getString(11) != null) {
					controller.chicken_t[0] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str = rs.getString(11).substring(0, 19));
				}
				if (rs.getString(12) != null) {
					controller.chicken_t[1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str = rs.getString(12).substring(0, 19));
				}
				if (rs.getString(13) != null) {	
					controller.cow_t[0] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str = rs.getString(13).substring(0, 19));
				}
				if (rs.getString(14) != null) {		
					controller.cow_t[1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str = rs.getString(14).substring(0, 19));
				}
				if (rs.getString(15) != null) {
					controller.honey_house_t[0] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str = rs.getString(15).substring(0, 19));
				}
				if (rs.getString(16) != null) {
					controller.honey_house_t[1] = new SimpleDateFormat("yyyy MM dd HH mm ss").parse(str = rs.getString(16).substring(0, 19));
				}
				controller.chicken_n[1] = rs.getInt(17);
				controller.chicken_n[2] = rs.getInt(18);
				controller.cow_n[1] = rs.getInt(19);
				controller.cow_n[2] = rs.getInt(20);
				controller.honey_house_n[1] = rs.getInt(21);
				controller.honey_house_n[2] = rs.getInt(22);
				Start.gold = rs.getInt(23);
				Start.week = rs.getInt(24);
				if (Start.week != 4) {
					if (Start.week == 0) {
						Start.mission_sub[Start.week][0][0] = rs.getInt(25);
						Start.mission_sub[Start.week][0][1] = rs.getInt(26);
					}
					else {
						Start.mission_sub[Start.week][0][0] = rs.getInt(25);
						Start.mission_sub[Start.week][0][1] = rs.getInt(26);
						Start.mission_sub[Start.week][1][0] = rs.getInt(27);
						Start.mission_sub[Start.week][1][1] = rs.getInt(28);
					}
				}
				if (controller.chicken_t[1] != null) {
					controller.chicken_n[0] = 2;
				}
				else if (controller.chicken_t[0] != null){
					controller.chicken_n[0] = 1;
				}
				else {
					controller.chicken_n[0] = 0;
				}
				if (controller.cow_t[1] != null) {
					controller.cow_n[0] = 2;
				}
				else if (controller.cow_t[0] != null){
					controller.cow_n[0] = 1;
				}
				else {
					controller.cow_n[0] = 0;
				}
				if (controller.honey_house_t[1] != null) {
					controller.honey_house_n[0] = 2;
				}
				else if (controller.honey_house_t[0] != null){
					controller.honey_house_n[0] = 1;
				}
				else {
					controller.honey_house_n[0] = 0;
				}
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
		
		try {
			search = "name ";
			BufferedReader name = new BufferedReader(new FileReader(text));
			str = name.readLine();
			if (str == null) {
				setTitle("name");
				setLocation(620, 355);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setLayout(new FlowLayout());
				
				check.addActionListener(this);
				
				add(new JLabel("이름 "));
				add(NAME);
				add(check);
				
				setSize(300,100);
				setVisible(true);
			}
			else if(str.substring(0, 5).equals(search)) {
				Start.name = str.substring(5);
				str = name.readLine();
				name.close();
				Start.info = Start.name;
				Start.player_move.start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			@SuppressWarnings("resource")
			BufferedReader pos = new BufferedReader(new FileReader(text));
			String str;
			while ((str = pos.readLine()) != null) {
				switch(str.substring(0, 2)) {
				case "eg":
					switch(str.substring(2, 3)) {
					case "0":
						controller.chicken_x[0] = Integer.valueOf(str.substring(3, 7));
						controller.chicken_y[0] = Integer.valueOf(str.substring(8, 11));
						break;
					case "1":
						controller.chicken_x[1] = Integer.valueOf(str.substring(3, 7));
						controller.chicken_y[1] = Integer.valueOf(str.substring(8, 11));
						break;
					}
					break;
				case "mi":
					switch(str.substring(2, 3)) {
					case "0":
						controller.cow_x[0] = Integer.valueOf(str.substring(3, 7));
						controller.cow_y[0] = Integer.valueOf(str.substring(8, 11));
						break;
					case "1":
						controller.cow_x[1] = Integer.valueOf(str.substring(3, 7));
						controller.cow_y[1] = Integer.valueOf(str.substring(8, 11));
						break;
					}
					break;
				case "ho":
					switch(str.substring(2, 3)) {
					case "0":
						controller.honey_house_x[0] = Integer.valueOf(str.substring(3, 7));
						controller.honey_house_y[0] = Integer.valueOf(str.substring(8, 11));
						break;
					case "1":
						controller.honey_house_x[1] = Integer.valueOf(str.substring(3, 7));
						controller.honey_house_y[1] = Integer.valueOf(str.substring(8, 11));
						break;
					}
					break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Start.name = NAME.getText();
		Start.info = Start.name;
		try {
			BufferedWriter name = new BufferedWriter(new FileWriter(text));
			name.write("name " + Start.name + "\n");
			name.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		dispose();
		Start.player_move.start();
	}
}
