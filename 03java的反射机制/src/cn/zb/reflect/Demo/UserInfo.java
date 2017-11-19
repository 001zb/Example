package cn.zb.reflect.Demo;

import java.io.Serializable;

public class UserInfo extends PrevClass implements Serializable{
private String name;
private int age;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public UserInfo(String name, int age) {
	super();
	this.name = name;
	this.age = age;
}
public UserInfo() {
	
}
@Override
public String toString() {
	return "UserInfo [name=" + name + ", age=" + age + "]";
}
public double pay(double money){
	System.out.println("Äú»¨ÁË"+money+"Ôª");
	return money;
};
}
