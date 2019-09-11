package com.klee.bean;

public class Student {

	private static String stuName;
	private static String stuNumber;
	private static String stuSex;
	private static String stuDepart; // 学院
	private static String stuSpec;// 专业
	private static String stuClass;// 班级
	private static float stuScore;
	private static String stuCource;

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		Student.stuName = stuName;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		Student.stuNumber = stuNumber;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		Student.stuSex = stuSex;
	}

	public String getStuDepart() {
		return stuDepart;
	}

	public void setStuDepart(String stuDepart) {
		Student.stuDepart = stuDepart;
	}

	public String getStuSpec() {
		return stuSpec;
	}

	public void setStuSpec(String stuSpec) {
		Student.stuSpec = stuSpec;
	}

	public String getStuClass() {
		return stuClass;
	}

	public void setStuClass(String stuClass) {
		Student.stuClass = stuClass;
	}

	public static float getStuScore() {
		return stuScore;
	}

	public static void setStuScore(float stuScore) {
		Student.stuScore = stuScore;
	}

	public static String getStuCource() {
		return stuCource;
	}

	public static void setStuCource(String stuCource) {
		Student.stuCource = stuCource;
	}
	
	

}
