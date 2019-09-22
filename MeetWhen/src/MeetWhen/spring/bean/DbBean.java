package MeetWhen.spring.bean;

/*
 * DB1~DB4 정보저장, 포멧, 정보 보여주기 기능 
 * PLUS)
 * 	파일 경로 >>> 학원은 D:  집은 C:
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
@RequestMapping("/Db/")
public class DbBean {
	@Autowired
	private SqlSessionTemplate sql = null;

	@RequestMapping("dbControl.mw")	
	public String dbControl(){
		System.out.println("[관리자 접근 가능 PAGE]");
		//관리자일경우만 확인할 수 있는 page로 설정하기
		return "/Db/dbControl";
	}

	@RequestMapping("dbCreate.mw")//--------------------------------------------------DB정보 생성
	public String dbCreate(HttpServletRequest request, int num) throws Exception{  
		System.out.println("[dbCreate PAGE]");

		boolean rs=true;
		int cnt=0;  //DB정보 갯수 담을 변수
		String msg="";

		while(rs) {
			switch(num) {
			case 1:
				cnt=sql.selectOne("airport.cntContry");
				break;
			case 2:
				cnt=sql.selectOne("latlon.cntLContry");
				break;
			case 3:
				cnt=sql.selectOne("airport.cntRegion");
				break;
			case 4:
				cnt=sql.selectOne("latlon.cntLRegion");
				break;
			}

			if(cnt!=0) { //정보 이미 존재, 생성필요x
				msg="DB에 이미 정보가 존재합니다. 재 생성하지 않겠습니다.";
				rs=false;
			}else{		//정보 생성해야함
				RConnection conn = new RConnection();
				//경로 재 설정 및 라이브러리 설치,추가
				conn.eval("setwd('C:/R-workspace')");
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
					conn.eval("allAir <- read.xlsx2('C:/R-workspace/Airport/7월_전체지역별통계.xlsx',1, stringsAsFactors=F)");
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
					conn.eval("InchAir <- read.xlsx2('C:/R-workspace/Airport/7월_인천지역별통계.xlsx',1, stringsAsFactors=F)");
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
				case 3: System.out.print("CASE 3 & 4"); //인천통계 엑셀 정보 가져오기-> 데이터프레임 작성
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
					//(추가)일본일 경우 지역명 정보에 국가명을 덧붙여야 "오이다"지역에서 Na값이 발생하지 않음
					conn.eval("for(i in 1:nrow(reg1)){" + 
							"  if(reg1$국가명[i]=='일본')" + 
							"    reg1$도시명[i]<-c(paste(reg1$국가명[i],reg1$도시명[i]))" + 
							"}");
					conn.eval("reg1<-reg1[,-1]");
					conn.eval("colnames(reg1)<-c('국가명','여객')"); //reg1 작성 완료

					conn.eval("reg2<-NULL;temp<-con2");
					conn.eval("for(i in 1:nrow(temp)){ " + 
							"  j<-0;" + 
							"  while(j<nrow(con1)){" + 
							"    j<-j+1;" + 
							"    if(temp$국가명[i]==con1$국가명[j]){" + 
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
					break;
				}
				//구체적 DB작성하는 곳
				if(num==1) {//DB1작성[Contry]		
					DB = conn.eval("DB1");
					list = DB.asList();
					arr = new String[list.size()][];//가변배열로 작성
					for(i=0;i<list.size();i++) {
						arr[i]=list.at(i).asStrings();
					}		
					//시퀀스 새로생성.
					for(i=0; i<list.at(0).length();i++) {
						ContryVO vo = new ContryVO();
						int seqNum =sql.selectOne("airport.getContrySeqNum"); 
						
						vo.setC_num(seqNum);
						vo.setC_con(arr[0][i]);
						vo.setC_cnt(Integer.parseInt(arr[1][i]));
						sql.insert("airport.insertContry",vo);	
					}
					rs=false;
					msg="DB1에 공항통계 정보가 생성 완료되었습니다.";

				}else if(num==2) {//DB2작성[Lcontry]		
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
						int seqNum =sql.selectOne("latlon.getLContrySeqNum"); 
						
						vo.setLc_num(seqNum);
						vo.setLc_con(arr[0][i]);
						vo.setLc_cnt(Integer.parseInt(arr[1][i]));
						vo.setLc_lat(Double.parseDouble(arr[2][i]));
						vo.setLc_lon(Double.parseDouble(arr[3][i]));
						sql.insert("latlon.insertLContry",vo);	
					}
					rs=false;
					msg="DB2에 공항통계 정보가 생성 완료되었습니다.";

				}else if(num==3) {//DB3작성 [Region]
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
						int seqNum =sql.selectOne("airport.getRegionSeqNum"); 
						
						vo.setR_num(seqNum);
						vo.setR_reg(arr[0][i]);
						vo.setR_cnt(Integer.parseInt(arr[1][i]));
						sql.insert("airport.insertRegion",vo);	
					}
					rs=false;
					msg="DB3에 공항통계 정보가 생성 완료되었습니다.";

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
						int seqNum =sql.selectOne("latlon.getLRegionSeqNum"); 
						
						vo.setLr_num(seqNum);
						vo.setLr_reg(arr[0][i]);
						vo.setLr_cnt(Integer.parseInt(arr[1][i]));
						vo.setLr_lat(Double.parseDouble(arr[2][i]));
						vo.setLr_lon(Double.parseDouble(arr[3][i]));
						sql.insert("latlon.insertLRegion",vo);	
					}
					rs=false;
					msg="DB4에 공항통계 정보가 생성 완료되었습니다.";
				}
				System.out.println("->DB"+num+"내용 생성 ");
			}	
		}		
		request.setAttribute("msg", msg);
		return "/Db/dbCreate";
	}


	@RequestMapping("dbDelete.mw")//-------------------------------------------DB정보 삭제
	public String dbDelete(HttpServletRequest request, int num) {
		System.out.print("[DB Delete PAGE]");
		switch(num) {
		case 1:
			sql.delete("airport.deleContry");
			break;
		case 2:
			sql.delete("latlon.deleLContry");
			break;
		case 3:
			sql.delete("airport.deleRegion");
			break;
		case 4:
			sql.delete("latlon.deleLRegion");
			break;
		}
		System.out.println("->DB"+num+"FORMAT 완료");

		request.setAttribute("num", num);
		return "/Db/dbDelete";
	}

	@RequestMapping("dbInfoCheck.mw")//-------------------------------------------DB정보 확인
	public String dbInfoCheck(HttpServletRequest request, int num) throws Exception{
		System.out.print("[DB InfoCheck PAGE]");

		List rsList = new ArrayList(); //모든 vo를 담기위해 제네릭사용x
		int siz=0;

		switch(num) {
		case 1:
			rsList = sql.selectList("airport.getContry");
			break;			
		case 2:
			rsList = sql.selectList("latlon.getLContry");
			break;
		case 3:
			rsList = sql.selectList("airport.getRegion");
			break;
		case 4:
			rsList = sql.selectList("latlon.getLRegion");
			break;
		}
		siz = rsList.size();
		System.out.println("->DB"+num+"내용 확인");
		
		request.setAttribute("num", num);
		request.setAttribute("listSize", siz);
		request.setAttribute("dataList", rsList);
		return "/Db/dbInfoCheck";
	}



}
