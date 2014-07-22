package com.github.alex_moon.trim.term;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Controller extends Thread {
    private Map<String, Term> terms;
    private Term term;

    public Controller() {
        terms = new HashMap<String, Term>();
    }

    public Term getTerm(String termString) {
        if (!terms.containsKey(termString)) {
            return null;
        } else {
            return terms.get(termString);
        }
    }

    public Term putTerm(String termString, Double score) {
        Term term;
        if (!terms.containsKey(termString)) {
            term = new Term(termString);
            terms.put(termString, term);
        } else {
            term = terms.get(termString);
        }
        term.update(score);
        return term;
    }
}
