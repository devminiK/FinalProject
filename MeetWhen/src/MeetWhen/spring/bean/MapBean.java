package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LonlatVO;
/*지도에 관련된 모든 것 */
@Controller
@RequestMapping("/Map/")
public class MapBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	//DB내용으로 마크 여러개 찍기_하는중
		@RequestMapping("map_total.mw")
		public String map_total(HttpServletRequest request) {
			List<LonlatVO> dataList = new ArrayList<LonlatVO>();

			int count = sql.selectOne("lonlat.getCnt"); //DB레코드 갯수

			dataList = sql.selectList("lonlat.getAll"); //모든 정보 가져오기
			int listSize = dataList.size();//infoList의 길이
			String [][] total = new String[listSize][4];

			for(int i=0;i<listSize;i++) {
					total[i][0] = dataList.get(i).getL_conreg();
					total[i][1] = Double.toString(dataList.get(i).getL_lon());
					total[i][2] = Double.toString(dataList.get(i).getL_lat());
					total[i][3] = Integer.toString(dataList.get(i).getL_cnt());
			}
			
			request.setAttribute("total", total);	
			request.setAttribute("listSize", listSize);	

			return "/Map/map_total";
		}
	

}
