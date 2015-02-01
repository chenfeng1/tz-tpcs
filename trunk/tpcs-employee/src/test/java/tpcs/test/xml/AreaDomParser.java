package tpcs.test.xml;

import com.tz.tpcs.entity.Area;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(xmlpath);
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
		area.setCode(areaElement.getAttribute("code"));
		//2.依次设置各个元素的文本值给省
		NodeList list = areaElement.getElementsByTagName("city");
		List<Area> children=new ArrayList<Area>();
		if (list != null && list.getLength() != 0) {
			for(int i=0;i<list.getLength();i++){
			//说明有city这个子元素,就创建City对象
			Area area1 = new Area();
			Element cityElement = (Element) list.item(i);
			String name = cityElement.getAttribute("name");
			String  code = cityElement.getAttribute("code");
			//设置值
			area1.setName(name);
			area1.setCode(code);
			children.add(area1);
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
