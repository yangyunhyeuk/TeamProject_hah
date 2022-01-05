package model.mypage;

public class ConcermVO {
		
	// 멤버변수 
	private int favnum;		// FAVNUM INT PRIMARY KEY -- 관심 게시글 고유 번호  
	private String mid;		// MID VARCHAR(50) NOT NULL -- 회원 아이디
	private String mname;	// MNAME VARCHAR(50) NOT NULL, -- 작성자 이름
	private int pnum;		// PNUM INT NOT NULL -- 게시글 번호
	private String ptitle;  // PTITLE VARCHAR(50) NOT NULL -- 게시글 제목
	private String category;// CATEGORY VARCHAR(50) NOT NULL -- 카테고리
	
	// Getter & Setter
	public int getFavnum() {
		return favnum;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setFavnum(int favnum) {
		this.favnum = favnum;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "ConcermVO [favnum=" + favnum + ", mid=" + mid + ", mname=" + mname + ", pnum=" + pnum + ", ptitle="
				+ ptitle + ", category=" + category + "]";
	}

	
}
