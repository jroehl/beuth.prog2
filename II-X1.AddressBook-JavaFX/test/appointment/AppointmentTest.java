package appointment;

import static org.junit.Assert.assertTrue;
import io.BINAppointmentReader;
import io.CSVAppointmentReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Testet die Appointment Klasse als JUNIT Test
 */
public class AppointmentTest {

	Appointment app;
	Appointment app2;

	/**
	 * Initialisiert ein Appointment Objekt
	 */
	@Before
	public void init() {

		try {
			app = new Appointment("TEST", "11:00", "12:00", "12/04/2015",
					"12/04/2015", "Meeting");
		} catch (IllegalTimeException e) {
			System.out.println(e);
		} catch (ParseException e) {
			System.out.println(e);
		}
	}

	/**
	 * Testet das erfolgreiche ändern eines Wertes
	 */
	@Test
	public void testChange() {
		String oldEnd = null;
		String newEnd = null;
		try {
			oldEnd = app.getEndDate();
			newEnd = "12/05/2015";
			app.setEndDate(newEnd);
		} catch (IllegalTimeException e) {
			System.out.println(e);
		} catch (ParseException e) {
			System.out.println(e);
		}
		assertTrue(app.getEndDate() != oldEnd & app.getEndDate() != null);
	}
	
	/**
	 * Testet ob das ObjectBinding der duration funktioniert
	 */
	@Test
	public void testChangeDuration() {
		String newEnd = null;
		Long duration = (long) -1;
		
		try {
			duration = app.getDurationMin();
			newEnd = "12/05/2015";
			app.setEndDate(newEnd);
		} catch (IllegalTimeException e) {
			System.out.println(e);
		} catch (ParseException e) {
			System.out.println(e);
		}
		assertTrue(duration != app.getDurationMin());
	}

	/**
	 * Testet ob die IllegalTimeException bei Start- vor Endzeit geworfen wird
	 */
	@Test(expected = IllegalTimeException.class)
	public void testChangeExc() throws IllegalTimeException, ParseException {
		app.setStartDate("12/05/2015");
	}
	
	/**
	 * Testet ob der Konstruktor(leer) funktioniert
	 */
	@Test
	public void testAdd() {
		app2 = new Appointment();
		assertTrue(app2.getEndDate() != null);
	}

	/**
	 * Testet ob die IOException geworfen wird, falls die csv Datei nicht vorhanden ist
	 */
	@Test(expected = IOException.class)
	public void testLoadcsvExc() throws IOException {
		CSVAppointmentReader.readEntityList("appo.csv", ";");
	}
	
	/**
	 * Testet ob die IOException geworfen wird, falls die bin Datei nicht vorhanden ist
	 */
	@Test(expected = IOException.class)
	public void testLoadbinExc() throws IOException {
		BINAppointmentReader.readEntityList("appo.bin", ";");
	}

	List<Appointment> aListCSV;

	/**
	 * Testet ob der Ladevorgang bei einer vorhandenen csv Datei erfolgreich ist
	 */
	@Test
	public void testLoadcsv() {
		try {
			aListCSV = CSVAppointmentReader.readEntityList("appointments.csv", ";");
		} catch (IOException e) {
			System.out.println(e);
		}
		assertTrue(aListCSV != null);
	}

	List<Appointment> aListBIN;
	
	/**
	 * Testet ob der Ladevorgang bei einer vorhandenen bin Datei erfolgreich ist
	 */
	@Test
	public void testLoadbin() {
		try {
			aListBIN = BINAppointmentReader.readEntityList("appointments.bin", ";");
		} catch (IOException e) {
			System.out.println(e);
		}
		assertTrue(aListBIN != null);
	}

	/**
	 * Testet ob die Liste nach erfolgreichem Ladevorgang der csv Datei mit Appointment Objekten gefüllt ist
	 */
	public void testLoadedListCSV() {
		String date = aListCSV.get(0).getDateContent();
		assertTrue(date != null);
	}
	
	/**
	 * Testet ob die Liste nach erfolgreichem Ladevorgang der bin Datei mit Appointment Objekten gefüllt ist
	 */
	public void testLoadedListBIN() {
		String date = aListBIN.get(0).getDateContent();
		assertTrue(date != null);
	}

}
