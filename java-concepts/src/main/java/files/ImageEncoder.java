package files;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class ImageEncoder {
	
	public String encodeBase64Image(String filePath) throws IOException {
		byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		return encodedString;
	}
	
	public void decodeBase64Image(String encodedString, String outputFileName) throws IOException {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
	}

}
