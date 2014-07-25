package com.github.alex_moon.trim.correlation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.alex_moon.trim.correlation.persistence.Manager;
import com.github.alex_moon.trim.correlation.persistence.Write;
import com.github.alex_moon.trim.term.Term;

public class Controller extends Thread {
    Map<Term, Map<Term, Correlation>> correlations;
    Manager persistenceManager;

    public void run() {
        correlations = new HashMap<Term, Map<Term, Correlation>>();
        persistenceManager = new Manager();
        persistenceManager.start();
    }

    public List<Correlation> getCorrelations(Term term) {
        Map<Term, Correlation> correlationsMap = correlations.get(term);
        return new ArrayList<Correlation>(correlationsMap.values());
    }

    public void addCorrelation(Correlation correlation) {
        Term a = correlation.getPrimaryTerm();
        Term b = correlation.getSecondaryTerm();
        if (correlations.containsKey(a)) {
            correlations.get(a).put(b, correlation);
            return;
        }
        if (correlations.containsKey(b)) {
            correlations.get(b).put(a, correlation);
            return;
        }
        Map<Term, Correlation> correlationMap = new HashMap<Term, Correlation>();
        correlationMap.put(b, correlation);
        correlations.put(a, correlationMap);
    }

    public void updateCorrelation(Term a, Term b) {
        Correlation correlation = null;
        if (correlations.containsKey(a)) {
            correlation = correlations.get(a).get(b);
        } else if (correlations.containsKey(b)) {
            correlation = correlations.get(b).get(a);
        }
        if (correlation == null) {
            correlation = new Correlation(a, b, 1.0);
            addCorrelation(correlation);
        }

        correlation.update();

        if (shouldStore(correlation.getCoefficient())) {
            Write write = new Write(correlation);
            persistenceManager.push(write);
        }
    }

    private Boolean shouldStore(Double coefficient) {
        if (coefficient.isNaN()) {
            return false;
        }
        // arbitrary range checker for demo purposes
        // inside +-(0.5-0.9)
        if (coefficient > 0.9) {
            return false;
        }
        if (coefficient > 0 && coefficient < 0.5) {
            return false;
        }
        if (coefficient < 0 && coefficient > -0.5) {
            return false;
        }
        if (coefficient < -0.9) {
            return false;
        }
        return true;
    }
}
