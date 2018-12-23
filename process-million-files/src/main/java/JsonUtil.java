import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtil {

	public static String readFromFile(String path) {

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) obj;

			String fileName = (String) jsonObject.get("fileName");
			String errorCause = (String) jsonObject.get("errorCause");
			return errorCause;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeToFile(String filePath, String errorCause) {

		JSONObject obj = new JSONObject();
		obj.put("filePath", filePath);
		obj.put("errorCause", errorCause);

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			System.out.println("path: " + e.getMessage());
		}
	}
}
