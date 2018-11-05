package com.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class bus {
	@Id
	private int id;

}
