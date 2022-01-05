package model.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;
import model.member.MemVO;

public class ConcermDAO {
	// C R U D

	// R - Select All ( ���ɱ� ��� ���� )
	public ArrayList<ConcermVO> getConcermList(MemVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<ConcermVO> concermList= new ArrayList<>();
		try {
			System.out.println("getConcermList() ����");
			String sql ="SELECT * FROM CONCERM WHERE MID=? ORDER BY FAVNUM DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				// FAVNUM / MID / PNUM / CATEGORY 
				ConcermVO post = new ConcermVO();
				post.setFavnum(rs.getInt("favnum"));						
				post.setMid(rs.getString("mid"));
				post.setMname(rs.getString("mname"));
				post.setPnum(rs.getInt("pnum"));
				post.setPtitle(rs.getString("ptitle"));
				post.setCategory(rs.getString("category"));
				concermList.add(post);
			}rs.close();
		}catch(Exception e) {
			System.out.println("[Exception�߻�] getFreePostList() Ȯ��!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}		
		return concermList;
	}

	// R - Select One ( ���ɱ� ��� ���� )
	public ConcermVO getConcerm(ConcermVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ConcermVO post = null;
		try {
			System.out.println("getConcerm() ����");
			String sql ="SELECT * FROM CONCERM WHERE PNUM=? AND CATEGORY=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.setString(2, vo.getCategory());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				// FAVNUM / MID / PNUM / CATEGORY 
				post = new ConcermVO();
				post.setFavnum(rs.getInt("favnum"));						
				post.setMid(rs.getString("mid"));
				post.setMname(rs.getString("mname"));
				post.setPnum(rs.getInt("pnum"));
				post.setPtitle(rs.getString("ptitle"));
				post.setCategory(rs.getString("category"));
			}rs.close();
		}catch(Exception e) {
			System.out.println("[Exception�߻�] getConcerm() Ȯ��!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}		
		return post;
	}

	// C - Insert (���ɱ� ���)
	public boolean insertConcermPost(ConcermVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res=false;
		try {
			//FAVNUM / MID / MNAME / PNUM / PTITLE / CATEGORY 
			String sql ="INSERT INTO CONCERM VALUES ((SELECT NVL(MAX(FAVNUM),0)+1 FROM CONCERM),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMname());
			pstmt.setInt(3, vo.getPnum());
			pstmt.setString(4, vo.getPtitle());
			pstmt.setString(5, vo.getCategory());
			pstmt.executeUpdate();
			res = true;			
		}catch(Exception e) {
			System.out.println("[Exception�߻�] insertNoticePost() Ȯ��!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}	
		return res;
	}


	// D - Delete (���ɱ� ����)			
	public boolean delConcermPost(ConcermVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res =false;
		try {
			String sql ="DELETE FROM CONCERM WHERE FAVNUM =? AND MID=?";
			pstmt = conn.prepareStatement(sql);;
			pstmt.setInt(1, vo.getFavnum());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
			res=true;
		}catch(Exception e) {
			System.out.println("[Exception�߻�] delNoticePost() Ȯ��!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}		
		return res;
	}

}
