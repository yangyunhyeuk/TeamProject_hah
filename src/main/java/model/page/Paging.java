package model.page;

public class Paging {
	
	// 멤버변수 
	private int pageSize; // (한 페이지에 보이는)게시글수 
	private int firstPageNo; // 첫번째 페이지번호 
	private int prevPageNo; // 이전페이지 번호 
	private int startPageNo; // 시작페이지 (페이징네비 기준)
	private int pageNo; // 페이지 번호
	private int endPageNo; //마지막 페이지 (페이징네비 기준)
	private int nextPageNo; // 다음 페이지 번호 
	private int finalPageNo; //마지막 페이지 번호
	private int totalCount; // 게시글 전체수 
	
	// Getter & Setter 
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getFinalPageNo() {
		return finalPageNo;
	}
	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makePaging();
	}
	
	// paging 메서드 
	public void makePaging() {
		if(this.totalCount ==0) {return;} // 게시글이 아예 없는 경우 
		if(this.pageNo == 0 ) {this.setPageNo(1);} // 기본값 설정
		if(this.pageSize == 0) {this.setPageSize(10);} 
		// 기본값설정 == 페이지에서 보여져야할  게시글이 0개이면  
		
		int finalPage = (totalCount+(pageSize-1))/pageSize; 
		System.out.println("finalPage = "+finalPage); 
		
		if(this.pageNo>finalPage) {this.setPageNo(finalPage);} // 기본값설정
		if(this.pageNo<0 || this.pageNo>finalPage) {this.pageNo=1;} // 현재페이지 유효성 체크  
	
		boolean isNowFirst = pageNo==1?true:false; //시작페이지 (전체)	
		boolean isNowFinal = pageNo==finalPage?true:false; //마지막페이지(전체)
		
		int startPage = ((pageNo-1)/10)*10+1; // 시작페이지 (페이징 네비 기준)
		// pageNo==1 이면 ((1-1)/10)*10+1 ==1 
		int endPage = startPage+10-1; // 끝 페이지(페이징 네비 기준) 
		// startPage 1이면 1+10-1 =10 endPage=10 
		
		if (endPage>finalPage) { 
			endPage=finalPage;
		}
		this.setFirstPageNo(1); // 첫번째 페이지 번호 
		if(isNowFirst) {
			this.setPrevPageNo(1); // 이전페이지 번호 
		}else {
			this.setPrevPageNo(((pageNo-1) < 1 ? 1:(pageNo-1))); // 이전페이지번호 
		}
		this.setStartPageNo(startPage); // 시작페이지(페이징네비기준)
		this.setEndPageNo(endPage); 	// 끝 페이지(페이징네비기준)
		
		if(isNowFinal) {
			this.setNextPageNo(finalPage);
		}else {
			this.setNextPageNo(((pageNo+1)>finalPage?finalPage : (pageNo+1))); //다음 페이지 번호 
		}
		this.setFinalPageNo(finalPage);
		
	}
	
	// toString
	@Override
	public String toString() {
		return "Paging [pageSize=" + pageSize + ", firstPageNo=" + firstPageNo + ", prevPageNo=" + prevPageNo
				+ ", startPageNo=" + startPageNo + ", pageNo=" + pageNo + ", endPageNo=" + endPageNo + ", nextPageNo="
				+ nextPageNo + ", finalPageNo=" + finalPageNo + ", totalCount=" + totalCount + "]";
	}
	
	
}
