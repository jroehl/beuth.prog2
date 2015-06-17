package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import addressBook.ObservableContactDetails;

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
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
			target.addAll(null); // null addition to target to indicate problem
		}

		return target;

	}
}