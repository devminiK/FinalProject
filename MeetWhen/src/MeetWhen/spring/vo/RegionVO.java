package MeetWhen.spring.vo;

/*전체 공항통계 정보 저장 변수*/
public class RegionVO {
	private int r_num ;		//시퀀스 증가값
	private String r_reg; //지역 정보
	private int r_cnt;    //방문자 수
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getR_reg() {
		return r_reg;
	}
	public void setR_reg(String r_reg) {
		this.r_reg = r_reg;
	}
	public int getR_cnt() {
		return r_cnt;
	}
	public void setR_cnt(int r_cnt) {
		this.r_cnt = r_cnt;
	}
	


}
