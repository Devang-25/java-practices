
package com.preety.rest.webservices.fileapis;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.preety.rest.webservices.fileapis.service.RestfulClient;

//@SpringBootApplication
public class ImageApplication {

	public static void main(String[] args) {

		/*
		 * POST ENTITY
		 */

		RestfulClient restfulClient = new RestfulClient();

		restfulClient.postEntity();

		/*
		 * GET ENTITY
		 */
		restfulClient.getEntity();
	}
}
