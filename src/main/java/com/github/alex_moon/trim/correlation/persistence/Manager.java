package com.github.alex_moon.trim.correlation.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

public class Manager extends Thread {
    private LinkedBlockingQueue<Write> writeQueue;
    private AmazonDynamoDBClient client;
    private final String tableName = "Jaws"; 
    
    public Manager() {
        writeQueue = new LinkedBlockingQueue<Write>();
        client = getClient();
    }
    
    public void push(Write write) {
        writeQueue.add(write);
    }
    
    public void run() {        
        while (true) {
            try {
                Write write = writeQueue.take();
                handleUpdate(write);
                sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void handleUpdate(Write write) {

        String a = write.getPrimaryTerm();
        String b = write.getSecondaryTerm();
        String value = write.getCoefficient().toString();

        AttributeValueUpdate update = new AttributeValueUpdate()
            .withAction(AttributeAction.PUT)
            .withValue(new AttributeValue().withN(value));

        client.updateItem(buildUpdateRequest(a, b, update));
        client.updateItem(buildUpdateRequest(b, a, update));
    }
    
    private UpdateItemRequest buildUpdateRequest(String a, String b, AttributeValueUpdate update) {
        Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Term", new AttributeValue().withS(a));
        Map<String, AttributeValueUpdate> updates = new HashMap<String, AttributeValueUpdate>();
        updates.put(b, update);
        UpdateItemRequest updateItemRequest = new UpdateItemRequest()
            .withTableName(tableName)
            .withKey(key)
            .withAttributeUpdates(updates)
            .withReturnValues(ReturnValue.UPDATED_NEW);
        return updateItemRequest;
    }
    
    public AmazonDynamoDBClient getClient() {
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
}
