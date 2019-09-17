package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LContryVO;
import MeetWhen.spring.vo.LRegionVO;
/*������ ���õ� ��� �� */
@Controller
@RequestMapping("/Map/")
public class MapBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	//DB�������� ��ũ ������ ���_�ϴ���
		@RequestMapping("map_total.mw")
		public String map_total(HttpServletRequest request) {
			List<LContryVO> dataList = new ArrayList<LContryVO>();

			int count = sql.selectOne("lonlat.getCnt"); //DB���ڵ� ����

			dataList = sql.selectList("lonlat.getAll"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			String [][] total = new String[listSize][4];
			/*
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getL_conreg();
					total[i][1] = Double.toString(dataList.get(i).getL_lon());
					total[i][2] = Double.toString(dataList.get(i).getL_lat());
					total[i][3] = Integer.toString(dataList.get(i).getL_cnt());
			}
			*/
			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);	

			return "/Map/map_total";
		}
		
		//��� Map ������
		@RequestMapping("map1_all.mw")
		public String map1_all(HttpServletRequest request) {			
			List<LContryVO> dataList = new ArrayList<LContryVO>();
			dataList = sql.selectList("latlon.getLContry"); 
			int listSize = dataList.size();
			
			String [][] total = new String[listSize][4];
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLc_con();
					total[i][1] = Double.toString(dataList.get(i).getLc_lon());
					total[i][2] = Double.toString(dataList.get(i).getLc_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLc_cnt());
			}
			
			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map1_all";
		}
		
		@RequestMapping("map2_eur.mw")
		public String map2_eur(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}

			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map2_eur";
		}
		
		@RequestMapping("map3_af.mw")
		public String map3_af(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}
			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map3_af";
		}
		@RequestMapping("map4_mid.mw")
		public String map4_mid(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}

			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map4_mid";
		}
		@RequestMapping("map5_asia.mw")
		public String map5_asia(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}

			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map5_asia";
		}
		@RequestMapping("map6_oce.mw")
		public String map6_oce(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}

			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map6_oce";
		}
		@RequestMapping("map7_na.mw")
		public String map7_na(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}

			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map7_na";
		}
		@RequestMapping("map8_sa.mw")
		public String map8_sa(HttpServletRequest request) {			
			List<LRegionVO> dataList = new ArrayList<LRegionVO>();
			dataList = sql.selectList("latlon.getLRegion"); //��� ���� ��������
			int listSize = dataList.size();//infoList�� ����
			
			String [][] total = new String[listSize][4];	
			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getLr_reg();
					total[i][1] = Double.toString(dataList.get(i).getLr_lon());
					total[i][2] = Double.toString(dataList.get(i).getLr_lat());
					total[i][3] = Integer.toString(dataList.get(i).getLr_cnt());
			}

			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);
			return "/Map/map8_sa";
		}
	

}
