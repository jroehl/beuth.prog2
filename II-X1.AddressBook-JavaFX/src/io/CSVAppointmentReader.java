package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import appointment.Appointment;
import appointment.IllegalTimeException;

public class CSVAppointmentReader {

	public static List<Appointment> readEntityList(String dateiname,
			String splitter) throws IOException {

		FileInputStream inFl = new FileInputStream(dateiname);
		InputStreamReader inWr = new InputStreamReader(inFl);
		BufferedReader inBuf = new BufferedReader(inWr);

		List<Appointment> target = new ArrayList<>();
		String line = null;
		
		while ((line = inBuf.readLine()) != null) {
			try {
				target.add(new Appointment(line.split(splitter)));
			} catch (IllegalTimeException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		inBuf.close();
		
		return target;

	}

}