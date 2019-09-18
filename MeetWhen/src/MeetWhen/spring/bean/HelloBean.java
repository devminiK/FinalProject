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
	
	@RequestMapping("boots_original.mw")  //BootStrap 원본
	public String boots_original() {
		return "/Main/boots_original";
	}
	@RequestMapping("boots_form.mw")  //공동 form
	public String boots_form() {
		return "/Main/boots_form";
	}
	
	@RequestMapping("boots_menubar.mw")  //공동 header
	public String boots_menubar() {
		return "/Main/boots_menubar";
	}
	
	@RequestMapping("boots_footer.mw")  //공동 footer
	public String boots_footer() {
		return "/Main/boots_footer";
	}
	//------------------------------------------------------------------
	@RequestMapping("welcome.mw")  //첫 메인 화면
	public String welcome() {
		System.out.println("Welcome Runing..!");
		return "/Main/welcome";
	}
	/*--------------------------------테스트 용------*/
	
	//lonlatinfo DB정보 출력
	@RequestMapping("test_data2.mw") //DB가져와 리스트 작성.
	public String test_data2(HttpServletRequest request) {
		List<LContryVO> infoList = new ArrayList<LContryVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB정보 갯수="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);
		return "/Main/test_data2";
	}
	
	
	
	@RequestMapping("test.mw") //크롤링> 명소 추천 내용
	public String test() {
		return "/Main/test";
	}
	
	@RequestMapping("test2.mw")  // 나라별 크롤링 > 세계 뉴스정보
	public String test2(HttpServletRequest request, String cont) throws Exception{
		
		/*
		RConnection conn = new RConnection();
		
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");
		
		List<ContryVO> dataList = new ArrayList<ContryVO>();
		dataList = sql.selectList("airport.getContry");
		int size = dataList.size();
		
		String[] cons = new String[size];
		String[][] info = new String [size][5];
		for(int i=0;i<size;i++) {
			cons[i]=dataList.get(i).getC_con();
			System.out.print(cons[i]+" 정보 추출");
			
			if(cons[i].equals("괌")||cons[i].equals("사이판")||cons[i].equals("사이판")||cons[i].equals("마카오")) {
				System.out.print("x");
				continue;
			}
			System.out.println();
			conn.eval("remDr$navigate('https://www.naver.com/')");
			conn.eval("WebEle <- remDr$findElement(using='css',\"[id='query']\")");
			conn.eval("WebEle$sendKeysToElement(list('"+cons[i]+"',key=\"enter\"))"); //반복문 value.
			info[i][0] = cons[i];
			info[i][1] = String.valueOf(i);
			//conn.eval("");
			//국기 img저장
			conn.eval("html<-remDr$getPageSource()[[1]]");
			conn.eval("html<-read_html(html)");
			conn.eval("flag<-html_node(html,\"[alt='flag']\")");
			conn.eval("flag<-html_attr(flag,\"src\")");
			conn.eval("imgRes<-GET(flag)");
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('d:/save/%03d.png'),"+i+"))");
			
			conn.eval("contry<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dt\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			REXP con = conn.eval("contry");
			info[i][2]=con.toString();
			
			conn.eval("capital<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dd:nth-child(2) > a\")");
			conn.eval("capital<-sapply(capital,function(x){x$getElementText()})");
			REXP cap = conn.eval("capital");
			info[i][3]=cap.toString();
			
			conn.eval("rate<-remDr$findElements(using='css',\"#dss_nation_tab_summary_content > dl.lst_overv > dd:not(frst)\")");
			conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
			REXP rate = conn.eval("rate[3]");
			info[i][4]=rate.toString();
		
		}
		for(int a=0;a<size;a++) {
			for(int b=0;b<5;b++) {
				System.out.print(info[a][b]+" ");
			}
			System.out.println();
		}
		*/
		request.setAttribute("cont", cont);
		return "/Main/test2";
	}
	
	
	


}