package com.preety.rest.webservices.fileapis.payload;

public class ImageResponse {
	private String name;
	private String data;
	
	public ImageResponse() {}

	public ImageResponse(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("ImageResponse [name= %s, data= %s ]",name, data);
	}
	

}
