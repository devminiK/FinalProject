package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LContryVO;
import MeetWhen.spring.vo.LRegionVO;

@Controller
@RequestMapping("/Main/")
public class HelloBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("main.mw")  //���� ����ȭ��
	public String main() {
		return "/Main/main";
	}
	
	@RequestMapping("welcome.mw")  //ù ���� ȭ��
	public String welcome() {
		System.out.println("Welcome Runing..!");
		return "/Main/welcome";
	}
	/*--------------------------------�׽�Ʈ ��------*/
	
	//lonlatinfo DB���� ���
	@RequestMapping("test_data2.mw") //DB������ ����Ʈ �ۼ�.
	public String test_data2(HttpServletRequest request) {
		List<LContryVO> infoList = new ArrayList<LContryVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB���� ����="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);
		return "/Main/test_data2";
	}
	
	
	
	@RequestMapping("test.mw") //ũ�Ѹ�> ��� ��õ ����
	public String test() {
		return "/Main/test";
	}
	
	@RequestMapping("test2.mw")  //ũ�Ѹ� > ���� ��������
	public String test2() throws Exception{
		RConnection conn = new RConnection();
		
		conn.eval("setwd('C:/R-workspace')");
		conn.eval("library(rvest)");
		conn.eval("library(httr)");
		conn.eval("install.packages(\"RSelenium\")");
		conn.eval("library(RSelenium)");
		
		conn.eval("remDr <- remoteDriver(remoteServerAdd=\"localhost\", port=4445, browserName=\"chrome\")");
		conn.eval("remDr$open()");
		conn.eval("remDr$navigate('https://www.naver.com/')");
		conn.eval("WebEle <- remDr$findElement(using='css',\"[id='query']\")");
		conn.eval("WebEle$sendKeysToElement(list('�̱�',key=\"enter\"))"); //�̱� value�� for������ .
		
		
		
		return "/Main/test2";
	}
	
	
	


}