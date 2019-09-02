package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.AirportVO;
import MeetWhen.spring.vo.LonlatVO;

@Controller
@RequestMapping("/rjava/")
public class RjavaBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("delete_data.mw") 	//airportinfo&lonlatinfo TABLE ����
	public String delete_data(){         
			sql.delete("airport.deleteinfo");	
			sql.delete("lonlat.deleteinfo");
		return "/rjava/delete_data";
	}
	
	//xlsx ���� -R ǥ������ȭ- AIRPORTINFO, LONLATINFO DB ����
	@RequestMapping("createDB.mw")		//���� ���Ϸ� DB����
	public String createDB() throws Exception{    
		RConnection conn = new RConnection();
	
		//��������-7�� ����
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("install.packages(\"xlsx\")");
		conn.eval("library(xlsx)");

		//���� ���� ��������, �ʿ� ���� ����
		conn.eval("july_all <- read.xlsx2('D:/R-workspace/Airport/7��_�����뼱�����.xlsx',1, stringsAsFactors=F)");
		conn.eval("july_all<- july_all[,-4:-7]");
		conn.eval("july_all <- july_all[,-5:-9]");
		conn.eval("colnames(july_all)<- c(\"����\",\"����\",\"\",\"��������(��)\")");
		conn.eval("july_all <-july_all[-1:-2,]");
		conn.eval("rownames(july_all) <-NULL");
		System.out.println("arrange xlsmFile-ok");
		
		REXP df = conn.eval("july_all");
		//RList list = df.asList();
		//System.out.println("ListSize="+list.size()+
		//					" / ListLength="+list.at(0).length());
		
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
				"    if(test[i,2]!='�� ��'&test[i,2]!='�Ұ�'){" + 
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
				"    if(test[i,3]==\"�� ��\"|test[i,3]==\"�Ұ�\"){" + 
				"      reVec<-c(con,reg,test[i,4]);" + 
				"      reDf<-rbind(reDf,reVec);" + 
				"    }" + 
				"  }else if(test[i,1]=='�հ�'|test[i,1]=='�� ��'){" + 
				"    reDf <- rbind(reDf,test[i,-3]);" + 
				"  }" + 
				"}");
		conn.eval("rownames(reDf)<-NULL"); // 1�� �����
		
		REXP result = conn.eval("reDf");
		RList list2 = result.asList();
		//System.out.println("ListSize="+list2.size()+
		//				   " / ListLength="+list2.at(0).length());		
		
		String[][] s = new String[list2.size()][]; //�����迭�� �ۼ�
		for(int i=0; i<list2.size();i++) {
			s[i] = list2.at(i).asStrings();
		}
		
		//Ȯ�ο�) �迭 �� ���
		//System.out.println("----------DB�� �����  ���� ���----------");
		//for(int i=0;i<list2.at(0).length();i++) {  
		//	for(int j=0; j<list2.size(); j++) { 
		//		System.out.print(s[j][i]+" ");}
		//	System.out.println();}System.out.println("-----------------------------------");
		
		
		/*ǥ������ȭ�� ��ģ ������ DB�� �����ϱ� ���� �ڵ� */
		//sql) DB�� ���� ���� >airportinfo Table
		for(int a=0;a<list2.at(0).length();a++) {
			int num = (Integer)sql.selectOne("airport.getNum");
			AirportVO vo = new AirportVO();
			vo.setA_num(num);
			vo.setA_con(s[0][a]);
			vo.setA_reg(s[1][a]);
			vo.setA_cnt(Integer.parseInt(s[2][a]));
			sql.insert("airport.insertinfo",vo);
		}	
		
		
		/*ǥ������ȭ�� ��ģ DB+ �����浵 �������� �Բ� �����ϱ� ���� �ڵ� */
		conn.eval("reDf2<-NULL"); 
		conn.eval("for(i in 1:nrow(reDf)){" + 
				"  if(reDf[i,1]==reDf[i,2]){" + 
				"    reDf2 <-rbind(reDf2,reDf[i,1])" + 
				"  }else if(reDf[i,1]!=reDf[i,2]){" + 
				"    if(reDf[i,1]!=\"�հ�\"&reDf[i,1]!=\"�� ��\"){" + 
				"      reDf2 <-rbind(reDf2,paste(reDf[i,1],reDf[i,2]))" + 
				"    }" + 
				"  }" + 
				"}");	
		
		conn.eval("install.packages(\"ggmap\")");
		conn.eval("library(ggmap)");
		conn.eval("register_google(key='AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw')");
		conn.eval("latlon<-NULL;lat<-NULL;lon<-NULL");

		conn.eval("for(i in 1:nrow(reDf2)){" + 
				"  latlon<-geocode(location=enc2utf8(x=reDf2[i])," + 
				"          output='latlon', source='google');" + 
				"  lat <-c(lat,latlon$lat); lon <-c(lon,latlon$lon)" + 
				"}");
		conn.eval("reDf2 <-cbind(reDf2,lon)");  //�浵
		conn.eval("reDf2 <-cbind(reDf2,lat)");  //����
		conn.eval("cnt <- reDf[-nrow(reDf),3]"); //�湮�ڼ�
		conn.eval("reDf2 <-cbind(reDf2,cnt)");
		conn.eval("reDf2<-as.data.frame(reDf2)"); // 2�� �����
		//Ȯ�ο�
		REXP result3 = conn.eval("reDf2");
		RList list3 = result3.asList();
		//System.out.println("ListSize="+list3.size()+
		//				   " / ListLength="+list3.at(0).length());	
		
		String[][] ss = new String[list3.size()][]; //�����迭�� �ۼ�
		for(int i=0; i<list3.size();i++) {
			ss[i] = list3.at(i).asStrings();
		}
		
		//sql) DB�� ���� ���� > lonlatinfo Table
		for(int a=0;a<list3.at(0).length();a++) {
			int num = (Integer)sql.selectOne("lonlat.getNum");
			LonlatVO vo = new LonlatVO();
			vo.setL_num(num);
			vo.setL_conreg(ss[0][a]);
			vo.setL_lon(Double.parseDouble(ss[1][a]));
			vo.setL_lat(Double.parseDouble(ss[2][a]));
			vo.setL_cnt(Integer.parseInt(ss[3][a]));
			sql.insert("lonlat.insertinfo",vo);
		}		
		conn.close();
		return "/rjava/createDB";
	}
	
	

}
