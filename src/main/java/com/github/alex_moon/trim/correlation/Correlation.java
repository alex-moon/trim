package com.github.alex_moon.trim.correlation;

import com.github.alex_moon.trim.Application;
import com.github.alex_moon.trim.term.Term;

public class Correlation {
    private Term primaryTerm;
    private Term secondaryTerm;
    private Double coefficient;
    private Integer n = 0;

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

    public void update() {
        n++;
        if (n > 2) {
            Double oldCoefficient = coefficient;
            Double oldCovariance = oldCoefficient * primaryTerm.getLastStandardDeviation() * secondaryTerm.getLastStandardDeviation();
            Double aDelta = primaryTerm.getLastScore() - primaryTerm.getLastMean();
            Double bDelta = secondaryTerm.getLastScore() - secondaryTerm.getLastMean();
            Double newCovariance = oldCovariance + (n - 1) * aDelta * bDelta / n;
            coefficient = newCovariance / (primaryTerm.getStandardDeviation() * secondaryTerm.getStandardDeviation());
        } else {
            coefficient = 1.0;
        }
    }
}
