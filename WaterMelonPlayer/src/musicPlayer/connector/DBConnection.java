package musicPlayer.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn = null;

	public static Connection getConn() {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/watermelon_java?serverTimezone=Asia/Seoul";
			
			
			String user = "yunkwon";
			String pwd = "yunkwon";
			
			conn = DriverManager.getConnection(url,user, pwd);
			
			
					
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("JDBC ����̹��� ã�� ���Ͽ����ϴ�.");
		} catch(SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("MySQL ������ �����Ǿ��ų� url, DB ������ ID, �н����尡 �߸��Ǿ����ϴ�.");
		}
		
		System.out.println("�����ͺ��̽� ���� ����");
		
		return conn;
	}
	
	
}
