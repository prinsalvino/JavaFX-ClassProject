package javaFx;

import static javax.swing.JOptionPane.showMessageDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class userQuery {
	
	ObservableList<Person> persons = FXCollections.observableArrayList();
	
	public ObservableList<Teacher> getTeacher() {
		ObservableList<Teacher> teachers = FXCollections.observableArrayList();
		for(Person person : persons) 
		{
			if (person instanceof Teacher) {
				teachers.add((Teacher) person);
			}		
		}
		
		return teachers;
	}
	
	public ObservableList<Student> getStudent() {
		ObservableList<Student> students = FXCollections.observableArrayList();
		for(Person person : persons) 
		{
			if (person instanceof Student) {
				students.add((Student) person);
			}		
		}
		
		return students;
	}
	public Student getStudent(String email) {
		Student student = null;
		for(Person person : persons) 
		{
			if (person.getEmail().trim().equals(email)) {
				student = (Student) person;
				break;
			}	
		}
		return student;
	}
	public void addPerson(Person person) {
		try {
			persons.add(person);
		} catch (Exception e) {
			showMessageDialog(null, e);
		}
	}
	
	public ObservableList<Person> getPersons() {
		return persons;
	}
	
	public void setGrade(String email, String coursename, int grade) {
		coursename = coursename.toLowerCase();
		Student student = getStudent(email);
		
		if (student != null) {
			if (coursename == "java") {
				student.javaGrade = grade;
			}
			else if (coursename == "csharp") {
				student.csharpGrade = grade;
			}
		} 
		else {
			showMessageDialog(null, "Student is null");
		}
	}
	
	public void modifyTeacher(String oldemail, String newemail, String pass, String firstn, String lastn, int salary) {
		try {
			for(Person person : persons) 
			{
				if (person instanceof Teacher) {
					if (person.getEmail().trim().equals(oldemail)) {
						person.setEmail(newemail);
						person.setFirstName(firstn);
						person.setLastName(lastn);
						person.setPassword(pass);
						((Teacher) person).setSalary(salary);
						
					}
				}		
			}
		} catch (Exception e) {
			showMessageDialog(null, e);
		}
		
	}

}
