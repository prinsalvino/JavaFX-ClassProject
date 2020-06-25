package javaFx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application 
{
	Stage window;
	Scene sceneLogin, sceneHome;
	ObservableList<Person> persons = FXCollections.observableArrayList();
	

	userQuery query = new userQuery();

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Student student1 = new Student("Prins","Alvino","IT1","Prins123","prins@gmail.com",80,85, "1994/06/23");
		Teacher teacher1 = new Teacher("Michael","Corneole","Michael123","michael@gmail.com",2000, "1980/06/23");
		Manager manager1 = new Manager("Justin","Hewlett","Justin123","justin@gmail.com", "1985/06/23");
		
		query.addPerson(student1);
		query.addPerson(manager1);
		query.addPerson(teacher1);

		window = primaryStage;
		window.setTitle("University Project");
		
		buildLoginScene();
				
        window.show();
	}
	
	private void buildLoginScene() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25,25,25,25));
		grid.setHgap(10);
		grid.setVgap(10);
		
		Menu menu = new Menu("About");
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menu);
		
		MenuItem menuItem = new MenuItem("Credits");
		menu.getItems().add(menuItem);
		
		menuItem.setOnAction(e -> {
			try {
				aboutView aboutView = new aboutView(window);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		//Name label
		Label nameLabel = new Label("Username");
		grid.add(nameLabel,0,0);
		
		//Name Input
		TextField nameInput = new TextField();
		grid.add(nameInput,1,0);
		
		//Label Password
		Label pw = new Label("Password:");
		grid.add(pw, 0, 1);

		//Password Input
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 1);
	
		Button loginbtn = new Button("Login");
		grid.add(loginbtn, 1, 2);
		
		Label notif = new Label("");
		grid.add(notif, 1, 3);
		
		loginbtn.setOnAction(e -> {
			try {
				checkLogin(nameInput.getText(), pwBox.getText(),notif);
			} catch (UnauthorizedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		VBox box = new VBox(menuBar,grid);
		sceneLogin = new Scene(box, 300, 200);
		window.setScene(sceneLogin);
		
		closeWindow();
	}
	
	private void checkLogin(String email, String password, Label notif) throws UnauthorizedException {
		persons = query.getPersons();
		boolean checkLogin = false;
		String message = "";
	
		for(Person person : persons) {
				if (email.trim().equals(person.getEmail()) && password.trim().equals(person.getPassword())) {

					if (person.type == AccessType.ADMIN) {
						new managerView(query, window);
					}
					else if(person.type == AccessType.EDITOR) {
						new teacherView(window, query);
						
					}
					else if(person.type == AccessType.BASIC) {
						new studentView(query, window);
					}
					checkLogin = true;
				}
		}
		//Question 1 Answer
		if (email.trim().isEmpty() || password.trim().isEmpty()) {
			message = "Empty username or password";
			notif.setText(message);
			throw new UnauthorizedException(message);
			
		}
		else if (checkLogin == false) {
			message = "Bad Credentials";
			notif.setText(message);
			throw new UnauthorizedException(message);
		} 
	}
	
	//Question 3 answer
	void closeWindow() {
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				Stage dialog = new Stage();
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(25,25,25,25));
				grid.setHgap(10);
				grid.setVgap(10);
				
				Label close = new Label("Close the window");
				grid.add(close,0,0);
				
				Button btnYes = new Button("Yes");
				grid.add(btnYes, 0, 2);
				
				Button btnNo = new Button("No");
				grid.add(btnNo, 1, 2);
				
				btnYes.setOnAction(e ->{
					window.close();
				});
				
				btnNo.setOnAction(e->{
					event.consume();
					dialog.close();
				});
				
				
				Scene modalScene = new Scene(grid, 300, 200);
				dialog.setScene(modalScene);

				dialog.initOwner(window);
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.showAndWait();
				
			}
		});
	}
	

	

}

