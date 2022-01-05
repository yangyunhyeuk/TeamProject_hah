package model.mypage;

public class ConcermVO {
		
	// ������� 
	private int favnum;		// FAVNUM INT PRIMARY KEY -- ���� �Խñ� ���� ��ȣ  
	private String mid;		// MID VARCHAR(50) NOT NULL -- ȸ�� ���̵�
	private String mname;	// MNAME VARCHAR(50) NOT NULL, -- �ۼ��� �̸�
	private int pnum;		// PNUM INT NOT NULL -- �Խñ� ��ȣ
	private String ptitle;  // PTITLE VARCHAR(50) NOT NULL -- �Խñ� ����
	private String category;// CATEGORY VARCHAR(50) NOT NULL -- ī�װ�
	
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
