package appointment;

import java.text.ParseException;
import java.util.Arrays;




public class testApp {
	
	
	public static void main(String[] args) throws IllegalTimeException, ParseException {
		
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
	}
}
