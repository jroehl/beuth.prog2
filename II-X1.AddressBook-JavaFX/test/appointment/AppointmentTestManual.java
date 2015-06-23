package appointment;

import io.BINAppointmentReader;
import io.BINAppointmentWriter;
import io.CSVAppointmentReader;
import io.CSVAppointmentWriter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Testet die Appointment Klasse (ohne JUNIT Test)
 */
public class AppointmentTestManual {
	
	
	public static void main(String[] args) throws IllegalTimeException, ParseException, IOException {
		
//		ObservableContactDetails cdet = new ObservableContactDetails("Pan", "Peter", "Test1", "Test2", "Test3");
//		
//		System.out.println(cdet.getFirstName());
//		System.out.println(cdet.genKey());
//		cdet.setFirstNameProp("Horst");
//		System.out.println(cdet.getFirstName());
//		System.out.println(cdet.genKey());
		
		Appointment app = new Appointment("TEST", "11:00", "12:00", "12/04/2015", "12/04/2015", "Meeting");
		
		System.out.println(app.getStartDateTime());
		System.out.println(app.getEndDateTime());
		System.out.println(app.getCategory());
		System.out.println(app.getDurationMin());
		System.out.println(Arrays.toString(app.getDurationSplit()));
		
		app.setEndDate("12/05/2015");
		
		System.out.println(app.getStartDateTime());
		System.out.println(app.getEndDateTime());
		System.out.println(app.getDurationMin());
		System.out.println(Arrays.toString(app.getDurationSplit()));
		
		try {
			app.setStartTime("14:00");
		} catch (IllegalTimeException e) {
			System.out.println(e);
		}
		
		System.out.println(app.getStartDateTime());
		System.out.println(app.getEndDateTime());
		System.out.println(app.getDurationMin());
		System.out.println(Arrays.toString(app.getDurationSplit()));
		
		app.setEndDate("12/06/2015");
		
		System.out.println(app.getStartDateTime());
		System.out.println(app.getEndDateTime());
		System.out.println(app.getDurationMin());
		System.out.println(Arrays.toString(app.getDurationSplit()));
		
		app.setEndDate("12/05/2015");
		
		System.out.println(app.getStartDateTime());
		System.out.println(app.getEndDateTime());
		System.out.println(app.getDurationMin());
		System.out.println(Arrays.toString(app.getDurationSplit()));
		
		Appointment app2 = new Appointment("TEST2", "10:00", "13:45", "11/19/2015", "11/19/2015", "Meeting nu");
		
		
		List<Appointment> alist = new ArrayList<Appointment>();
		alist.add(app);
		alist.add(app2);
		
		try {
			CSVAppointmentWriter.writeEntityList(alist, "appointments.csv", ";");
		} catch (IOException e) {
			System.out.println("!!!ERROR!!!");
			System.out.println("!File not found!");
		}
		
		try {
			BINAppointmentWriter.writeEntityList(alist, "appointments.bin", ";");
		} catch (IOException e) {
			System.out.println("!!!ERROR!!!");
			System.out.println("!File not found!");
		}
		
		List<Appointment> aListNew = CSVAppointmentReader.readEntityList("appointments.csv", ";");
		List<Appointment> aListBIN = BINAppointmentReader.readEntityList("appointments.bin", ";");
		
//		System.out.println(aListNew);
//		aListNew.get(0).getStartDateTimeBinding().getValue();
		System.out.println(aListNew.get(0).getDateContent());
		System.out.println(aListBIN.get(0).getDateContent());
//		aListNew.get(1).getStartDateTimeBinding().getValue();	
		System.out.println(aListNew.get(1).getDateContent());
		System.out.println(aListBIN.get(1).getDateContent());
		
	}
}
