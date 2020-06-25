package javaFx;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class studentView extends userView{

	public studentView(userQuery query, Stage window) {
		super(query, window);
		super.displayHomeScene();
	}
}
