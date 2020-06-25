package javaFx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;


public abstract class Person {
	 private String firstName;
	 private String lastName;
	 private String password;
	 private String email;
	 private String birthDate;
	 private int age;
	 AccessType type;
	
	public Person(String firstName, String lastName, String password, String email, AccessType type, String birthDate) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.type = type;
		this.birthDate = birthDate;
		calculateAge();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public String setEmail(String email) {
		return this.email = email;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public int getAge() {
		return age;
	}
	
	public AccessType getType() {
		return type;
	}
	void calculateAge() throws ParseException {
		String s = birthDate;
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		  java.util.Date d = sdf.parse(s);
		  Calendar c = Calendar.getInstance();
		  c.setTime(d);
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  int date = c.get(Calendar.DATE);
		  LocalDate l1 = LocalDate.of(year, month, date);
		  LocalDate now1 = LocalDate.now();
		  Period diff1 = Period.between(l1, now1);
		  age = diff1.getYears();
	}
}
