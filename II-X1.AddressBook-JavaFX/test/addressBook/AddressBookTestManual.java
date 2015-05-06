package addressBook;

public class AddressBookTestManual {

	private static AddressBookInterfaceNew addBook;

	static public void main(String[] args) {
		addBook = new AddressBook();

		testerAdd();
		testerGetDetails();
		testerSearch();
		testerChangeContact();
		testerRemoveContact();
	}

	public static void testerAdd() {
		try {
			addBook.add(null);
		} catch (IllegalArgumentException e) {
			System.out.println("Schritt 1: In Ordnung");
			return;
		}
		System.out.println("Schritt 1: Fehlerhaft");
	}

	public static void testerGetDetails() {
		try {
			addBook.getDetails(null);
		} catch (IllegalArgumentException e) {
			System.out.println("Schritt 2: In Ordnung");
			return;
		}
		System.out.println("Schritt 2: Fehlerhaft");
	}

	public static void testerSearch() {
		try {
			addBook.search(null);
		} catch (IllegalArgumentException e) {
			System.out.println("Schritt 3: In Ordnung");
			return;
		}
		System.out.println("Schritt 3: Fehlerhaft");
	}

	public static void testerChangeContact() {
		try {
			addBook.changeContact(null, null);
		} catch (IllegalArgumentException e) {
			System.out.println("Schritt 4: In Ordnung");
			return;
		}
		System.out.println("Schritt 4: Fehlerhaft");
	}

	public static void testerRemoveContact() {
		try {
			addBook.removeContact(null);
		} catch (IllegalArgumentException e) {
			System.out.println("Schritt 5: In Ordnung");
			return;
		}
		System.out.println("Schritt 5: Fehlerhaft");
	}

}
