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
	public String test2(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println("[helloBean]확인용="+clickCont);
		
		RConnection conn = new RConnection();
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");

		//검색 셋팅
		conn.eval("remDr$navigate('https://www.naver.com/')");
		conn.eval("WebEle <- remDr$findElement(using='css',\"[id='query']\")");
		conn.eval("WebEle$sendKeysToElement(list('"+clickCont+"',key=\"enter\"))"); //value값 바뀜

		//하는 중임...
		//다른 검색결과  > 괌/홍콩/ 마카오
		//사이판
		if(clickCont.equals("괌") |clickCont.equals("홍콩") |clickCont.equals("마카오")) { //예외
			//정식 국가명
			conn.eval("contry<-remDr$findElements(using='css',\"div.overseas_thumb > div > div > strong.title_text\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			conn.eval("contry<-contry[[1]]");
			conn.eval("contry");
			
			//수도대신, 위치
			conn.eval("spot<-remDr$findElements(using='css',\"div.city_info > dl > dd:nth-child(2)\")");
			conn.eval("spot<-sapply(spot,function(x){x$getElementText()})");
			conn.eval("spot<- gsub(' 위치보기','',spot[[1]])");
			conn.eval("spot");
			
			//환율 
			conn.eval("rate<-remDr$findElements(using='css',\"div.rate_area > ul > li:nth-child(1) > span.info_text\")");
			conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
			conn.eval("rate<- gsub(' 환율정보','',rate[[2]])");
			conn.eval("rate");
			
			
			
			
			
			
		}else if(clickCont.equals("사이판")) {//예외중 예외
			
		}else {	//예외가 아닐 경우 모두.
			//정식 국가명
			conn.eval("contry<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dt\")");
			conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
			conn.eval("contry<-gsub('\\n',' _ ',contry[[1]])");
			REXP contry = conn.eval("contry");
			String con = contry.asString();
			System.out.println("검색결과="+con+"/클릭결과="+clickCont);	
			
			//국가 수도
			conn.eval("capital<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dd:nth-child(2) > a\")");
			conn.eval("capital<-sapply(capital,function(x){x$getElementText()})");
			REXP capital = conn.eval("capital[[1]]");
			//System.out.println(capital.asString());
			String cap = capital.asString();
			
			//국기 저장
			conn.eval("html<-remDr$getPageSource()[[1]]");
			conn.eval("html<-read_html(html)");
			conn.eval("flag<-html_node(html,\"[alt='flag']\")");
			conn.eval("flag<-html_attr(flag,\"src\")");
			conn.eval("imgRes<-GET(flag)");
			
			//로컬 폴더에 저장(확인용)
			int ContNum = sql.selectOne("airport.getContryNum",clickCont); //이미지 이름 (번호)부여
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('D:/save/%03d.png'),"+ContNum+"))");
			
			//project 폴더 내 저장
			String orgPath = request.getRealPath("img"); //flag폴더 경로 못찾음
			String newPath = orgPath.replace("\\","/")+"/flag";
			//System.out.println("저장경로 확인="+newPath);
			
			String cNum=Integer.toString(ContNum);
			if(ContNum/100 == 0) {	//1-9, 10-99경우
				if(ContNum%100 < 10) {
					cNum="00"+cNum;
				}else {
					cNum="0"+cNum;
				}
			}
			conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('"+newPath+"/%03d.png'),"+ContNum+"))");
			String imgSrc="/MeetWhen/img/flag/"+cNum+".png";
				
			//국가 환율-존재하지않을 수 있음.
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
	
	@RequestMapping("test3.mw")  //공동 footer
	public String test3() {
		return "/Main/test3";
	}
	
	


}