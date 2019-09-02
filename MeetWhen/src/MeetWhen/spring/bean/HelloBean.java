package MeetWhen.spring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import MeetWhen.spring.vo.LonlatVO;

@Controller
@RequestMapping("/Main/")
public class HelloBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("welcome.mw")  //ù ���� ȭ��
	public String welcome() {
		System.out.println("Welcome Runing..!");
		return "/Main/welcome";
	}
	
	@RequestMapping("header.mw") //���
	public String header() {
		return "/Main/header";
	}
	
	@RequestMapping("footer.mw") //Ǫ��
	public String footer() {
		return "/Main/footer";
	}
	/*--------------------------------�׽�Ʈ ��------*/
	

	//��� �� ��ư Ŭ���� ���� ī�װ�
	@RequestMapping("category.mw")
	public String category() {
		return "/Main/category";
	}

	//����
	@RequestMapping("map_total.mw")
	public String map_total(HttpServletRequest request) {
		List<LonlatVO> dataList = new ArrayList<LonlatVO>();

		int count = sql.selectOne("lonlat.getCnt"); //DB���ڵ� ����

		dataList = sql.selectList("lonlat.getAll"); //��� ���� ��������
		int listSize = dataList.size();//infoList�� ����
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
	
	
	//lonlatinfo DB���� ���
	@RequestMapping("test_data2.mw") //DB������ ����Ʈ �ۼ�.
	public String test_data2(HttpServletRequest request) {
		List<LonlatVO> infoList = new ArrayList<LonlatVO>();

		int count = sql.selectOne("lonlat.getCnt");
		System.out.println("DB���� ����="+count);

		infoList = sql.selectList("lonlat.getAll");

		request.setAttribute("infoList", infoList);
		return "/Main/test_data2";
	}

}