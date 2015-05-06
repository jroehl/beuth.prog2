package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AddressBookMain extends Application {

	/*
	 * Erzeugt das JavaFX Hauptfenster.
	 */

	public static Stage stage1;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage1 = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource(
					"/application/GUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage
					.getIcons()
					.add(new Image(
							"file:/Users/jroehl/Dropbox/Programmierung/workspace/II-X1.AddressBook-JavaFX/src/application//book_ffffff_20.png"));
			primaryStage.setTitle("Addressbook");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}