package inputoutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Mongo {
	MongoClient mongo;

	public MongoDatabase connect() {
		mongo = new MongoClient("localhost", 27017);

		MongoDatabase db = mongo.getDatabase("seo");
		return db;

	}

	public void createMany(String collection, ArrayList<Map<String, String>> docs) {
		try {
			MongoDatabase db = connect();
			MongoCollection coll = db.getCollection(collection);
			ArrayList<Document> documents = new ArrayList<Document>();

			for (Map document : docs) {
				Document doc = new Document();
				doc.putAll(document);
				documents.add(doc);
			}

			coll.insertMany(documents);

		} catch (MongoException e) {
			System.out.println("MongoException error: " + e);
		} catch (Exception e) {
			System.out.println("error: " + e);
		} finally {
			mongo.close();
		}

	}

	public List<Document> findMany(String collection) {
		try {
			MongoDatabase db = connect();
			MongoCollection<Document> coll = db.getCollection(collection);
			FindIterable<Document> it = coll.find();
			List<Document> documents = (List<Document>) it.into(new ArrayList<Document>());
			return documents;
		} finally {
			mongo.close();
		}
	}

	public void updateOne(String collection, Bson query, Bson update) {
		try {
			MongoDatabase db = connect();
			MongoCollection<Document> coll = db.getCollection(collection);
			coll.updateMany(query, update);
		} finally {
			mongo.close();
		}
	}

}
