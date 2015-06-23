package addressBook;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObservableContactDetails extends ContactDetails {

	private ObjectBinding<String> key;
	private SimpleStringProperty lastName, firstName, address, phone, mail;
	ObservableList<SimpleStringProperty> olist = FXCollections.observableArrayList(lastName, firstName, address, phone, mail);
	
	/**
	 * Konstruktor (leer) Erzeugt leeres Dummy ContactDetails Objekt
	 */
	public ObservableContactDetails() {
		lastName = new SimpleStringProperty("-");
		firstName = new SimpleStringProperty("-");
		address = new SimpleStringProperty("-");
		phone = new SimpleStringProperty("-");
		mail = new SimpleStringProperty("-");
		
		/**
		 * ObjectBinding der Werte des ContactDetails Objekts um einen aktuellen Key zu generieren
		 */
		key = new ObjectBinding<String>() {
			{ bind(lastName, firstName, address, phone, mail) ;}
			protected String computeValue() {
				return " # " + lastName.get() + firstName.get() + address.get() + phone.get() + mail.get();
			}
		};
	}
	
	/**
	 * Konstruktor (aus Strings) Erzeugt ein ContactDetails Objekt
	 */
	public ObservableContactDetails(String lastNameIN, String firstNameIN,
			String addressIN, String phoneIN, String mailIN)
			throws IllegalArgumentException {
		super(lastNameIN, firstNameIN, addressIN, phoneIN, mailIN);
		
		lastName = new SimpleStringProperty(lastNameIN);
		firstName = new SimpleStringProperty(firstNameIN);
		address = new SimpleStringProperty(addressIN);
		phone = new SimpleStringProperty(phoneIN);
		mail = new SimpleStringProperty(mailIN);
		
		/**
		 * ObjectBinding der Werte des ContactDetails Objekts um einen aktuellen Key zu generieren
		 */
		key = new ObjectBinding<String>() {
			{ bind(lastName, firstName, address, phone, mail) ;}
			protected String computeValue() {
				return " # " + lastName.get() + firstName.get() + address.get() + phone.get() + mail.get();
			}
		};
	}

	/**
	 * Konstruktor (aus Liste mit Strings) Erzeugt ein ContactDetails Objekt aus den aus der CSV Datei gelesenen Werten
	 */
	public ObservableContactDetails(String[] split) {
		
		lastName = new SimpleStringProperty(split[0]);
		firstName = new SimpleStringProperty(split[1]);
		address = new SimpleStringProperty(split[2]);
		phone = new SimpleStringProperty(split[3]);
		mail = new SimpleStringProperty(split[4]);
		
		key = new ObjectBinding<String>() {
			{ bind(lastName, firstName, address, phone, mail) ;}
			protected String computeValue() {
				return " # " + lastName.get() + firstName.get() + address.get() + phone.get() + mail.get();
			}
		};
	}
	
	/**
	 * Getter und Setter
	 */
	public SimpleStringProperty getLastNameProperty() {
		return lastName;
	}
	
	public String getLastName() {
		return lastName.get();
	}

	public void setLastNameProp(String lastName) {
		this.lastName.set(lastName);
	}

	public SimpleStringProperty getFirstNameProperty() {
		return firstName;
	}
	
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstNameProp(String firstName) {
		this.firstName.set(firstName);
	}

	public SimpleStringProperty getAddressProperty() {
		return address;
	}
	
	public String getAddress() {
		return address.get();
	}

	public void setAddressProp(String address) {
		this.address.set(address);
	}

	public SimpleStringProperty getPhoneProperty() {
		return phone;
	}
	
	public String getPhone() {
		return phone.get();
	}

	public void setPhoneProp(String phone) {
		this.phone.set(phone);
	}

	public SimpleStringProperty getMailProperty() {
		return mail;
	}
	
	public String getMail() {
		return mail.get();
	}

	public void setMailProp(String mail) {
		this.mail.set(mail);
	}
	
	public String getKey() {
		return key.get();
	}
	
	public ObservableList<SimpleStringProperty> getOList() {
		return olist;
	}

}
