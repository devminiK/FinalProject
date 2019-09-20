package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LContryVO;

@Controller
@RequestMapping("/Main/")
public class HelloBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("boots_original.mw")  //BootStrap ����
	public String boots_original() {
		return "/Main/boots_original";
	}
	@RequestMapping("boots_form.mw")  //���� form
	public String boots_form() {
		return "/Main/boots_form";
	}
	
	@RequestMapping("boots_menubar.mw")  //���� header
	public String boots_menubar() {
		return "/Main/boots_menubar";
	}
	
	@RequestMapping("boots_footer.mw")  //���� footer
	public String boots_footer() {
		return "/Main/boots_footer";
	}
	//------------------------------------------------------------------
	@RequestMapping("welcome.mw")  //ù ���� ȭ��
	public String welcome() {
		System.out.println("Welcome Runing..!");
		return "/Main/welcome";
	}
	/*--------------------------------�׽�Ʈ ��------*/
	
	//lonlatinfo DB���� ���
	@RequestMapping("test_data2.mw") //DB������ ����Ʈ �ۼ�.
	public String test_data2(HttpServletRequest request) {
		List<LContryVO> infoList = new ArrayList<LContryVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB���� ����="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);
		return "/Main/test_data2";
	}
	
	
	
	@RequestMapping("test.mw") //ũ�Ѹ�2> ��� ��õ ����
	public String test() {
		return "/Main/test";
	}
	
	@RequestMapping("crawl1.mw")  //ũ�Ѹ�1 > ���̹� �˻����
	public String crawl1(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println("[hB:crawl1]Ȯ�ο�="+clickCont);
		
		REXP contry=null, capital=null, rate=null;
		String con="", cap="", rat="",imgSrc="";
		int caseNum=0; //������ ������ ����	
		int ContNum = sql.selectOne("airport.getContryNum",clickCont); //�̹��� �̸� (��ȣ)�ο�
		String cNum=Integer.toString(ContNum);//�̹��� �̸�, ����000 �����ֱ� ����.
		if(ContNum/100 == 0) {	
			if(ContNum%100 < 10) {	//ContNum�� 1-9 ���
				cNum="00"+cNum;
			}else {					//ContNum�� 10-99���
				cNum="0"+cNum;
			}
		}
		
		RConnection conn = new RConnection();
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");

		//�˻� ����
		conn.eval("remDr$navigate('https://www.naver.com/')");
		conn.eval("WebEle <- remDr$findElement(using='css',\"[id='query']\")");
		conn.eval("WebEle$sendKeysToElement(list('"+clickCont+"',key=\"enter\"))"); //value�� �ٲ�

		//���1) �ٸ� �˻����1 :��,�Ͽ���,ȫ��,��ī��
		//���2) �ٸ� �˻����2 : ������ 
		//���3) �Ϲݰ�� : �׿� ���
		//���3-2) �Ϲݰ��, ȯ������X : ����,�����,��Ƽ���Ǿ�
		if(clickCont.equals("��") |clickCont.equals("�Ͽ���")|clickCont.equals("ȫ��") |clickCont.equals("��ī��")) { 
			caseNum=1;
			//���� ������
			conn.eval("contry<-remDr$findElements(using='css',\"div.overseas_thumb > div > div > strong.title_text\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			conn.eval("contry<-contry[[1]]");
			contry = conn.eval("contry");
			con=contry.asString();
			//�������, ��ġ
			conn.eval("spot<-remDr$findElements(using='css',\"div.city_info > dl > dd:nth-child(2)\")");
			conn.eval("spot<-sapply(spot,function(x){x$getElementText()})");
			conn.eval("spot<- gsub(' ��ġ����','',spot[[1]])");
			capital = conn.eval("spot");
			cap=capital.asString();
			//ȯ�� 
			conn.eval("trvBtn <- remDr$findElements(using='css', 'li._second_tab > a')");
			conn.eval("sapply(trvBtn, function(x){x$clickElement()})");
			conn.eval("rate<-remDr$findElements(using='css',\"div.rate_area > ul > li:nth-child(1) > span.info_text\")");
			conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
			conn.eval("rate<- gsub(' ȯ������','',rate[[1]])");
			rate = conn.eval("rate");
			rat=rate.asString();
			//����� ���Ͽ��ִ°����� ����.
			imgSrc="/MeetWhen/img/flag/"+cNum+".png";
		}else if(clickCont.equals("������")) {//������ ����
			caseNum=2;
			//������
			con=clickCont;
			//��ġ
			conn.eval("remDr$navigate('https://terms.naver.com/entry.nhn?docId=1107822&cid=40942&categoryId=33295')");
			conn.eval("spot<-remDr$findElements(using='css',\"div.wr_tmp_profile > div > table > tbody > tr:nth-child(1) > td\")");
			conn.eval("spot<-sapply(spot,function(x){x$getElementText()})");
			capital = conn.eval("spot[[1]]");
			cap = capital.asString();
			//ȯ����� ��ȭ
			conn.eval("cash<-remDr$findElements(using='css',\"div.wr_tmp_profile > div > table > tbody > tr:nth-child(10) > td\")");
			conn.eval("cash<-sapply(cash,function(x){x$getElementText()})");
			rate = conn.eval("cash[[1]]");
			rat=rate.asString();
			//����� ���Ͽ��ִ°����� ����.
			imgSrc="/MeetWhen/img/flag/"+cNum+".png";
		}else {	
			caseNum=3;
			//���� ������
			conn.eval("contry<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dt\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			conn.eval("contry<-gsub('\\n',' _ ',contry[[1]])");
			contry = conn.eval("contry");
			con = contry.asString();			
			//���� ����
			conn.eval("capital<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dd:nth-child(2) > a\")");
			conn.eval("capital<-sapply(capital,function(x){x$getElementText()})");
			capital = conn.eval("capital[[1]]");
			cap = capital.asString();
			//���� 
			conn.eval("html<-remDr$getPageSource()[[1]]");
			conn.eval("html<-read_html(html)");
			conn.eval("flag<-html_node(html,\"[alt='flag']\")");
			conn.eval("flag<-html_attr(flag,\"src\")");
			conn.eval("imgRes<-GET(flag)");
			
			//���� ������(Ȯ�ο�)����
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('D:/save/%03d.png'),"+ContNum+"))");
			//project ���� �� ����
			String orgPath = request.getRealPath("img"); //flag���� ��� ��ã�⶧���� img�� ã�� ������
			String newPath = orgPath.replace("\\","/")+"/flag";
			
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('"+newPath+"/%03d.png'),"+ContNum+"))");
			imgSrc="/MeetWhen/img/flag/"+cNum+".png";
				
			//���� ȯ��, ��������������� ����ó��
			conn.eval("rate<-remDr$findElements(using='css',\"#dss_nation_tab_summary_content > dl.lst_overv > dd:not(.frst):not(._world_clock) \")");
			conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
			try {
				conn.eval("rate<-gsub('\\n','',rate[[1]])"); //�����߻�
				rate = conn.eval("rate");
				rat = rate.asString();		
			}catch(RserveException ex) {
				ex.printStackTrace();
				System.out.println("ȯ�� ���� x");
				rat="������ �����ϴ�";
			}
		}
		conn.eval("remDr$close()");
		conn.close();
		
		request.setAttribute("contryName", clickCont);
		request.setAttribute("contry", con);
		request.setAttribute("capital", cap);
		request.setAttribute("imgSrc", imgSrc);
		request.setAttribute("rate", rat);
		request.setAttribute("caseNum", caseNum);
		return "/Main/crawl1";
	}
	
	@RequestMapping("crawl3.mw")  //ũ�Ѹ�3
	public String crawl3(HttpServletRequest request) {
		String clickCont = request.getParameter("cont");
		System.out.println("[hB:crawl3]Ȯ�ο�="+clickCont);
		
		
		
		request.setAttribute("clickCont", clickCont);
		return "/Main/crawl3";		
	}
	
	


}