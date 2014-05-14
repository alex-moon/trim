package com.github.alex_moon.wick.term;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Controller extends Thread {
	private Map<String, Term> terms;
	private List<String> termStrings = Arrays.asList("limpid", "sod", "grandiloquent", "indubitable", "discretion");
	private Random random = new Random();
	
	private Term term;

	public void run() {
		// for our purposes all we want is to generate a bunch of random term values
		while (true) {
			String termString = termStrings.get(random.nextInt(termStrings.size()));
			term = getTerm(termString);
			Double score = random.nextDouble();
			term.update(score);
		}
	}
	
	public Term getTerm(String termString) {
		if (!terms.containsKey(termString)) {
			term = new Term(termString);
			terms.put(termString, term);
		} else {
			term = terms.get(termString);
		}
		return term;
	}
}
