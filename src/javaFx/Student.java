package javaFx;

import java.text.ParseException;

import javaFx.AccessType;

public class Student extends Person {
	String classStudent;
	int javaGrade;
	int csharpGrade;
	public Student(String firstName, String lastName, String classStudent, String password, String email,int javaGrade,int csharpGrade, String birthDate) throws ParseException {
		super(firstName,lastName, password, email, AccessType.BASIC, birthDate);
		this.classStudent = classStudent;
		this.javaGrade = javaGrade;
		this.csharpGrade = csharpGrade;
	}
	
	public Student(String firstName, String lastName, String classStudent, String password, String email, String birthDate) throws ParseException {
		super(firstName,lastName, password, email, AccessType.BASIC, birthDate);
		this.classStudent = classStudent;
	}

	public String getClassStudent() {
		return classStudent;
	}
	
	public void setClassStudent(String classStudent) {
		this.classStudent = classStudent;
	}

	public int getCsharpGrade() {
		return csharpGrade;
	}
	public void setCsharpGrade(int csharpGrade) {
		this.csharpGrade = csharpGrade;
	}
	public int getJavaGrade() {
		return javaGrade;
	}
	public void setJavaGrade(int javaGrade) {
		this.javaGrade = javaGrade;
	}
	

}
