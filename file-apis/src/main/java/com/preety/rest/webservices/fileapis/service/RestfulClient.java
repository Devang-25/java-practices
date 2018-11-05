package com.preety.rest.webservices.fileapis.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.preety.rest.webservices.fileapis.payload.ImageResponse;
import com.preety.rest.webservices.fileapis.util.UtilBase64Image;

public class RestfulClient {
	RestTemplate restTemplate;
	
	public RestfulClient(){
		restTemplate = new RestTemplate();
	}
	
	/**
	 * post entity
	 */
	public void postEntity(){
		System.out.println("Begin /POST request!");
		String postUrl = "http://localhost:8080/image";
		String name = "test_image.jpg";
		String imagePath = "./uploads/test_image.jpg";
		String data = UtilBase64Image.encoder(imagePath);
		
		System.out.println("Post Image'info: name = " + name + " ,data = " + data);
		ImageResponse image = new ImageResponse(name, data);
		
		ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, image, String.class);
		System.out.println("Response for Post Request: " + postResponse.getBody());
	}
	
	/**
	 * get entity
	 */
	public void getEntity(){
		System.out.println("Begin /GET request!");
		String getUrl = "http://localhost:8080/image?imageName=test_image.jpg";
		ResponseEntity<ImageResponse> getResponse = restTemplate.getForEntity(getUrl, ImageResponse.class);
		
		if(getResponse.getBody() != null){
			ImageResponse image = getResponse.getBody();
			System.out.println("Response for Get Request: " + image.toString());
			System.out.println("Save Image to ./images");
			UtilBase64Image.decoder( "./images/" + image.getName(), image.getData());
			System.out.println("Done!");
		}else{
			System.out.println("Response for Get Request: NULL");
		}
	}
}
