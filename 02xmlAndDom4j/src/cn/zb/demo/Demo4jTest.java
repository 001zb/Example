package cn.zb.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo4jTest {
	static Document doc=null;
	//test
	public static void main(String[] args) {
		SAXReader sax=new SAXReader();
		try {
			doc = sax.read("src/cn/zb/demo/Student.xml");
			//add();
			//remove("赵六");
			//selectAll();
			update("张三","49");
			saveXML("src/cn/zb/demo/Student.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	//转换为xml文档
	public static void saveXML(String path){
		OutputFormat format=OutputFormat.createPrettyPrint();
		//转换输出编码格式
		format.setEncoding("utf-8");
		try {
			XMLWriter writer=new XMLWriter(new FileWriter(path),format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//向students.xml添加数据
	public static void add(){
		Element root = doc.getRootElement();
		Element addElement = root.addElement("Student");
		addElement.addAttribute("id", "stu04");
		Element addElement2 = addElement.addElement("name");
		addElement2.addText("赵六");
		Element addElement3 = addElement.addElement("age");
		addElement3.addText("13");
		System.out.println("添加成功！");
	}
	//删除stutdents.xml中的数据
	public static void remove(String name){
		Element root = doc.getRootElement();
		Iterator<Element> ments = root.elementIterator();
		while(ments.hasNext()){
			/*if(name.contentEquals(ments.next().attributeValue("id"))){
				System.out.println("删除成功！");
			}else{
				System.out.println("删除失败！");
			};*/
			Element next2 = ments.next();
			Iterator<Element> ments2 = next2.elementIterator();
			while(ments2.hasNext()){
				Element next = ments2.next();
				if(next.getName().equals("name")){
					if(name.equals(next.getText())){
						if(next.getParent().getParent().remove(next.getParent())){
							System.out.println("删除成功！");
						}else{
							System.out.println("删除失败！");
						}
				}
				}
				}
		};
	};
	//删除stutdents.xml中的数据
	public static void selectAll(){
		Element root = doc.getRootElement();
		Iterator<Element> ments = root.elementIterator();
		System.out.print("学生编号\t");
		System.out.print("学生姓名\t");
		System.out.print("学生年龄\t");
		System.out.println();
		while(ments.hasNext()){
			Element next2 = ments.next();
			Iterator<Element> ments2 =next2 .elementIterator();
			System.out.print(next2.attributeValue("id")+"\t");
			while(ments2.hasNext()){
				Element next = ments2.next();
				System.out.print(next.getText()+"\t");
			}
			System.out.println();
		}
	}
	//更改Student的年龄
	public static void update(String name,String newAge){
		Element rootElement = doc.getRootElement();
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while(elementIterator.hasNext()){
			Element next = elementIterator.next();
			Iterator<Element> elementIterator2 = next.elementIterator();
			while(elementIterator2.hasNext()){
				Element next2 = elementIterator2.next();
				if(next2.getName().equals("name")){
						Iterator<Element> elementIterator3 = next2.getParent().elementIterator();
						while(elementIterator3.hasNext()){
							Element next3 = elementIterator3.next();
							if((next3.getName()).equals("age")){
								next3.setText(newAge);
								System.out.println("修改成功！");
								return;
							}
						}
					};
				}
		}
	};
	
}
