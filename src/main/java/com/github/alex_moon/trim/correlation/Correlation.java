package com.github.alex_moon.trim.correlation;

import com.github.alex_moon.trim.term.Term;

public class Correlation {
    private Term primaryTerm;
    private Term secondaryTerm;
    private Double coefficient;
    
    public Correlation(Term a, Term b, Double c) {
        primaryTerm = a;
        secondaryTerm = b;
        coefficient = c;
    }
    
    public Term getPrimaryTerm() {
        return primaryTerm;
    }
    
    public Term getSecondaryTerm() {
        return secondaryTerm;
    }
    
    public Double getCoefficient() {
        return coefficient;
    }
}
