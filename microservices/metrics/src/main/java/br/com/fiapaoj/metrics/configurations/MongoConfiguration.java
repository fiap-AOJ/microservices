package br.com.fiapaoj.metrics.configurations;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.fiapaoj.metrics")
public class MongoConfiguration extends AbstractMongoConfiguration {

	private static final String DATABASE_NAME = "users";

	@Value("${mongo.client.host:127.0.0.1}")
	private String mongoClientHost;

	@Value("${mongo.client.port:27017}")
	private Integer mongoClientPort;


	@Override
	public MongoClient mongoClient() {
		return new MongoClient(mongoClientHost, mongoClientPort);
	}

	@Override
	protected String getDatabaseName() {
		return DATABASE_NAME;
	}
}