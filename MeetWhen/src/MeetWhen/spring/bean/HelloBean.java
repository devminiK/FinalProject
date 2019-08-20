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
	
	@RequestMapping("menu.mw")
	public String menu() {
		return "/Main/menu";
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
	
	//추후 삭제할것
	@RequestMapping("maptest.mw")
	public String maptest() {
		return "/Main/maptest";
	}


}