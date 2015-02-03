package tpcs.test.xml;

import com.tz.tpcs.entity.Area;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 描述 本类用来演示通过DOM解析XML文档
 * 时间 Dec 5, 2013 
 * 作者 JSD1308
 */
public class AreaDomParser {
	//用来保存地区的集合
		private List<Area> areas = new ArrayList<Area>();

		/**
		 * 采用DOM解析XML文档放入集合中
		 */
		public List<Area> getAreaFromXML(String xmlpath) {
			//1.获取DOM解析工厂
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				//2.通过工厂获取DOM解析器
				DocumentBuilder builder = factory.newDocumentBuilder();
				//3.调用parse()方法来产生文档树
				//3.1获取XML的文档输入流
				InputStream in = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(xmlpath);
//				InputStream in = new FileInputStream(xmlpath);
				Document doc = builder.parse(in);
				//处理文档树
				processDoc(doc);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return areas;
		}

		/**
		 * 处理文档树
		 */
		public void processDoc(Document doc) {
			//1.获取根节点
			Element root = doc.getDocumentElement();
			//2.获取所有province子节点
			NodeList list = root.getElementsByTagName("province");
			//3.迭代
			for (int i = 0; i < list.getLength(); i++) {
				Element areaElement = (Element) list.item(i);
				//4.处理province元素节点,一个province节点对于一个province对象
				Area area = buildArea(areaElement);
				//5.把省加入到集合中
				areas.add(area);
			}
		}

		/**
		 * 把Area元素转换成Area对象
		 */
		public Area buildArea(Element areaElement) {
			//创建省对象
			Area area = new Area();
			//1.设置属性值
			area.setName(areaElement.getAttribute("name"));
			area.setZipCode(areaElement.getAttribute("zipCode"));
			area.setDivisionCode(areaElement.getAttribute("divisionCode"));
			//2.依次设置各个元素的文本值给省
			NodeList list = areaElement.getElementsByTagName("city");
			List<Area> children=new ArrayList<Area>();
			if (list != null && list.getLength() != 0) {
				for(int i=0;i<list.getLength();i++){
					//说明有city这个子元素,就创建City对象
					Area area1 = new Area();
					Element cityElement = (Element) list.item(i);
					//设置值
					area1.setName(cityElement.getAttribute("name"));
					area1.setZipCode(cityElement.getAttribute("zipCode"));
					area1.setDivisionCode(cityElement.getAttribute("divisionCode"));
					children.add(area1);
					//获取所有area的元素，继续遍历
					NodeList lastlist = cityElement.getElementsByTagName("area"); 
					List<Area> lastchildren=new ArrayList<Area>();
					if(lastlist != null && lastlist.getLength() !=0){
						for(int j=0;j<lastlist.getLength();j++){
							//说明有area子元素，创建area对象
							Area area2 = new Area();
							Element lastElement = (Element) lastlist.item(j);
							area2.setName(lastElement.getAttribute("name"));
							area2.setZipCode(lastElement.getAttribute("zipCode"));
							area2.setDivisionCode(lastElement.getAttribute("divisionCode"));
							lastchildren.add(area2);
						}
						area1.setChildren(lastchildren);
					}
				}
				//把city设置给province
				area.setChildren(children);
			}
			return area;
		}

		/**
		 * 把元素值赋值给相应的变量
		 */
		public String getTextValue(Element provinceElement, String tagName) {
			Element temp = (Element) provinceElement.getElementsByTagName(tagName)
					.item(0);
			return temp.getTextContent();
		}
	
}
