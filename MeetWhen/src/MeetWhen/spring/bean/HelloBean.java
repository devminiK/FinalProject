package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LonlatVO;

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
	

	//대륙 별 버튼 클릭을 위한 카테고리
	@RequestMapping("category.mw")
	public String category() {
		return "/Main/category";
	}

	//삭제
	@RequestMapping("map_total.mw")
	public String map_total(HttpServletRequest request) {
		List<LonlatVO> dataList = new ArrayList<LonlatVO>();

		int count = sql.selectOne("lonlat.getCnt"); //DB레코드 갯수

		dataList = sql.selectList("lonlat.getAll"); //모든 정보 가져오기
		int listSize = dataList.size();//infoList의 길이
		String [][] total = new String[listSize][4];

		for(int i=0;i<listSize;i++) {
				total[i][0] = dataList.get(i).getL_conreg();
				total[i][1] = Double.toString(dataList.get(i).getL_lon());
				total[i][2] = Double.toString(dataList.get(i).getL_lat());
				total[i][3] = Integer.toString(dataList.get(i).getL_cnt());
		}
		
		request.setAttribute("total", total);	
		request.setAttribute("listSize", listSize);	

		return "/Map/map_total";
	}
	
	
	//lonlatinfo DB정보 출력
	@RequestMapping("test_data2.mw") //DB가져와 리스트 작성.
	public String test_data2(HttpServletRequest request) {
		List<LonlatVO> infoList = new ArrayList<LonlatVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB정보 갯수="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);
		return "/Main/test_data2";
	}

}