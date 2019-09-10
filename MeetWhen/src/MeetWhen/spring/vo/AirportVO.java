package MeetWhen.spring.vo;

/*공항통계 정보 저장 변수*/
public class AirportVO {
	private String place; 	//나라or지역 정보
	private int cnt;   	 	//방문자 수
	
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
}
