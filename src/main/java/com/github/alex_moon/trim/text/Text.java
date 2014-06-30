package com.github.alex_moon.trim.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Text {
    private String stringValue;
    private UUID uuid;

    private Map<String, Double> proportions = new HashMap<String, Double>();

    public Text(String initialStringValue) {
        stringValue = initialStringValue;
        uuid = UUID.randomUUID();
        buildProportions();
    }

    public Text(String initialStringValue, String uuid) {
        stringValue = initialStringValue;
        try {
            this.uuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            this.uuid = UUID.randomUUID();
        }
        buildProportions();
    }

    public void buildProportions() {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        Integer total = 0;

        for (String termString : asWordList()) {
            Integer count = 0;
            if (counts.containsKey(termString)) {
                count += counts.get(termString);
            }
            count++;
            total++;
            counts.put(termString, count);
        }

        for (String termString : counts.keySet()) {
            proportions.put(termString, counts.get(termString).doubleValue() / total);
        }
    }

    public Double getProportion(String termString) {
        return proportions.get(termString);
    }

    public String[] asWordList() {
        String result = stringValue.replaceAll("[^a-zA-Z0-9]+", " ");
        result = result.toLowerCase();
        String[] terms = result.split(" ");
        List<String> significantTerms = new ArrayList<String>();
        for (int i = 0; i < terms.length; i++) {
            if (! Controller.isStopWord(terms[i])
             && ! "".equals(terms[i])
             && ! (terms[i].length() < 3)) {
                significantTerms.add(terms[i]);
            }
        }
        return significantTerms.toArray(new String[significantTerms.size()]);
    }

    public String toString() {
        return stringValue;
    }

    public String getUuid() {
        return uuid.toString();
    }

    public Map<String, Double> getProportions() {
        return proportions;
    }
}