package MeetWhen.spring.bean;

//로그인 관련
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Log/")
public class LogBean {
	@RequestMapping("loginForm.mw")
	public String loginForm() {
	
		return "/Log/loginForm";
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
