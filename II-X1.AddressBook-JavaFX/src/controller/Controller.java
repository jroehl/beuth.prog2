package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
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
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import addressBook.ObservableAddressBook;
import addressBook.ObservableContactDetails;

public class Controller implements Initializable {

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
	@FXML
	private AnchorPane rightAnch;
	@FXML
	private MenuBar menuBar;

	ObservableAddressBook addBook = new ObservableAddressBook();
	String tempKey = null;

	private TableView<ObservableContactDetails> table;
	private TableColumn<ObservableContactDetails, String> firstNameCol;
	private TableColumn<ObservableContactDetails, String> lastNameCol;
	private TableColumn<ObservableContactDetails, String> addressCol;
	private TableColumn<ObservableContactDetails, String> phoneCol;
	private TableColumn<ObservableContactDetails, String> mailCol;

	/**
	 * Initialize Methode wird aufgerufen wenn das fxml Dokument geladen wird
	 * Erstellt die TableView, den DefaultButton, den PrintDataButton. Setzt die
	 * CellValueFactories und befüllt die Tabelle Initial mit allen vorhandenen
	 * Values
	 */

	public void initialize(URL location, ResourceBundle resources) {
		createTableView();
		createDefaultButton();
		createPrintDataMenu();
		setCellValueFactories();
		// Initialbefüllung der TableView
		table.setItems(addBook.getAllValues());
		initializeChangeListeners();
	}

	/**
	 * Methode um die ChangeListeners zu erstellen
	 */
	public void initializeChangeListeners() {

		// ChangeListeners für die Textfelder
		firstName
				.selectionProperty()
				.addListener(
						(observable, oldValue, newValue) -> searchExistingContact(firstName));
		address.selectionProperty()
				.addListener(
						(observable, oldValue, newValue) -> searchExistingContact(lastName));
		phone.selectionProperty()
				.addListener(
						(observable, oldValue, newValue) -> searchExistingContact(phone));
		mail.selectionProperty()
				.addListener(
						(observable, oldValue, newValue) -> searchExistingContact(mail));
		searchFld
				.selectionProperty()
				.addListener(
						(observable, oldValue, newValue) -> searchExistingContact(searchFld));
		// ChangeListener für die HashMap
		addBook.getOHashMap().addListener(
				new MapChangeListener<String, ObservableContactDetails>() {
					@Override
					public void onChanged(Change change) {
						allEntriesBtn.setText("Show all "
								+ addBook.getNumberOfEntries() + " entries");
						table.setItems(addBook.getAllValues());
					}
				});
	}

	// Verknüofung der TabellenSpalten mit den jeweiligen
	// ContactDetailsProperties
	public void setCellValueFactories() {
		firstNameCol.setCellValueFactory(cellData -> cellData.getValue()
				.getFirstNameProperty());
		lastNameCol.setCellValueFactory(cellData -> cellData.getValue()
				.getLastNameProperty());
		addressCol.setCellValueFactory(cellData -> cellData.getValue()
				.getAddressProperty());
		phoneCol.setCellValueFactory(cellData -> cellData.getValue()
				.getPhoneProperty());
		mailCol.setCellValueFactory(cellData -> cellData.getValue()
				.getMailProperty());
	}

