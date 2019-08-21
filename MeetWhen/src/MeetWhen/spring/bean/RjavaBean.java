package MeetWhen.spring.bean;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/rjava/")
public class RjavaBean {
	
	@RequestMapping("test_data.mw")
	public String test_data() throws Exception{
		RConnection conn = new RConnection();
	
		//국제공항-7월 정보
		conn.eval("setwd('D:/R-workspace')");
		REXP res = conn.eval("getwd()");
		System.out.println(res.asString());
		
		conn.eval("install.packages(\"xlsx\")");
		conn.eval("library(xlsx)");
		System.out.println("read xlsmFile-ok");

		//엑셀 파일 가져오기, 필요 정보 추출
		conn.eval("july_all <- read.xlsx2('D:/R-workspace/Airport/7월_국제노선별통계.xlsx',1, stringsAsFactors=F)");
		conn.eval("july_all<- july_all[,-4:-7]");
		conn.eval("july_all <- july_all[,-5:-9]");
		conn.eval("colnames(july_all)<- c(\"국가\",\"도시\",\"\",\"여객도착(명)\")");
		conn.eval("july_all <-july_all[-1:-2,]");
		conn.eval("rownames(july_all) <-NULL");
		System.out.println("arrange xlsmFile-ok");
		
		REXP df = conn.eval("july_all");
		RList list = df.asList();
		System.out.println("ListSize="+list.size()+
							"\nListLength="+list.at(0).length());
		
		conn.eval("test <- july_all");
		conn.eval("reVec<-NULL; reDf <-NULL");
		conn.eval("reg <-NULL; con <-NULL");
		conn.eval("for(i in 1:(nrow(test))){" + 
				"  if(test[i,1]!=\"\"& test[i,2]!=\"\"){" + 
				"    if(test[i+1 ,1]!=\"\" & test[i+1,2]!=\"\"){" + 
				"      reDf <-rbind(reDf,test[i,-3]);     " + 
				"    }else if(test[i+1,1]==\"\"& test[i+1,2]!=\"\"){" + 
				"      reDf <-rbind(reDf,test[i,-3]);" + 
				"      con <- test[i,1];" + 
				"    }else if(test[i+1,1]==\"\"& test[i+1,2]==\"\"){" + 
				"      con<- test[i,1];" + 
				"      reg<- test[i,2];" + 
				"    }" + 
				"  }else if(test[i,1]==\"\"& test[i,2]!=\"\"){" + 
				"    if(test[i,2]!='소 계'&test[i,2]!='소계'){" + 
				"      reg<- test[i,2];" + 
				"      if(test[i+1,2]!=\"\"){" + 
				"        reVec <-c(con,reg,test[i,4]);" + 
				"        reDf <-rbind(reDf,reVec);" + 
				"      }else if(test[i+1,2]==\"\"& test[i+1,3]!=\"\"){}" + 
				"    }else{" + 
				"      reVec <-NULL;" + 
				"      reVec <-c(con,con,test[i,4]);" + 
				"      reDf <-rbind(reDf,reVec);" + 
				"    }  " + 
				"  }else if(test[i,1]==\"\"& test[i,2]==\"\"){" + 
				"    if(test[i,3]==\"소 계\"|test[i,3]==\"소계\"){" + 
				"      reVec<-c(con,reg,test[i,4]);" + 
				"      reDf<-rbind(reDf,reVec);" + 
				"    }" + 
				"  }else if(test[i,1]=='합계'|test[i,1]=='합 계'){" + 
				"    reDf <- rbind(reDf,test[i,-3]);" + 
				"  }" + 
				"}");
		
		REXP result = conn.eval("reDf");
		RList list2 = result.asList();
		System.out.println("ListSize="+list2.size()+
							"\nListLength="+list2.at(0).length());
		
		//2019.08.21 -이까지 ok문제 없음
		//엑셀 db에 저장하는 방법 알아보기 or db에 일일이 저장하는 방법 할것.
		
		
		
		//통계자료 엑셀 정규화 과정.
		//...
		
		conn.close();
		return "/rjava/test_data";
	}

}
