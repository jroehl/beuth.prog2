package addressBook;

import javafx.beans.property.SimpleStringProperty;

public class ObservableContactDetails extends ContactDetails {

	private SimpleStringProperty lastName, firstName, address, phone, mail;
	
	public ObservableContactDetails(String lastNameIN, String firstNameIN,
			String addressIN, String phoneIN, String mailIN)
			throws IllegalArgumentException {
		super(lastNameIN, firstNameIN, addressIN, phoneIN, mailIN);
		
		lastName = new SimpleStringProperty(lastNameIN);
		firstName = new SimpleStringProperty(firstNameIN);
		address = new SimpleStringProperty(addressIN);
		phone = new SimpleStringProperty(phoneIN);
		mail = new SimpleStringProperty(mailIN);
	
	}

	public SimpleStringProperty getLastNameProp() {
		return lastName;
	}

	public void setLastNameProp(SimpleStringProperty lastName) {
		this.lastName = lastName;
	}

	public SimpleStringProperty getFirstNameProp() {
		return firstName;
	}

	public void setFirstNameProp(SimpleStringProperty firstName) {
		this.firstName = firstName;
	}

	public SimpleStringProperty getAddressProp() {
		return address;
	}

	public void setAddressProp(SimpleStringProperty address) {
		this.address = address;
	}

	public SimpleStringProperty getPhoneProp() {
		return phone;
	}

	public void setPhoneProp(SimpleStringProperty phone) {
		this.phone = phone;
	}

	public SimpleStringProperty getMailProp() {
		return mail;
	}

	public void setMailProp(SimpleStringProperty mail) {
		this.mail = mail;
	}

}
