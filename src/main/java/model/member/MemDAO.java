package model.member;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;

//private String mid; // MID VARCHAR(50) PRIMARY KEY, -- ȸ�� ���̵�
//private String mpw; // MPW VARCHAR(50) NOT NULL, -- ȸ�� ��й�ȣ
//private String mname; // MNAME VARCHAR(50) NOT NULL, -- ȸ�� �г���
//private String memail; // MEMAIL VARCHAR(50) NOT NULL, -- ȸ�� �̸���
//private String role; // ROLE VARCHAR(20) NOT NULL -- �����/������

public class MemDAO {
//==========================================================================================================
	public ArrayList<MemVO> SelectAll() {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<MemVO> datas = new ArrayList<MemVO>();
		try {
			String sql_SelectAll = "SELECT * FROM MEMBER"; // �ֱ� �Խñ� ��� ��ġ
			pstmt = conn.prepareStatement(sql_SelectAll);
			ResultSet rs = pstmt.executeQuery(); // executeQuery ��ȯŸ�� rs!
			while (rs.next()) {
				MemVO vo = new MemVO();
				vo.setMid(rs.getString("mid"));
				vo.setMpw(rs.getString("mpw"));
				vo.setMname(rs.getString("mname"));
				vo.setMemail(rs.getString("memail"));
				vo.setRole(rs.getString("role"));
				datas.add(vo);
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

//==========================================================================================================	
	public MemVO SelectOne(MemVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		MemVO data = null;
		try {
			String sql_SelectOne = "select * from MEMBER where mid = ?";
			pstmt = conn.prepareStatement(sql_SelectOne);
			pstmt.setString(1, vo.getMid());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new MemVO();
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
				data.setMname(rs.getString("mname"));
				data.setMemail(rs.getString("memail"));
				data.setRole(rs.getString("role"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("getDBData()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return data;
	}

//==========================================================================================================	
	public boolean InsertDB(MemVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_INSERT = "INSERT INTO MEMBER (MID,MPW,MNAME,MEMAIL) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			pstmt.setString(3, vo.getMname());
			pstmt.setString(4, vo.getMemail());
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

//==========================================================================================================
	public boolean UpdateDB(MemVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_UPDATE = "UPDATE MEMBER SET MPW=?, MNAME =?, MEMAIL=? where MID=?";
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getMpw());
			pstmt.setString(2, vo.getMname());
			pstmt.setString(3, vo.getMemail());
			pstmt.setString(4, vo.getMid());
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

//==========================================================================================================
	public boolean DeleteDB(MemVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_DeleteDB = "DELETE FROM MEMBER WHERE MID=?";
			pstmt = conn.prepareStatement(sql_DeleteDB);
			pstmt.setString(1, vo.getMid());
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
//==========================================================================================================
	public boolean UpdatePW(MemVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_UPDATE = "UPDATE MEMBER SET MPW=? where MID=?";
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getMpw());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("UpdatePW()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

//==========================================================================================================

	public boolean CheckUser(MemVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_SelectOne = "select * from MEMBER where mid = ?";
			pstmt = conn.prepareStatement(sql_SelectOne);
			pstmt.setString(1, vo.getMid());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				res = true;
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("CheckUser()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}
//==========================================================================================================
	public boolean CheckMname(MemVO vo) {
		Connection conn = JDBC.connect();
		boolean res = false;
		PreparedStatement pstmt = null;
		try {
			String sql_SelectOne = "select * from MEMBER where mname= ?";
			pstmt = conn.prepareStatement(sql_SelectOne);
			pstmt.setString(1, vo.getMname());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				res = true;
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("CheckMname()���� ���");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}
}