	/**
	 * Methode wird TableColumn übergeben und setzt Cellfactories (um die Zelle
	 * editierbar zu machen) und benutzt Lambda Ausdruck um dei Ausführung der
	 * Änderung zu veranlassen
	 */
	public void setCellFactories(
			TableColumn<ObservableContactDetails, String> column) {

		column.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	/**
	 * Zeigt alle Einträge (Values) des Adressbuches
	 */
	public void showAllEntries() {
		table.setItems(addBook.getAllValues());
	}

	/**
	 * WICHTIG Create the Table View Erzeugt die Tabelle mit Anpassungen des
	 * Layouts. Erzeugt einen Event-Handler (Lambda Ausdruck) um die Methode
	 * editContact() bei klick auf die Tabellenspalte aufzurufen. Fügt alle
	 * Children dem AnchorPane hinzu.
	 */
	public void createTableView() {

		table = new TableView<ObservableContactDetails>();
		table.setEditable(true);
		firstNameCol = new TableColumn<ObservableContactDetails, String>(
				"First Name");
		firstNameCol.setMinWidth(100.0);
		setCellFactories(firstNameCol);
		firstNameCol.setOnEditCommit((event) -> {
			table.getSelectionModel().getSelectedItem()
					.setFirstNameProp(event.getNewValue());
			ObservableContactDetails details = table.getSelectionModel()
					.getSelectedItem();
			addBook.changeContact(details, tempKey);
		});
		lastNameCol = new TableColumn<ObservableContactDetails, String>(
				"Last Name");
		lastNameCol.setMinWidth(100.0);
		setCellFactories(lastNameCol);
		lastNameCol.setOnEditCommit((event) -> {
			table.getSelectionModel().getSelectedItem()
					.setLastNameProp(event.getNewValue());
			ObservableContactDetails details = table.getSelectionModel()
					.getSelectedItem();
			addBook.changeContact(details, tempKey);
		});
		addressCol = new TableColumn<ObservableContactDetails, String>(
				"Address");
		addressCol.setMinWidth(150.0);
		setCellFactories(addressCol);
		addressCol.setOnEditCommit((event) -> {
			table.getSelectionModel().getSelectedItem()
					.setAddressProp(event.getNewValue());
			ObservableContactDetails details = table.getSelectionModel()
					.getSelectedItem();
			addBook.changeContact(details, tempKey);
		});
		phoneCol = new TableColumn<ObservableContactDetails, String>("Phone");
		phoneCol.setMinWidth(110.0);
		setCellFactories(phoneCol);
		phoneCol.setOnEditCommit((event) -> {
			table.getSelectionModel().getSelectedItem()
					.setPhoneProp(event.getNewValue());
			ObservableContactDetails details = table.getSelectionModel()
					.getSelectedItem();
			addBook.changeContact(details, tempKey);
		});
		mailCol = new TableColumn<ObservableContactDetails, String>("Mail");
		mailCol.setMinWidth(140.0);
		setCellFactories(mailCol);
		mailCol.setOnEditCommit((event) -> {
			table.getSelectionModel().getSelectedItem()
					.setMailProp(event.getNewValue());
			ObservableContactDetails details = table.getSelectionModel()
					.getSelectedItem();
			addBook.changeContact(details, tempKey);
		});
		table.setOnMouseClicked((event) -> {
			editContact();
		});
		table.getColumns().add(firstNameCol);
		table.getColumns().add(lastNameCol);
		table.getColumns().add(addressCol);
		table.getColumns().add(phoneCol);
		table.getColumns().add(mailCol);
		AnchorPane.setBottomAnchor(table, 10.0);
		AnchorPane.setRightAnchor(table, 30.0);
		AnchorPane.setTopAnchor(table, 45.0);
		AnchorPane.setLeftAnchor(table, 10.0);
		leftAnch.getChildren().addAll(table);
	}

	/**
	 * Erzeugt den DefaultButton, dieser ruft den "Leer"-Konstruktor des
	 * ObservableContactDetails auf. Hierdurch wird ein default-Eintrag
	 * erstellt.
	 */
	public void createDefaultButton() {
		Button defaultEntry = new Button("Default entry");
		defaultEntry.setOnAction((event) -> {
			ObservableContactDetails cdet = new ObservableContactDetails();
			addBook.add(cdet);
		});
		AnchorPane.setTopAnchor(defaultEntry, 15.0);
		AnchorPane.setLeftAnchor(defaultEntry, 185.0);
		AnchorPane.setRightAnchor(defaultEntry, 10.0);
		rightAnch.getChildren().add(defaultEntry);
	}

	/**
	 * Erzeugt das Menü um den Inhalt des Adressbuches an die Konsole auszugeben
	 */
	public void createPrintDataMenu() {

		MenuItem menuItem = new MenuItem("Print Data");
		menuItem.setOnAction((event) -> {
			for (ObservableContactDetails entry : addBook.getAllValues()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getFirstName() + "\t"
						+ entry.getLastName() + "\t" + entry.getAddress()
						+ "\t" + entry.getPhone() + "\t" + entry.getMail());
				System.out.println("");
			}
		});
		Menu menu = new Menu("Stats");
		menu.getItems().add(menuItem);
		menuBar.getMenus().add(menu);
	}

