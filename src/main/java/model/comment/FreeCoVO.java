package model.comment;

public class FreeCoVO {
	private int cnum; // CNUM INT PRIMARY KEY, -- 댓글 고유 번호
	private String comm; // COMMENT VARCHAR(200) NOT NULL, -- 댓글 내용
	private String cdate; // CDATE DATE NOT NULL, -- 댓글 작성 일자
	private int pnum; // PNUM INT NOT NULL, -- 게시글 번호
	private String mname; // MNAME VARCHAR(50) NOT NULL, -- 회원 닉네임
	private String mid; // MID VARCHAR(50) NOT NULL -- 회원 아이디

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
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

	@Override
	public String toString() {
		return "FreeCoVO [cnum=" + cnum + ", comm=" + comm + ", cdate=" + cdate + ", pnum=" + pnum + ", mname=" + mname
				+ ", mid=" + mid + "]";
	}

}
