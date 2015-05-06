package addressBook;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

	private AddressBook addBook;

	@Before
	public void init() {
		addBook = new AddressBook();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdd1() throws IllegalArgumentException {
		addBook.add(null);
	}
	
	@Test
	public void testAdd2() throws IllegalArgumentException {
		int numberEntries = addBook.getNumberOfEntries();
		ContactDetails detail = new ContactDetails("lastName", "firstName",
			"address", "123455768", "mail");
		addBook.add(detail);
		assertTrue(numberEntries+1 == addBook.getNumberOfEntries());
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDetails() throws IllegalArgumentException {
		addBook.getDetails(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSearch() throws IllegalArgumentException {
		addBook.search(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChangeContact() throws IllegalArgumentException {
		addBook.changeContact(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveContact1() throws IllegalArgumentException {
		addBook.removeContact(null);
	}
	
	@Test
	public void testRemoveContact2() throws IllegalArgumentException {
		int numberEntries = addBook.getNumberOfEntries();
		String key = " # MusterMaxMusterweg 10123456789maxmuster@example.com";
		addBook.removeContact(key);
		assertTrue(numberEntries-1 == addBook.getNumberOfEntries());
		
	}
}
