package appointment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class testApp {
	
	
	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String testStartDate = null;
		String testEndDate = null;
		String testStartTime = null;
		String testEndTime = null;
		try {
			System.out.println("Enter start date in format: 03/24/2013");
			testStartDate = in.readLine();
			System.out.println("Enter start time in format: 21:54");
			testStartTime = in.readLine();
			System.out.println("Enter end date in format: 03/24/2013");
			testStartDate = in.readLine();
			System.out.println("Enter end time in format: 21:54");
			testEndTime = in.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		Date startTimeDate = null;
		Date endTimeDate = null;
		
		try {
			startTimeDate = simpleDateTimeFormat.parse(testStartDate+" "+testStartTime);
			endTimeDate = simpleDateTimeFormat.parse(testEndDate+" "+testEndTime);
			System.out.println((endTimeDate.getTime()/60000) - (startTimeDate.getTime()/60000));
			System.out.println(startTimeDate);
			System.out.println(endTimeDate);
		}
		catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
	}
}
