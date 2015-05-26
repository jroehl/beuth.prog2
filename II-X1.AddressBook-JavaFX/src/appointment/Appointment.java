package appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
	
	private SimpleStringProperty dateContent, category;
	private SimpleIntegerProperty length;
	private SimpleObjectProperty<Date> startTime, endTime;

	public Appointment(String dateContentIN,String startTimeIN,String endTimeIN, String startDateIN, String endDateIN, String categoryIN) throws TimeException {
		dateContent = new SimpleStringProperty(dateContentIN);
		
		SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	
		Date startTimeDate = null;
		Date endTimeDate = null;

		category = new SimpleStringProperty(categoryIN);
		
		try {
			startTimeDate = simpleDateTimeFormat.parse(startDateIN+" "+startTimeIN);
			endTimeDate = simpleDateTimeFormat.parse(endDateIN+" "+endTimeIN);
		}
		catch (ParseException ex)
        {
            System.out.println("Exception "+ex); // Has to be caught and processed
        }
		
		startTime = new SimpleObjectProperty<Date>(startTimeDate);
		endTime = new SimpleObjectProperty<Date>(endTimeDate);
		
		// Binding length : endTime - startTime
		length = new SimpleIntegerProperty((int)((endTimeDate.getTime()/60000) - (startTimeDate.getTime()/60000)));
		
		if(startTimeDate.compareTo(endTimeDate)>0) {
			throw new TimeException();
    	}
	}

	public SimpleStringProperty getDateContent() {
		return dateContent;
	}

	public void setDateContent(SimpleStringProperty dateContent) {
		this.dateContent = dateContent;
	}

	public SimpleStringProperty getCategory() {
		return category;
	}

	public void setCategory(SimpleStringProperty category) {
		this.category = category;
	}

	public SimpleObjectProperty<Date> getStartTime() {
		return startTime;
	}

	public void setStartTime(SimpleObjectProperty<Date> startTime) {
		this.startTime = startTime;
	}

	public SimpleObjectProperty<Date> getEndTime() {
		return endTime;
	}

	public void setEndTime(SimpleObjectProperty<Date> endTime) {
		this.endTime = endTime;
	}

	public SimpleIntegerProperty getLength() {
		return length;
	}
}
