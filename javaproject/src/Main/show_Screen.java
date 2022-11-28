package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class show_Screen extends JFrame implements ActionListener {
	
	private Connection con;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private String sql = "SELECT * FROM user_info";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private JButton startButton = new JButton("게임 시작");
	
	public show_Screen() throws SQLException, ClassNotFoundException {
		
		setTitle("java-project");
		setSize(1540, 824);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		startButton.setBorderPainted(false);//테두리
		startButton.setContentAreaFilled(false);//배경
		startButton.setFocusPainted(false);//포커스
		
		startButton.addActionListener(this);
		
		startButton.setFont(new Font("굴림", Font.BOLD, 25));
		
		startButton.setBounds(698, 590, 144, 22);
		
		add(startButton);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "system", "1234");
			
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		}
		catch(Exception e) {
			sql = "create table user_info(user_ID varchar2(20) not null, user_PW varchar2(20) not null, gold int, xpos varchar2(4), ypos varchar2(3), to_sub int, wh_sub int, st_sub int, sh_sub int, co_sub int, eg_sub int, mi_sub int, ho_sub int, chicken0 varchar2(20), chicken1 varchar2(20), cow0 varchar2(20), cow1 varchar2(20), honey_house0 varchar2(20), honey_house1 varchar2(20), chicken_n0 varchar2(20), chicken_n1 varchar2(20), cow_n0 varchar2(20), cow_n1 varchar2(20), honey_house_n0 varchar2(20), honey_house_n1 varchar2(20), week int,mission1 int,mission2 int,mission3 int,mission4 int,primary key(user_ID))";
			stmt = con.createStatement();
			stmt.execute(sql);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == startButton) {
			dispose();
			new Start();
		}
	}
}
