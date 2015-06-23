package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import appointment.Appointment;
/**
 * Schreibt die übergebenen Daten aus einer Liste von Appointments in eine CSV Datei
 */
public class CSVAppointmentWriter {
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void writeEntityList(
			List<Appointment> appointm, String filename,
			String splitter)

	throws IOException {
		
		FileOutputStream outFl = new FileOutputStream(filename);
		OutputStreamWriter outWr = new OutputStreamWriter(outFl);
		BufferedWriter outBuf = new BufferedWriter(outWr);

		for (Appointment appo : appointm) {
			outBuf.write(appointmentAsCSVLine(appo, splitter));
			outBuf.newLine();

		}
		
		outBuf.flush();
		outBuf.close();
		
	}

	private static String appointmentAsCSVLine(Appointment a,
			String splitter) {

		return a.getDateContent() + splitter + a.getCategory() + splitter
				+ a.getStartDate() + splitter + a.getStartTime() + splitter
				+ a.getEndDate() + splitter + a.getEndTime();
	}
}