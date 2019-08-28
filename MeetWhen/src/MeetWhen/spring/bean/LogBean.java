package MeetWhen.spring.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//�α��� ����
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Log/")
public class LogBean {
	@RequestMapping("loginForm.mw")
	public String loginForm(HttpSession session) {
		return "/Log/loginForm";
	}
	
	//���̹� �α��� ���� ����.
	@RequestMapping("naverlogin.mw")
	public String naverlogin(HttpSession session) {
		//���̹� �α��� �߰�
		
		return "/Log/naverlogin";
	}
	@RequestMapping("callback.mw")
	public String callback(HttpServletRequest request) throws Exception {
		//���̹� �α��� �߰�
		
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
