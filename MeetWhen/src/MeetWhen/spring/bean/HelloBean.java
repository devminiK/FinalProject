package MeetWhen.spring.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.RFactor;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;

import MeetWhen.spring.vo.ContryVO;
import MeetWhen.spring.vo.CrawlA1VO;
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
	
	
	@RequestMapping("crawl2.mw") //ũ�Ѹ�2> ��� ��õ ����
	public String crawl2(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println(clickCont);
		
		RConnection conn = new RConnection();
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");
		
		conn.eval("remDr$navigate('https://www.google.com/travel/guide')");
		conn.eval("WebEle <- remDr$findElement(using='css','input.gb_kf')");
		conn.eval("WebEle$sendKeysToElement(list('"+clickCont+"',key='enter'))");
		
//		conn.eval("url<-remDr$getCurrentUrl()");
//		REXP URL = conn.eval("url[[1]]");
//		String url = URL.asString();
//		System.out.println(url);

//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		Document doc = dBuilder.parse(url);
//		doc.getDocumentElement().normalize();
//		System.out.println("Root Element:"+doc.getDocumentElement().getNodeName());	
		
		conn.eval("Sys.sleep(1)");
		conn.eval("source<-remDr$getPageSource()[[1]]");
		conn.eval("html<-read_html(source)");
		conn.eval("recom<-html_nodes(html,'div.gws-trips-modules__top-sight-card')");
		
		//�ϴ� ��
		REXP recom = conn.eval("recom");
		RList recomHTML = recom.asList();
		
		String[][] arr = new String[recomHTML.size()][];
		System.out.println("R����Ʈ ������="+recomHTML.size());
		
		//System.out.println("=====>>"+recomHTML.at(0).);
		
		/*
		 * for(int i=0;i<recomHTML.size();i++) { arr[i]=recomHTML.at(i).asStrings();
		 * System.out.println(recomHTML.at(i)); }
		 */
	
//		System.out.println("START");
//		//���test
//		for(int i=0;i<list.size();i++) {
//			for(int j=0;j<arr[i].length;j++) {
//				System.out.print(arr[i][j]+"/");
//			}
//			System.out.println();
//		}
//		System.out.println("END");
		
		//conn.close();
		request.setAttribute("cont", clickCont);
		return "/Main/crawl2";
	}
	
	
	@RequestMapping("ajaxTest.mw")//�׽�Ʈpage
	public String ajaxTest() {
		return "/Main/ajaxTest";
	}
	@RequestMapping("test4.mw")//�׽�Ʈpage
	public String test4() {
		return "/Main/test4";
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}