package com.preety.rest.webservices.fileapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.preety.rest.webservices.fileapis.Property.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@SpringBootApplication
public class FileApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileApisApplication.class, args);
	}
}
