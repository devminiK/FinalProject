package MeetWhen.spring.vo;

/*전체 공항통계 정보 저장 변수*/
public class ContryVO {
	
	private int c_num; 		//시퀀스 증가값
	private String c_con; 	//나라 정보
	private int c_cnt;   	//방문자 수
	
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	
	public String getC_con() {
		return c_con;
	}
	public void setC_con(String c_con) {
		this.c_con = c_con;
	}
	
	public int getC_cnt() {
		return c_cnt;
	}
	public void setC_cnt(int c_cnt) {
		this.c_cnt = c_cnt;
	}
	
	
}
