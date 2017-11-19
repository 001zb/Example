package cn.zb.reflect.Demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class TestReflect {
	
	public static void main(String[] args) throws Exception {
		//获取对象的三种方法
		//实例化classNo.1
		Class forName = Class.forName("cn.zb.reflect.Demo.UserInfo");
		//+==========================================
		System.out.println("No.1包++++++++++++++++++++++++++");
		getPack(forName);
		System.out.println("No.2类++++++++++++++++++++++++++");
		getClassName(forName);
		System.out.println("No.3接口++++++++++++++++++++++++++");
		getInterface(forName);
		System.out.println("No.4父类++++++++++++++++++++++++++");
		getSuper(forName);
		System.out.println("No.5字段++++++++++++++++++++++++++");
		getField(forName);
		System.out.println("No.6构造方法++++++++++++++++++++++++++");
		getConstructor(forName);
		System.out.println("No.7方法++++++++++++++++++++++++++");
		getMethod(forName);
		System.out.println("No.8注解++++++++++++++++++++++++++");
		getAnnotation(forName);
		System.out.println("No.8实例化对象++++++++++++++++++++++++++");
		UserInfo instance = getInstance(forName);
		instance.setAge(17);
		instance.setName("小何");
		System.out.println("姓名:"+instance.getName());
		System.out.println("年龄:"+instance.getAge());
		//实例化classNo.2
		Class clazz=UserInfo.class;
		//实例化classNo.3
		Class class1=new UserInfo().getClass();
	}
	//No.1获得包名
	public static void getPack(Class clazz){
		System.out.println(clazz.getSimpleName()+"类的包名是："+clazz.getPackage());
	}
	//No.2获得类名
	public static void getClassName(Class clazz){
		System.out.println(clazz.getSimpleName()+"类的全类名："+clazz.getName());
		System.out.println(clazz.getSimpleName()+"类的类名："+clazz.getSimpleName());
	}
	//No.3获得接口
	public static void getInterface(Class clazz){
		Class[] interfaces = clazz.getInterfaces();
		for (Class class1 : interfaces) {
			System.out.println(clazz.getSimpleName()+"类实现的接口："+class1);
		}
	}
	//No.4获得父类
	public static void getSuper(Class clazz){
		System.out.println(clazz.getSimpleName()+"类的父类："+clazz.getSuperclass().getSimpleName());
	}
	//No.5获得字段
	public static void getField(Class clazz) throws Exception{
		//getfields不可以获得私有字段，但可以获得父类中的字段
		System.out.println("指定字段名获得"+clazz.getSimpleName()+"类中的字段类型："+clazz.getField("realName").getType());
		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println("显示"+clazz.getSimpleName()+"类中的第"+(i+1)+"个字段是："+fields[i].getName());
		}
		//getDeclaredFields可以获得所有字段，但不可以获得父类中的字段
		Field[] declaredFields = clazz.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			System.out.println("显示"+clazz.getSimpleName()+"类中的第"+(i+1)+"个字段是："+declaredFields[i].getName());
		}
	}
	//No.6获得构造方法
	public static void getConstructor(Class clazz) throws Exception{
		//获得所有构造参数
		Constructor[] constructors = clazz.getConstructors();
		for (int i = 0; i < constructors.length; i++) {
			System.out.println("构造方法的方法名"+constructors[i].getName());
			Parameter[] parameters = constructors[i].getParameters();
			for (int j = 0; j < parameters.length; j++) {
				System.out.println("构造方法的参数类型"+parameters[j].getParameterizedType());
				System.out.println("构造方法的参数名"+parameters[j].getName());
			}
		}
		//获得指定参数
		Constructor constructor = clazz.getConstructor(String.class,Integer.TYPE);
		System.out.println("指定构造方法的参数名获得的方法名称"+constructor.getName());
		Parameter[] parameters = constructor.getParameters();
		for (int j = 0; j < parameters.length; j++) {
			System.out.println("构造方法的参数类型"+parameters[j].getType());
			System.out.println("构造方法的参数名"+parameters[j].getName());
		}
	}
	//No.7获得方法
	public static void getMethod(Class clazz){
		//getDeclaredMethods不可以获得父类中的方法
		//getMethods可以获得父类中的方法
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println("方法名："+method.getName());
			System.out.println("方法返回类型："+method.getReturnType());
			Parameter[] parameters = method.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println("方法参数类型："+parameter.getType().getName());
				System.out.println("方法参数名："+parameter.getName());
			}
			System.out.println("方法访问修饰符："+Modifier.toString(method.getModifiers()));
		}
	}
	//No.8注解
	public static void getAnnotation(Class clazz) throws NoSuchMethodException, SecurityException{
		Method method = clazz.getDeclaredMethod("toString");
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation2 : annotations) {
			System.out.println("注解名称"+annotation2.toString());
		}
		
	};
	//No.9创建对象
	public static UserInfo getInstance(Class clazz) throws InstantiationException, IllegalAccessException{
		Object obj = clazz.newInstance();
		return (UserInfo)obj;
	};
}
