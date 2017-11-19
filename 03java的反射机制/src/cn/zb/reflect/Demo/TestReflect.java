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
		//��ȡ��������ַ���
		//ʵ����classNo.1
		Class forName = Class.forName("cn.zb.reflect.Demo.UserInfo");
		//+==========================================
		System.out.println("No.1��++++++++++++++++++++++++++");
		getPack(forName);
		System.out.println("No.2��++++++++++++++++++++++++++");
		getClassName(forName);
		System.out.println("No.3�ӿ�++++++++++++++++++++++++++");
		getInterface(forName);
		System.out.println("No.4����++++++++++++++++++++++++++");
		getSuper(forName);
		System.out.println("No.5�ֶ�++++++++++++++++++++++++++");
		getField(forName);
		System.out.println("No.6���췽��++++++++++++++++++++++++++");
		getConstructor(forName);
		System.out.println("No.7����++++++++++++++++++++++++++");
		getMethod(forName);
		System.out.println("No.8ע��++++++++++++++++++++++++++");
		getAnnotation(forName);
		System.out.println("No.8ʵ��������++++++++++++++++++++++++++");
		UserInfo instance = getInstance(forName);
		instance.setAge(17);
		instance.setName("С��");
		System.out.println("����:"+instance.getName());
		System.out.println("����:"+instance.getAge());
		//ʵ����classNo.2
		Class clazz=UserInfo.class;
		//ʵ����classNo.3
		Class class1=new UserInfo().getClass();
	}
	//No.1��ð���
	public static void getPack(Class clazz){
		System.out.println(clazz.getSimpleName()+"��İ����ǣ�"+clazz.getPackage());
	}
	//No.2�������
	public static void getClassName(Class clazz){
		System.out.println(clazz.getSimpleName()+"���ȫ������"+clazz.getName());
		System.out.println(clazz.getSimpleName()+"���������"+clazz.getSimpleName());
	}
	//No.3��ýӿ�
	public static void getInterface(Class clazz){
		Class[] interfaces = clazz.getInterfaces();
		for (Class class1 : interfaces) {
			System.out.println(clazz.getSimpleName()+"��ʵ�ֵĽӿڣ�"+class1);
		}
	}
	//No.4��ø���
	public static void getSuper(Class clazz){
		System.out.println(clazz.getSimpleName()+"��ĸ��ࣺ"+clazz.getSuperclass().getSimpleName());
	}
	//No.5����ֶ�
	public static void getField(Class clazz) throws Exception{
		//getfields�����Ի��˽���ֶΣ������Ի�ø����е��ֶ�
		System.out.println("ָ���ֶ������"+clazz.getSimpleName()+"���е��ֶ����ͣ�"+clazz.getField("realName").getType());
		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println("��ʾ"+clazz.getSimpleName()+"���еĵ�"+(i+1)+"���ֶ��ǣ�"+fields[i].getName());
		}
		//getDeclaredFields���Ի�������ֶΣ��������Ի�ø����е��ֶ�
		Field[] declaredFields = clazz.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			System.out.println("��ʾ"+clazz.getSimpleName()+"���еĵ�"+(i+1)+"���ֶ��ǣ�"+declaredFields[i].getName());
		}
	}
	//No.6��ù��췽��
	public static void getConstructor(Class clazz) throws Exception{
		//������й������
		Constructor[] constructors = clazz.getConstructors();
		for (int i = 0; i < constructors.length; i++) {
			System.out.println("���췽���ķ�����"+constructors[i].getName());
			Parameter[] parameters = constructors[i].getParameters();
			for (int j = 0; j < parameters.length; j++) {
				System.out.println("���췽���Ĳ�������"+parameters[j].getParameterizedType());
				System.out.println("���췽���Ĳ�����"+parameters[j].getName());
			}
		}
		//���ָ������
		Constructor constructor = clazz.getConstructor(String.class,Integer.TYPE);
		System.out.println("ָ�����췽���Ĳ�������õķ�������"+constructor.getName());
		Parameter[] parameters = constructor.getParameters();
		for (int j = 0; j < parameters.length; j++) {
			System.out.println("���췽���Ĳ�������"+parameters[j].getType());
			System.out.println("���췽���Ĳ�����"+parameters[j].getName());
		}
	}
	//No.7��÷���
	public static void getMethod(Class clazz){
		//getDeclaredMethods�����Ի�ø����еķ���
		//getMethods���Ի�ø����еķ���
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println("��������"+method.getName());
			System.out.println("�����������ͣ�"+method.getReturnType());
			Parameter[] parameters = method.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println("�����������ͣ�"+parameter.getType().getName());
				System.out.println("������������"+parameter.getName());
			}
			System.out.println("�����������η���"+Modifier.toString(method.getModifiers()));
		}
	}
	//No.8ע��
	public static void getAnnotation(Class clazz) throws NoSuchMethodException, SecurityException{
		Method method = clazz.getDeclaredMethod("toString");
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation2 : annotations) {
			System.out.println("ע������"+annotation2.toString());
		}
		
	};
	//No.9��������
	public static UserInfo getInstance(Class clazz) throws InstantiationException, IllegalAccessException{
		Object obj = clazz.newInstance();
		return (UserInfo)obj;
	};
}
