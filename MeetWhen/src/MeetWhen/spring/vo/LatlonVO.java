package MeetWhen.spring.vo;

/*������� + ����,�浵 ���� ���� ����*/
public class LatlonVO {

	private String place;		//����or����
	private int cnt;			//�湮�ڼ�
	private double lat; 		//����
	private double lon; 		//�浵
	
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
