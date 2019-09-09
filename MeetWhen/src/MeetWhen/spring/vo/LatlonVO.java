package MeetWhen.spring.vo;

/*공항통계 + 위도,경도 정보 저장 변수*/
public class LatlonVO {

	private String place;		//나라or지역
	private int cnt;			//방문자수
	private double lat; 		//위도
	private double lon; 		//경도
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}	
}
