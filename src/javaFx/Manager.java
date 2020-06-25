package javaFx;

import java.text.ParseException;

public class Manager extends Person{

	public Manager(String firstName, String lastName, String password,String email, String birthDate) throws ParseException {
		super(firstName, lastName, password,email,AccessType.ADMIN, birthDate);
	}
	
}