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
			System.out.println("JDBC 드라이버를 찾지 못하였습니다.");
		} catch(SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("MySQL 서버가 중지되었거나 url, DB 관리자 ID, 패스워드가 잘못되었습니다.");
		}
		
		System.out.println("데이터베이스 정상 연결");
		
		return conn;
	}
	
	
}
