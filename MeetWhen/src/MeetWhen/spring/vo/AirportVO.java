package MeetWhen.spring.vo;

/*공항통계 정보 저장 변수*/
public class AirportVO {
	private int a_num; 	  //시퀀스 증가 값
	private String a_con; //나라 정보
	private String a_reg; //지역 정보
	private int a_cnt;    //방문자 수

	
	public int getA_num() {
		return a_num;
	}
	public void setA_num(int a_num) {
		this.a_num = a_num;
	}
	public String getA_con() {
		return a_con;
	}
	public void setA_con(String a_con) {
		this.a_con = a_con;
	}
	public String getA_reg() {
		return a_reg;
	}
	public void setA_reg(String a_reg) {
		this.a_reg = a_reg;
	}
	public int getA_cnt() {
		return a_cnt;
	}
	public void setA_cnt(int a_cnt) {
		this.a_cnt = a_cnt;
	}
}
