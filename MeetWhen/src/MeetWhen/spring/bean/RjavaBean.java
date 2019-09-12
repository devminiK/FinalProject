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

import MeetWhen.spring.vo.ContryVO;
import MeetWhen.spring.vo.LContryVO;
import MeetWhen.spring.vo.LRegionVO;
import MeetWhen.spring.vo.RegionVO;

@Controller
@RequestMapping("/rjava/")
public class RjavaBean {
	@Autowired
	private SqlSessionTemplate sql = null;

	//DB ���� �� ����, ���� �����ֱ� : DB1 ,DB2, DB3, DB4	
	@RequestMapping("dbControl.mw")	
	public String dbControl(){
		System.out.print("[������ ���� ���� PAGE]");
		//�������ϰ�츸 Ȯ���� �� �ִ� page�� �����ϱ�
		return "/Main/dbControl";
	}
	
	@RequestMapping("dbCreate.mw")//���� ���Ϸ� DB����-------------------------------------------
	public String dbCreate(HttpServletRequest request, int num) throws Exception{   
		RConnection conn = new RConnection();
		//�п��� D:, ���� C:
		//��� �� ���� �� ���̺귯�� ��ġ,�߰�
		conn.eval("setwd('C:/R-workspace')");
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
				conn.eval("allAir <- read.xlsx2('C:/R-workspace/Airport/7��_��ü���������.xlsx',1, stringsAsFactors=F)");
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
						conn.eval("InchAir <- read.xlsx2('C:/R-workspace/Airport/7��_��õ���������.xlsx',1, stringsAsFactors=F)");
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
			case 3: System.out.print("CASE 3 & 4"); //��õ��� ���� ���� ��������-> ������������ �ۼ�
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
				//(�߰�)�Ϻ��� ��� ������ ������ �������� ���ٿ��� "���̴�"�������� Na���� �߻����� ����
				conn.eval("for(i in 1:nrow(reg1)){" + 
						"  if(reg1$������[i]=='�Ϻ�')" + 
						"    reg1$���ø�[i]<-c(paste(reg1$������[i],reg1$���ø�[i]))" + 
						"}");
				conn.eval("reg1<-reg1[,-1]");
				conn.eval("colnames(reg1)<-c('������','����')"); //reg1 �ۼ� �Ϸ�
				
				conn.eval("reg2<-NULL;temp<-con2");
				conn.eval("for(i in 1:nrow(temp)){ " + 
						"  j<-0;" + 
						"  while(j<nrow(con1)){" + 
						"    j<-j+1;" + 
						"    if(temp$������[i]==con1$������[j]){" + 
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
				break;
		}
				
		//��ü�� DB�ۼ��ϴ� ��
		if(num==1) {//DB1�ۼ�[Contry]						
			DB = conn.eval("DB1");
			list = DB.asList();
			arr = new String[list.size()][];//�����迭�� �ۼ�
			for(i=0;i<list.size();i++) {
				arr[i]=list.at(i).asStrings();
			}		
			
			for(i=0; i<list.at(0).length();i++) {
				ContryVO vo = new ContryVO();
				vo.setC_con(arr[0][i]);
				vo.setC_cnt(Integer.parseInt(arr[1][i]));
				sql.insert("airport.insertContry",vo);	
			}
			System.out.println(">>ContryTable�� ��������Ϸ�");
			
		}else if(num==2) {//DB2�ۼ�[Lcontry]		
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
				LContryVO vo = new LContryVO();
				vo.setLc_con(arr[0][i]);
				vo.setLc_cnt(Integer.parseInt(arr[1][i]));
				vo.setLc_lat(Double.parseDouble(arr[2][i]));
				vo.setLc_lon(Double.parseDouble(arr[3][i]));
				sql.insert("latlon.insertLcontry",vo);	
			}
			System.out.println(">>LcontryTable�� ��������Ϸ�");	
				
		}else if(num==3) {//DB3�ۼ� [Region]
			conn.eval("DB3<-NULL");
			conn.eval("DB3<-rbind(DB3,reg1)");
			conn.eval("DB3<-rbind(DB3,reg2)");
			DB = conn.eval("DB3");
			list = DB.asList();
			arr=new String[list.size()][];
			for(i=0;i<list.size();i++) {
				arr[i]=list.at(i).asStrings();
			}
			
			for(i=0; i<list.at(0).length();i++) {
				RegionVO vo = new RegionVO();
				vo.setR_reg(arr[0][i]);
				vo.setR_cnt(Integer.parseInt(arr[1][i]));
				sql.insert("airport.insertRegion",vo);	
			}
			System.out.println(">>RegionTable�� ��������Ϸ�");
			
		}else if(num==4) {//DB4 [Lregion]	
			conn.eval("latlon<-NULL;lat<-NULL;lon<-NULL");
			conn.eval("DB4<-DB3");
			conn.eval("for(i in 1:nrow(DB4)){" + 
					"  latlon<-geocode(location=enc2utf8(x=DB4[,1][i]),output='latlon',source='google');" + 
					"  lat <-c(lat,latlon$lat);" + 
					"  lon <-c(lon,latlon$lon)" + 
					"}");
			conn.eval("DB4 <-cbind(DB4,lat)");
			conn.eval("DB4 <-cbind(DB4,lon)");
			
			DB = conn.eval("DB4");
			list = DB.asList();
			arr = new String[list.size()][];
			for(i=0;i<list.size();i++) {
				arr[i]=list.at(i).asStrings();
			}
			
			for(i=0; i<list.at(0).length();i++) {
				LRegionVO vo = new LRegionVO();
				vo.setLr_reg(arr[0][i]);
				vo.setLr_cnt(Integer.parseInt(arr[1][i]));
				vo.setLr_lat(Double.parseDouble(arr[2][i]));
				vo.setLr_lon(Double.parseDouble(arr[3][i]));
				sql.insert("latlon.insertLregion",vo);	
			}
			System.out.println(">>LRegionTable�� ��������Ϸ�");

		}
		
		request.setAttribute("num", num);
		return "/Main/dbCreate";
	}
	
	
	@RequestMapping("dbDelete.mw")//-------------------------------------------
	public String dbDelete(HttpServletRequest request, int num) {
		switch(num) {
		case 1:
			sql.delete("airport.deleContry");
			break;
		case 2:
			sql.delete("latlon.deleLcontry");
			break;
		case 3:
			sql.delete("airport.deleRegion");
			break;
		case 4:
			sql.delete("latlon.deleLregion");
			break;
		}
		System.out.println(">>�ش� ���̺� FORMAT �Ϸ�");
		
		request.setAttribute("num", num);
		return "/Main/dbDelete";
	}


	@RequestMapping("dbInfoCheck.mw")//-------------------------------------------ing
	public String dbInfoCheck(HttpServletRequest request, int num) throws Exception{
		System.out.println("DB���� Ȯ�� ������"+"num="+num);
		
		
		return "/Main/dbInfoCheck";
	}



}
