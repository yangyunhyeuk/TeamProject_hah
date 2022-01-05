package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;

public class StudyBoDAO {
	// C R U D

	// R - Select All ( 게시글 목록 보기 )
	public ArrayList<StudyBoVO> getStudyPostList(String num) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<StudyBoVO> spList = new ArrayList<>();
		String sql;
		try {
			if (num == "1") {
				sql = "SELECT * FROM STUDYBOARD ORDER BY PNUM DESC";
				pstmt = conn.prepareStatement(sql);
			} else if (num == "2") {
				sql = "SELECT * FROM STUDYBOARD ORDER BY CNT DESC";
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// PNUM / PTITLE / PCONTENT / PDATE / MNAME / MID / CNT / CATEGORY
				StudyBoVO post = new StudyBoVO();
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
			System.out.println("[Exception발생] getStudyPostList() 확인!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return spList;
	}

	// R - Select One ( 게시글 상세 보기 )
	public StudyBoVO getStudyPost(StudyBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		StudyBoVO post = null;
		try {
			String sql = "SELECT * FROM STUDYBOARD WHERE PNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// PNUM / PTITLE / PCONTENT / PDATE / MNAME / MID / CNT / CATEGORY
				post = new StudyBoVO();
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
		} catch (Exception e) {
			System.out.println("[Exception발생] getStudyPost() 확인!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return post;
	}

	// U - CNT+1 업데이트
	public boolean updateStudyCnt(StudyBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			String sql = "UPDATE STUDYBOARD SET CNT=CNT+1 WHERE PNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("[Exception발생] updateStudyCnt() 확인!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// C - Insert (게시글 작성)
	public boolean insertStudyPost(StudyBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			String sql = "INSERT INTO STUDYBOARD (PNUM,PTITLE,PCONTENT,MNAME,MID) VALUES ((SELECT NVL(MAX(PNUM),0)+1 FROM STUDYBOARD),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPtitle());
			pstmt.setString(2, vo.getPcontent());
			pstmt.setString(3, vo.getMname());
			pstmt.setString(4, vo.getMid());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("[Exception발생] insertStudyPost() 확인!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// U - Update ( 게시글 수정 )
	public boolean editStudyPost(StudyBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			String sql = "UPDATE STUDYBOARD SET PTITLE=?,PCONTENT=? WHERE PNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPtitle());
			pstmt.setString(2, vo.getPcontent());
			pstmt.setInt(3, vo.getPnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("[Exception발생] editStudyPost() 확인!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// D - Delete (게시글 삭제)

	public boolean delStudyPost(StudyBoVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			String sql = "DELETE FROM STUDYBOARD WHERE PNUM =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("[Exception발생] delStudyPost() 확인!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return res;
	}

	// 검색기능 구현 - Select All
	public ArrayList<StudyBoVO> searchStudy(String condition, String content) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<StudyBoVO> spList = new ArrayList<>();
		String sql;
		try {
			sql = "SELECT * FROM STUDYBOARD WHERE " + condition + " LIKE '%" + content + "%' ORDER BY PNUM DESC";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StudyBoVO post = new StudyBoVO();
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
			System.out.println("searchTitle() 이셉션 발생!");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(conn, pstmt);
		}
		return spList;
	}



}
