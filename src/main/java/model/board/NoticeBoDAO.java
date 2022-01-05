package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;

public class NoticeBoDAO {

	// C R U D

	// R - Select All ( �Խñ� ��� ���� )
			public ArrayList<NoticeBoVO> getNoticePostList(String num) {
				Connection conn = JDBC.connect();
				PreparedStatement pstmt = null;
				ArrayList<NoticeBoVO> spList = new ArrayList<NoticeBoVO>();
				String sql;
				try {
					if (num == "1") {
						sql = "SELECT * FROM NOTICEBOARD ORDER BY PNUM DESC";
						pstmt = conn.prepareStatement(sql);
					} else if (num == "2") {
						sql = "SELECT * FROM NOTICEBOARD ORDER BY CNT DESC";
						pstmt = conn.prepareStatement(sql);
					}
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						// PNUM / PTITLE / PCONTENT / PDATE / MNAME / MID / CNT / CATEGORY
						NoticeBoVO post = new NoticeBoVO();
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
					System.out.println("[Exception�߻�] getNoticePostList() Ȯ��!");
					e.printStackTrace();
				} finally {
					JDBC.disconnect(conn, pstmt);
				}
				return spList;
			}
		

		// R - Select One ( �Խñ� �� ���� ) -- ������
		public NoticeBoVO getNoticePost(NoticeBoVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			NoticeBoVO post = null;
			try {
				//System.out.println("getNoticePost() ����");
				String sql ="SELECT * FROM NOTICEBOARD WHERE PNUM = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getPnum());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
		//PNUM / PTITLE / PCONTENT / PDATE / MNAME / MID / CNT / CATEGORY	
					post = new NoticeBoVO();	
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
				System.out.println("[Exception�߻�] getFreePost() Ȯ��!");
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}		
			// System.out.println("getFreePost() : "+post);
			return post;
		}
		
		// U - CNT+1 ������Ʈ 
		public boolean updateNoticeCnt(NoticeBoVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			boolean res =false;
			try {
				String sql = "UPDATE NOTICEBOARD SET CNT=CNT+1 WHERE PNUM=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getPnum());
				pstmt.executeUpdate();
				res=true;
			}catch(Exception e) {
				System.out.println("[Exception�߻�] updateNoticeCnt() Ȯ��!");
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}		
			return res;
		}
		
		// C - Insert (�Խñ� �ۼ�)
		public boolean insertNoticePost(NoticeBoVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			boolean res=false;
			try {
				String sql ="INSERT INTO NOTICEBOARD (PNUM,PTITLE,PCONTENT,MNAME,MID) VALUES ((SELECT NVL(MAX(PNUM),0)+1 FROM NOTICEBOARD),?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,vo.getPtitle());
				pstmt.setString(2,vo.getPcontent());
				pstmt.setString(3, vo.getMname());
				pstmt.setString(4, vo.getMid());
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
		// U - Update ( �Խñ� ���� )
		public boolean editNoticePost(NoticeBoVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			boolean res= false;
			try {
				String sql ="UPDATE NOTICEBOARD SET PTITLE=?,PCONTENT=? WHERE PNUM=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,vo.getPtitle());
				pstmt.setString(2, vo.getPcontent());
				pstmt.setInt(3, vo.getPnum());
				pstmt.executeUpdate();
				res=true;
			}catch(Exception e) {
				System.out.println("[Exception�߻�] editNoticePost() Ȯ��!");
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}		
			return res;
		}
		
		// D - Delete (�Խñ� ����)
		public boolean delNoticePost(NoticeBoVO vo) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			boolean res =false;
			try {
				String sql ="DELETE FROM NOTICEBOARD WHERE PNUM =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getPnum());
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
		// �˻���� ���� - Select All 
		public ArrayList<NoticeBoVO> searchNotice(String condition, String content) {
			Connection conn = JDBC.connect();
			PreparedStatement pstmt = null;
			ArrayList<NoticeBoVO> npList= new ArrayList<>();
			String sql;
			try {
				sql = "SELECT * FROM NOTICEBOARD WHERE "+condition+" LIKE '%"+content+"%' ORDER BY PNUM DESC";
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					NoticeBoVO post = new NoticeBoVO();
					post.setPnum(rs.getInt("pnum"));
					post.setPtitle(rs.getString("ptitle"));
					post.setPcontent(rs.getString("pcontent"));
					post.setPdate(rs.getDate("pdate"));
					post.setMname(rs.getString("mname"));
					post.setMid(rs.getString("mid"));
					post.setCnt(rs.getInt("cnt"));
					post.setCategory(rs.getString("category"));
					npList.add(post);
				}rs.close();
			}catch(Exception e) {
				System.out.println("searchTitle() �̼��� �߻�!");
				e.printStackTrace();
			}finally {
				JDBC.disconnect(conn, pstmt);
			}	
			return npList;
		}

}
