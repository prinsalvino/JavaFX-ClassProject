package javaFx;

import static javax.swing.JOptionPane.showMessageDialog;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class teacherView extends userView {

	TextField csInput;
	TextField javaInput ;
	public teacherView(Stage window, userQuery query) {
		super(query,window);
		super.displayHomeScene();
		
		Label uname = new Label("Student Email");
		TextField emailinput = new TextField();
		
		Label password = new Label("Student Password");
		TextField passinput = new TextField();
		
		Label fnamelbl = new Label("Student First Name");	
		TextField firstnameInput = new TextField();
		
		Label lnamelbl = new Label("Student Last Name");
		TextField lastNameInput = new TextField();
		
		Label classstudent = new Label("Student Class");
		TextField classinput = new TextField();
		
		Label birthDate = new Label("Student Birth Date");
		TextField birthDate1 = new TextField();
		birthDate1.setPromptText("yyyy/MM/dd");

		
		
		Label javaGrade = new Label("Java Grade");
		javaInput = new TextField();
		
		Label csGrade = new Label("C#");
		csInput = new TextField();
		
		Button addbtn = new Button("Add Students");	
		addbtn.setOnAction(e -> addStudent(emailinput.getText(), passinput.getText(), firstnameInput.getText(),lastNameInput.getText(),classinput.getText(), birthDate1.getText()));
		
		Label noticelbl = new Label("Fill Email to update grade");
		Button updateGradebtn = new Button("Update Grade");	
		updateGradebtn.setOnAction(e -> updateGrade(emailinput.getText()));
		
		super.vBox.getChildren().addAll(uname,emailinput,password,passinput,fnamelbl,firstnameInput,lnamelbl,lastNameInput,classstudent,classinput
				,birthDate, birthDate1,javaGrade,javaInput,csGrade,csInput,addbtn, noticelbl, updateGradebtn);
		
	}

	private void addStudent(String email, String pass, String fname, String lname, String classstud,  String birthDate) {
		try {
			query.addPerson(new Student(fname,lname,classstud,pass,email, birthDate));
			
			updateGrade(email);
		} catch (Exception e) {
			showMessageDialog(null, e);
		}
		
	}
	
	void updateGrade(String email) {
		try {
			int javagrade = 0;
			int csgrade = 0;
			Student student = query.getStudent(email);
			
			if (student != null) {
				//Set textbox value as current user grade
				if (javaInput.getText().isEmpty()) {
					javaInput.appendText(Integer.toString(student.getJavaGrade()));
				}
				else if (csInput.getText().isEmpty()) {
					csInput.appendText(Integer.toString(student.getCsharpGrade()));

				}
				
			}
			else {
				//Set textbox value as 0
				if (javaInput.getText().isEmpty()) {
					javaInput.appendText("0");
					
				}
				else if (csInput.getText().isEmpty()) {
					csInput.appendText("0");
				}
	
			}
			javagrade = Integer.parseInt(javaInput.getText());
			csgrade = Integer.parseInt(csInput.getText());
			
			query.setGrade(email, "java", javagrade);
			query.setGrade(email, "csharp", csgrade);
			
			new teacherView(window, query);
		} catch (Exception e) {
			showMessageDialog(null, e);
		}
		
	}
}
