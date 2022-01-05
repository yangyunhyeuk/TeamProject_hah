// 1.0.0 
package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;


public class FreeBoDAO {
	
	// C R U D

	// R - Select All ( 게시글 목록 보기 )
		public ArrayList<FreeBoVO> getFreePostList(String num) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			ArrayList<FreeBoVO> spList = new ArrayList<>();
			String sql;
			try {
				if (num == "1") {
					sql = "SELECT * FROM FREEBOARD ORDER BY PNUM DESC";
					pstmt = conn.prepareStatement(sql);
				} else if (num == "2") {
					sql = "SELECT * FROM FREEBOARD ORDER BY CNT DESC";
					pstmt = conn.prepareStatement(sql);
				}
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					// PNUM / PTITLE / PCONTENT / PDATE / MNAME / MID / CNT / CATEGORY
					FreeBoVO post = new FreeBoVO();
					post.setPnum(rs.getInt("pnum"));
					post.setPtitle(rs.getString("ptitle"));
					post.setPcontent(rs.getString("pcontent"));
					post.setPdate(rs.getDate("pdate"));
					post.setMname(rs.getString("mname"));
					post.setMid(rs.getString("mid"));
					post.setCnt(rs.getInt("cnt"));
					post.setCategory(rs.getString("category"));
					spList.add(post);
				}
				rs.close();
			} catch (Exception e) {
				System.out.println("[Exception발생] getFreePostList() 확인!");
				e.printStackTrace();
			} finally {
				JDBC.disconnect(conn, pstmt);
			}
			return spList;
		}
	
	
	// R - Select One ( 게시글 상세 보기 )
	public FreeBoVO getFreePost(FreeBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		FreeBoVO post = null;
		try {
			// System.out.println("getFreePost() 수행");
			String sql ="SELECT * FROM FREEBOARD WHERE PNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
	//PNUM / PTITLE / PCONTENT / PDATE / MNAME / MID / CNT / CATEGORY	
				post = new FreeBoVO();	
				post.setPnum(rs.getInt("pnum"));
				post.setPtitle(rs.getString("ptitle"));
				post.setPcontent(rs.getString("pcontent"));
				post.setPdate(rs.getDate("pdate"));
				post.setMname(rs.getString("mname"));
				post.setMid(rs.getString("mid"));
				post.setCnt(rs.getInt("cnt"));
				post.setCategory(rs.getString("category"));
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("[Exception발생] getFreePost() 확인!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}		
		// System.out.println("getFreePost() : "+post);
		return post;
	}
		
	// U - CNT+1 업데이트 
		public boolean updateFreeCnt(FreeBoVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			boolean res =false;
			try {
				// System.out.println("updateFreeCnt() 수행");
				String sql = "UPDATE FREEBOARD SET CNT=CNT+1 WHERE PNUM=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getPnum());
				pstmt.executeUpdate();
				res=true;
			}catch(Exception e) {
				System.out.println("[Exception발생] updateFreeCnt() 확인!");
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}		
			// System.out.println("getFreePost() : "+post);
			return res;
		}
		
	// C - Insert (게시글 작성)
	public boolean insertFreePost(FreeBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res=false;
		try {
			String sql ="INSERT INTO FREEBOARD (PNUM,PTITLE,PCONTENT,MNAME,MID) VALUES ((SELECT NVL(MAX(PNUM),0)+1 FROM FREEBOARD),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getPtitle());
			pstmt.setString(2,vo.getPcontent());
			pstmt.setString(3, vo.getMname());
			pstmt.setString(4, vo.getMid());
			pstmt.executeUpdate();
			res = true;			
		}catch(Exception e) {
			System.out.println("[Exception발생] insertFreePost() 확인!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}			
		return res;
	}
	
	// U - Update ( 게시글 수정  )
	public boolean editFreePost(FreeBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res= false;
		try {
			String sql ="UPDATE FREEBOARD SET PTITLE=?,PCONTENT=? WHERE PNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getPtitle());
			pstmt.setString(2, vo.getPcontent());
			pstmt.setInt(3, vo.getPnum());
			pstmt.executeUpdate();
			res=true;
		}catch(Exception e) {
			System.out.println("[Exception발생] editFreePost() 확인!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}		
		return res;
	}
	
	// D - Delete (게시글 삭제)
	public boolean delFreePost(FreeBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res =false;
		try {
			String sql ="DELETE FROM FREEBOARD WHERE PNUM =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.executeUpdate();
			res=true;
		}catch(Exception e) {
			System.out.println("delFreePost() 이셉션 발생!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}		
		return res;
	}
	
	// 검색기능 구현 - Title  Select All 
	public ArrayList<FreeBoVO> searchFree(String condition, String content) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<FreeBoVO> fpList= new ArrayList<>();
		String sql;
		try {
			sql = "SELECT * FROM FREEBOARD WHERE "+condition+" LIKE '%"+content+"%' ORDER BY PNUM DESC";
			//sql = "SELECT * FROM FREEBOARD WHERE PTITLE LIKE '%1%' ORDER BY PNUM DESC";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeBoVO post = new FreeBoVO();
				post.setPnum(rs.getInt("pnum"));
				post.setPtitle(rs.getString("ptitle"));
				post.setPcontent(rs.getString("pcontent"));
				post.setPdate(rs.getDate("pdate"));
				post.setMname(rs.getString("mname"));
				post.setMid(rs.getString("mid"));
				post.setCnt(rs.getInt("cnt"));
				post.setCategory(rs.getString("category"));
				fpList.add(post);
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("searchTitle() 이셉션 발생!");
			e.printStackTrace();
		}finally {
			JDBC.disconnect(conn, pstmt);
		}	
		return fpList;
	}

	
	
	
}
