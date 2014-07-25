package com.github.alex_moon.trim;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.github.alex_moon.trim.term.Term;

public class Application {
    private static DynamoDBMapper mapper;
    private static AmazonDynamoDBClient client;
    private static com.github.alex_moon.trim.term.Controller termController = getTermController();
    private static com.github.alex_moon.trim.text.Controller textController = getTextController();
    private static com.github.alex_moon.trim.correlation.Controller correlationController = getCorrelationController();

    public static DynamoDBMapper getMapper() {
        if (mapper == null) {
            mapper = new DynamoDBMapper(getClient());
        }
        return mapper;
    }
    
    public static AmazonDynamoDBClient getClient() {
        if (client == null) {
            try {
                // Dev uses profile credentials
                AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
                client = new AmazonDynamoDBClient(credentials);
            } catch (IllegalArgumentException e) {
                // Live uses EC2 service role
                client = new AmazonDynamoDBClient();
            }

            Region ireland = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(ireland);
        }

        return client;
    }

    public static com.github.alex_moon.trim.term.Controller getTermController() {
        if (termController == null) {
            termController = new com.github.alex_moon.trim.term.Controller();
            termController.start();
        }
        return termController;
    }

    public static com.github.alex_moon.trim.text.Controller getTextController() {
        if (textController == null) {
            textController = new com.github.alex_moon.trim.text.Controller();
            textController.start();
        }
        return textController;
    }

    public static com.github.alex_moon.trim.correlation.Controller getCorrelationController() {
        if (correlationController == null) {
            correlationController = new com.github.alex_moon.trim.correlation.Controller();
            correlationController.start();
        }
        return correlationController;
    }
}
