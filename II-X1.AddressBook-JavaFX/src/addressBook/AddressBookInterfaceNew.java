package addressBook;

import java.io.IOException;
import java.util.Set;

import javafx.collections.ObservableList;

public interface AddressBookInterfaceNew {

	/*
	 * Save Methode, schreibt die TreeMap in eine Datei.
	 */
	public abstract void save() throws IOException;

	/*
	 * Methode bekommt ein ContactDetails Objekt übergeben. Es wird mit dem
	 * generierten genKey() String als Key und dem Objet ein Addressbuch
	 * (TreeMap) Eintrag erzeugt. Im Konstruktor wird schon überprüft, ob eine
	 * IllegalArgumentsException geworfen wird
	 */
	public abstract void add(ContactDetails details)
			throws IllegalArgumentException;

	// Diese Methode sucht in den Keys, ob der SuchString vorkommt und
	// speichert dann das Ergebnis in der ObservableList
	public abstract ObservableList<ContactDetails> getDetails(String key)
			throws IllegalArgumentException;

	// Diese Methode sucht in den Values, ob der SuchString vorkommt und
	// speichert dann das Ergebnis in der ObservableList
	public abstract ObservableList<ContactDetails> search(String keyPrefix)
			throws IllegalArgumentException;

	/*
	 * Methode bekommt ein ContactDetails Objekt und einen String übergeben. Es
	 * wird der entsprechende Eintrag des oldKeys gelöscht um dann aus dem
	 * ContactDetails Objekt einen neuen Eintrag zu erzeugen.
	 */
	public abstract void changeContact(ContactDetails details, String removeThis)
			throws IllegalArgumentException;

	/*
	 * Methode gibt die Größe des Addressbuches als Int Zahl wieder.
	 */
	public abstract int getNumberOfEntries();

	/*
	 * Methode bekommt einen String übergeben und löscht den entsprechenden
	 * Eintrag in dem Addressbuch.
	 */
	public abstract void removeContact(String key)
			throws IllegalArgumentException;

	/*
	 * Methode bekommt einen String übergeben und testet über die search
	 * Methode, ob ein Eintrag mit dem entsprechenden Schlüssel vorhanden ist
	 * und gibt einen boolschen Wert (true ^ false) wieder.
	 */
	public abstract boolean keyInUse(String key)
			throws IllegalArgumentException;

	public Set<String> getKeys();
}