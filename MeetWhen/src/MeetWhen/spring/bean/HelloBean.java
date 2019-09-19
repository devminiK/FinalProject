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
	
	
	
	@RequestMapping("test.mw") //ũ�Ѹ�> ��� ��õ ����
	public String test() {
		return "/Main/test";
	}
	
	@RequestMapping("test2.mw")  // ���� ũ�Ѹ� > ���� ��������
	public String test2(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println("[helloBean]Ȯ�ο�="+clickCont);
		
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

		//�ϴ� ����...
		//�ٸ� �˻����  > ��/ȫ��/ ��ī��
		//������
		if(clickCont.equals("��") |clickCont.equals("ȫ��") |clickCont.equals("��ī��")) { //����
			//���� ������
			conn.eval("contry<-remDr$findElements(using='css',\"div.overseas_thumb > div > div > strong.title_text\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			conn.eval("contry<-contry[[1]]");
			conn.eval("contry");
			
			//�������, ��ġ
			conn.eval("spot<-remDr$findElements(using='css',\"div.city_info > dl > dd:nth-child(2)\")");
			conn.eval("spot<-sapply(spot,function(x){x$getElementText()})");
			conn.eval("spot<- gsub(' ��ġ����','',spot[[1]])");
			conn.eval("spot");
			
			//ȯ�� 
			conn.eval("rate<-remDr$findElements(using='css',\"div.rate_area > ul > li:nth-child(1) > span.info_text\")");
			conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
			conn.eval("rate<- gsub(' ȯ������','',rate[[2]])");
			conn.eval("rate");
			
			
			
			
			
			
		}else if(clickCont.equals("������")) {//������ ����
			
		}else {	//���ܰ� �ƴ� ��� ���.
			//���� ������
			conn.eval("contry<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dt\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			conn.eval("contry<-gsub('\\n',' _ ',contry[[1]])");
			REXP contry = conn.eval("contry");
			String con = contry.asString();
			System.out.println("�˻����="+con+"/Ŭ�����="+clickCont);	
			
			//���� ����
			conn.eval("capital<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dd:nth-child(2) > a\")");
			conn.eval("capital<-sapply(capital,function(x){x$getElementText()})");
			REXP capital = conn.eval("capital[[1]]");
			//System.out.println(capital.asString());
			String cap = capital.asString();
			
			//���� ����
			conn.eval("html<-remDr$getPageSource()[[1]]");
			conn.eval("html<-read_html(html)");
			conn.eval("flag<-html_node(html,\"[alt='flag']\")");
			conn.eval("flag<-html_attr(flag,\"src\")");
			conn.eval("imgRes<-GET(flag)");
			
			//���� ������ ����(Ȯ�ο�)
			int ContNum = sql.selectOne("airport.getContryNum",clickCont); //�̹��� �̸� (��ȣ)�ο�
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('D:/save/%03d.png'),"+ContNum+"))");
			
			//project ���� �� ����
			String orgPath = request.getRealPath("img"); //flag���� ��� ��ã��
			String newPath = orgPath.replace("\\","/")+"/flag";
			//System.out.println("������ Ȯ��="+newPath);
			
			String cNum=Integer.toString(ContNum);
			if(ContNum/100 == 0) {	//1-9, 10-99���
				if(ContNum%100 < 10) {
					cNum="00"+cNum;
				}else {
					cNum="0"+cNum;
				}
			}
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('"+newPath+"/%03d.png'),"+ContNum+"))");
			String imgSrc="/MeetWhen/img/flag/"+cNum+".png";
				
			//���� ȯ��-������������ �� ����.
			conn.eval("rate<-remDr$findElements(using='css',\"#dss_nation_tab_summary_content > dl.lst_overv > dd:not(.frst):not(._world_clock) \")");
			conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
			String rat="";
			try {
				conn.eval("rate<-gsub('\\n','',rate[[1]])");
				REXP rate = conn.eval("rate");
				System.out.println(rate.asString());
				rat = rate.asString();
				
			}catch(RserveException ex) {
				ex.printStackTrace();
			}
		}
		
		
		
		request.setAttribute("contryName", clickCont);
		//request.setAttribute("contry", con);
		//request.setAttribute("capital", cap);
		//request.setAttribute("imgSrc", imgSrc);
		//request.setAttribute("rate", rat);
		return "/Main/test2";
	}
	
	@RequestMapping("test3.mw")  //���� footer
	public String test3() {
		return "/Main/test3";
	}
	
	


}