package javaFx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;


public class managerView extends userView{


	public managerView(userQuery query, Stage window) {
		super(query, window);
		displayHomeScene();
		TableColumn<Teacher,String> salarycolumn = new TableColumn<>("Salary");
		salarycolumn.setMinWidth(200);
		salarycolumn.setCellValueFactory(new  PropertyValueFactory<>("salary"));	
		
		teacherView.getColumns().add(salarycolumn);

		

		Label uname = new Label("Teacher Email");
		TextField emailinput = new TextField();
		
		Label password = new Label("Teacher Password");
		TextField passinput = new TextField();
		
		Label fnamelbl = new Label("Teacher First Name");	
		TextField firstnameInput = new TextField();
		
		Label lnamelbl = new Label("Teacher Last Name");
		TextField lastNameInput = new TextField();
		
		Label salarylbl = new Label("Teacher Salary");
		TextField salaryInput = new TextField();
		
		Label bdate = new Label("Teacher Birthdate");
		TextField bdatei = new TextField();
		bdatei.setPromptText("yyyy/MM/dd");
		
		
		Button addbtn = new Button("Add Teacher");	
		addbtn.setOnAction(e -> addTeacher(emailinput.getText(), passinput.getText(), firstnameInput.getText(),lastNameInput.getText(),salaryInput.getText(), bdatei.getText()));
		
		Button updateTeacher = new Button("Update Teacher Information");	
		updateTeacher.setOnAction(e -> updateTeacherForm());
		
		Button makenotepad = new Button("Make Notepad");	
		makenotepad.setOnAction(e -> {
			try {
				createDocument();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		
		
		super.vBox.getChildren().addAll(uname,emailinput,password,passinput,fnamelbl,firstnameInput,lnamelbl,lastNameInput,salarylbl,salaryInput, bdate, bdatei
			,addbtn,  updateTeacher, makenotepad);
	
	}
	
	private void addTeacher(String email, String pass, String fname, String lname, String ssalary, String birtDate) {
		try {
			int salary = 0;
			if (ssalary == "") {
				salary = 0;
			} else {
				salary = Integer.parseInt(ssalary);
			}
			query.addPerson(new Teacher(fname, lname, pass, email, salary, birtDate));
			super.FillTeacherTable();
		} 
		catch (Exception e) {
			showMessageDialog(null, e);
			
		}
	}

	void updateTeacherForm() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25,25,25,25));
		grid.setHgap(10);
		grid.setVgap(10);
		
		//Name label
		Label nameLabel = new Label("Old Email");
		//column,row
		grid.add(nameLabel,0,0);
		
		//Name Input
		TextField oldemailInput = new TextField();
		grid.add(oldemailInput,1,0);
		
		//Label Password
		Label pw = new Label("New Email");
		grid.add(pw, 0, 1);

		//Password Input
		TextField newemailinput = new TextField();
		grid.add(newemailinput, 1, 1);
		
		Label password = new Label("Teacher Password");
		grid.add(password, 0, 2);
		
		TextField passinput = new TextField();
		grid.add(passinput, 1, 2);

		
		Label fnamelbl = new Label("Teacher First Name");	
		grid.add(fnamelbl, 0, 3);

		
		TextField firstnameInput = new TextField();
		grid.add(firstnameInput, 1, 3);

		Label lnamelbl = new Label("Teacher Last Name");
		grid.add(lnamelbl, 0, 4);

		
		TextField lastNameInput = new TextField();
		grid.add(lastNameInput, 1, 4);

		Label salarylbl = new Label("Teacher Salary");
		grid.add(salarylbl, 0, 5);

		
		TextField salaryInput = new TextField();
		grid.add(salaryInput, 1, 5);

		Button updatebtn = new Button("Update");
		grid.add(updatebtn, 1, 6);
		updatebtn.setOnAction(e ->updateTeacher(oldemailInput.getText(),newemailinput.getText(), passinput.getText(),firstnameInput.getText(),lastNameInput.getText(),salaryInput.getText()));
		
		Scene sceneLogin = new Scene(grid, 500, 400);
		window.setScene(sceneLogin);
	}

	private void updateTeacher(String oldemail, String newemail, String pass, String firstn, String lastn, String ssalary) {
		try {
			int salary = 0;
			if (ssalary == null) {
				salary = 0;
			} else {
				salary = Integer.parseInt(ssalary);
			}
			
			query.modifyTeacher(oldemail,newemail,pass, firstn, lastn, salary);
			new managerView(query, window);
		} catch (Exception e) {
			showMessageDialog(null, e);
		}
	}
	
	void createDocument() throws IOException  {        
		try {
			PrintWriter out = new PrintWriter("report.txt");
			ObservableList<Person> persons = query.getPersons();
			for (Person person : persons) {
				out.println("Name: " + person.getFirstName() + " " + person.getLastName());
				out.println("Email: " + person.getEmail());
				out.println("Birth Date:" + person.getBirthDate());
				out.println("Age: " + person.getAge());
				out.println("Type: " + person.getType());
				if (person instanceof Student ) {
					out.println("C#: " + ((Student) person).getCsharpGrade());
					out.println("Java: " + ((Student) person).getJavaGrade());

				}
				else if (person instanceof Teacher) {
					out.println("Salary: " +  ((Teacher) person).getSalary());
				}
				out.println("");
			}
			out.close();
			showMessageDialog(null, "Notepad created in your Java Document");
		} catch (Exception e) {
			showMessageDialog(null, e);

		}
	


	}


	

}
