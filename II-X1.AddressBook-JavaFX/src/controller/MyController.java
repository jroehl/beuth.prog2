package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import addressBook.AddressBook;
import addressBook.AddressBookInterfaceNew;
import addressBook.ContactDetails;

public class MyController implements Initializable {

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField address;
	@FXML
	private TextField mail;
	@FXML
	private TextField phone;
	@FXML
	private TextField searchFld;
	@FXML
	private Button addBtn;
	@FXML
	private Button newBtn;
	@FXML
	private Button allEntriesBtn;
	@FXML
	private Button changeBtn;
	@FXML
	private Button newEntryBtn;
	@FXML
	private Button delBtn;
	@FXML
	private Button noBtn;
	@FXML
	private Button yesBtn;
	@FXML
	private MenuItem saveItem;
	@FXML
	private AnchorPane leftAnch;

	AddressBookInterfaceNew addBook = new AddressBook();
	String tempSearchString = null;

	// WICHTIG
	private TableView<ContactDetails> table;
	private TableColumn<ContactDetails, String> firstNameCol;
	private TableColumn<ContactDetails, String> lastNameCol;
	private TableColumn<ContactDetails, String> addressCol;
	private TableColumn<ContactDetails, String> phoneCol;
	private TableColumn<ContactDetails, String> mailCol;

	public void initialize(URL location, ResourceBundle resources) {
		createTableView();
		showAllEntries();
	}

	/*
	 * WICHTIG Create the Table View Erzeugt die Tabelle mit Anpassungen des
	 * Layouts. Erzeugt einen Event-Handler (Lambda Ausdruck) um die Methode
	 * editContact() bei klick auf die Tabellenspalte aufzurufen. Fügt alle
	 * Children dem AnchorPane hinzu.
	 */
	public void createTableView() {
		table = new TableView<ContactDetails>();
		firstNameCol = new TableColumn<ContactDetails, String>("First Name");
		lastNameCol = new TableColumn<ContactDetails, String>("Last Name");
		addressCol = new TableColumn<ContactDetails, String>("Address");
		phoneCol = new TableColumn<ContactDetails, String>("Phone");
		mailCol = new TableColumn<ContactDetails, String>("Mail");
		firstNameCol.setMinWidth(100.0);
		lastNameCol.setMinWidth(100.0);
		addressCol.setMinWidth(150.0);
		phoneCol.setMinWidth(110.0);
		mailCol.setMinWidth(140.0);
		table.setOnMouseClicked((event) -> {
			editContact();
		});
		table.getColumns().addAll(firstNameCol, lastNameCol, addressCol,
				phoneCol, mailCol);
		AnchorPane.setBottomAnchor(table, 10.0);
		AnchorPane.setRightAnchor(table, 30.0);
		AnchorPane.setTopAnchor(table, 45.0);
		AnchorPane.setLeftAnchor(table, 10.0);
		leftAnch.getChildren().addAll(table);
	}

	/*
	 * Fügt den jeweilig zugehörigen Text (firstName : firstNameTab) in die
	 * Textfelder ein.
	 */

	public void setEntryField() {
		firstName.setText(firstNameCol.getCellData(table.getSelectionModel()
				.getSelectedItem()));
		lastName.setText(lastNameCol.getCellData(table.getSelectionModel()
				.getSelectedItem()));
		address.setText(addressCol.getCellData(table.getSelectionModel()
				.getSelectedItem()));
		phone.setText(phoneCol.getCellData(table.getSelectionModel()
				.getSelectedItem()));
		mail.setText(mailCol.getCellData(table.getSelectionModel()
				.getSelectedItem()));
	}

	/*
	 * WICHTIG Erstellt die CellValueFactory für die Tabellenspalte. Holt durch
	 * einen Lambda Ausdruck die ObservableList aus ContactDetails und erzeugt
	 * eine SimpleStringProperty. Setzt die an die Methode übergebene
	 * ObservableList aus ContactDetails in die Tabelle ein.
	 */

	public void fillTableView(ObservableList<ContactDetails> personData) {

		firstNameCol.setCellValueFactory(olistelem -> new SimpleStringProperty(
				olistelem.getValue().getFirstName()));
		lastNameCol.setCellValueFactory(olistelem -> new SimpleStringProperty(
				olistelem.getValue().getLastName()));
		addressCol.setCellValueFactory(olistelem -> new SimpleStringProperty(
				olistelem.getValue().getAddress()));
		phoneCol.setCellValueFactory(olistelem -> new SimpleStringProperty(
				olistelem.getValue().getPhone()));
		mailCol.setCellValueFactory(olistelem -> new SimpleStringProperty(
				olistelem.getValue().getMail()));

		table.setItems(personData);
	}

