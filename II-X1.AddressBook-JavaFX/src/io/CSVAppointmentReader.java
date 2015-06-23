package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import appointment.Appointment;
import appointment.IllegalTimeException;

/**
 * Liest aus einer csv Datei die Appointment Daten Zeile für Zeile ein.
 * Erzeugt ein leeres Dummy Appointment, wenn aus den gegebenen Werten kein Appointment erzeugt werden kann
 * Gibt eine Liste der Appointments wieder
 */
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
				target.add(new Appointment());
				System.out.println(e);
			}
		}
		inBuf.close();
		
		return target;

	}

}