package com.github.alex_moon.wick.term;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonValue;

public class Term {
	private String termString;
	private Double mean = 0.0;
	private Double standardDeviation = 0.0;
	
	public Term(String termString) {
		this.termString = termString;
	}
	
	public void update(Double score) {
		mean = standardDeviation = score;
	}
	
	public Double getMean() {
		return mean;
	}
	
	public Double getStandardDeviation() {
		return standardDeviation;
	}
	
	public String toString() {
		return termString;
	}
	
	@JsonValue
	public Map<String, Object> serialise() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("term", termString);
		result.put("mean", mean);
		result.put("sd", standardDeviation);
		return result;
	}
}
