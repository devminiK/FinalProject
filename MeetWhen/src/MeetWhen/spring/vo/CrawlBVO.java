package MeetWhen.spring.vo;

public class CrawlBVO {
	private int cwb_num;		//기사고유번호
	private String  cwb_img;	//기사 이미지 주소
	private String cwb_title;	//기사 제목
	private String cwb_url; 	//기사 링크
	
	public int getCwb_num() {
		return cwb_num;
	}
	public void setCwb_num(int cwb_num) {
		this.cwb_num = cwb_num;
	}
	public String getCwb_img() {
		return cwb_img;
	}
	public void setCwb_img(String cwb_img) {
		this.cwb_img = cwb_img;
	}
	public String getCwb_title() {
		return cwb_title;
	}
	public void setCwb_title(String cwb_title) {
		this.cwb_title = cwb_title;
	}
	public String getCwb_url() {
		return cwb_url;
	}
	public void setCwb_url(String cwb_url) {
		this.cwb_url = cwb_url;
	}
	
	
}
