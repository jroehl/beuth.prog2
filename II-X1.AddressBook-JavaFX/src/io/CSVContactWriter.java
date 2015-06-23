package io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import addressBook.ObservableContactDetails;

/**
 * Schreibt die übergebenen Daten aus einer Liste von Kontakten in eine CSV Datei
 */
public class CSVContactWriter {
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void writeEntityList(
			List<ObservableContactDetails> contacts, String filename,
			String splitter)

	throws IOException {

		Path path = Paths.get(filename);
		writeEntityList(contacts, path, splitter);
	}

	public static void writeEntityList(List<ObservableContactDetails> contacts,
			Path path, String splitter)

	throws IOException {

		List<String> lines = new ArrayList<>();
		for (ObservableContactDetails contact : contacts) {

			lines.add(contactAsCSVLine(contact, splitter));

		}

		Files.write(path, lines, ENCODING);
	}

	private static String contactAsCSVLine(ObservableContactDetails c,
			String splitter) {

		return c.getLastName() + splitter + c.getFirstName() + splitter
				+ c.getAddress() + splitter + c.getPhone() + splitter
				+ c.getMail();
	}
}