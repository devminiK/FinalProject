package MeetWhen.spring.vo;

public class Crawl1VO {
	private int cw1_num;		//고유번호
	private String cw1_cont;	//국가명
	private String cw1_con;		//정식 국가명
	private String cw1_cap; 	//수도or위치
	private String cw1_rat; 	//환율or없음
	private String cw1_img; 	//이미지 주소
	private int cw1_type;		//검색결과가 다르기때문에, 타입부여
	
	public int getCw1_num() {
		return cw1_num;
	}
	public void setCw1_num(int cw1_num) {
		this.cw1_num = cw1_num;
	}
	public String getCw1_con() {
		return cw1_con;
	}
	public void setCw1_con(String cw1_con) {
		this.cw1_con = cw1_con;
	}
	public String getCw1_cont() {
		return cw1_cont;
	}
	public void setCw1_cont(String cw1_cont) {
		this.cw1_cont = cw1_cont;
	}
	public String getCw1_cap() {
		return cw1_cap;
	}
	public void setCw1_cap(String cw1_cap) {
		this.cw1_cap = cw1_cap;
	}
	public String getCw1_rat() {
		return cw1_rat;
	}
	public void setCw1_rat(String cw1_rat) {
		this.cw1_rat = cw1_rat;
	}
	public String getCw1_img() {
		return cw1_img;
	}
	public void setCw1_img(String cw1_img) {
		this.cw1_img = cw1_img;
	}
	public int getCw1_type() {
		return cw1_type;
	}
	public void setCw1_type(int cw1_type) {
		this.cw1_type = cw1_type;
	}
	
	
}