	/*
	 * WICHTIG Öffnet neues Fenster mit der ListView und füllt diese. Es wird
	 * das keyset des Adressbuchs geholt.
	 */
	public void showListView() {

		Stage stage = new Stage();
		Set<String> keyset = addBook.getKeys();
		ObservableList<String> keys = FXCollections.observableArrayList();
		keys.addAll(keyset);
		ListView<String> lView = new ListView<String>();
		lView.setItems(keys);
		stage.setScene(new Scene(lView, 450, 200));
		stage.showAndWait();
	}

	/*
	 * WICHTIG Öffnet neues Fenster mit dem LineChart und füllt diesen. Es wird
	 * als Bezeichner des XYCharts (x-Achse) die Anfangsbuchstaben des Vor- und
	 * Nachnamens verbunden. Als Werte der y-Achse wird die Länge der Keys
	 * genutzt
	 */
	public void showLineChart() {
		Stage LineChartStage = new Stage();
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Entries");
		for (ContactDetails entry : addBook.getDetails("#")) {
			series.getData().add(
					new XYChart.Data<String, Number>(entry.getFirstName()
							.charAt(0)
							+ ". "
							+ entry.getLastName().charAt(0)
							+ ".", entry.genKey().length()));
		}
		CategoryAxis catAxis = new CategoryAxis();
		catAxis.setLabel("Names");
		catAxis.setSide(Side.BOTTOM);
		NumberAxis numAxis = new NumberAxis();
		numAxis.setSide(Side.LEFT);
		LineChart<String, Number> lineChart = new LineChart<String, Number>(
				catAxis, numAxis);
		lineChart.setAnimated(true);
		lineChart.getData().add(series);
		LineChartStage.setScene(new Scene(lineChart, 500, 250));
		LineChartStage.showAndWait();
	}

	/*
	 * Testet durch eine if-else Schleife, ob die Eingabefelder leer sind. Wenn
	 * alle Felder leer sind wird die Methode showtemp() mit den Werten für den
	 * temporären Text, der Zeitspanne und des gewählten Buttons übergeben. Wenn
	 * eins der Eingabefelder einen Eintrag aufweist, wird ein neues
	 * ContactDetails Objekt mit den übergebenen Werten aus den EIngabefeldern
	 * erstellt. Dieses wird an die add() Methode des Adressbuchs übergeben.
	 * Danach wird showAllEntries() und clearEntryField() aufgerufen.
	 */

	public void addDetails() throws NullPointerException {
		try {
			if (lastName.getText().isEmpty() & firstName.getText().isEmpty()
					& address.getText().isEmpty() & phone.getText().isEmpty()
					& mail.getText().isEmpty()) {
				showTemp("No entry!", "Add", 1000, addBtn);
			} else {
				ContactDetails details = new ContactDetails(lastName.getText(),
						firstName.getText(), address.getText(),
						phone.getText(), mail.getText());
				addBook.add(details);
				// save();
				showAllEntries();
				clearEntryField();
			}
		} catch (NullPointerException e) {
			System.out.println("Caught in the act!");
		}
	}

	/*
	 * Triggert AddressBook save().
	 */
	public void save() {
		try {
			addBook.save();
		} catch (IOException e) {
			System.out.println("Caught in the act!");
		}
	}

	/*
	 * Schließt die Applikation.
	 */
	public void close() {
		System.exit(0);
	}

	/*
	 * Triggert AddressBook save() und schließt die Applikation.
	 */
	public void saveAndExit() {
		try {
			addBook.save();
		} catch (IOException e) {
			System.out.println("Caught in the act!");
		}
		System.exit(0);
	}

	/*
	 * Leitet ein "#" zur Suche in den Keys an AddressBook getDetails() weiter
	 * und füllt mit dem Ergebnis die Tabelle Aktualisiert den Text des
	 * allEntriesBtn.
	 */
	public void showAllEntries() {
		fillTableView(addBook.getDetails("#"));
		allEntriesBtn.setText("Show all " + addBook.getNumberOfEntries()
				+ " entries");
	}

