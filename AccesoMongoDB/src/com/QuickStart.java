package com;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

public class QuickStart {
    public static void main( String[] args ) {
        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "mongodb+srv://miusuario:Pass!123456@cluster0.wgwpvbi.mongodb.net/?appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("federacion");
            MongoCollection<Document> collection = database.getCollection("asociaciones");
            Document doc = collection.find(eq("nombre", "Patitos")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
}