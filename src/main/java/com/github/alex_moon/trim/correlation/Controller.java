package com.github.alex_moon.trim.correlation;

import java.util.List;
import java.util.Map;

import com.github.alex_moon.trim.term.Term;

public class Controller {
    Map<Term, List<Correlation>> correlations;
    
    public List<Correlation> getCorrelations(Term term) {
        return correlations.get(term);
    }
    
    public void addCorrelation(Correlation correlation) {
        correlations.get(correlation.getPrimaryTerm()).add(correlation);
    }
}
