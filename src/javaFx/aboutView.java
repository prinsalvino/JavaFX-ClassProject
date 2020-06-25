package javaFx;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class aboutView {
	Stage window;
	VBox vBox;
	public aboutView( Stage window) throws FileNotFoundException {
		super();
		this.window = window;
		readFile();
	}
	
	
	
	public aboutView() throws FileNotFoundException {
		readFile();
	}



	void readFile() throws FileNotFoundException {
		line line = new line();
		long id = 0;
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25,25,25,25));
		grid.setHgap(10);
		grid.setVgap(10);
		
		File myFile = new File("lines.dat");
		Scanner scanner = new Scanner(myFile);
		int vert = 0;
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();
			line.text = data;
			line.id = id;
			id++;
			Text text = new Text();
			text.setText(line.text + "\n");
			grid.add(text, 0, vert);
			if (line.id % 2 == 0) {
				text.setFill(Color.BLUE);
			}
			else {
				text.setFill(Color.RED);
			}
			vert++;
		}
		scanner.close();
		Button button = new Button("Close");
		button.setOnAction(e ->{
			window.close();
		});
		grid.add(button);
		window.setScene(new Scene(grid,500,500));

	}
	
	
}
