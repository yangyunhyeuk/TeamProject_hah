package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;

// DAO : NoticeCoDAO
// VO : NoticeCoVO
// 테이블명 : NOTICECOMMENT

public class NoticeCoDAO {
	// ==========================================================================================================
	public ArrayList<NoticeCoVO> SelectAll(NoticeCoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<NoticeCoVO> datas = new ArrayList<NoticeCoVO>();
		try {
			String sql_SelectAll = "SELECT * FROM NOTICECOMMENT WHERE PNUM=? ORDER BY CNUM DESC"; // 최근 게시글 상단 배치
			pstmt = conn.prepareStatement(sql_SelectAll);
			pstmt.setInt(1, vo.getPnum());
			ResultSet rs = pstmt.executeQuery(); // executeQuery 반환타입 rs!
			while (rs.next()) {
				NoticeCoVO data = new NoticeCoVO();
				data.setCnum(rs.getInt("cnum"));
				data.setComm(rs.getString("comm"));
				data.setCdate(rs.getString("cdate"));
				data.setPnum(rs.getInt("pnum"));
				data.setMname(rs.getString("mname"));
				data.setMid(rs.getString("mid"));
				datas.add(data);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("SelectAll()에서 출력");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}

		return datas;
	}

	// ==========================================================================================================
	public FreeCoVO SelectOne(NoticeCoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		FreeCoVO commdata = null;
		try {
			String sql_SelectOne = "select * from NOTICECOMMENT where cnum = ?"; // 일단 기본키인 cnum으로 조건절 작성
			pstmt = conn.prepareStatement(sql_SelectOne);
			pstmt.setInt(1, vo.getCnum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				commdata = new FreeCoVO();
				commdata.setCnum(rs.getInt("cnum"));
				commdata.setComm(rs.getString("comm"));
				commdata.setCdate(rs.getString("cdate"));
				commdata.setPnum(rs.getInt("pnum"));
				commdata.setMname(rs.getString("mname"));
				commdata.setMid(rs.getString("mid"));

			}
			rs.close();
		} catch (Exception e) {
			System.out.println("SelectOne()에서 출력");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return commdata;
	}

	public int getTotalCount(NoticeCoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		int total = 0;
		try {
			conn = JDBC.connect();
			String sql = "SELECT COUNT(*) FROM NOTICECOMMENT WHERE PNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("getTotalCount 에러 발생");
				e.printStackTrace();
			}
			System.out.println("getTotalCount 퇴장");

		}
		return total;
	}
	
	// ==========================================================================================================
	public boolean InsertDB(NoticeCoVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_INSERT = "INSERT INTO NOTICECOMMENT VALUES ((SELECT NVL(MAX(cnum),0)+1 FROM NOTICECOMMENT),?,SYSDATE,?,?,?)";
			// 현재 댓글 작성 시 ①댓글 내용 ②해당 게시글 번호 ③회원 닉네임 ④회원 아이디를 입력값으로 받는 상태
			// 추후 수정할 예정

			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getComm()); // 댓글 내용
			pstmt.setInt(2, vo.getPnum()); // 게시글 번호
			pstmt.setString(3, vo.getMname()); // 회원 닉네임
			pstmt.setString(4, vo.getMid()); // 회원 아이디

			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("InsertDB()에서 출력");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// ==========================================================================================================
	public boolean UpdateDB(NoticeCoVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_UPDATE = "UPDATE NOTICECOMMENT SET COMM=?, PNUM=?, MNAME=?, MID=? where CNUM=?"; // 업데이트 조건, cnum
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getComm());
			pstmt.setInt(2, vo.getPnum());
			pstmt.setString(3, vo.getMname());
			pstmt.setString(4, vo.getMid());
			pstmt.setInt(5, vo.getCnum());

			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("UpdateDB()에서 출력");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// ==========================================================================================================
	public boolean DeleteDB(NoticeCoVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_DeleteDB = "DELETE FROM NOTICECOMMENT WHERE CNUM=?";
			pstmt = conn.prepareStatement(sql_DeleteDB);
			pstmt.setInt(1, vo.getCnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("DeleteDB()에서 출력");
			e.printStackTrace();
			// res=false;
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}
	// ==========================================================================================================
}
