package model.common;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {

// 1. Connection 객체를 생성한다. 
// 2. 생성된 Connection 객체를 닫아준다. 

//DB 연결=================================================================
	public static Connection connect() {
		Connection conn = null;

		String Dname = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "yang";
		String password = "1234";

		try {
			Class.forName(Dname);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return conn;
	}

//DB 연결 해제=================================================================
	public static void disconnect(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
