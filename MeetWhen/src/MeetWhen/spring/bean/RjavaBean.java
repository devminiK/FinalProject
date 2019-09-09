package MeetWhen.spring.bean;

/*
 * DB1~DB4 작성 하는 기능 
 * DB 포멧하는 기능
 * 작성, 포멧할때 기존 내용 존재 유무알려주기.
 * db2,db4은 소요시간이 거릴수있음을 script로 알려주기.
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

	//xlsx 파일 -R 표준정규화- AIRPORTINFO, LONLATINFO DB 저장
	//생성 및 삭제, 정보 보여주기.
	//DB1 ,DB2, DB3, DB4
	
	@RequestMapping("dbControl.mw")	
	public String dbControl(){
		System.out.println("관리자가 DB생성 및 삭제 할수 있는 페이지");
		//관리자일경우만 확인할 수 있는 page로 설정하기
		return "/Main/dbControl";
	}
	
	@RequestMapping("dbCreate.mw")		//엑셀 파일로 DB생성
	public String dbCreate(HttpServletRequest request, int num) throws Exception{   
		RConnection conn = new RConnection();
		
		//경로 재 설정 및 라이브러리 설치,추가
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("install.packages(\"xlsx\")");
		conn.eval("library(xlsx)");
		conn.eval("install.packages(\"ggmap\")");
		conn.eval("library(ggmap)");
		conn.eval("register_google(key='AIzaSyCexlJx5Gqv4JLwdSxZIeYwAE2IIRN_iGw')");
		
		REXP DB=null;	//DB작성 전 데이터 프레임을 담을 변수	
		RList list=null;
		String [][] arr =null;
		int i=0;
		
		switch(num) {
			//국제공항-7월 정보
			case 1: System.out.print("CASE 1 & 2"); //전체통계 엑셀 정보 가져오기-> 데이터프레임 작성
			case 2:
				conn.eval("allAir <- read.xlsx2('D:/R-workspace/Airport/7월_전체지역별통계.xlsx',1, stringsAsFactors=F)");
				conn.eval("allAir<- allAir[,-4:-7]");
				conn.eval("allAir <- allAir[,-5:-9]");
				conn.eval("colnames(allAir)<- c('국가명','도시명','','여객')");
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
						"    if(allAir[i,2]!='소 계'&allAir[i,2]!='소계'){ " + 
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
						"    if(allAir[i,3]=='소 계'|allAir[i,3]=='소계'){ " + 
						"      reVec<-c(con,reg,allAir[i,4]); " + 
						"      reDf<-rbind(reDf,reVec); " + 
						"    } " + 
						"  }else if(allAir[i,1]=='합계'|allAir[i,1]=='합 계'){ " + 
						"    reDf <- rbind(reDf,allAir[i,-3]); " + 
						"  } " + 
						"}"); //xlsx에서 필요 정보만 빼낸, 1차결과물

						//미국의 관광지 섬(괌,사이판) 제외
						conn.eval("island<-subset(reDf, 도시명==\"사이판\"|도시명==\"괌\",select=여객)");
						conn.eval("sum<-0");
						conn.eval("for(i in 1:nrow(island)){" + 
								"  sum<-sum+as.integer(island[,1][i]);" + 
								"}");
						conn.eval("temp<-reDf; con1<-NULL");
						conn.eval("for(i in 1:nrow(temp)){" + 
								"  if(temp[i,1]==temp[i,2]){" + 
								"    if(temp[i,2]=='미국'){" + 
								"      temp[i,3]<-as.integer(temp[i,3])-sum;" + 
								"    };" + 
								"    con1<-rbind(con1,temp[i,])" + 
								"  }else if(temp[i,2]=='사이판'|temp[i,2]=='괌'){" + 
								"    con1<-rbind(con1,temp[i,])" + 
								"  }" + 
								"}");				
						conn.eval("con1<-con1[,-1]");
						conn.eval("names(con1)<-c('국가명','여객')"); //con1 작성 완료.
						
						//인천통계 엑셀 정보 가져오기
						conn.eval("InchAir <- read.xlsx2('D:/R-workspace/Airport/7월_인천지역별통계.xlsx',1, stringsAsFactors=F)");
						conn.eval("con2<- InchAir[,-7:-11]");
						conn.eval("con2<-con2[-1,-3:-5]");
						conn.eval("con2<-con2[-nrow(con2),-1]");
						conn.eval("con2[,2]<-gsub(',','',con2$여객)"); //con2 작성 완료
						
						//DB1 데이터프레임 작성
						conn.eval("DB1<-con2");
						conn.eval("for(i in 1:nrow(con1)){" + 
								"  j<-1;" + 
								"  while(j<=nrow(DB1)){" + 
								"    if(con1$국가명[i]==DB1$국가명[j]){" + 
								"      DB1$여객[j]<-as.integer(con1$여객[i])+as.integer(DB1$여객[j]);" + 
								"      break" + 
								"    };" + 
								"    j<-j+1;" + 
								"  };" + 
								"}");	
				break;
				
			//인천공항-7월 정보
			case 3: System.out.println("CASE 3 & 4"); //인천통계 엑셀 정보 가져오기-> 데이터프레임 작성
			case 4:
				conn.eval("reg1<-NULL");
				conn.eval("reg1<-reDf[1,]");
				conn.eval("for(i in 2:nrow(reDf)){" + 
						"  if(reDf[i,][1]!=reDf[i,][2]){" + 
						"    if(reDf[i,][1]!='한국'&reDf[i,][1]!='합계'&reDf[i,][1]!='합 계'" + 
						"       &reDf[i,][2]!='사이판'&reDf[i,][2]!='괌'){" + 
						"      reg1<-rbind(reg1,reDf[i,])" + 
						"    }" + 
						"  }else if(reDf[i,][1]!=reDf[i-1,][1]){" + 
						"    reg1<-rbind(reg1,reDf[i,])" + 
						"  }" + 
						"}");
				conn.eval("reg1<-reg1[,-1]");
				conn.eval("colnames(reg1)<-c('국가명','여객')"); //reg1 작성 완료
				/*
				conn.eval("reg2<-NULL;temp<-con2");
				conn.eval("for(i in 1:nrow(temp)){ " + 
						"  j<-0;" + 
						"  while(j<nrow(con1)){" + 
						"    j<-j+1;" + 
						"    if(temp$국가명[i]==con1$국가명[j]){#같을경우, 저장x" + 
						"      if(temp$국가명[i]=='사이판'|temp$국가명[i]=='괌'){" + 
						"        temp$여객[i] <- as.integer(temp$여객[i])+as.integer(con1$여객[j]);" + 
						"        reg2<-rbind(reg2,temp[i,])" + 
						"      };" + 
						"      break;" + 
						"    }" + 
						"  };" + 
						"  if(j==nrow(con1)){" + 
						"    reg2<-rbind(reg2,temp[i,])" + 
						"  }" + 
						"}"); //reg2작성
				*/
				break;
				
		}
		
		
		//구체적 DB작성하는 곳
		if(num==1) {						
			DB = conn.eval("DB1");
			list = DB.asList();
			arr = new String[list.size()][];//가변배열로 작성
			for(i=0;i<list.size();i++) {
				arr[i]=list.at(i).asStrings();
			}		
			
			for(i=0; i<list.at(0).length();i++) {
				AirportVO vo = new AirportVO();
				vo.setPlace(arr[0][i]);
				vo.setCnt(Integer.parseInt(arr[1][i]));
				sql.insert("airport.insertContry",vo);	
			}
			System.out.println(">>ContryTable에 정보저장완료");
			
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
			System.out.println(">>LcontryTable에 정보저장완료");
				
				
		}else if(num==3) {
			System.out.println("if-else문: 3이야");
			
			
		}else if(num==4) {
			System.out.println("if-else문: 4이야");
			
		}
		
		request.setAttribute("num", num);
		return "/Main/dbCreate";
	}
	
	
	@RequestMapping("dbDelete.mw")
	public String dbDelete(HttpServletRequest request, int num) {
		switch(num) {
		case 1:
			System.out.println("contry db비우기");
			sql.delete("airport.deleContry");
			break;
		case 2:
			System.out.println("lcontry 비우기");
			//sql.delete("airport.deleLcontry");
			break;
		case 3:
			System.out.println("region 비우기");
			//sql.delete("airport.deleContry");
			break;
		case 4:
			System.out.println("lregion 비우기");
			//sql.delete("airport.deleContry");
			break;
		}
		request.setAttribute("num", num);
		return "/Main/dbDelete";
	}


	@RequestMapping("practice.mw")
	public String practice() throws Exception{
		//createDB 옮겨둔거임. 연습용
		RConnection conn = new RConnection();

		//국제공항-7월 정보
		conn.eval("setwd('D:/R-workspace')");
		conn.eval("install.packages(\"xlsx\")");
		conn.eval("library(xlsx)");

		//엑셀 파일 가져오기, 필요 정보 추출
		conn.eval("july_all <- read.xlsx2('D:/R-workspace/Airport/7월_국제노선별통계.xlsx',1, stringsAsFactors=F)");
		conn.eval("july_all<- july_all[,-4:-7]");
		conn.eval("july_all <- july_all[,-5:-9]");
		conn.eval("colnames(july_all)<- c(\"국가\",\"도시\",\"\",\"여객도착(명)\")");
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
		conn.eval("rownames(reDf)<-NULL"); // 1차 결과물

		REXP result = conn.eval("reDf");
		RList list2 = result.asList();
		//System.out.println("ListSize="+list2.size()+
		//				   " / ListLength="+list2.at(0).length());		

		String[][] s = new String[list2.size()][]; //가변배열로 작성
		for(int i=0; i<list2.size();i++) {
			s[i] = list2.at(i).asStrings();
		}

		//확인용) 배열 값 출력
		//System.out.println("----------DB에 저장될  정보 출력----------");
		//for(int i=0;i<list2.at(0).length();i++) {  
		//	for(int j=0; j<list2.size(); j++) { 
		//		System.out.print(s[j][i]+" ");}
		//	System.out.println();}System.out.println("-----------------------------------");


		/*표준정규화를 거친 정보를 DB에 저장하기 위한 코드 */
		//sql) DB에 정보 삽입 >airportinfo Table
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

		/*표준정규화를 거친 DB+ 위도경도 정보까지 함께 저장하기 위한 코드 */
		conn.eval("reDf2<-NULL"); 
		conn.eval("for(i in 1:nrow(reDf)){" + 
				"  if(reDf[i,1]==reDf[i,2]){" + 
				"    reDf2 <-rbind(reDf2,reDf[i,1])" + 
				"  }else if(reDf[i,1]!=reDf[i,2]){" + 
				"    if(reDf[i,1]!=\"합계\"&reDf[i,1]!=\"합 계\"){" + 
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
		conn.eval("reDf2 <-cbind(reDf2,lon)");  //경도
		conn.eval("reDf2 <-cbind(reDf2,lat)");  //위도
		conn.eval("cnt <- reDf[-nrow(reDf),3]"); //방문자수
		conn.eval("reDf2 <-cbind(reDf2,cnt)");
		conn.eval("reDf2<-as.data.frame(reDf2)"); // 2차 결과물
		//확인용
		REXP result3 = conn.eval("reDf2");
		RList list3 = result3.asList();
		//System.out.println("ListSize="+list3.size()+
		//				   " / ListLength="+list3.at(0).length());	

		String[][] ss = new String[list3.size()][]; //가변배열로 작성
		for(int i=0; i<list3.size();i++) {
			ss[i] = list3.at(i).asStrings();
		}

		//sql) DB에 정보 삽입 > lonlatinfo Table
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
