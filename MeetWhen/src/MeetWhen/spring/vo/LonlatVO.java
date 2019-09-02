package MeetWhen.spring.vo;

/*공항통계 +위도/경도 정보 저장 변수*/
public class LonlatVO {
	private int l_num;			//고유번호
	
	private String l_conreg;	//나라+지역
	private double l_lon; 		//경도
	private double l_lat; 		//위도
	private int l_cnt;			//방문자수
	
	public int getL_num() {
		return l_num;
	}
	public void setL_num(int l_num) {
		this.l_num = l_num;
	}
	public String getL_conreg() {
		return l_conreg;
	}
	public void setL_conreg(String l_conreg) {
		this.l_conreg = l_conreg;
	}
	public double getL_lon() {
		return l_lon;
	}
	public void setL_lon(double l_lon) {
		this.l_lon = l_lon;
	}
	public double getL_lat() {
		return l_lat;
	}
	public void setL_lat(double l_lat) {
		this.l_lat = l_lat;
	}
	public int getL_cnt() {
		return l_cnt;
	}
	public void setL_cnt(int l_cnt) {
		this.l_cnt = l_cnt;
	}
	
}
