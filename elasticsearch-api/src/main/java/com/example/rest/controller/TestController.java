package com.example.rest.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	private static TransportClient client = null;

	@SuppressWarnings("resource")
	public static void createClient() throws UnknownHostException {
		// on startup

		Settings settings = Settings.builder().put("client.transport.sniff", true).build();
		client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
				.addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));

	}

	public static void closeClient() {
		// on shutdown
		client.close();
	}

	@GetMapping("/test")
	@ResponseBody
	public IndexResponse getResponse() {
		try {
			createClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndexResponse response = client.prepareIndex("kodcucom", "article", "1")
				.setSource(putJsonDocument("ElasticSearch: Java API",
						"ElasticSearch provides the Java API, all operations "
								+ "can be executed asynchronously using a client object.",
						new Date(), new String[] { "elasticsearch" }, "Hüseyin Akdoğan"))
				.execute().actionGet();

		return response;
	}

	public static Map<String, Object> putJsonDocument(String title, String content, Date postDate, String[] tags,
			String author) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("title", title);
		jsonDocument.put("content", content);
		jsonDocument.put("postDate", postDate);
		jsonDocument.put("tags", tags);
		jsonDocument.put("author", author);
		return jsonDocument;
	}

}
