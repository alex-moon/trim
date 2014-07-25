package com.github.alex_moon.trim.term;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName="JawsSimple")
public class Term {
    private String termString;
    private Double mean = 0.5;
    private Double standardDeviation = 0.5;
    private Integer count = 0;

    private Double lastMean, lastStandardDeviation, lastScore;

    public Term(String termString) {
        this.termString = termString;
    }

    public void update(Double score) {
        count++;
        lastScore = score;
        lastMean = mean;
        lastStandardDeviation = standardDeviation;
        mean = mean + (lastScore - mean) / count;
        Double sumOfSquaredDifferences = (standardDeviation * standardDeviation) * (count - 1) + ((lastScore - lastMean) * (lastScore - mean));
        standardDeviation = Math.sqrt(sumOfSquaredDifferences / count);
    }

    public Double getMean() {
        return mean;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public Double getLastStandardDeviation() {
        return lastStandardDeviation;
    }

    public Double getLastMean() {
        return lastMean;
    }

    public Double getLastScore() {
        return lastScore;
    }

    public String getTerm() {
        return termString;
    }
}
