package indi.dpl.dao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import indi.dpl.domain.User;

/**
*
*Description:Dao类，与数据库连接，负责查询用户和增加新的用户
*@author Karoy
*Start time:2018年9月7日
*Version:v0.1 2018年9月7日
*
*/

public class UserDao {
	private String path = "D:/idea/webtest/src/users.xml";
	
	public  User findByUsername(String username) {
		/*
		 * 本例使用xml作为数据库，使用dom4j解析xml文件
		 * 1.创建解析器
		 * 2.得到Document
		 * 3.通过xpath查询得到Element
		 * 4.校验Element
		 */
		
		//解析器
		SAXReader reader = new SAXReader();
		
		try {
			//获取Document
			Document doc = reader.read(path);
			//xpath查询得到Element
			Element ele=(Element)doc.selectSingleNode("//user[@username='" + username + "']");
			
			//判断ele是否为空，如果为空，则返回null，否则封装进一个User对象中返回
			if(ele == null) return null;
			else {
				User user = new User();
				user.setUsername(ele.attributeValue("username"));
				user.setPassword(ele.attributeValue("password"));
				return user;
			}
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void add(User user) {
		/*
		 * 向数据库中增加一条记录
		 * 1.得到Document
		 * 2.通过Document获取root元素
		 * 3.将user对象变为Element
		 * 4.将Element添加进xml数据库中
		 */
		
		//解析器
		SAXReader reader = new SAXReader();
		try {
			//获取Document
			Document doc = reader.read(path);
			//得到root元素
			Element root =doc.getRootElement();
			//向root中增加新的元素
			Element userEle = root.addElement("user");
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			
			/*
			 * 保存文档
			 * 1.创建格式
			 * 2.使用XMLWriter写文档
			 */
			OutputFormat format = new OutputFormat("\t",true);//两个参数为缩进符和是否换行
			format.setTrimText(true);//清除原有格式
			
			//创建XMLWriter
			XMLWriter writer;
			try {
				writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"),format);//使用字符流写入，设置编码格式
				writer.write(doc);
				writer.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
}
