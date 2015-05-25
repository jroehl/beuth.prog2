package addressBook;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BackupContactDetails {

	private String[] list = new String[5];
	private SimpleStringProperty lastName, firstName, address, phone, mail;
	private String lastNameString, firstNameString, addressString, phoneString, mailString;
	ObservableList<String> olist = FXCollections.observableArrayList(list);

	/*
	 * Konstruktor bekommt 5 Strings übergeben, erstellt jeweils ein
	 * SimpleStringProperty und übergibt diese an die entsprechende Stelle des
	 * Arrays.
	 */
	public BackupContactDetails(String lastNameIN, String firstNameIN,
			String addressIN, String phoneIN, String mailIN)
			throws IllegalArgumentException {
		lastName = new SimpleStringProperty(lastNameIN);
		firstName = new SimpleStringProperty(firstNameIN);
		address = new SimpleStringProperty(addressIN);
		phone = new SimpleStringProperty(phoneIN);
		mail = new SimpleStringProperty(mailIN);
		list[0] = lastNameIN;
		list[1] = firstNameIN;
		list[2] = addressIN;
		list[3] = phoneIN;
		list[4] = mailIN;
		lastNameString = lastNameIN;
		firstNameString = firstNameIN;
		addressString = addressIN;
		phoneString = phoneIN;
		mailString = mailIN;

		for (int i = 0; i < list.length; i++)
			if (list[i] == null)
				throw new IllegalArgumentException();
	}

	// Getter und Setter funktionieren über den ArrayIndex

	public String getLastName() {
		return list[0];
	}

	public void setLastName(String name1) {
		list[0] = name1;
	}

	public StringProperty getLastNameProp() {
		return lastName;
	}

	public void setLastNameProp(String lastNameProp1) {
		lastName.set(lastNameProp1);
	}

	public String getFirstName() {
		return list[1];
	}

	public void setFirstName(String firstName1) {
		list[1] = firstName1;
	}

	public StringProperty getFirstNameProp() {
		return firstName;
	}

	public void setFirstNameProp(String firstNameProp1) {
		firstName.set(firstNameProp1);
	}

	public String getAddress() {
		return list[2];
	}

	public void setAddress(String address1) {
		list[2] = address1;
	}

	public StringProperty getAddressProp() {
		return address;
	}

	public void setAddressProp(String addressProp1) {
		address.set(addressProp1);
	}

	public String getPhone() {
		return list[3];
	}

	public void setPhone(String phone1) {
		list[3] = phone1;
	}

	public StringProperty getPhoneProp() {
		return phone;
	}

	public void setPhoneProp(String phoneProp1) {
		phone.set(phoneProp1);
	}

	public String getMail() {
		return list[4];
	}

	public void setMail(String mail1) {
		list[4] = mail1;
	}

	public StringProperty getMailProp() {
		return mail;
	}

	public void setMailProp(String mailProp1) {
		mail.set(mailProp1);
	}

	public String[] getList() {
		return list;
	}

	/*
	 * Gibt einen zusammengesetzten, durch Leerzeichen getrennten String der
	 * gespeicherten Strings wieder.
	 */
	public String getContent() {
		return list[0].toLowerCase() + " " + list[1].toLowerCase() + " "
				+ list[2].toLowerCase() + " " + list[3].toLowerCase() + " "
				+ list[4].toLowerCase();
	}

	/*
	 * Gibt einen zusammengesetzten, durch § Zeichen getrennten String der
	 * gespeicherten Strings wieder.
	 */
	public String getSaveContent() {
		return list[0] + "§" + list[1] + "§" + list[2] + "§" + list[3] + "§"
				+ list[4];
	}

	/*
	 * Generiert einen einmaligen Schlüssel aus dem # und dem direkt
	 * zusammengesetzten String der gespeicherten Strings wieder.
	 */
	public String genKey() {
		return " # " + list[0] + list[1] + list[2] + list[3] + list[4];
	}

}
