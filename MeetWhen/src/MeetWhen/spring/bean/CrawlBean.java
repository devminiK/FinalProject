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

import MeetWhen.spring.vo.ContryVO;
import MeetWhen.spring.vo.CrawlA1VO;
import MeetWhen.spring.vo.CrawlA2VO;
import MeetWhen.spring.vo.RegionVO;

@Controller
@RequestMapping("/Crawl/")
public class CrawlBean {
	@Autowired
	private SqlSessionTemplate sql = null;

	@RequestMapping("doCrawla1.mw")  
	public String doCrawla1(HttpServletRequest request) throws Exception{
		//Map1 페이지 실행되면 특정 시간 마다 재실행됨.
		//Map2-8페이지 용도 만들어야함.> Region내용 사용

		sql.delete("crawl.deleCrawlA1");  //리셋
		System.out.println("[doCrawl_CrawlA1 TABLE 리셋]");

		List<ContryVO> conList = new ArrayList<ContryVO>();
		ContryVO vo = new ContryVO();
		CrawlA1VO cwa1Vo = null;
		conList = sql.selectList("airport.getContry");

		RConnection conn = new RConnection();
		REXP contry=null, capital=null, rate=null;
		String con="", cap="", rat="",imgSrc="";

		conn.eval("setwd('C:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");

		for(int i=0;i<conList.size();i++) { //conList.size()
			vo=conList.get(i);

			int currentNum=vo.getC_num();
			String currentCont = vo.getC_con();
			int caseType=0;
			//이름에 맞는 지역 정보 크롤링.vo.getC_con()
			int ContNum = sql.selectOne("airport.getContryNum",currentCont); //이미지 이름 (번호)부여
			String cNum=Integer.toString(ContNum);//이미지 이름, 단위000 맞춰주기 위함.
			if(ContNum/100 == 0) {	
				if(ContNum%100 < 10) {	//ContNum이 1-9 경우
					cNum="00"+cNum;
				}else {					//ContNum이 10-99경우
					cNum="0"+cNum;
				}
			}
			//검색 셋팅
			conn.eval("remDr$navigate('https://www.naver.com/')");
			conn.eval("Sys.sleep(1)");
			conn.eval("WebEle <- remDr$findElement(using='css',\"[id='query']\")");
			conn.eval("WebEle$sendKeysToElement(list('"+currentCont+"',key=\"enter\"))");

			//경우1) 다른 검색결과1 :괌,하와이,홍콩,마카오
			//경우2) 다른 검색결과2 : 사이판 
			//경우3) 일반결과 : 그외 모두
			//경우3-2) 일반결과, 환율정보X : 피지,몰디브,에티오피아

			if(currentCont.equals("괌") |currentCont.equals("하와이")|currentCont.equals("홍콩") |currentCont.equals("마카오")|currentCont.equals("싱가포르")) { 
				//정식 국가명
				caseType=1;
				conn.eval("contry<-remDr$findElements(using='css',\"div.overseas_thumb > div > div > strong.title_text\")");
				conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
				conn.eval("contry<-contry[[1]]");
				contry = conn.eval("contry");
				con=contry.asString();
				//수도대신, 위치
				conn.eval("spot<-remDr$findElements(using='css',\"div.city_info > dl > dd:nth-child(2)\")");
				conn.eval("spot<-sapply(spot,function(x){x$getElementText()})");
				conn.eval("spot<- gsub(' 위치보기','',spot[[1]])");
				capital = conn.eval("spot");
				cap=capital.asString();
				//환율 
				conn.eval("trvBtn <- remDr$findElements(using='css', 'li._second_tab > a')");
				conn.eval("sapply(trvBtn, function(x){x$clickElement()})");
				conn.eval("rate<-remDr$findElements(using='css',\"div.rate_area > ul > li:nth-child(1) > span.info_text\")");
				conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
				conn.eval("rate<- gsub(' 환율정보','',rate[[1]])");
				rate = conn.eval("rate");
				rat=rate.asString();
				//국기는 파일에있는것으로 지정.
				imgSrc="/MeetWhen/img/flag/"+cNum+".png";
			}else if(currentCont.equals("사이판")) {//예외중 예외
				caseType=2;
				//국가명
				con=currentCont;
				//위치
				conn.eval("remDr$navigate('https://terms.naver.com/entry.nhn?docId=1107822&cid=40942&categoryId=33295')");
				conn.eval("spot<-remDr$findElements(using='css',\"div.wr_tmp_profile > div > table > tbody > tr:nth-child(1) > td\")");
				conn.eval("spot<-sapply(spot,function(x){x$getElementText()})");
				capital = conn.eval("spot[[1]]");
				cap = capital.asString();
				//환율대신 통화
				conn.eval("cash<-remDr$findElements(using='css',\"div.wr_tmp_profile > div > table > tbody > tr:nth-child(10) > td\")");
				conn.eval("cash<-sapply(cash,function(x){x$getElementText()})");
				rate = conn.eval("cash[[1]]");
				rat=rate.asString();
				//국기는 파일에있는것으로 지정.
				imgSrc="/MeetWhen/img/flag/"+cNum+".png";
			}else {	
				caseType=3;
				//정식 국가명
				conn.eval("contry<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dt\")");
				conn.eval("contry<-sapply(contry,function(x){x$getElementText()})");
				conn.eval("contry<-gsub('\\n',' _ ',contry[[1]])");
				contry = conn.eval("contry");
				con = contry.asString();			
				//국가 수도
				conn.eval("capital<-remDr$findElements(using='css',\"#main_pack > div.content_search.section > div > div.contents03_sub > div > div.nacon_area._info_area > div.naflag_box > dl > dd:nth-child(2) > a\")");
				conn.eval("capital<-sapply(capital,function(x){x$getElementText()})");
				capital = conn.eval("capital[[1]]");
				cap = capital.asString();
				//국기 
				conn.eval("html<-remDr$getPageSource()[[1]]");
				conn.eval("html<-read_html(html)");
				conn.eval("flag<-html_node(html,\"[alt='flag']\")");
				conn.eval("flag<-html_attr(flag,\"src\")");
				conn.eval("imgRes<-GET(flag)");

				//로컬 폴더에(확인용)저장
				conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('C:/save/%03d.png'),"+ContNum+"))");
				//project 폴더 내 저장
				String orgPath = request.getRealPath("img"); //flag폴더 경로 못찾기때문에 img를 찾아 덧붙임
				String newPath = orgPath.replace("\\","/")+"/flag";

				conn.eval("writeBin(content(imgRes,'raw'),sprintf(paste0('"+newPath+"/%03d.png'),"+ContNum+"))");
				imgSrc="/MeetWhen/img/flag/"+cNum+".png";

				//국가 환율, 존재하지않을경우 예외처리
				conn.eval("rate<-remDr$findElements(using='css',\"#dss_nation_tab_summary_content > dl.lst_overv > dd:not(.frst):not(._world_clock) \")");
				conn.eval("rate<-sapply(rate,function(x){x$getElementText()})");
				try {
					conn.eval("rate<-gsub('\\n','',rate[[1]])"); //에러발생
					rate = conn.eval("rate");
					rat = rate.asString();		
				}catch(RserveException ex) {
					ex.printStackTrace();
					System.out.println("환율 정보 x");
					rat="정보가 없습니다";
				}
			}

