package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.ContryVO;
import MeetWhen.spring.vo.LContryVO;
import MeetWhen.spring.vo.LRegionVO;

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

		//cons[i].equals("��")||cons[i].equals("������")||cons[i].equals("��ī��"))
		//��, ������, ��ī�� ����
		
		//�˻� ����
		conn.eval("remDr$navigate('https://www.naver.com/')");
		conn.eval("WebEle <- remDr$findElement(using='css',\"[id='query']\")");
		conn.eval("WebEle$sendKeysToElement(list('"+clickCont+"',key=\"enter\"))"); //value�� �ٲ�
		
		//���� ������
		conn.eval("contry<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dt\")");
		conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
		conn.eval("contry<-gsub('\\n',' _ ',contry[[1]])");
		REXP contry = conn.eval("contry");
		String con = contry.asString();
		System.out.println("�˻����="+con+"/Ŭ�����="+clickCont);

		int ContNum = sql.selectOne("airport.getContryNum",clickCont);
		System.out.println(ContNum);
		
		//���� ����
		conn.eval("capital<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dd:nth-child(2) > a\")");
		conn.eval("capital<-sapply(capital,function(x){x$getElementText()})");
		REXP capital = conn.eval("capital[[1]]");
		System.out.println(capital.asString());
		String cap = capital.asString();
		

		//���� img����
		conn.eval("html<-remDr$getPageSource()[[1]]");
		conn.eval("html<-read_html(html)");
		conn.eval("flag<-html_node(html,\"[alt='flag']\")");
		conn.eval("flag<-html_attr(flag,\"src\")");
		conn.eval("imgRes<-GET(flag)");
		conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('D:/save/%03d.png'),"+ContNum+"))");
		
		//���� ȯ��-������������ �� ����.
		conn.eval("rate<-remDr$findElements(using='css',\"#dss_nation_tab_summary_content > dl.lst_overv > dd\")");
		conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
		conn.eval("rate<-gsub('\\n','',rate[[2]])");
		REXP rate = conn.eval("rate");
		System.out.println(rate.asString());

		//conn.eval("remDr$close()");
		
		//if(rate!=null) {
			System.out.println(rate.asString());
			String rat = rate.asString();
			request.setAttribute("rate", rat);
		//}//else {
			
			//System.out.println("rate�� null");
		//}
		
		request.setAttribute("contryName", clickCont);
		request.setAttribute("contry", con);
		request.setAttribute("capital", cap);
		
		return "/Main/test2";
	}
	
	@RequestMapping("test3.mw")  //���� footer
	public String test3() {
		return "/Main/test3";
	}
	
	


}