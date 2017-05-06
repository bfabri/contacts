package br.com.fabri.contacts.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@ApplicationScoped
public class DynamoDBMapperFactory {

	public DynamoDBMapperFactory() {
	}
	
	@Produces
	public DynamoDBMapper createDynamoDBMapper() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new DefaultAWSCredentialsProviderChain())
			.withRegion(Regions.US_EAST_1)
			.build();
		
		return new DynamoDBMapper(client);
	}
	
	public void close(@Disposes DynamoDBMapper dynamoDBMapper) {
    }
}
