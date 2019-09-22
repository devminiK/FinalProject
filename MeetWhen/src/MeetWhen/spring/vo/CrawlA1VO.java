package MeetWhen.spring.vo;

public class CrawlA1VO {
	private int cwa1_num;		//고유번호
	private String cwa1_cont;	//국가명
	private String cwa1_con;		//정식 국가명
	private String cwa1_cap; 	//수도or위치
	private String cwa1_rat; 	//환율or없음
	private String cwa1_img; 	//이미지 주소
	private int cwa1_type;		//검색결과가 다르기때문에, 타입부여
	
	public int getCwa1_num() {
		return cwa1_num;
	}
	public void setCwa1_num(int cwa1_num) {
		this.cwa1_num = cwa1_num;
	}
	public String getCwa1_cont() {
		return cwa1_cont;
	}
	public void setCwa1_cont(String cwa1_cont) {
		this.cwa1_cont = cwa1_cont;
	}
	public String getCwa1_con() {
		return cwa1_con;
	}
	public void setCwa1_con(String cwa1_con) {
		this.cwa1_con = cwa1_con;
	}
	public String getCwa1_cap() {
		return cwa1_cap;
	}
	public void setCwa1_cap(String cwa1_cap) {
		this.cwa1_cap = cwa1_cap;
	}
	public String getCwa1_rat() {
		return cwa1_rat;
	}
	public void setCwa1_rat(String cwa1_rat) {
		this.cwa1_rat = cwa1_rat;
	}
	public String getCwa1_img() {
		return cwa1_img;
	}
	public void setCwa1_img(String cwa1_img) {
		this.cwa1_img = cwa1_img;
	}
	public int getCwa1_type() {
		return cwa1_type;
	}
	public void setCwa1_type(int cwa1_type) {
		this.cwa1_type = cwa1_type;
	}
	
	
	
}
