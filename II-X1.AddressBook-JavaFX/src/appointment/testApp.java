package appointment;




public class testApp {
	
	
	public static void main(String[] args) throws TimeException {
		
		Appointment app = new Appointment("TEST", "11:00", "12:00", "12/04/2015", "12/04/2015", "Meeting");
		
		System.out.println(app.getEndTime());
		System.out.println(app.getLength());
		
	}
}
