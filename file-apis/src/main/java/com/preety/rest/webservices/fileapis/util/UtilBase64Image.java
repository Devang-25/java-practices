package com.preety.rest.webservices.fileapis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class UtilBase64Image {
	public static String encoder(String imagePath) {
		File file = new File(imagePath);
		String base64Image = null;
		try {
			FileInputStream stream = new FileInputStream(file);
			byte[] imageBytes = new byte[(int) file.length()];

			stream.read(imageBytes);

			base64Image = Base64.getEncoder().encodeToString(imageBytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Image;
	}

	public static void decoder(String filepath, String encodedString) {
		try {
			FileOutputStream stream = new FileOutputStream(filepath);
			byte[] imagebyte = Base64.getDecoder().decode(encodedString);

			stream.write(imagebyte);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
