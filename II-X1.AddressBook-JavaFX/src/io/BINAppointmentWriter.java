package io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import appointment.Appointment;

public class BINAppointmentWriter {
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void writeEntityList(
			List<Appointment> appointm, String filename,
			String splitter)

	throws IOException {
		
		DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));

		for (Appointment appo : appointm) {
			out.writeUTF(appointmentAsCSVLine(appo, splitter));
		}
		
		out.flush();
		out.close();
	}

	private static String appointmentAsCSVLine(Appointment a,
			String splitter) {

		return a.getDateContent() + splitter + a.getCategory() + splitter
				+ a.getStartDate() + splitter + a.getStartTime() + splitter
				+ a.getEndDate() + splitter + a.getEndTime();
	}
}