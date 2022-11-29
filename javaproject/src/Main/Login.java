package Main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*; 
import java.sql.*;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener{
	
	private JTextField ID = new JTextField(20);
	private JPasswordField PW = new JPasswordField(20);
	
	private Connection con;
	private PreparedStatement pstmt = null;
	
	JButton join = new JButton("계정 생성");
	JButton check = new JButton("로그인");
	JButton delete = new JButton("계정 삭제");

	public Login() {
		setTitle("로그인");
		setLocation(640, 330);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		PW.setEchoChar('*');
		
		add(new JLabel("ID "));
		add(ID);
		add(new JLabel("PW "));
		add(PW);
		add(check);
		add(join);
		add(delete);
			
		check.addActionListener(this);
		join.addActionListener(this);
		delete.addActionListener(this);
			
		setSize(260,150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == check) {
			String ID_S = ID.getText(), PW_S = new String(PW.getPassword());
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String sql = "SELECT user_ID, user_PW FROM user_info WHERE user_ID = '" + ID_S + "'";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, "system", "1234");
				
				pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					if (PW_S.equals(rs.getString(2))) {
						new present_login_userData(ID_S);
						dispose();
						Start.wating_login.setVisible(false);
						Start.start_game.setVisible(true);
						new file_data();
					}
					else {
						new Notmatch_Error_PW();
					}
				}
				else {
					new Notmatch_Error_ID();
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == join) {
			new join();
		}
		else if (e.getSource() == delete) {
			new delete();
		}
	}
}

@SuppressWarnings("serial")
class join extends JFrame implements ActionListener {
	
	private JTextField ID = new JTextField(20);
	private JPasswordField PW = new JPasswordField(20);
	
	private Connection con;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	private PreparedStatement pstmt2 = null;
	
	JButton createAccount = new JButton("계정 생성");
	
	public join() {
		setTitle("계정 생성");
		setLocation(640, 330);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		PW.setEchoChar('*');

		add(new JLabel("ID "));
		add(ID);
		add(new JLabel("PW "));
		add(PW);
		add(createAccount);
		
		createAccount.addActionListener(this);

		setSize(260,150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ID_S = ID.getText(), PW_S = new String(PW.getPassword());
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "INSERT INTO user_info(user_ID, user_PW, xpos, ypos, gold, week, mission1, mission2, mission3, mission4) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "SELECT user_ID FROM user_info WHERE user_ID = '" + ID_S + "'";
		String sql2 = "SELECT COUNT(user_ID) FROM user_info";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "system", "1234");
			
			pstmt = con.prepareStatement(sql);
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			ResultSet rs = pstmt1.executeQuery();
			ResultSet rs1 = pstmt2.executeQuery();
			if (!ID_S.equals("")) {
				if (rs.next()) {
					if (ID_S.equals(rs.getString(1))) {
						new ID_Error();
					}
					else if (PW_S.equals("")) {
						new Notwrite_Error_PW();
					}
				}
				else {
					if (rs1.next()) {
						if (PW_S.equals("")) {
							new Notwrite_Error_PW();
						}
						else if (Integer.valueOf(rs1.getString(1)) >= 5) {
							new Full_Account();
						}
						else {
							pstmt.setString(1, ID_S);
							pstmt.setString(2, PW_S);
							pstmt.setString(3, "720");
							pstmt.setString(4, "312");
							pstmt.setInt(5, 200);
							pstmt.setInt(6, 0);
							pstmt.setInt(7, 0);
							pstmt.setInt(8, 0);
							pstmt.setInt(9, 0);
							pstmt.setInt(10, 0);
							
							pstmt.executeUpdate();
							try {
								con.close();
							}
							catch(SQLException e1) {}
							
							dispose();
							
							new CreateComplate();
						}
					}
				}
			}
			else {
				new Notwrite_Error_ID();
			}
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}

@SuppressWarnings("serial")
class delete extends JFrame implements ActionListener{
	
	JButton[] delete_Account = null;
	String[] delete_user_ID;
	JLabel delete = new JLabel("삭제할 계정 선택");
	
	private Connection con;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	int count = 0;
	
	public delete() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "SELECT COUNT(user_ID) FROM user_info";
		String sql1 = "SELECT user_ID FROM user_info";
		
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "system", "1234");
			
