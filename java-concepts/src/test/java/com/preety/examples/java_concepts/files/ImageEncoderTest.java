package com.preety.examples.java_concepts.files;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ImageEncoderTest {
	 private String inputFilePath = "test_image.jpg";
	    private String outputFilePath = "test_image_copy.jpg";
	 
	    @Test
	    public void fileToBase64StringConversion() throws IOException {
	        // load file from /src/test/resources
	        
	        File picturehomedir = new File(System.getProperty("user.home")+"/Pictures/");
	        System.out.println(picturehomedir);
	        File inputFile = new File(picturehomedir, inputFilePath);
	 
	        byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
	        String encodedString = Base64
	          .getEncoder()
	          .encodeToString(fileContent);
	 
	        // create output file
	        String filepath= inputFile
	  	          .getParentFile()
		          .getAbsolutePath() + File.separator;
	        System.out.println(filepath);
	        File outputFile = new File(filepath + outputFilePath);
	 
	        // decode the string and write to file
	        byte[] decodedBytes = Base64
	          .getDecoder()
	          .decode(encodedString);
	        FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
	 
	        assertTrue(FileUtils.contentEquals(inputFile, outputFile));
	    }
	}
