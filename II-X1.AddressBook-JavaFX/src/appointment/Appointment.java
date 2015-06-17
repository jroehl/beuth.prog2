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
	
	public Appointment() throws IllegalTimeException {
		dateContent = new SimpleStringProperty("-");
		category = new SimpleStringProperty("-");
		startDate = new SimpleStringProperty("01/01/1971");
		startTime = new SimpleStringProperty("00:00");
		endDate = new SimpleStringProperty("01/01/1971");
		endTime = new SimpleStringProperty("00:01");
		
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
	
	public Appointment(String[] split) throws IllegalTimeException {
		this.
		dateContent = new SimpleStringProperty(split[0]);
		category = new SimpleStringProperty(split[1]);
		startDate = new SimpleStringProperty(split[2]);
		startTime = new SimpleStringProperty(split[3]);
		endDate = new SimpleStringProperty(split[4]);
		endTime = new SimpleStringProperty(split[5]);
		
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
		return dateContent.getValue();
	}

	public void setDateContent(String dateContent) {
		this.dateContent.set(dateContent);
	}

	public SimpleStringProperty getCategoryProperty() {
		return category;
	}
	
	public String getCategory() {
		return category.getValue();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public ObjectBinding<LocalDateTime> getStartDateTimeBinding() {
		return startDateTime;
	}
	
	public String getStartDateTime() {
		return startDateTime.get().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
	}

	public ObjectBinding<LocalDateTime> getEndDateTimeBinding() {
		return endDateTime;
	}
	
	public String getEndDateTime() {
		return endDateTime.get().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
	}

	public SimpleStringProperty getStartDateProperty() {
		return startDate;
	}
	
	public String getStartDate() {
		return startDate.getValue();
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
		return endDate.getValue();
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
		return startTime.getValue();
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
		return endTime.getValue();
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