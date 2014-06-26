package com.github.alex_moon.wick.term;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Controller extends Thread {
	private Map<String, Term> terms;
	private List<String> termStrings;
	private Random random;

	private Term term;

	public Controller() {
		random = new Random();
		termStrings = Arrays.asList("limpid", "sod", "grandiloquent", "hetorodoxia", "fulgurant");
		terms = new HashMap<String, Term>();
		for (String termString : termStrings) {
			terms.put(termString, new Term(termString));
		}
	}

	public void run() {
		// for our purposes all we want is to generate a bunch of random term values
		while (true) {
			String termString = termStrings.get(random.nextInt(termStrings.size()));
			term = getTerm(termString);
			Double score = random.nextDouble();
			term.update(score);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				break;
			}
		}
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
