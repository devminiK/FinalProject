package MeetWhen.spring.bean;

/*
 * DB1~DB4 �ۼ� �ϴ� ��� 
 * DB �����ϴ� ���
 * �ۼ�, �����Ҷ� ���� ���� ���� �����˷��ֱ�.
 * db2,db4�� �ҿ�ð��� �Ÿ��������� script�� �˷��ֱ�.
 * */
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
import MeetWhen.spring.vo.LatlonVO;

@Controller
@RequestMapping("/rjava/")
public class RjavaBean {
	@Autowired
	private SqlSessionTemplate sql = null;

	//xlsx ���� -R ǥ������ȭ- AIRPORTINFO, LONLATINFO DB ����
	//���� �� ����, ���� �����ֱ�.
	//DB1 ,DB2, DB3, DB4
	
	@RequestMapping("dbControl.mw")	
	public String dbControl(){
		System.out.println("�����ڰ� DB���� �� ���� �Ҽ� �ִ� ������");
		//�������ϰ�츸 Ȯ���� �� �ִ� page�� �����ϱ�
		return "/Main/dbControl";
	}
	
	@RequestMapping("dbCreate.mw")		//���� ���Ϸ� DB����
	public String dbCreate(HttpServletRequest request, int num) throws Exception{   
		RConnection conn = new RConnection();
		
		//��� �� ���� �� ���̺귯�� ��ġ,�߰�
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("install.packages(\"xlsx\")");
		conn.eval("library(xlsx)");
		conn.eval("install.packages(\"ggmap\")");
		conn.eval("library(ggmap)");
		conn.eval("register_google(key='AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw')");
		
		REXP DB=null;	//DB�ۼ� �� ������ �������� ���� ����	
		RList list=null;
		String [][] arr =null;
		int i=0;
		
		switch(num) {
			//��������-7�� ����
			case 1: System.out.print("CASE 1 & 2"); //��ü��� ���� ���� ��������-> ������������ �ۼ�
			case 2:
				conn.eval("allAir <- read.xlsx2('D:/R-workspace/Airport/7��_��ü���������.xlsx',1, stringsAsFactors=F)");
				conn.eval("allAir<- allAir[,-4:-7]");
				conn.eval("allAir <- allAir[,-5:-9]");
				conn.eval("colnames(allAir)<- c('������','���ø�','','����')");
				conn.eval("allAir <-allAir[-1:-2,]");
				
				conn.eval("reVec<-NULL; reDf <-NULL");
				conn.eval("reg <-NULL; con <-NULL");
				conn.eval("for(i in 1:(nrow(allAir))){ " + 
						"  if(allAir[i,1]!=''& allAir[i,2]!=''){ " + 
						"    if(allAir[i+1 ,1]!=\"\" & allAir[i+1,2]!=''){ " + 
						"      reDf <-rbind(reDf,allAir[i,-3]); " + 
						"    }else if(allAir[i+1,1]==''& allAir[i+1,2]!=''){ " + 
						"      reDf <-rbind(reDf,allAir[i,-3]); " + 
						"      con <- allAir[i,1]; " + 
						"    }else if(allAir[i+1,1]==''& allAir[i+1,2]==''){ " + 
						"      con<- allAir[i,1]; " + 
						"      reg<- allAir[i,2]; " + 
						"    } " + 
						"  }else if(allAir[i,1]==''& allAir[i,2]!=''){ " + 
						"    if(allAir[i,2]!='�� ��'&allAir[i,2]!='�Ұ�'){ " + 
						"      reg<- allAir[i,2]; " + 
						"      if(allAir[i+1,2]!=''){ " + 
						"        reVec <-c(con,reg,allAir[i,4]); " + 
						"        reDf <-rbind(reDf,reVec); " + 
						"      } " + 
						"    }else{ " + 
						"      reVec <-NULL; " + 
						"      reVec <-c(con,con,allAir[i,4]); " + 
						"      reDf <-rbind(reDf,reVec); " + 
						"    } " + 
						"  }else if(allAir[i,1]==''& allAir[i,2]==''){ " + 
						"    if(allAir[i,3]=='�� ��'|allAir[i,3]=='�Ұ�'){ " + 
						"      reVec<-c(con,reg,allAir[i,4]); " + 
						"      reDf<-rbind(reDf,reVec); " + 
						"    } " + 
						"  }else if(allAir[i,1]=='�հ�'|allAir[i,1]=='�� ��'){ " + 
						"    reDf <- rbind(reDf,allAir[i,-3]); " + 
						"  } " + 
						"}"); //xlsx���� �ʿ� ������ ����, 1�������

						//�̱��� ������ ��(��,������) ����
						conn.eval("island<-subset(reDf, ���ø�==\"������\"|���ø�==\"��\",select=����)");
						conn.eval("sum<-0");
						conn.eval("for(i in 1:nrow(island)){" + 
								"  sum<-sum+as.integer(island[,1][i]);" + 
								"}");
						conn.eval("temp<-reDf; con1<-NULL");
						conn.eval("for(i in 1:nrow(temp)){" + 
								"  if(temp[i,1]==temp[i,2]){" + 
								"    if(temp[i,2]=='�̱�'){" + 
								"      temp[i,3]<-as.integer(temp[i,3])-sum;" + 
								"    };" + 
								"    con1<-rbind(con1,temp[i,])" + 
								"  }else if(temp[i,2]=='������'|temp[i,2]=='��'){" + 
								"    con1<-rbind(con1,temp[i,])" + 
								"  }" + 
								"}");				
						conn.eval("con1<-con1[,-1]");
						conn.eval("names(con1)<-c('������','����')"); //con1 �ۼ� �Ϸ�.
						
						//��õ��� ���� ���� ��������
						conn.eval("InchAir <- read.xlsx2('D:/R-workspace/Airport/7��_��õ���������.xlsx',1, stringsAsFactors=F)");
						conn.eval("con2<- InchAir[,-7:-11]");
						conn.eval("con2<-con2[-1,-3:-5]");
						conn.eval("con2<-con2[-nrow(con2),-1]");
						conn.eval("con2[,2]<-gsub(',','',con2$����)"); //con2 �ۼ� �Ϸ�
						
						//DB1 ������������ �ۼ�
						conn.eval("DB1<-con2");
						conn.eval("for(i in 1:nrow(con1)){" + 
								"  j<-1;" + 
								"  while(j<=nrow(DB1)){" + 
								"    if(con1$������[i]==DB1$������[j]){" + 
								"      DB1$����[j]<-as.integer(con1$����[i])+as.integer(DB1$����[j]);" + 
								"      break" + 
								"    };" + 
								"    j<-j+1;" + 
								"  };" + 
								"}");	
				break;
				
			//��õ����-7�� ����
			case 3: System.out.println("CASE 3 & 4"); //��õ��� ���� ���� ��������-> ������������ �ۼ�
			case 4:
				conn.eval("reg1<-NULL");
				conn.eval("reg1<-reDf[1,]");
				conn.eval("for(i in 2:nrow(reDf)){" + 
						"  if(reDf[i,][1]!=reDf[i,][2]){" + 
						"    if(reDf[i,][1]!='�ѱ�'&reDf[i,][1]!='�հ�'&reDf[i,][1]!='�� ��'" + 
						"       &reDf[i,][2]!='������'&reDf[i,][2]!='��'){" + 
						"      reg1<-rbind(reg1,reDf[i,])" + 
						"    }" + 
						"  }else if(reDf[i,][1]!=reDf[i-1,][1]){" + 
						"    reg1<-rbind(reg1,reDf[i,])" + 
						"  }" + 
						"}");
				conn.eval("reg1<-reg1[,-1]");
				conn.eval("colnames(reg1)<-c('������','����')"); //reg1 �ۼ� �Ϸ�
				/*
				conn.eval("reg2<-NULL;temp<-con2");
				conn.eval("for(i in 1:nrow(temp)){ " + 
						"  j<-0;" + 
						"  while(j<nrow(con1)){" + 
						"    j<-j+1;" + 
						"    if(temp$������[i]==con1$������[j]){#�������, ����x" + 
						"      if(temp$������[i]=='������'|temp$������[i]=='��'){" + 
						"        temp$����[i] <- as.integer(temp$����[i])+as.integer(con1$����[j]);" + 
						"        reg2<-rbind(reg2,temp[i,])" + 
						"      };" + 
						"      break;" + 
						"    }" + 
						"  };" + 
						"  if(j==nrow(con1)){" + 
						"    reg2<-rbind(reg2,temp[i,])" + 
						"  }" + 
						"}"); //reg2�ۼ�
				*/
				break;
				
		}
		
		
		//��ü�� DB�ۼ��ϴ� ��
		if(num==1) {						
			DB = conn.eval("DB1");
			list = DB.asList();
			arr = new String[list.size()][];//�����迭�� �ۼ�
			for(i=0;i<list.size();i++) {
				arr[i]=list.at(i).asStrings();
			}		
			
			for(i=0; i<list.at(0).length();i++) {
				AirportVO vo = new AirportVO();
				vo.setPlace(arr[0][i]);
				vo.setCnt(Integer.parseInt(arr[1][i]));
				sql.insert("airport.insertContry",vo);	
			}
			System.out.println(">>ContryTable�� ��������Ϸ�");
			
		}else if(num==2) {		
			conn.eval("latlon<-NULL;lat<-NULL;lon<-NULL;");
			conn.eval("DB2<-DB1");
			conn.eval("for(i in 1:nrow(DB2)){" + 
					"  latlon<-geocode(location=enc2utf8(x=DB2[,1][i]),output='latlon',source='google');" + 
					"  lat<-c(lat,latlon$lat);" + 
					"  lon<-c(lon,latlon$lon)" + 
					"}");
			conn.eval("DB2<-cbind(DB2,lat)");
			conn.eval("DB2<-cbind(DB2,lon)");
			
			DB = conn.eval("DB2");
			list = DB.asList();
			arr = new String[list.size()][];
			for(i=0;i<list.size();i++) {
				arr[i]=list.at(i).asStrings();
			}
			
			for(i=0; i<list.at(0).length();i++) {
				LatlonVO vo = new LatlonVO();
				vo.setPlace(arr[0][i]);
				vo.setCnt(Integer.parseInt(arr[1][i]));
				vo.setLat(Double.parseDouble(arr[2][i]));
				vo.setLon(Double.parseDouble(arr[3][i]));
				sql.insert("lonlat.insertLcontry",vo);	
			}
			System.out.println(">>LcontryTable�� ��������Ϸ�");
				
				
		}else if(num==3) {
			System.out.println("if-else��: 3�̾�");
			
			
		}else if(num==4) {
			System.out.println("if-else��: 4�̾�");
			
		}
		
		request.setAttribute("num", num);
		return "/Main/dbCreate";
	}
	
	
	@RequestMapping("dbDelete.mw")
	public String dbDelete(HttpServletRequest request, int num) {
		switch(num) {
		case 1:
			System.out.println("contry db����");
			sql.delete("airport.deleContry");
			break;
		case 2:
			System.out.println("lcontry ����");
			//sql.delete("airport.deleLcontry");
			break;
		case 3:
			System.out.println("region ����");
			//sql.delete("airport.deleContry");
			break;
		case 4:
			System.out.println("lregion ����");
			//sql.delete("airport.deleContry");
			break;
		}
		request.setAttribute("num", num);
		return "/Main/dbDelete";
	}


	@RequestMapping("practice.mw")
	public String practice() throws Exception{
		//createDB �Űܵа���. ������
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
		/*
		for(int a=0;a<list2.at(0).length();a++) {
			int num = (Integer)sql.selectOne("airport.getNum");
			ContryVO vo = new ContryVO();
			vo.setA_num(num);
			vo.setA_con(s[0][a]);
			vo.setA_reg(s[1][a]);
			vo.setA_cnt(Integer.parseInt(s[2][a]));
			sql.insert("airport.insertinfo",vo);
		}	
*/

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
		/*
		for(int a=0;a<list3.at(0).length();a++) {
			int num = (Integer)sql.selectOne("lonlat.getNum");
			LcontryVO vo = new LcontryVO();
			vo.setL_num(num);
			vo.setL_conreg(ss[0][a]);
			vo.setL_lon(Double.parseDouble(ss[1][a]));
			vo.setL_lat(Double.parseDouble(ss[2][a]));
			vo.setL_cnt(Integer.parseInt(ss[3][a]));
			sql.insert("lonlat.insertinfo",vo);
		}	
		*/	
		conn.close();
		return "/rjava/practic";
	}



}
