package MeetWhen.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Member/")
public class MemberBean {
	
	//-------------------------------------------Register
	@RequestMapping("comfirmId.mw")
	public String comfirmId() {
		
		return "/Register/comfirmId";
	}
	@RequestMapping("registerForm.mw")
	public String registerForm() {
		
		return "/Register/registerForm";
	}
	@RequestMapping("registerPro.mw")
	public String registerPro() {
		
		return "/Register/registerPro";
	}

}