	/*
	 * Wird bei Eingabe im searchFld kontinuierlich ausgelöst Leitet den Text
	 * aus dem searchFld zur Suche in den Values an AddressBook searchDetails()
	 * weiter und füllt mit dem Ergebnis die Tabelle Aktualisiert den Text des
	 * allEntriesBtn.
	 */
	public void searchContact() {
		try {
			delBtn.setVisible(false);
			fillTableView(addBook.search(searchFld.getText().toLowerCase()));
			allEntriesBtn.setText("Show all " + addBook.getNumberOfEntries()
					+ " entries");
		} catch (IllegalArgumentException e) {
			System.out.println("String is null");
		}
	}

	/*
	 * Wird bei Eingabe im firstName & lastName Feld kontinuierlich ausgelöst
	 * 
	 * Setzt den Text für den addBtn und setzt den delBtn auf unsichtbar Leitet
	 * den Text aus dem firstName & lastName Feld zur Suche in den Values an
	 * AddressBook searchDetails() weiter und füllt mit dem Ergebnis die Tabelle
	 * Wenn erkannt wird, dass der Kontakt vorhanden ist, wird der addBtn Text
	 * geändert und löst bei Klick ein Alert Fenster aus und gibt drei Optionen:
	 * 1. yes = addBtn Text ändern & Eingabe aus den Feldern zu einem neuen
	 * Eintrag machen (Dialog schließen) 2. no = addBtn Text ändern, die
	 * Eingabefelder leeren und alle Einträge in der Tabelle anzeigen (Dialog
	 * schließen) 3. cancel = Dialog schließen, keine Änderung
	 */
	public void searchExistingContact() {
		delBtn.setVisible(false);
		try {
			if (addBook.search(firstName.getText().toLowerCase()) != null) {
				addBtn.setText("Add?");
				if (addBook.search(firstName.getText().toLowerCase()) != null) {
					fillTableView(addBook.search(firstName.getText()
							.toLowerCase()));
					fillTableView(addBook.search(firstName.getText()
							.toLowerCase()
							+ " "
							+ lastName.getText().toLowerCase()));
					// Probleme andere Rechner
					addBtn.setOnAction((event) -> {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Alert!");
						alert.setHeaderText("Entry does probably already exist or is not rational -\n do you want to continue anyway?");
						ButtonType yesBtn = new ButtonType("Yes");
						ButtonType noBtn = new ButtonType("No");
						ButtonType cancelBtn = new ButtonType("Cancel",
								ButtonData.CANCEL_CLOSE);
						alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn);
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == yesBtn) {
							addBtn.setText("Add");
							addBtn.setOnAction((event2) -> {
								addDetails();
							});
							addDetails();
						} else if (result.get() == noBtn) {
							addBtn.setText("Add");
							clearEntryField();
							showAllEntries();
							addBtn.setOnAction((event2) -> {
								addDetails();
							});
						} else {
							// ... user chose CANCEL or closed the dialog
						}
					});
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("String is null");
		}
		allEntriesBtn.setText("Show all " + addBook.getNumberOfEntries()
				+ " entries");
	}

	/*
	 * Leitet den Schlüssel des ausgewählten Kontakts zu AddressBook
	 * removeContact() weiter. Setzt den delBtn unsichtbar, ruft switchButtons()
	 * showAllEntries() und clearEntryField() auf.
	 */
	public void removeContact() {
		try {
			ContactDetails contact = table.getSelectionModel()
					.getSelectedItem();
			addBook.removeContact(contact.genKey());
			delBtn.setVisible(false);
			switchButtons();
			showAllEntries();
			clearEntryField();
		} catch (IllegalArgumentException e) {
			System.out.println("String is null!");
		}
	}

	/*
	 * Ruft clearEntryField() und switchButtons() auf
	 */
	public void newEntry() {
		clearEntryField();
		switchButtons();
	}

	/*
	 * Setzt den changeBtn und den newEntryBtn auf unsichtbar und den addBtn auf
	 * sichtbar
	 */
	public void switchButtons() {
		changeBtn.setVisible(false);
		newEntryBtn.setVisible(false);
		addBtn.setVisible(true);
	}

