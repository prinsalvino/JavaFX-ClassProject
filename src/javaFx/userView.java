package javaFx;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class userView {
userQuery query;
Stage window;
VBox vBox;
TableView<Teacher> teacherView;
//Student Table
TableView<Student> studentView;
	
	
	public userView(userQuery query, Stage window) {
	this.query = query;
	this.window = window;
	}

	public void displayHomeScene() {

		vBox = new VBox();
		//Label Teacher
		Label teacherlbl = new Label("Teacher List");
		teacherlbl.setFont(new Font("Arial",40));
		//Label Student
		Label studentlbl = new Label("Student List");
		studentlbl.setFont(new Font("Arial",40));
		
		makeStudentTable();
		makeTeacherTable();

		vBox.getChildren().addAll(teacherlbl,teacherView, studentlbl, studentView);
		
		Scene sceneHome = new Scene(vBox, 1000, 1000);
		window.setScene(sceneHome);
	}
	
	@SuppressWarnings("unchecked")
	private void makeTeacherTable() {
		TableColumn<Teacher,String> fnamecolumn = new TableColumn<>("First Name");
		fnamecolumn.setMinWidth(200);
		fnamecolumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Teacher,String> lnamecolumn = new TableColumn<>("Last Name");
		lnamecolumn.setMinWidth(200);
		lnamecolumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));		
		
		TableColumn<Teacher,String> emailcolumn = new TableColumn<>("email");
		emailcolumn.setMinWidth(200);
		emailcolumn.setCellValueFactory(new  PropertyValueFactory<>("email"));	
		
		TableColumn<Teacher,String> agecol = new TableColumn<>("Age");
		agecol.setMinWidth(200);
		agecol.setCellValueFactory(new  PropertyValueFactory<>("age"));		
		
		TableColumn<Teacher,String> datecol = new TableColumn<>("BirthDate");
		datecol.setMinWidth(200);
		datecol.setCellValueFactory(new  PropertyValueFactory<>("birthDate"));	
		
		teacherView = new TableView<>();
		
		FillTeacherTable();

		teacherView.getColumns().addAll(fnamecolumn, lnamecolumn,emailcolumn,agecol,datecol);
		
		teacherView.autosize();
		
	}
	
	public void FillTeacherTable() {
		//Filling Table
		teacherView.getItems().setAll(query.getTeacher());
	}
	
	@SuppressWarnings("unchecked")
	private void makeStudentTable() {
		TableColumn<Student,String> fnamecolumn = new TableColumn<>("First Name");
		fnamecolumn.setMinWidth(100);
		fnamecolumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Student,String> lnamecolumn = new TableColumn<>("Last Name");
		lnamecolumn.setMinWidth(100);
		lnamecolumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));		
		
		TableColumn<Student,String> emailcolumn = new TableColumn<>("email");
		emailcolumn.setMinWidth(100);
		emailcolumn.setCellValueFactory(new  PropertyValueFactory<>("email"));	
		
		TableColumn<Student,String> agecol = new TableColumn<>("Age");
		agecol.setMinWidth(100);
		agecol.setCellValueFactory(new  PropertyValueFactory<>("age"));	
		
		TableColumn<Student,String> datecol = new TableColumn<>("BirthDate");
		datecol.setMinWidth(100);
		datecol.setCellValueFactory(new  PropertyValueFactory<>("birthDate"));	

		TableColumn<Student,String> classcolumn = new TableColumn<>("Class");
		classcolumn.setMinWidth(100);
		classcolumn.setCellValueFactory(new  PropertyValueFactory<>("classStudent"));
		
		TableColumn<Student,String> javacol = new TableColumn<>("Java");
		javacol.setMinWidth(100);
		javacol.setCellValueFactory(new  PropertyValueFactory<>("javaGrade"));
		
		TableColumn<Student,String> cscol = new TableColumn<>("C#");
		cscol.setMinWidth(100);
		cscol.setCellValueFactory(new  PropertyValueFactory<>("csharpGrade"));
		
		
		
		studentView = new TableView<>();
		FillStudentTable();
		studentView.getColumns().addAll(fnamecolumn, lnamecolumn,emailcolumn,agecol,datecol,classcolumn,javacol,cscol);
		
		studentView.autosize();	
	}
	
	public void FillStudentTable() {
		//Fill Table
		studentView.setItems(query.getStudent());
	}
}
