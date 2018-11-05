package inputoutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

	private static String csvFilePath = "/home/preetykumari/Projects/travel-growth/scrapper-app/Content Format - Requirement - Airport Data Points updated v2.1.csv";
	String[] dataFields = null;

	public static void main(String[] args) {
		CSVReader reader = new CSVReader();
		//reader.getCSVData(csvFilePath);
		
	}
	

	public void getCSVData(String path) {
		Path filePath = Paths.get(path);
		try {
			BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
			String line = br.readLine();
			dataFields = line.split(",");

			System.out.println(line);
			System.out.println("total dataFields: " + dataFields.length);

			ArrayList<Map<String, String>> airports = new ArrayList<Map<String, String>>();
			while ((line = br.readLine()) != null) {

				String[] dataValues = line.split(",");

				airports.add(createAirport(dataValues));
			}
			System.out.println("total airports: " + airports.size());

			// new Mongo().createMany("flights_airports_details_test", airports);
			// System.out.println("completed");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, String> createAirport(String[] dataValues) {
		Map<String, String> airport = new LinkedHashMap<String, String>();
		int i = 0;
		int size = dataValues.length;
		for (String key : dataFields) {
			if (i < size) {
				airport.put(key, dataValues[i++]);
			} else {
				airport.put(key, "");
			}

		}
		return airport;
	}

}

class Airport {

}