	/*
	 * Setzt den delBtn auf sichtbar. Es wird dann die angewählte Reihe an die
	 * setLayoutY() Methode übergeben, da diese den delBtn immer zugehörig zu
	 * der Reihe verschiebt. Auch wird der TopAnchor immer neu bestimmt. Die
	 * X-Koordinate bleibt jeweils statisch. Der Methode setTooltip() wird der
	 * Text, das gewählte Objekt und der Wert für die Opacity übergeben. Die
	 * Methode fadeIn() wird mit dem übergebenen Objekt, den Werten für die
	 * Opacity und der Zeitspanne aufgerufen. Die Methode setEntryField() wird
	 * aufgerufen. Es wird ein String aus der Methode genKey() des
	 * ContactDetails Objekt der angewählten Zeile global zwischengespeichert.
	 * Der addBtn wird unsichtbar und der newEntryBtn und changeBtn sichtbar
	 * gemacht. Durch einen try-catch Block wird abgeprüft ob eine
	 * NullPointerException auftritt um diese Zu fangen und den delBtn
	 * unsichtbar zu machen.
	 */
	public void editContact() throws NullPointerException {
		try {
			delBtn.setVisible(true);
			int pos = table.getFocusModel().getFocusedCell().getRow() * 24;
			setTooltip("delete contact", delBtn, 0.4);
			delBtn.setLayoutX(615);
			delBtn.setLayoutY(72 + pos);
			AnchorPane.setRightAnchor(delBtn, 6.0);
			AnchorPane.setTopAnchor(delBtn, (double) (72 + (pos)));
			fadeIn(delBtn, 0, 1, 1000);
			setEntryField();
			tempSearchString = table.getSelectionModel().getSelectedItem()
					.genKey();
			// addBtn.setDefaultButton(false);
			addBtn.setVisible(false);
			newEntryBtn.setVisible(true);
			// changeBtn.setDefaultButton(true);
			changeBtn.setVisible(true);
		} catch (NullPointerException e) {
			delBtn.setVisible(false);
			System.out.println("Caught in the act!");
		}
	}

	/*
	 * Es wird ird ein neues ContactDetails Objekt mit den übergebenen Werten
	 * aus den EIngabefeldern erstellt. Wenn der genKey() des neuen
	 * ContactDetails dem durch die Methode editContact() erstellten String
	 * gleicht, wird die showTemp() Methdoe aufgerufen. Andernfalls wird die
	 * changeDetails() Methode des Adressbuchs mit dem neuen ContactDetails
	 * Objekt und dem String aus der editContact() Methode aufgerufen.
	 * clearEntryField(), siwtchButtons() und showAllEntries() wird aufgerufen.
	 */
	public void confirmEdit() {
		try {
			ContactDetails details = new ContactDetails(lastName.getText(),
					firstName.getText(), address.getText(), phone.getText(),
					mail.getText());
			if (details.genKey().equals(tempSearchString)) {
				showTemp("No change!", "Change", 1000, changeBtn);
				// System.out.println("vorhanden");
			} else {
				try {
					addBook.changeContact(details, tempSearchString);
					clearEntryField();
					switchButtons();
					// addBtn.setDefaultButton(true);
					// changeBtn.setDefaultButton(false);
					showAllEntries();
				} catch (IllegalArgumentException e) {
					System.out.println("String was empty!");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("No valid entry!");
		}
	}

	/*
	 * Methode löscht alle Einträge aus den Textfeldern.
	 */
	public void clearEntryField() {
		lastName.clear();
		firstName.clear();
		phone.clear();
		mail.clear();
		address.clear();
	}

	/*
	 * Benutzt die an die Methode übergebenen Werte: Erstellt eine
	 * FadeTransition, setzt das Objekt auf sichtbar. Setzt Start und Endwert
	 * der FadeTransition. Startet diese.
	 */
	public void fadeIn(Control obj, int from, int to, int time) {
		FadeTransition ft = new FadeTransition(Duration.millis(time), delBtn);
		obj.setVisible(true);
		ft.setFromValue(from);
		ft.setToValue(to);
		ft.play();
	}

	/*
	 * Benutzt die an die Methode übergebenen Werte: Erstellt einen Tooltip,
	 * setzt die Opacity, den Text und das zugehörige Objekt.
	 */
	public void setTooltip(String text, Control btn, double opac) {
		final Tooltip tooltip = new Tooltip();
		tooltip.setOpacity(opac);
		tooltip.setText(text);
		btn.setTooltip(tooltip);
	}

	/*
	 * Benutzt die an die Methode übergebenen Werte: Setzt den Text des
	 * Objektes, erstellt eine Timeline und startet diese.
	 */
	public void showTemp(String tempText, String text, int time, Button obj) {
		obj.setText(tempText);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(time),
				ae -> obj.setText(text)));
		timeline.play();
	}

}
