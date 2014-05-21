package com.github.alex_moon.wick.term;

import java.util.HashMap;
import java.util.Map;

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
		count ++;
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
	
	public String getTerm() {
		return termString;
	}
	
    public Map<String, Object> toJson() {
        // returns a JSON string for means and standard deviations
        Map<String, Object> result = new HashMap<String, Object>();
        
        result.put("term", termString);
        result.put("mean", mean);
        result.put("standard_deviation", standardDeviation);
        
        return result;
    }
}
