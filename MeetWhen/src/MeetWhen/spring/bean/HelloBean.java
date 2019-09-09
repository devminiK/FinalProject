package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LatlonVO;

@Controller
@RequestMapping("/Main/")
public class HelloBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("welcome.mw")  //첫 메인 화면
	public String welcome() {
		System.out.println("Welcome Runing..!");
		return "/Main/welcome";
	}
	
	@RequestMapping("header.mw") //헤더
	public String header() {
		return "/Main/header";
	}
	
	@RequestMapping("footer.mw") //푸터
	public String footer() {
		return "/Main/footer";
	}
	/*--------------------------------테스트 용------*/
	
	//lonlatinfo DB정보 출력
	@RequestMapping("test_data2.mw") //DB가져와 리스트 작성.
	public String test_data2(HttpServletRequest request) {
		List<LatlonVO> infoList = new ArrayList<LatlonVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB정보 갯수="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);
		return "/Main/test_data2";
	}
	//모든 Map 페이지
	@RequestMapping("cate1_All.mw")
	public String cate1_All(HttpServletRequest request) {			//세계지도
		List<LatlonVO> dataList = new ArrayList<LatlonVO>();
		dataList = sql.selectList("lonlat.getAll"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		String [][] total = new String[listSize][4];
		/*
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getL_conreg();
				total[i][1] = Double.toString(dataList.get(i).getL_lon());
				total[i][2] = Double.toString(dataList.get(i).getL_lat());
				total[i][3] = Integer.toString(dataList.get(i).getL_cnt());
		}
		*/
		
		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate1_All";
	}
	
	@RequestMapping("cate2.mw")
	public String cate2(HttpServletRequest request) {			//세계지도
		List<LatlonVO> dataList = new ArrayList<LatlonVO>();
		dataList = sql.selectList("lonlat.getAll"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		String [][] total = new String[listSize][4];
		/*	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getL_conreg();
				total[i][1] = Double.toString(dataList.get(i).getL_lon());
				total[i][2] = Double.toString(dataList.get(i).getL_lat());
				total[i][3] = Integer.toString(dataList.get(i).getL_cnt());
		}
		*/
		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate2";
	}
	
	@RequestMapping("cate3.mw")
	public String cate3() {
		return "/Main/cate3";
	}
	@RequestMapping("cate4.mw")
	public String cate4() {
		return "/Main/cate4";
	}
	@RequestMapping("cate5.mw")
	public String cate5() {
		return "/Main/cate5";
	}
	@RequestMapping("cate6.mw")
	public String cate6() {
		return "/Main/cate6";
	}
	@RequestMapping("cate7.mw")
	public String cate7() {
		return "/Main/cate7";
	}
	@RequestMapping("cate8.mw")
	public String cate8() {
		return "/Main/cate8";
	}
	
	


}