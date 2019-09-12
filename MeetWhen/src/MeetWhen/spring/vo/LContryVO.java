package MeetWhen.spring.vo;

/*공항통계 + 위도,경도 정보 저장 변수*/
public class LContryVO {

	private String lc_con;		//나라or지역
	private int lc_cnt;			//방문자수
	private double lc_lat; 		//위도
	private double lc_lon; 		//경도
	
	public String getLc_con() {
		return lc_con;
	}
	public void setLc_con(String lc_con) {
		this.lc_con = lc_con;
	}
	public int getLc_cnt() {
		return lc_cnt;
	}
	public void setLc_cnt(int lc_cnt) {
		this.lc_cnt = lc_cnt;
	}
	public double getLc_lat() {
		return lc_lat;
	}
	public void setLc_lat(double lc_lat) {
		this.lc_lat = lc_lat;
	}
	public double getLc_lon() {
		return lc_lon;
	}
	public void setLc_lon(double lc_lon) {
		this.lc_lon = lc_lon;
	}
}
