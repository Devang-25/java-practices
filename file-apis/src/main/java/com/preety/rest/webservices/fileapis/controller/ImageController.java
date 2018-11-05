package com.preety.rest.webservices.fileapis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.preety.rest.webservices.fileapis.payload.ImageResponse;
import com.preety.rest.webservices.fileapis.util.UtilBase64Image;

@RestController
public class ImageController {
	
	@PostMapping("/image")
	public String postImage(@RequestBody ImageResponse image) {
		String path= "./images/";
		UtilBase64Image.decoder(path, image.getData());
		return "Successful";
	}
	
	@GetMapping("/image")
	public ImageResponse getImage(@RequestParam String imageName) {
		String path= "./images/" + imageName;
		String imageEncoded= UtilBase64Image.encoder(path);
		return new ImageResponse(imageName, imageEncoded);
	}

}
