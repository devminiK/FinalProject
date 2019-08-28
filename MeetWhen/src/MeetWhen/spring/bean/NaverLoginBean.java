package MeetWhen.spring.bean;

//네이버 로그인 연동 도전...2019.08.28 어려워서 그만 둠(추후 해결할것)
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Naver/")
public class NaverLoginBean {
	
	
	@RequestMapping(value="naverlogin.mw", method=RequestMethod.GET)
	public String naverlogin() {	
		
		
		return "/Log/naverlogin";
	}
	@RequestMapping(value="navercallback.mw", method=RequestMethod.GET)
	public String navercallback(HttpSession session) throws Exception {
		
		return "/Log/navercallback";
	}

}
