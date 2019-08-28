package MeetWhen.spring.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//로그인 관련
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Log/")
public class LogBean {
	@RequestMapping("loginForm.mw")
	public String loginForm(HttpSession session) {
		return "/Log/loginForm";
	}
	
	//네이버 로그인 연동 도전.
	@RequestMapping("naverlogin.mw")
	public String naverlogin(HttpSession session) {
		//네이버 로그인 추가
		
		return "/Log/naverlogin";
	}
	@RequestMapping("callback.mw")
	public String callback(HttpServletRequest request) throws Exception {
		//네이버 로그인 추가
		
		return "/Log/callback";
	}
	
	
	@RequestMapping("loginPro.mw")
	public String loginPro() {
		
		return "/Log/loginPro";
	}
	@RequestMapping("logout.mw")
	public String logout() {
		
		return "/Log/logout";
	}
}
