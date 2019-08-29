package MeetWhen.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Main/")
public class HelloBean {
	
	@RequestMapping("welcome.mw")
	public String welcome() {
		System.out.println("Welcome Runing..!");
		return "/Main/welcome";
	}
	
	@RequestMapping("header.mw")
	public String header() {
		return "/Main/header";
	}
	
	@RequestMapping("footer.mw")
	public String footer() {
		return "/Main/footer";
	}
	/*--------------------------------------*/
	
	@RequestMapping("googlemaps.mw")
	public String googlemaps() {
		return "/Main/googlemaps";
	}
	
	//���� �����Ұ�
	@RequestMapping("test_map.mw")
	public String test_map() {
		return "/Main/test_map";
	}

	//���� �����Ұ�
	@RequestMapping("map_all.mw")
	public String map_all() {
		return "/Main/map_all";
	}

	//���� �����Ұ�
	@RequestMapping("map_europe.mw")
	public String map_europe() {
		return "/Main/map_europe";
	}

}