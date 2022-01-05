package model.member;

public class MemVO {
	private String mid; // MID VARCHAR(50) PRIMARY KEY, -- 회원 아이디
	private String mpw; // MPW VARCHAR(50) NOT NULL, -- 회원 비밀번호
	private String mname; // MNAME VARCHAR(50) NOT NULL, -- 회원 닉네임
	private String memail; // MEMAIL VARCHAR(50) NOT NULL, -- 회원 이메일
	private String role; // ROLE VARCHAR(20) NOT NULL -- 사용자/관리자

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MemVO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", memail=" + memail + ", role=" + role
				+ "]";
	}

}
