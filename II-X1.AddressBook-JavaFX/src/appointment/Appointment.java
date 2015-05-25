package appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
	
	private SimpleStringProperty date, category;
	private SimpleIntegerProperty length;
	private SimpleObjectProperty startTime, endTime;

	public Appointment(String dateIN,String startTimeIN,String endTimeIN,String categoryIN, String startDateIN, String endDateIN) {
		date = new SimpleStringProperty(dateIN);
SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		Date startTimeDate = null;
		Date endTimeDate = null;
		
		try {
			
			startTimeDate = simpleDateTimeFormat.parse(startDateIN+" "+startTimeIN);
			endTimeDate = simpleDateTimeFormat.parse(endDateIN+" "+endTimeIN);
		}
		
		catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
		startTime = new SimpleObjectProperty(startTimeDate);
		endTime = new SimpleObjectProperty(endTimeIN);
		category = new SimpleStringProperty(categoryIN);
		length = new SimpleIntegerProperty(int((endTimeDate.getTime()/60000) - (startTimeDate.getTime()/60000);
		
		
		
		
		if(startTimeDate.compareTo(endTimeDate)>0) {
			System.out.println("AAHAAAAAAAA");
			throw new TimeException();
    	}
			
	}
}
