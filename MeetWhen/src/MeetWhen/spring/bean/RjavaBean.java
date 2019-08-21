package MeetWhen.spring.bean;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rjava/")
public class RjavaBean {
	
	@RequestMapping("test_data.mw")
	public String test_data() throws Exception{
		RConnection conn = new RConnection();
		
		//��������-7�� ����
		conn.eval("setwd('D:/R-workspace')");
		REXP res = conn.eval("getwd()");
		System.out.println(res.asString());
		
		//conn.eval("install.packages(\"xlsx\")");
		//conn.eval("library(xlsx)");
		System.out.println("test....");
		
		//conn.eval("july_all <- read.xlsx2('D:/R-workspace/Airport/7��_�����뼱�����.xlsx',1, stringsAsFactors=F)");
		//conn.eval("july_all<- july_all[,-4:-7]");
		//conn.eval("july_all <- july_all[,-5:-9]");
		//conn.eval("colnames(july_all)<- c(\"����\",\"����\",\"\",\"��������(��)\")");
		//conn.eval("july_all <-july_all[-1:-2,]");
		//conn.eval("rownames(july_all) <-NULL");
		
		//REXP info = conn.eval("july_all");
		//RList list = info.asList();
		//System.out.println("list.size()���="+list.size());
		//System.out.println("list.at(0).length()���="+list.at(0).length());
	
		//����ڷ� ���� ����ȭ ����.
		//...
		
		conn.close();
		return "/rjava/test_data";
	}

}
