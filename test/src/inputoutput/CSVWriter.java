package inputoutput;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class CSVWriter {
	private static String jsonFilePath = "/home/preetykumari/Projects/travel-growth/travel-seo-apis/src/assets/airports.json";
	String[] dataFields = null;

	public static void main(String[] args) {
		CSVWriter writer = new CSVWriter();
		// writer.writeToCSV(csvFilePath);
		writer.readJsonFile();

	}

	public void readJsonFile() {
		try {
			// BufferedWriter bw= Files.newBufferedWriter(Paths.get(jsonFilePath),
			// StandardCharsets.UTF_8, StandardOpenOption.WRITE);
			JSONParser jsonParser = new JSONParser();
			try {
				// read from json has array of data
				// JSONArray airportList = (JSONArray) jsonParser.parse(new
				// FileReader(jsonFilePath));
				// airportList.forEach(doc -> readAirportObject((JSONObject)doc));

				// read from json has object of data

				JSONObject airports = (JSONObject) jsonParser.parse(new FileReader(jsonFilePath));
				readAirportObject(airports);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readAirportObject(JSONObject obj) {
		for (Object key : obj.keySet()) {
			// System.out.println(key + ", " + obj.get(key));
			JSONObject o = (JSONObject) obj.get(key);
			//String airportName = (String) o.get("airport_name");
			// System.out.println(key + ", " + airportName);
			updateAirport(key, o);
		}

	}

	public void updateAirports(JSONObject obj) {
		List<Document> documents = (List<Document>) new Mongo().findMany("flights_airports_details_test");

		for (Document document : documents) {
			// String airportName = document.getString("airport_name");
			// String iataCode = document.getString("iata_code");
			// System.out.println(airportName + ", " + iataCode);
			updateAirport(document, obj);
		}

	}

	public void updateAirport(Object key, JSONObject obj) {
		Bson query = Filters.eq("iata_code", key);
		Bson update = Updates.combine(Updates.set("airport_name", obj.get("airport_name")),
				Updates.currentDate("lastModified"));

		new Mongo().updateOne("flights_airports_details_test", query, update);

	}

}