			//디비에 삽입.확인용 출력
			System.out.println(currentNum+" "+currentCont+" "+con+" "+cap+" "
					+rat+" "+imgSrc+" "+caseType);

			cwa1Vo = new CrawlA1VO();
			cwa1Vo.setCwa1_num(currentNum);
			cwa1Vo.setCwa1_cont(currentCont);
			cwa1Vo.setCwa1_con(con);
			cwa1Vo.setCwa1_cap(cap);
			cwa1Vo.setCwa1_rat(rat);
			cwa1Vo.setCwa1_img(imgSrc);
			cwa1Vo.setCwa1_type(caseType);
			sql.insert("crawl.insertCrawlA1",cwa1Vo);
		}
		conn.eval("remDr$close()");
		conn.close();
		return "/Crawl/doCrawla1";
	}
	@RequestMapping("showCrawla1.mw")  //> 네이버 검색결과
	public String showCrawla1(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println(clickCont);

		CrawlA1VO vo = sql.selectOne("crawl.getCrawlA1Click",clickCont);
		System.out.println(vo);
		System.out.println(vo.getCwa1_cont()+vo.getCwa1_con()+vo.getCwa1_cap());

		request.setAttribute("vo", vo);
		return "/Crawl/showCrawla1";
	}	
	
	@RequestMapping("doCrawla2.mw")  //> 구글 검색결과
	public String doCrawla2(HttpServletRequest request) throws Exception{
		//Map2-8페이지 실행되면 특정 시간 마다 재실행됨 > Region내용 사용
		//리셋하고, 새로운 내용 받아오기.
		sql.delete("crawl.deleCrawlA2");  //리셋
		System.out.println("[doCrawl_CrawlA2 TABLE 리셋]");

		List<RegionVO> conList = new ArrayList<RegionVO>();
		RegionVO vo = new RegionVO();
		CrawlA2VO cwa2Vo = null;
		conList = sql.selectList("airport.getRegion");
		
		RConnection conn = new RConnection();
		REXP explain1=null, explain2=null;
		String ex1="",ex2="";

		conn.eval("setwd('D:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");
		
		for(int i=0;i<conList.size();i++) { //conList.size()
			vo=conList.get(i);

			int currentNum=vo.getR_num();
			String currentCont = vo.getR_reg();
			System.out.println(currentNum+currentCont);
			
			conn.eval("remDr$navigate('https://www.google.com/')");
			conn.eval("Sys.sleep(1)");
			conn.eval("WebElem <- remDr$findElement(using='css', \"[name='q']\")");
			try {
				conn.eval("WebElem$sendKeysToElement(list('"+currentCont+"',key=\"enter\"))");
			}catch(RserveException ex) {
				System.out.println(currentCont+"정보없음");
			}
			//검색 셋팅
			if(currentCont.equals("씨엠립")|currentCont.equals("클락국제공항")) {//예외
				System.out.println(currentCont+"->예외 정보");
				
				//이름
				conn.eval("c<-remDr$findElements(using='css',\"div.kno-ecr-pt.kno-fb-ctx.PZPZlf.gsmt > span\")");
				conn.eval("c<-sapply(c,function(x){x$getElementText()})");
				explain1 = conn.eval("c[[1]]");
				ex1 = explain1.asString();
				//설명
				conn.eval("d<-remDr$findElements(using='css',\"div.SALvLe.farUxc.mJ2Mod > div > div:nth-child(1) > div > div > div > div > span:nth-child(2)\")");
				conn.eval("d<-sapply(d,function(x){x$getElementText()})");
				explain2 = conn.eval("d[[1]]");
				ex2 = explain2.asString();
			}
			else {
				//위치설명
				conn.eval("a<-remDr$findElements(using='css',\"div.wwUB2c.kno-fb-ctx.PZPZlf.E75vKf > span\")");
				conn.eval("a<-sapply(a,function(x){x$getElementText()})");
				explain1 = conn.eval("a[[1]]");
				ex1 = explain1.asString();
				
				//설명
				conn.eval("b<-remDr$findElements(using='css',\"div.ifM9O > div:nth-child(2) > div.SALvLe.farUxc.mJ2Mod > div > div:nth-child(1) > div > div > div > div > span:nth-child(2)\")");
				conn.eval("b<-sapply(b,function(x){x$getElementText()})");
				conn.eval("b<-gsub('\\\"','',b[[1]])");
				explain2 = conn.eval("b");
				ex2 = explain2.asString();
			}
			//System.out.println(ex1+"\n:"+ex2);
			cwa2Vo = new CrawlA2VO();
			cwa2Vo.setCwa2_num(currentNum);
			cwa2Vo.setCwa2_cont(currentCont);
			cwa2Vo.setCwa2_ex1(ex1);
			cwa2Vo.setCwa2_ex2(ex2);
			sql.insert("crawl.insertCrawlA2",cwa2Vo);
		}
		conn.eval("remDr$close()");
		conn.close();		
		return "/Crawl/doCrawla2";
	}	
	@RequestMapping("showCrawla2.mw")  //> 네이버 검색결과
	public String showCrawla2(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println(clickCont);

		CrawlA2VO vo = sql.selectOne("crawl.getCrawlA2Click",clickCont);
		System.out.println(vo.getCwa2_cont()+vo.getCwa2_ex1()+vo.getCwa2_ex2());

		request.setAttribute("vo", vo);
		request.setAttribute("cont", clickCont);
		return "/Crawl/showCrawla2";
	}

}
