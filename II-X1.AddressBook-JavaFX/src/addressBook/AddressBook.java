package addressBook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBook implements AddressBookInterfaceNew {

	private TreeMap<String, ContactDetails> addressBook;

	/*
	 * In dem Standardkonstruktor wird eine neue TreeMap erstellt. Die
	 * gespeicherten Properties werden aus einer Datei gelesen. Mittels einer
	 * forEach Schleife wird jeder vorhandene String der vorhandenen
	 * StringProperties durch die Split Methode geteilt und in einem Array
	 * zwischengespeichert, die gespeicherten Strings werden nun jeweils wieder
	 * benutzt um ein neues ContactDetails Objekt zu erzeugen und in die TreeMap
	 * einzufügen.
	 */
	public AddressBook() {
		addressBook = new TreeMap<String, ContactDetails>();
		Properties properties = new Properties();
		try {
			// /Users/jroehl/Dropbox/Programmierung/GIT/Beuth_Programmieren_2/II-X1.AddressBook-JavaFX/data.properties
			properties
					.load(new FileInputStream(
							"/Users/jroehl/Dropbox/Programmierung/GIT/Beuth_Programmieren_2/II-X1.AddressBook-JavaFX/data.properties"));
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
			ContactDetails tempDet;
			try {
				tempDet = new ContactDetails(tempList[0], tempList[1],
						tempList[2], tempList[3], tempList[4]);
				addressBook.put(tempDet.genKey(), tempDet);
			} catch (IllegalArgumentException e) {
				System.out.println("No valid entry!");
			}

		}
	}

	/*
	 * Save Methode, schreibt die TreeMap in eine Datei.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#save()
	 */
	@Override
	public void save() throws IOException {
		Properties properties = new Properties();
		for (Map.Entry<String, ContactDetails> entry : addressBook.entrySet()) {
			properties.put(entry.getKey(), entry.getValue().getSaveContent());
		}
		try {
			properties.store(new FileOutputStream("data.properties"), null);
		} catch (IOException e) {
			System.out.println("!!!ERROR!!!");
			System.out.println("!File not found!");
		}
	}


	/*
	 * Methode bekommt ein ContactDetails Objekt übergeben. Es wird mit dem
	 * generierten genKey() String als Key und dem Objet ein Addressbuch
	 * (TreeMap) Eintrag erzeugt. Im Konstruktor wird schon überprüft, ob eine
	 * IllegalArgumentsException geworfen wird
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#add(addressBook.ContactDetails)
	 */
	@Override
	public void add(ContactDetails details) throws IllegalArgumentException {
		if (details == null)
			throw new IllegalArgumentException();
		try {
			addressBook.put(details.genKey(), details);
		} catch (IllegalArgumentException e) {
			System.out.println("No valid entry!");
		}

	}

	/*
	 * Die nächsten beiden Methoden bekommen einen String übergeben. Es wird
	 * eine neue ObservableList für ContactDetails erzeugt. In diese Liste wird
	 * mittels einer foreach Schleife das Ergebnis der Suche gespeichert.
	 * Entweder wird bei keinem gefundenen Key null ausgegeben oder aber alle
	 * gefundenen Kontakte in der ObservableList gespeichert.
	 */

	// Diese Methode sucht in den Keys, ob der SuchString vorkommt und
	// speichert dann das Ergebnis in der ObservableList
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#getDetails(java.lang.String)
	 */
	@Override
	public ObservableList<ContactDetails> getDetails(String key)
			throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		ObservableList<ContactDetails> cdet = FXCollections
				.observableArrayList();
		for (Entry<String, ContactDetails> entry : addressBook.entrySet()) {
			if (entry.getKey().contains(key)) {
				cdet.add(addressBook.get(entry.getKey()));
			}
		}
		if (cdet.size() == 0)
			return null;
		else
			return cdet;
	}

	// Diese Methode sucht in den Values, ob der SuchString vorkommt und
	// speichert dann das Ergebnis in der ObservableList
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#search(java.lang.String)
	 */
	@Override
	public ObservableList<ContactDetails> search(String keyPrefix)
			throws IllegalArgumentException {
		if (keyPrefix == null) {
			throw new IllegalArgumentException();
		}
		ObservableList<ContactDetails> cdet = FXCollections
				.observableArrayList();
		for (Entry<String, ContactDetails> entry : addressBook.entrySet()) {
			if (entry.getValue().getContent().contains(keyPrefix)) {
				cdet.add(addressBook.get(entry.getKey()));
			}
		}
		if (cdet.size() == 0)
			return null;
		else
			return cdet;
	}

	/*
	 * Methode bekommt ein ContactDetails Objekt und einen String übergeben. Es
	 * wird der entsprechende Eintrag des oldKeys gelöscht um dann aus dem
	 * ContactDetails Objekt einen neuen Eintrag zu erzeugen.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * addressBook.AddressBookInterfaceNew#changeContact(addressBook.ContactDetails
	 * , java.lang.String)
	 */
	@Override
	public void changeContact(ContactDetails details, String removeThis)
			throws IllegalArgumentException {

		if (removeThis == null || details == null) {
			throw new IllegalArgumentException();
		}
		addressBook.put(details.genKey(), details);
		addressBook.remove(removeThis);
	}

	/*
	 * Methode gibt die Größe des Addressbuches als Int Zahl wieder.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#getNumberOfEntries()
	 */
	@Override
	public int getNumberOfEntries() {
		return addressBook.keySet().size();
	}

	/*
	 * Methode bekommt einen String übergeben und löscht den entsprechenden
	 * Eintrag in dem Addressbuch.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#removeContact(java.lang.String)
	 */
	@Override
	public void removeContact(String key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		addressBook.remove(key);
	}

	/*
	 * Methode bekommt einen String übergeben und testet über die search
	 * Methode, ob ein Eintrag mit dem entsprechenden Schlüssel vorhanden ist
	 * und gibt einen boolschen Wert (true ^ false) wieder.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see addressBook.AddressBookInterfaceNew#keyInUse(java.lang.String)
	 */
	@Override
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
