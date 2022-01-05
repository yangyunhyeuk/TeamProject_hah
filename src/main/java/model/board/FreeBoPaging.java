package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;
import model.member.MemVO;

public class FreeBoPaging {
	// Study 테이블 전체 게시글 개수 
	public int getTotalCount(MemVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		int total = 0;
		try {
			conn = JDBC.connect();
			if(vo.getMid()==null || vo.getMid()=="") {
				String sql = "SELECT COUNT(*) FROM FREEBOARD";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				String sql = "SELECT COUNT(*) FROM FREEBOARD WHERE MID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMid());
			}

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
	// getTotalCount() Overloading
	public int getTotalCount(String condition, String content) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		int total = 0;
		String sql;
		try {
			if(condition==null||content==null||condition==""||content=="") {
				sql = "SELECT COUNT(*) FROM FREEBOARD";
			}
			else {
				sql = "SELECT COUNT(*) FROM FREEBOARD WHERE "+condition+" LIKE '%"+content+"%'";
			}
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
			System.out.println("getTotalCount 퇴장");
		}
		return total;
	}

	// 한페이지에 게시글리스트를 보여줄 메서드 
	public ArrayList<FreeBoVO> getList(int startRow, int endRow,MemVO vo){
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<FreeBoVO> list = new ArrayList<FreeBoVO>();
		System.out.println("start:"+startRow +" end:"+endRow);
		try {
			if(vo.getMid()==null || vo.getMid()=="") {
				String sql ="SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM FREEBOARD ORDER BY PNUM DESC ) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				pstmt = conn.prepareStatement(sql);
			}
			else {
				String sql ="SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM FREEBOARD WHERE MID=? ORDER BY PNUM DESC ) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMid());
			}

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print("페이지 관련 dao 접속!");
				FreeBoVO p = new FreeBoVO();
				p.setPnum(rs.getInt("pnum"));
				p.setPtitle(rs.getString("ptitle"));
				p.setPcontent(rs.getString("pcontent"));
				p.setPdate(rs.getDate("pdate"));
				p.setMname(rs.getString("mname"));
				p.setMid(rs.getString("mid"));
				p.setCnt(rs.getInt("cnt"));
				p.setCategory(rs.getString("category"));
				
				String sql="SELECT * FROM FREECOMMENT WHERE PNUM=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("pnum"));
				ResultSet rrs = pstmt.executeQuery();
				int replyCnt=0;
				while(rrs.next()) {
					replyCnt++;
				}
				p.setReplyCnt(replyCnt);
				list.add(p);
			}
			System.out.println("FreeBoPaging의List="+list);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}
		return list;
	}
	// getList() Overloading
	public ArrayList<FreeBoVO> getList(int startRow, int endRow, String condition, String content){
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<FreeBoVO> list = new ArrayList<FreeBoVO>();
		String sql;
		System.out.println("start:"+startRow +" end:"+endRow);
		try {
			if(condition==null||content==null||condition==""||content=="") {
				sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM FREEBOARD ORDER BY PNUM DESC) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
			}
			else {
				sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM FREEBOARD WHERE "+condition+" LIKE '%"+content+"%'ORDER BY PNUM DESC) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
			}
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//System.out.print("페이지 관련 dao 접속!");
				FreeBoVO p = new FreeBoVO();
				p.setPnum(rs.getInt("pnum"));
				p.setPtitle(rs.getString("ptitle"));
				p.setPcontent(rs.getString("pcontent"));
				p.setPdate(rs.getDate("pdate"));
				p.setMname(rs.getString("mname"));
				p.setMid(rs.getString("mid"));
				p.setCnt(rs.getInt("cnt"));
				p.setCategory(rs.getString("category"));
				
				sql="SELECT * FROM FREECOMMENT WHERE PNUM=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("pnum"));
				ResultSet rrs = pstmt.executeQuery();
				int replyCnt=0;
				while(rrs.next()) {
					replyCnt++;
				}
				p.setReplyCnt(replyCnt);
				list.add(p);
			}
			System.out.println("FreeBoPaging의List="+list);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}
		return list;
	}
}
