package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LContryVO;
import MeetWhen.spring.vo.LRegionVO;

@Controller
@RequestMapping("/Main/")
public class HelloBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("main.mw")  //공동 메인화면
	public String main() {
		return "/Main/main";
	}
	
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
	
	//모든 Map 페이지
	@RequestMapping("cate1_All.mw")
	public String cate1_All(HttpServletRequest request) {			
		List<LContryVO> dataList = new ArrayList<LContryVO>();
		dataList = sql.selectList("latlon.getLContry"); 
		int listSize = dataList.size();
		
		String [][] total = new String[listSize][4];
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLc_con();
				total[i][1] = Double.toString(dataList.get(i).getLc_lon());
				total[i][2] = Double.toString(dataList.get(i).getLc_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLc_cnt());
		}
		
		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate1_All";
	}
	
	@RequestMapping("cate2.mw")
	public String cate2(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate2";
	}
	
	@RequestMapping("cate3.mw")
	public String cate3(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate3";
	}
	@RequestMapping("cate4.mw")
	public String cate4(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate4";
	}
	@RequestMapping("cate5.mw")
	public String cate5(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate5";
	}
	@RequestMapping("cate6.mw")
	public String cate6(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate6";
	}
	@RequestMapping("cate7.mw")
	public String cate7(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate7";
	}
	@RequestMapping("cate8.mw")
	public String cate8(HttpServletRequest request) {			
		List<LRegionVO> dataList = new ArrayList<LRegionVO>();
		dataList = sql.selectList("latlon.getLRegion"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		
		String [][] total = new String[listSize][4];	
		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getLr_reg();
				total[i][1] = Double.toString(dataList.get(i).getLr_lon());
				total[i][2] = Double.toString(dataList.get(i).getLr_lat());
				total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
		}

		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);
		return "/Main/cate8";
	}
	
	@RequestMapping("test.mw")
	public String test() {
		return "/Main/test";
	}
	
	@RequestMapping("test2.mw")
	public String test2() {
		return "/Main/test2";
	}
	
	
	


}