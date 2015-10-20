package addressBook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import io.CSVContactWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class ObservableAddressBook {

	private ObservableMap<String, ObservableContactDetails> addressBook;
	
	/**
	 * In dem Standardkonstruktor wird eine neue TreeMap erstellt. Die
	 * gespeicherten Werte werden aus einer csv-Datei gelesen. Mittels einer
	 * forEach Schleife wird jeder vorhandene String der vorhandenen
	 * StringProperties durch die Split Methode geteilt und in einem Array
	 * zwischengespeichert, die gespeicherten Strings werden nun jeweils wieder
	 * benutzt um ein neues ContactDetails Objekt zu erzeugen und in die TreeMap
	 * einzufügen.
	 */
	
	public ObservableAddressBook() {
		addressBook = FXCollections.observableHashMap();
		
//		List<ObservableContactDetails> list = CSVContactsReader.readEntityList("contacts.csv", ":");
//		
//		for (ObservableContactDetails cdet : list) {
//			try {
//				addressBook.put(cdet.getKey(), cdet);
//			} catch (IllegalArgumentException e) {
//				System.out.println("No valid entry!");
//			}
//		}
//	}
		Properties properties = new Properties();
		try {
			properties
					.load(new FileInputStream(
							"data.properties"));
		} catch (IOException e) {
			System.out.println("File not found!!");
			System.exit(0);
		}
		for (String key : properties.stringPropertyNames()) {
			String[] tempList = new String[5];
			int n = 0;
			for (int i = 0; i < properties.get(key).toString().split("§").length; i++) {
				tempList[n] = properties.get(key).toString().split("§")[n];
				n++;
			}
			ObservableContactDetails tempDet;
			try {
				tempDet = new ObservableContactDetails(tempList[0], tempList[1],
						tempList[2], tempList[3], tempList[4]);
				addressBook.put(tempDet.getKey(), tempDet);
			} catch (IllegalArgumentException e) {
				System.out.println("No valid entry!");
			}
		}
	}
		
	
	/***
	 * Die ObservableMap Hashmap addressbook wird wiedergegeben
	 */
	public ObservableMap<String, ObservableContactDetails> getOHashMap() {
		return addressBook;
		
	}
	
	/**
	 * Eine ObservableList mit allen Values der OMap wird wiedergegeben
	 */
	public ObservableList<ObservableContactDetails> getAllValues() {
		ObservableList<ObservableContactDetails> cdet = FXCollections
				.observableArrayList();
		cdet.addAll(addressBook.values());
		return cdet;
	}
	
	
	/**
	 * Save Methode, schreibt die HashMap in eine properties Datei.
	 */
	public void save() throws IOException {
		Properties properties = new Properties();
		for (Map.Entry<String, ObservableContactDetails> entry : addressBook.entrySet()) {
			properties.put(entry.getKey(), entry.getValue().getSaveContent());
		}
		try {
			properties.store(new FileOutputStream("data.properties"), null);
		} catch (IOException e) {
			System.out.println("!!!ERROR!!!");
			System.out.println("!File not found!");
		}
	}

	/**
	 * Save Methode, schreibt die HashMap in eine csv Datei.
	 */
	public void saveToCSV() throws IOException {
		Collection<ObservableContactDetails> coll = addressBook.values();
		List<ObservableContactDetails> list = new ArrayList<ObservableContactDetails>();
		list.addAll(coll);
		try {
			CSVContactWriter.writeEntityList(list, "contacts.csv", ":");
		} catch (IOException e) {
			System.out.println("!!!ERROR!!!");
			System.out.println("!File not found!");
		}
	}
	
	/**
	 * Methode bekommt ein ContactDetails Objekt übergeben. Es wird mit dem
	 * generierten getKey() String als Key und dem Objet ein Addressbuch
	 * (TreeMap) Eintrag erzeugt. Im Konstruktor wird schon überprüft, ob eine
	 * IllegalArgumentsException geworfen wird
	 */
	public void add(ObservableContactDetails details) throws IllegalArgumentException {
		if (details == null)
			throw new IllegalArgumentException();
		try {
			addressBook.put(details.getKey(), details);
		} catch (IllegalArgumentException e) {
			System.out.println("No valid entry!");
		}
	}

	/**
	 * Die nächsten beiden Methoden bekommen einen String übergeben. Es wird
	 * eine neue ObservableList für ContactDetails erzeugt. In diese Liste wird
	 * mittels einer foreach Schleife das Ergebnis der Suche gespeichert.
	 * Entweder wird bei keinem gefundenen Key null ausgegeben oder aber alle
	 * gefundenen Kontakte in der ObservableList gespeichert.
	 */

	// Diese Methode sucht in den Values, ob der SuchString vorkommt und
	// speichert dann das Ergebnis in der ObservableList
	public ObservableList<ObservableContactDetails> search(String keyPrefix) 
			throws IllegalArgumentException {
		if (keyPrefix == null) {
			throw new IllegalArgumentException();
		}
		ObservableList<ObservableContactDetails> cdet = FXCollections
				.observableArrayList();
		for (Entry<String, ObservableContactDetails> entry : addressBook.entrySet()) {
			if (entry.getValue().getContent().contains(keyPrefix)) {
				cdet.add(addressBook.get(entry.getKey()));
			}
		}
		if (cdet.size() == 0)
			return null;
		else
			return cdet;
	}

	/**
	 * Methode bekommt ein ContactDetails Objekt und einen String übergeben. Es
	 * wird der entsprechende Eintrag des oldKeys gelöscht um dann aus dem
	 * ContactDetails Objekt einen neuen Eintrag zu erzeugen.
	 */
	public void changeContact(ObservableContactDetails details, String removeThis)
			throws IllegalArgumentException {
		if (removeThis == null || details == null) {
			throw new IllegalArgumentException();
		}
		addressBook.put(details.getKey(), details);
		addressBook.remove(removeThis);
	}

	/**
	 * Methode gibt die Größe des Addressbuches als Int Zahl wieder.
	 */

	public int getNumberOfEntries() {
		return addressBook.keySet().size();
	}

	/**
	 * Methode bekommt einen String übergeben und löscht den entsprechenden
	 * Eintrag in dem Addressbuch.
	 */
	public void removeContact(String key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		addressBook.remove(key);
	}

	/**
	 * Methode bekommt einen String übergeben und testet über die search
	 * Methode, ob ein Eintrag mit dem entsprechenden Schlüssel vorhanden ist
	 * und gibt einen boolschen Wert (true ^ false) wieder.
	 */
	public boolean keyInUse(String key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (search(key) != null) {
			return true;
		}
		return false;
	}

	public Set<String> getKeys() {
		return addressBook.keySet();
	}
	
}
