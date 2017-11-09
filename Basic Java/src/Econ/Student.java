package Econ;

import java.util.ArrayList;

public class Student {
	private String name;
	private ArrayList<String> qualities;
	public Student(String name){
		qualities = new ArrayList<String>();
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void addQuality(String q){
		qualities.add(q);
	}
	public void setName(String name){
		this.name = name;
	}
	public ArrayList<String> getQualities(){
		return qualities;
	}
	public String toString(){
		return name;
	}
	
}
