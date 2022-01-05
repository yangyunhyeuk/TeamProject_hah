package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;

public class NoticeBoPaging {
	// Notice 테이블 전체 게시글 개수 
		public int getTotalCount() {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			int total = 0;
			try {
				conn = JDBC.connect();

				String sql = "SELECT COUNT(*) FROM NOTICEBOARD";
				pstmt = conn.prepareStatement(sql);

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
					sql = "SELECT COUNT(*) FROM NOTICEBOARD";
				}
				else {
					sql = "SELECT COUNT(*) FROM NOTICEBOARD WHERE "+condition+" LIKE '%"+content+"%'";
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
		public ArrayList<NoticeBoVO> getList(int startRow, int endRow){
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			ArrayList<NoticeBoVO> list = new ArrayList<NoticeBoVO>();
			try {
				String sql ="SELECT * FROM (SELECT ROWNUM AS PNUM,PTITLE,PCONTENT,PDATE,MNAME,MID,CNT,CATEGORY FROM NOTICEBOARD ORDER BY PNUM DESC) WHERE PNUM BETWEEN "+startRow+" AND "+endRow;
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					NoticeBoVO p = new NoticeBoVO();
					p.setPnum(rs.getInt("pnum"));
					p.setPtitle(rs.getString("ptitle"));
					p.setPcontent(rs.getString("pcontent"));
					p.setPdate(rs.getDate("pdate"));
					p.setMname(rs.getString("mname"));
					p.setMid(rs.getString("mid"));
					p.setCnt(rs.getInt("cnt"));
					p.setCategory(rs.getString("category"));

					sql="SELECT * FROM NOTICECOMMENT WHERE PNUM=?";
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
				System.out.println("NoticePaging의List="+list);
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}
			return list;
		}
		// getList() Overloading
		public ArrayList<NoticeBoVO> getList(int startRow, int endRow, String condition, String content){
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			ArrayList<NoticeBoVO> list = new ArrayList<NoticeBoVO>();
			String sql;
			try {
				if(condition==null||content==null||condition==""||content=="") {
					sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM NOTICEBOARD ORDER BY PNUM DESC) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				}
				else {
					sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.PNUM,A.PTITLE,A.PCONTENT,A.PDATE,A.MNAME,A.MID,A.CNT,A.CATEGORY FROM (SELECT * FROM NOTICEBOARD WHERE "+condition+" LIKE '%"+content+"%'ORDER BY PNUM DESC) A) WHERE RNUM BETWEEN "+startRow+" AND "+endRow;
				}
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					NoticeBoVO p = new NoticeBoVO();
					p.setPnum(rs.getInt("pnum"));
					p.setPtitle(rs.getString("ptitle"));
					p.setPcontent(rs.getString("pcontent"));
					p.setPdate(rs.getDate("pdate"));
					p.setMname(rs.getString("mname"));
					p.setMid(rs.getString("mid"));
					p.setCnt(rs.getInt("cnt"));
					p.setCategory(rs.getString("category"));

					sql="SELECT * FROM NOTICECOMMENT WHERE PNUM=?";
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
				System.out.println("NoticePaging의List="+list);
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}
			return list;
		}
}
