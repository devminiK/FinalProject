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
	
	@RequestMapping("googlemaps.mw")
	public String googlemaps() {
		return "/Main/googlemaps";
	}
	
	//추후 삭제할것
	@RequestMapping("test_map.mw")
	public String test_map() {
		return "/Main/test_map";
	}

	//추후 삭제할것
	@RequestMapping("map_all.mw")
	public String map_all() {
		return "/Main/map_all";
	}

	//추후 삭제할것
	@RequestMapping("map_europe.mw")
	public String map_europe() {
		return "/Main/map_europe";
	}
	//추후 삭제할것
	@RequestMapping("test_data2.mw") //DB가져와 리스트 작성.
	public String test_data2(HttpServletRequest request) {
		List<LonlatVO> infoList = new ArrayList<LonlatVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB정보 갯수="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);

		//String[][] total = new String[buslist.size()][3];

		//model.addAttribute("buslistsize", buslist.size());
		//model.addAttribute("total", total);



		return "/Main/test_data2";
	}

}