			pstmt = con.prepareStatement(sql);
			pstmt1 = con.prepareStatement(sql1);			
			ResultSet rs = pstmt.executeQuery();
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				delete_Account = new JButton[count];
				delete_user_ID = new String[count];
				int i = 0;
				while (rs1.next()) {
					delete_Account[i] = new JButton(rs1.getString(1));
					delete_user_ID[i] = rs1.getString(1);
					i++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		setTitle("계정 삭제");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		add(delete);
		delete.setBounds(75, 0, 130, 50);
		for (int i = 0; i < count; i++) {
			add(delete_Account[i]);
			delete_Account[i].setBounds(0, i * 100 + 50, 260, 90);
			delete_Account[i].addActionListener(this);
		}
		
		setLocation(640, 355 - count * 50);
		setSize(260, count * 100 + 100);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File delfile = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "";
		if (e.getSource() == delete_Account[0]) {
			delfile = new File("textfile/" + delete_Account[0].getText() + ".txt");
			delfile.delete();
			sql = "delete user_info where user_ID = '" + delete_user_ID[0] + "'";
		}
		else if (e.getSource() == delete_Account[1]) {
			delfile = new File("textfile/" + delete_Account[1].getText() + ".txt");
			delfile.delete();
			sql = "delete user_info where user_ID = '" + delete_user_ID[1] + "'";
		}
		else if (e.getSource() == delete_Account[2]) {
			delfile = new File("textfile/" + delete_Account[2].getText() + ".txt");
			delfile.delete();
			sql = "delete user_info where user_ID = '" + delete_user_ID[2] + "'";
		}
		else if (e.getSource() == delete_Account[3]) {
			delfile = new File("textfile/" + delete_Account[3].getText() + ".txt");
			delfile.delete();
			sql = "delete user_info where user_ID = '" + delete_user_ID[3] + "'";
		}
		else if (e.getSource() == delete_Account[4]) {
			delfile = new File("textfile/" + delete_Account[4].getText() + ".txt");
			delfile.delete();
			sql = "delete user_info where user_ID = '" + delete_user_ID[4] + "'";
		}
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "system", "1234");
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			try {
				con.close();
			}
			catch(SQLException e2) {}
			dispose();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}

@SuppressWarnings("serial")
class Full_Account extends JFrame {
	
	public Full_Account() {
		setTitle("계정 꽉참");
		setLocation(640, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("이미 계정이 5개 입니다."));
		
		setSize(260,60);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class CreateComplate extends JFrame {
	
	public CreateComplate() {
		setTitle("생성 완료");
		setLocation(640, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("계정 생성이 완료되었습니다."));
		
		setSize(260,60);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class ID_Error extends JFrame {
	
	public ID_Error() {
		setTitle("아이디 중복");
		setLocation(610, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("중복된 아이디 입니다. 다시 입력해 주세요."));
		
		setSize(320,60);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class Notwrite_Error_ID extends JFrame {
	
	public Notwrite_Error_ID() {
		setTitle("아이디 공백");
		setLocation(580, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("아이디가 입력되지 않았습니다. 다시 입력해 주세요."));
		
		setSize(380,60);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class Notwrite_Error_PW extends JFrame {
	
	public Notwrite_Error_PW() {
		setTitle("비밀번호 공백");
		setLocation(580, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("비밀번호가 입력되지 않았습니다. 다시 입력해 주세요."));
		
		setSize(380,60);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class Notmatch_Error_PW extends JFrame {
	
	public Notmatch_Error_PW() {
		setTitle("잘못된 입력");
		setLocation(580, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("비밀번호가 잘못 입력되었습니다. 다시 입력해 주세요."));
		
		setSize(380,60);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class Notmatch_Error_ID extends JFrame {
	
	public Notmatch_Error_ID() {
		setTitle("잘못된 입력");
		setLocation(580, 375);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("아이디가 잘못 입력되었습니다. 다시 입력해 주세요."));
		
		setSize(380,60);
		setVisible(true);
	}
}

class present_login_userData {
	
	public static String ID;
	
	public present_login_userData() {}
	
	@SuppressWarnings("static-access")
	public present_login_userData(String ID_S) {
		this.ID = ID_S;
	}
}