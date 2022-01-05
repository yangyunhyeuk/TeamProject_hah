package model.board;

import java.sql.Date;

public class FreeBoVO {
	
	// 멤버 변수 
		private int pnum; 			// PNUM INT PRIMARY KEY -- 게시글 고유 번호
		private String ptitle;		// PTITLE VARCHAR(50) NOT NULL -- 게시글 제목 
		private String pcontent;	// PCONTENT VARCHAR(1000) NOT NULL -- 게시글 내용
		private Date pdate;			// PDATE VARCHAR(50) NOT NULL -- 게시글 작성일자 
		private String mname;		// MNAME VARCHAR(50) NOT NULL -- 회원 닉네임
		private String mid;			// MID VARCHAR(50) NOT NULL	-- 회원 아이디
		private int cnt;			// CNT INT NOT NULL -- 조회수
		private String category;	// CATEGORY VARCHAR(50) NOT NULL -- 카테고리 
		private int replyCnt;		// 댓글수 조회를 위한 변수
		
		public int getReplyCnt() {
			return replyCnt;
		}
		public void setReplyCnt(int replyCnt) {
			this.replyCnt = replyCnt;
		}
		// Getter & Setter 
		public int getPnum() {
			return pnum;
		}
		public void setPnum(int pnum) {
			this.pnum = pnum;
		}
		public String getPtitle() {
			return ptitle;
		}
		public void setPtitle(String ptitle) {
			this.ptitle = ptitle;
		}
		public String getPcontent() {
			return pcontent;
		}
		public void setPcontent(String pcontent) {
			this.pcontent = pcontent;
		}
		public Date getPdate() {
			return pdate;
		}
		public void setPdate(Date pdate) {
			this.pdate = pdate;
		}
		public String getMname() {
			return mname;
		}
		public void setMname(String mname) {
			this.mname = mname;
		}
		public String getMid() {
			return mid;
		}
		public void setMid(String mid) {
			this.mid = mid;
		}
		public int getCnt() {
			return cnt;
		}
		public void setCnt(int cnt) {
			this.cnt = cnt;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
		// toString()
		@Override
		public String toString() {
			return "FreeBoVO [pnum=" + pnum + ", ptitle=" + ptitle + ", pcontent=" + pcontent + ", pdate=" + pdate
					+ ", mname=" + mname + ", mid=" + mid + ", cnt=" + cnt + ", category=" + category + "]";
		}
		
		
}
