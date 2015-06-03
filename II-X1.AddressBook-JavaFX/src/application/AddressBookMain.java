package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import addressBook.ObservableAddressBook;

public class AddressBookMain extends Application {

	/*
	 * Erzeugt das JavaFX Hauptfenster.
	 */

	private ObservableAddressBook addBook = new ObservableAddressBook();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(
					"/application/ObservableGUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage
					.getIcons()
					.add(new Image(
							"/application/book_ffffff_20.png"));
			primaryStage.setTitle("Addressbook");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
		System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
		
	}

	public ObservableAddressBook getPersonData() {
        return addBook;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
