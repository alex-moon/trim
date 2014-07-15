package com.github.alex_moon.trim.correlation.persistence;

import java.util.Date;

import com.github.alex_moon.trim.correlation.Correlation;

public class Write {
    private String primaryTerm;
    private String secondaryTerm;
    private Double coefficient;
    private Date timestamp;
    
    public Write(Correlation correlation) {
        primaryTerm = correlation.getPrimaryTerm().getTerm();
        secondaryTerm = correlation.getSecondaryTerm().getTerm();
        coefficient = correlation.getCoefficient();
        timestamp = new Date();
    }
    
    public String getPrimaryTerm() {
        return primaryTerm;
    }
    
    public String getSecondaryTerm() {
        return secondaryTerm;
    }
    
    public Double getCoefficient() {
        return coefficient;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
}
