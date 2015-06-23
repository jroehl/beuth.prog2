package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import addressBook.ObservableContactDetails;

/**
 * Liest aus einer csv Datei die Kontakt-Daten Zeile für Zeile ein.
 * Erzeugt einen leeren Dummy Kontakt, wenn aus den gegebenen Werten kein Kontakt erzeugt werden kann
 * Gibt eine Liste der Appointments wieder
 */
public class CSVContactsReader {

	public static List<ObservableContactDetails> readEntityList(String dateiname, String splitter) {

		Path source = Paths.get(dateiname);
		return readEntityList(source, splitter);

	}

	public static List<ObservableContactDetails> readEntityList(Path source, String splitter) {

		List<ObservableContactDetails> target = new ArrayList<>();
		try {

			List<String> lines = Files.readAllLines(source);
			for (String line : lines) {
					target.add(new ObservableContactDetails(line.split(splitter)));
			}
		} catch (IOException e) {
			target.add(new ObservableContactDetails());
			System.out.println(e);
		}

		return target;

	}
}