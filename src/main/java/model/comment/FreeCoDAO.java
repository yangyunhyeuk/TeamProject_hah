package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;

//DAO : FreeCoDAO
//VO : FreeCoVO
//���̺�� : FREECOMMENT


public class FreeCoDAO {
	// ==========================================================================================================
	public ArrayList<FreeCoVO> SelectAll(FreeCoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<FreeCoVO> datas = new ArrayList<FreeCoVO>();
		try {
			String sql_SelectAll = "SELECT * FROM FREECOMMENT WHERE PNUM=? ORDER BY CNUM DESC"; // �ֱ� �Խñ� ��� ��ġ
			pstmt = conn.prepareStatement(sql_SelectAll);
			pstmt.setInt(1, vo.getPnum());
			ResultSet rs = pstmt.executeQuery(); // executeQuery ��ȯŸ�� rs!
			while (rs.next()) {
				FreeCoVO data = new FreeCoVO();
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
			System.out.println("SelectAll()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}

		return datas;
	}

	// ==========================================================================================================
	public FreeCoVO SelectOne(FreeCoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		FreeCoVO commdata = null;
		try {
			String sql_SelectOne = "select * from FREECOMMENT where cnum = ?"; // �ϴ� �⺻Ű�� cnum���� ������ �ۼ�
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
			System.out.println("SelectOne()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return commdata;
	}
	
	public int getTotalCount(FreeCoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		int total = 0;
		try {
			conn = JDBC.connect();
			String sql = "SELECT COUNT(*) FROM FREECOMMENT WHERE PNUM=?";
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
				System.out.println("getTotalCount ���� �߻�");
				e.printStackTrace();
			}
			System.out.println("getTotalCount ����");

		}
		return total;
	}

	// ==========================================================================================================
	public boolean InsertDB(FreeCoVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_INSERT = "INSERT INTO FREECOMMENT VALUES ((SELECT NVL(MAX(cnum),0)+1 FROM FREECOMMENT),?,SYSDATE,?,?,?)";
			// ���� ��� �ۼ� �� ���� ���� ���ش� �Խñ� ��ȣ ��ȸ�� �г��� ��ȸ�� ���̵� �Է°����� �޴� ����
			// ���� ������ ����
			
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getComm()); // ��� ����
			pstmt.setInt(2, vo.getPnum()); // �Խñ� ��ȣ
			pstmt.setString(3, vo.getMname()); // ȸ�� �г���
			pstmt.setString(4, vo.getMid()); // ȸ�� ���̵�

			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("InsertDB()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// ==========================================================================================================
	public boolean UpdateDB(FreeCoVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_UPDATE = "UPDATE FREECOMMENT SET COMM=?, PNUM=?, MNAME=?, MID=? where CNUM=?"; // ������Ʈ ����, cnum
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getComm());
			pstmt.setInt(2, vo.getPnum());
			pstmt.setString(3, vo.getMname());
			pstmt.setString(4, vo.getMid());
			pstmt.setInt(5, vo.getCnum());

			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("UpdateDB()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// ==========================================================================================================
	public boolean DeleteDB(FreeCoVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_DeleteDB = "DELETE FROM FREECOMMENT WHERE CNUM=?";
			pstmt = conn.prepareStatement(sql_DeleteDB);
			pstmt.setInt(1, vo.getCnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("DeleteDB()���� ���");
			e.printStackTrace();
			// res=false;
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}
	// ==========================================================================================================
}
