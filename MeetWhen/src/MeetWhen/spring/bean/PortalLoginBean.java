package MeetWhen.spring.bean;

//네이버 로그인 연동 도전
//네이버, 카카오톡, 구글 해볼것.
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Naver/")
public class PortalLoginBean {
	
	
	@RequestMapping(value="naverLogin.mw", method=RequestMethod.GET)
	public String naverLogin() {	
		
		
		return "/Naver/naverLogin";
	}
	@RequestMapping(value="naverCallback.mw", method=RequestMethod.GET)
	public String naverCallback(HttpSession session) throws Exception {
		
		return "/Naver/naverCallback";
	}
}
