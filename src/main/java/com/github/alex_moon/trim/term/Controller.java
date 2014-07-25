package com.github.alex_moon.trim.term;

import java.util.HashMap;
import java.util.Map;

public class Controller extends Thread {
    private Map<String, Term> terms;

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
