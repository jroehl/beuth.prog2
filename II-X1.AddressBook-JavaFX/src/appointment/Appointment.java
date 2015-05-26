package appointment;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
	
	private SimpleStringProperty dateContent, category;
	private ObjectBinding<Long> duration;
	private ObjectBinding<LocalDateTime> startDateTime, endDateTime;
	private SimpleStringProperty startDate, endDate, startTime, endTime;

	public Appointment(String dateContentIN,String startTimeIN,String endTimeIN, String startDateIN, String endDateIN, String categoryIN) throws IllegalTimeException, ParseException {
		dateContent = new SimpleStringProperty(dateContentIN);
		category = new SimpleStringProperty(categoryIN);
		startDate = new SimpleStringProperty(startDateIN);
		startTime = new SimpleStringProperty(startTimeIN);
		endDate = new SimpleStringProperty(endDateIN);
		endTime = new SimpleStringProperty(endTimeIN);
		
		startDateTime = new ObjectBinding<LocalDateTime>() {
			{ bind(startDate, startTime) ;}
			protected LocalDateTime computeValue() {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
				LocalDateTime timeDateTime = LocalDateTime.parse(startDate.get()+" "+startTime.get(), dateTimeFormatter);
				return timeDateTime;
			}
		};
		
		endDateTime = new ObjectBinding<LocalDateTime>() {
			{ bind(endDate, endTime) ;}
			protected LocalDateTime computeValue() {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
				LocalDateTime timeDateTime = LocalDateTime.parse(endDate.get()+" "+endTime.get(), dateTimeFormatter);
				return timeDateTime;
			}
		};
		
		duration = new ObjectBinding<Long>() {
			{ bind(startDateTime, endDateTime) ;}
			protected Long computeValue() {
				return startDateTime.get().until(endDateTime.get(), ChronoUnit.MINUTES);
			}
		};
		
		if (startDateTime.get().isAfter(endDateTime.get())) {
			throw new IllegalTimeException();
    	}
	}
	
	public SimpleStringProperty getDateContentProperty() {
		return dateContent;
	}
	
	public String getDateContent() {
		return dateContent.get();
	}

	public void setDateContent(String dateContent) {
		this.dateContent.set(dateContent);
	}

	public SimpleStringProperty getCategoryProperty() {
		return category;
	}
	
	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public ObjectBinding<LocalDateTime> getStartDateTimeBinding() {
		return startDateTime;
	}
	
	public String getStartDateTime() {
		return startDateTime.get().toString();
	}

	public ObjectBinding<LocalDateTime> getEndDateTimeBinding() {
		return endDateTime;
	}
	
	public String getEndDateTime() {
		return endDateTime.get().toString();
	}

	public SimpleStringProperty getStartDateProperty() {
		return startDate;
	}
	
	public String getStartDate() {
		return startDate.get();
	}

	public void setStartDate(String startDate) throws IllegalTimeException, ParseException {
		this.startDate.set(startDate);
		if (startDateTime.get().isAfter(endDateTime.get())) {
			throw new IllegalTimeException();
    	}
	}

	public SimpleStringProperty getEndDateProperty() {
		return endDate;
	}
	
	public String getEndDate() {
		return endDate.get();
	}

	public void setEndDate(String endDate) throws IllegalTimeException, ParseException {
		this.endDate.set(endDate);
		if (startDateTime.get().isAfter(endDateTime.get())) {
			throw new IllegalTimeException();
    	}
	}

	public SimpleStringProperty getStartTimeProperty() {
		return startTime;
	}

	public String getStartTime() {
		return startTime.get();
	}
	
	public void setStartTime(String startTime) throws IllegalTimeException, ParseException {
		this.startTime.set(startTime);
		if (startDateTime.get().isAfter(endDateTime.get())) {
			throw new IllegalTimeException();
    	}
	}
	
	public SimpleStringProperty getEndTimeProperty() {
		return endTime;
	}
	
	public String getEndTime() {
		return endTime.get();
	}

	public void setEndTime(String endTime) throws IllegalTimeException, ParseException {
		this.endTime.set(endTime);
		if (startDateTime.get().isAfter(endDateTime.get())) {
			throw new IllegalTimeException();
    	}
	}

	public Long[] getDurationSplit() {
		
		Long[] retArr = new Long[3];
		
		retArr[0] = (long) 0; // Minutes
		retArr[1] = (long) 0; // Hours
		retArr[2] = (long) 0; // Days
		
		if (duration.get() < 60) {
			retArr[0] = duration.get();
			return retArr; 
		}
	
		if (duration.get() >= 60 & duration.get() < 1440) {
			retArr[1] = duration.get() / 60;
			retArr[0] = duration.get() % 60;
			return retArr;
		}	
		else {
			retArr[2] = duration.get() / 1440;
			System.out.println(retArr[2]);
			retArr[1] = (duration.get() - (retArr[2] * 1440)) / 60;
			retArr[0] = (duration.get() - (retArr[2] * 1440)) % 60;
			return retArr;
		}
	}

	public Long getDurationMin() {
		return duration.get();
		
	}

}