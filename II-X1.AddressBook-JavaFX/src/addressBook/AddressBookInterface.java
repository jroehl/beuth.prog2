package addressBook;
import java.util.List;

public interface AddressBookInterface {

public List<ContactDetails> getDetails(String key);

public boolean keyInUse(String key);

public void addDetails(ContactDetails details);

public void changeDetails(String oldKey, ContactDetails details);

public ContactDetails[] search(String keyPrefix);

public int getNumberOfEntries();

public void removeDetails(String key);

}