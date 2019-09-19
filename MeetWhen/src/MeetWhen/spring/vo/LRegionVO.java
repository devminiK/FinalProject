package MeetWhen.spring.vo;

/*공항통계 + 위도,경도 정보 저장 변수*/
public class LRegionVO {
	private int lr_num ;		//시퀀스 증가값
	private String lr_reg;		//나라or지역
	private int lr_cnt;			//방문자수
	private double lr_lat; 		//위도
	private double lr_lon; 		//경도
	
	
	public int getLr_num() {
		return lr_num;
	}
	public void setLr_num(int lr_num) {
		this.lr_num = lr_num;
	}
	public String getLr_reg() {
		return lr_reg;
	}
	public void setLr_reg(String lr_reg) {
		this.lr_reg = lr_reg;
	}
	public int getLr_cnt() {
		return lr_cnt;
	}
	public void setLr_cnt(int lr_cnt) {
		this.lr_cnt = lr_cnt;
	}
	public double getLr_lat() {
		return lr_lat;
	}
	public void setLr_lat(double lr_lat) {
		this.lr_lat = lr_lat;
	}
	public double getLr_lon() {
		return lr_lon;
	}
	public void setLr_lon(double lr_lon) {
		this.lr_lon = lr_lon;
	}
	
	
}
