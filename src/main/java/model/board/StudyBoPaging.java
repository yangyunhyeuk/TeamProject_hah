package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;
import model.member.MemVO;

public class StudyBoPaging {
	// Study ���̺� ��ü �Խñ� ���� 
		public int getTotalCount(MemVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			int total = 0;
			try {
				conn = JDBC.connect();
				if(vo.getMid()==null || vo.getMid()=="") {
					String sql = "SELECT COUNT(*) FROM STUDYBOARD";
					pstmt = conn.prepareStatement(sql);
				}
				else {
					String sql = "SELECT COUNT(*) FROM STUDYBOARD WHERE MID=?";
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
					System.out.println("getTotalCount ���� �߻�");
					e.printStackTrace();
				}
				System.out.println("getTotalCount ����");

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
					sql = "SELECT COUNT(*) FROM STUDYBOARD";
				}
				else {
					sql = "SELECT COUNT(*) FROM STUDYBOARD WHERE "+condition+" LIKE '%"+content+"%'";
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
				System.out.println("getTotalCount ����");
			}
			return total;
		}
		
		// ���������� �Խñ۸���Ʈ�� ������ �޼��� 
		public ArrayList<StudyBoVO> getList(int startRow, int endRow,MemVO vo){
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			ArrayList<StudyBoVO> list = new ArrayList<StudyBoVO>();
			try {
				if(vo.getMid()==null || vo.getMid()=="") {
					String sql ="SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM STUDYBOARD ORDER BY PNUM DESC ) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
					pstmt = conn.prepareStatement(sql);
				}
				else {
					String sql ="SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM STUDYBOARD WHERE MID=? ORDER BY PNUM DESC ) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, vo.getMid());
				}
				System.out.println("start:"+startRow +" end:"+endRow);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					StudyBoVO p = new StudyBoVO();
					p.setPnum(rs.getInt("pnum"));
					p.setPtitle(rs.getString("ptitle"));
					p.setPcontent(rs.getString("pcontent"));
					p.setPdate(rs.getDate("pdate"));
					p.setMname(rs.getString("mname"));
					p.setMid(rs.getString("mid"));
					p.setCnt(rs.getInt("cnt"));
					p.setCategory(rs.getString("category"));

					String sql="SELECT * FROM STUDYCOMMENT WHERE PNUM=?";
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
				System.out.println("StudyPaging��List="+list);
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}
			return list;
		}
		// getList() Overloading
		public ArrayList<StudyBoVO> getList(int startRow, int endRow, String condition, String content){
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			ArrayList<StudyBoVO> list = new ArrayList<StudyBoVO>();
			String sql;
			try {
				if(condition==null||content==null||condition==""||content=="") {
					sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM STUDYBOARD ORDER BY PNUM DESC) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				}
				else {
					sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM STUDYBOARD WHERE "+condition+" LIKE '%"+content+"%'ORDER BY PNUM DESC) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				}
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					StudyBoVO p = new StudyBoVO();
					p.setPnum(rs.getInt("pnum"));
					p.setPtitle(rs.getString("ptitle"));
					p.setPcontent(rs.getString("pcontent"));
					p.setPdate(rs.getDate("pdate"));
					p.setMname(rs.getString("mname"));
					p.setMid(rs.getString("mid"));
					p.setCnt(rs.getInt("cnt"));
					p.setCategory(rs.getString("category"));

					sql="SELECT * FROM STUDYCOMMENT WHERE PNUM=?";
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
				System.out.println("StudyPaging��List="+list);
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}
			return list;
		}
}