	/**
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

	/**
	 * Öffnet neues Fenster mit der ListView und füllt diese
	 */
	public void showKeysListView() {

		Stage stage = new Stage();
		Set<String> keyset = addBook.getKeys();
		ObservableList<String> keys = FXCollections.observableArrayList();
		keys.addAll(keyset);
		ListView<String> lView = new ListView<String>();
		lView.setItems(keys);
		stage.setScene(new Scene(lView, 450, 200));
		stage.showAndWait();
	}

	/**
	 * Öffnet neues Fenster mit einer editierbaren ListView
	 */
	public void showEditableListView() {
		Stage stage = new Stage();
		ObservableList<String> entries = FXCollections.observableArrayList();
		Set<String> keyset = addBook.getKeys();
		entries.addAll(keyset);
		ListView<String> lView = new ListView<String>();
		lView.setCellFactory(TextFieldListCell.forListView());
		lView.setOnEditCommit((event) -> {
			System.out.println("Wert ist: " + event.getNewValue());
			System.out
					.println("Würde funktionieren, wenn es Sinn machen würde den Key hier direkt zu verändern");
		});
		lView.setItems(entries);
		lView.setEditable(true);
		stage.setScene(new Scene(lView, 450, 200));
		stage.showAndWait();
	}

	/**
	 * Öffnet neues Fenster mit dem LineChart und füllt diesen
	 */
	public void showLineChart() {
		Stage LineChartStage = new Stage();
		Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Entries");
		for (ObservableContactDetails entry : addBook.getAllValues()) {
			series.getData().add(
					new XYChart.Data<String, Number>(entry.getFirstName()
							.charAt(0)
							+ ". "
							+ entry.getLastName().charAt(0)
							+ ".", entry.getKey().length()));
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

	/**
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
				ObservableContactDetails details = new ObservableContactDetails(
						lastName.getText(), firstName.getText(),
						address.getText(), phone.getText(), mail.getText());
				addBook.add(details);
				// save();
				clearEntryField();
			}
		} catch (NullPointerException e) {
			System.out.println("Caught in the act!");
		}
	}

	/**
	 * Triggert AddressBook save().
	 */
	public void save() {
		try {
			addBook.save();
		} catch (IOException e) {
			System.out.println("Caught in the act!");
		}
	}

	public void saveToCSV() {
		try {
			addBook.saveToCSV();
		} catch (IOException e) {
			System.out.println("Caught in the act!");
		}
	}
	/**
	 * Schließt die Applikation.
	 */
	public void close() {
		System.exit(0);
	}

	/**
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

	/**
	 * Wird bei Eingabe im searchFld kontinuierlich ausgelöst Leitet den Text
	 * aus dem searchFld zur Suche in den Values an AddressBook searchDetails()
	 * weiter und füllt mit dem Ergebnis die Tabelle Aktualisiert den Text des
	 * allEntriesBtn.
	 */
	public void searchContact() {
		try {
			delBtn.setVisible(false);
			table.setItems(addBook.search(searchFld.getText().toLowerCase()));
		} catch (IllegalArgumentException e) {
			System.out.println("String is null");
		}
	}

	/**
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
	public void searchExistingContact(TextField txtFld) {
		delBtn.setVisible(false);
		try {
			if (addBook.search(txtFld.getText().toLowerCase()) != null) {
				table.setItems(addBook.search(txtFld.getText().toLowerCase()));
				// Probleme andere Rechner
				// addBtn.setOnAction((event) -> {
				// javafx.scene.control.Alert alert = new
				// javafx.scene.control.Alert(
				// javafx.scene.control.AlertType.WARNING);
				// alert.setTitle("Alert!");
				// alert.setHeaderText("Entry does probably already exist or is not rational -\n do you want to continue anyway?");
				// ButtonType yesBtn = new ButtonType("Yes");
				// ButtonType noBtn = new ButtonType("No");
				// ButtonType cancelBtn = new ButtonType("Cancel",
				// ButtonData.CANCEL_CLOSE);
				// alert.getButtonTypes().setAll(yesBtn, noBtn, cancelBtn);
				// Optional<ButtonType> result = alert.showAndWait();
				// if (result.get() == yesBtn) {
				// addBtn.setText("Add");
				// addBtn.setOnAction((event2) -> {
				// addDetails();
				// });
				// addDetails();
				// } else if (result.get() == noBtn) {
				// addBtn.setText("Add");
				// clearEntryField();
				// addBtn.setOnAction((event2) -> {
				// addDetails();
				// });
				// } else {
				// addBtn.setText("Add");
				// // ... user chose CANCEL or closed the dialog
				// }
				// });
			} else {
				table.setItems(addBook.search(txtFld.getText().toLowerCase()));
				addBtn.setOnAction((event) -> {
					addDetails();
				});
			}
		} catch (IllegalArgumentException e) {
			System.out.println("String is null");
		}
	}
	/**
	 * Leitet den Schlüssel des ausgewählten Kontakts zu AddressBook
	 * removeContact() weiter. Setzt den delBtn unsichtbar, ruft switchButtons()
	 * showAllEntries() und clearEntryField() auf.
	 */
	public void removeContact() {
		try {
			ObservableContactDetails contact = table.getSelectionModel()
					.getSelectedItem();
			addBook.removeContact(contact.getKey());
			delBtn.setVisible(false);
			switchButtons();
			clearEntryField();
		} catch (IllegalArgumentException e) {
			System.out.println("String is null!");
		}
	}

	/**
	 * Ruft clearEntryField() und switchButtons() auf
	 */
	public void newEntry() {
		clearEntryField();
		switchButtons();
	}

	/**
	 * Setzt den changeBtn und den newEntryBtn auf unsichtbar und den addBtn auf
	 * sichtbar
	 */
	public void switchButtons() {
		changeBtn.setVisible(false);
		newEntryBtn.setVisible(false);
		addBtn.setVisible(true);
	}

	/**
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
			addBtn.setText("Add");
			delBtn.setVisible(true);
			int pos = table.getFocusModel().getFocusedCell().getRow() * 24;
			setTooltip("delete contact", delBtn, 0.4);
			delBtn.setLayoutX(615);
			delBtn.setLayoutY(72 + pos);
			AnchorPane.setRightAnchor(delBtn, 6.0);
			AnchorPane.setTopAnchor(delBtn, (double) (72 + (pos)));
			fadeIn(delBtn, 0, 1, 1000);
			firstName.clear();
			lastName.clear();
			address.clear();
			phone.clear();
			mail.clear();
			setEntryField();
			tempKey = table.getSelectionModel().getSelectedItem().getKey();
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

	/**
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
			ObservableContactDetails details = new ObservableContactDetails(
					lastName.getText(), firstName.getText(), address.getText(),
					phone.getText(), mail.getText());
			if (details.getKey().equals(tempKey)) {
				showTemp("No change!", "Change", 1000, changeBtn);
			} else {
				try {
					addBook.changeContact(details, tempKey);
					clearEntryField();
					switchButtons();
				} catch (IllegalArgumentException e) {
					System.out.println("String was empty!");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("No valid entry!");
		}
	}

	/**
	 * Methode löscht alle Einträge aus den Textfeldern.
	 */
	public void clearEntryField() {
		lastName.clear();
		firstName.clear();
		phone.clear();
		mail.clear();
		address.clear();
	}

	/**
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

	/**
	 * Benutzt die an die Methode übergebenen Werte: Erstellt einen Tooltip,
	 * setzt die Opacity, den Text und das zugehörige Objekt.
	 */
	public void setTooltip(String text, Control btn, double opac) {
		final Tooltip tooltip = new Tooltip();
		tooltip.setOpacity(opac);
		tooltip.setText(text);
		btn.setTooltip(tooltip);
	}

	/**
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
