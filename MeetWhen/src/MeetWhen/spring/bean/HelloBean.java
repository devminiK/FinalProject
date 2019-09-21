package MeetWhen.spring.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
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
	
	
	
	@RequestMapping("test.mw") //크롤링2> 명소 추천 내용
	public String test() {
		return "/Main/test";
	}
	
	@RequestMapping("crawl1.mw")  //크롤링1 > 네이버 검색결과
	public String crawl1(HttpServletRequest request) throws Exception{
		String clickCont = request.getParameter("cont");
		System.out.println("[hB:crawl1]확인용="+clickCont);
		
		REXP contry=null, capital=null, rate=null;
		String con="", cap="", rat="",imgSrc="";
		int caseNum=0; //어떤경우의 수인지 변수	
		int ContNum = sql.selectOne("airport.getContryNum",clickCont); //이미지 이름 (번호)부여
		String cNum=Integer.toString(ContNum);//이미지 이름, 단위000 맞춰주기 위함.
		if(ContNum/100 == 0) {	
			if(ContNum%100 < 10) {	//ContNum이 1-9 경우
				cNum="00"+cNum;
			}else {					//ContNum이 10-99경우
				cNum="0"+cNum;
			}
		}
		
		RConnection conn = new RConnection();
		conn.eval("setwd('C:/R-workspace')");
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

		//경우1) 다른 검색결과1 :괌,하와이,홍콩,마카오
		//경우2) 다른 검색결과2 : 사이판 
		//경우3) 일반결과 : 그외 모두
		//경우3-2) 일반결과, 환율정보X : 피지,몰디브,에티오피아
		if(clickCont.equals("괌") |clickCont.equals("하와이")|clickCont.equals("홍콩") |clickCont.equals("마카오")) { 
			caseNum=1;
			//정식 국가명
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
		}else if(clickCont.equals("사이판")) {//예외중 예외
			caseNum=2;
			//국가명
			con=clickCont;
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
			caseNum=3;
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
	
	@RequestMapping("crawl3.mw")  //크롤링3
	public String crawl3(HttpServletRequest request) throws Exception{
		/*
		//기본 셋팅
		RConnection conn = new RConnection();
		conn.eval("setwd('C:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");
		
		//우선 map1용.
		String totalURL="https://www.yna.co.kr/international/all";
		conn.eval("remDr$navigate('"+totalURL+"')");
		conn.eval("html<-remDr$getPageSource()[[1]]");
		conn.eval("html<-read_html(html)"); //동적->정적 리로드
		
		//기사 제목
		conn.eval("titles<-html_nodes(html,'#content > div.contents > div.contents01 > div > div.headlines.headline-list > ul > li > div > strong > a')");
		conn.eval("titles<-html_text(titles)");
		conn.eval("titles<-gsub('\\\"',\"\",titles)");
		conn.eval("titles<-head(titles,15)");
		conn.eval("titles");
		conn.eval("articleDf<-titles");
		
		
		
		//기사 링크(이미지를 위함)
		//project 폴더 내 저장
		String orgPath = request.getRealPath("img"); //article폴더 경로 못찾기때문에 img를 찾아 덧붙임
		String newPath = orgPath.replace("\\","/")+"/article";
		System.out.println(orgPath);
		System.out.println(newPath);
		//이미지 저장전 기존에 존재하는 이미지를 삭제
		for(int i=1;i<=15;i++) {
			File f = new File(orgPath+"\\article\\"+i+".png");
			System.out.print("[파일경로]"+i+".png");
			if(f.exists()) {
				f.delete();
				System.out.println(" - 삭제");
			}else {
				System.out.println(" - 존재x");
			}
		}

		conn.eval("links<-html_nodes(html,'#content > div.contents > div.contents01 > div > div.headlines.headline-list > ul > li > div > strong > a')");
		conn.eval("links<-html_attr(links,\"href\")");
		conn.eval("links<-head(links,15)");
		conn.eval("links");
		
		conn.eval("inUrls<-NULL");
		conn.eval("for(i in 1:length(links)){" + 
				"  inUrl<-paste0('https:',links[i]);" + 
				"  inUrls<-c(inUrls,inUrl);" + 
				"  inHtml<-read_html(inUrl);" + 
				"  inner_nodes<-html_nodes(inHtml,\"#articleWrap > div.article > div > img\");" + 
				"  if(length(inner_nodes)>0){" + 
				"    href<-html_attr(inner_nodes[1],\"src\");" + 
				"    url<-paste0('http:',href);" + 
				"    res<-GET(url);" + 
				"    writeBin(content(res,'raw'),sprintf('C:/save/%d.png',i));" + //확인용
				"    writeBin(content(res,'raw'),sprintf('"+newPath+"/%d.png',i));" + //경로 변경할 자리 
				"  }" + 
				"}");
		//데이터프레임 작성
		conn.eval("articleDf<-rbind(articleDf,inUrls)");
		conn.eval("articleDf<-as.data.frame(articleDf)"); 
		REXP artDf = conn.eval("articleDf");
		RList list = artDf.asList(); //리스트로 출력하려면 dataframe형식이여야함.
		//배열에 정보 삽입
		String [][] arr = new String[list.size()][];
		for(int i=0;i<list.size();i++) {
			arr[i]=list.at(i).asStrings();
		}
		//해쉬맵 이용- jsp에서 출력하기 위함(vo가 존재하지않기때문에 변수이름 지정하기위해선map을 사용해야함)
		List<Map> allList = new ArrayList<Map>();
		allList.clear();
		HashMap<String,String> art =null;
		for(int i=0;i<list.size();i++) {
			art = new HashMap<String,String>();
			art.put("title", arr[i][0]);
			art.put("url", arr[i][1]);
			
			//폴더에 이미지가 존재하는가에따라 imgSrc 저장 유무 
			File fm = new File(orgPath+"\\article\\"+(i+1)+".png");
			if(fm.exists()) {
				String imgSrc="/MeetWhen/img/article/"+(i+1)+".png"; //존재하지않더라도 주소값 부여하고있음.(문제o)
				art.put("src", imgSrc);
				System.out.println((i+1)+".png - 저장o");
			}else {
				//art.put("src", imgSrc);
				System.out.println((i+1)+".png - 존재x");
			}
			allList.add(art);
		}
		
		conn.eval("remDr$close()");
		conn.close();
		*/
		int allList[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		
		request.setAttribute("allList",allList); //리스트
		//request.setAttribute("totalURL", totalURL);
		
		
		
		return "/Main/crawl3";		
	}
	
	@RequestMapping("ajaxTest.mw")//테스트page
	public String ajaxTest() {
		return "/Main/ajaxTest";
	}

	


}