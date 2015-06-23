package io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import appointment.Appointment;
import appointment.IllegalTimeException;

/**
 * Liest aus einer Binärdatei die Appointment Daten ein.
 * Benutzt die geworfenen EOFException um das Lesen aus der Datei (am Dateiende) zu beenden und den DataInputStream zu schließen
 * Erzeugt ein leeres Dummy Appointment, wenn aus den gegebenen Werten kein Appointment erzeugt werden kann
 * Gibt eine Liste der Appointments wieder
 */
public class BINAppointmentReader {

	public static List<Appointment> readEntityList(String dateiname,
			String splitter) throws IOException {

		DataInputStream in  = new DataInputStream(new FileInputStream(dateiname));
		List<Appointment> target = new ArrayList<>();
		
		try {
			while (true) {
				try {
					target.add(new Appointment(in.readUTF().split(splitter)));
				} catch (IllegalTimeException e) {
					target.add(new Appointment());
					System.out.println(e);
				}
			}
		} catch (EOFException e) {
			in.close();
		}
			
		return target;

	}

}