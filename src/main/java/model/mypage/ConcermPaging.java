package model.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;
import model.member.MemVO;

public class ConcermPaging {
	// Study 테이블 전체 게시글 개수 
	public int getTotalCount(MemVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		int total = 0;
		try {
			conn = JDBC.connect();
			if(vo.getMid()==null || vo.getMid()=="") {
				String sql = "SELECT COUNT(*) FROM CONCERM";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				String sql = "SELECT COUNT(*) FROM CONCERM WHERE MID=?";
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
	
	// 한페이지에 게시글리스트를 보여줄 메서드 
	public ArrayList<ConcermVO> getList(int startRow, int endRow,MemVO vo){
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<ConcermVO> list = new ArrayList<ConcermVO>();
		System.out.println("start:"+startRow +" end:"+endRow);
		try {
			if(vo.getMid()==null || vo.getMid()=="") {
				String sql ="SELECT * FROM (SELECT ROWNUM AS RNUM, A.FAVNUM,A.MID,A.MNAME,A.PNUM,A.PTITLE,A.CATEGORY FROM (SELECT * FROM CONCERM ORDER BY FAVNUM DESC ) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				pstmt = conn.prepareStatement(sql);
			}
			else {
				String sql ="SELECT * FROM (SELECT ROWNUM AS RNUM, A.FAVNUM,A.MID,A.MNAME,A.PNUM,A.PTITLE,A.CATEGORY FROM (SELECT * FROM CONCERM WHERE MID=? ORDER BY FAVNUM DESC ) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMid());
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ConcermVO p = new ConcermVO();
				p.setFavnum(rs.getInt("favnum"));
				p.setMid(rs.getString("mid"));
				p.setMname(rs.getString("mname"));
				p.setPnum(rs.getInt("pnum"));
				p.setPtitle(rs.getString("ptitle"));
				p.setCategory(rs.getString("category"));
				list.add(p);
			}
			System.out.println("ConcermPaging의List="+list);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}
		return list;
	}
}
