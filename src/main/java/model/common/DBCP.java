package model.common;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCP {
	public static Connection connect() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext(); // 를 사용하기 위해 javax.sql.*를 임포트해야한다.
			Context envContext = (Context) initContext.lookup("java:/comp/env"); // 실무에선 21,22번째줄에 나눠쓰는 이유는 결합도를 생각한다는
																					// 의미
			DataSource ds = (DataSource) envContext.lookup("jdbc/orcl");
			conn = ds.getConnection();
			// 내가 설정한 그 이름을 데이터 소스로 받아온다. initContext.lookup()는 반환이 오브젝트타입이라 위에서 캐스팅해야한다.
			// 프로그래머가 지향하는 낮은 결합도 ★

		} catch (Exception e) {
			System.out.println("connect()에서 출력");
			e.printStackTrace();
		}
		return conn;
	}

	public static void disconnect(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("disconnect()에서 출력");
			e.printStackTrace();
		}
	}
}
