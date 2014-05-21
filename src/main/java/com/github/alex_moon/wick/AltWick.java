package com.github.alex_moon.wick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.github.alex_moon.wick.term.Controller;
import com.github.alex_moon.wick.term.Term;

public class AltWick extends HttpServlet {
	private Controller controller;

	public void init() {
		if (controller == null) {
			controller = new Controller();
			controller.start();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
		PrintWriter out = res.getWriter();
        String url = req.getRequestURL().toString();
        List<String> url_path = Arrays.asList(url.split("/"));

		String termString;
		Double score;
		String lastToken = url_path.get(url_path.size()-1);
		if (lastToken.equals("json")) {
			BufferedReader reader = req.getReader();
			JSONObject jsonObject = (JSONObject) JSONValue.parse(reader);
			termString = (String) jsonObject.get("term");
			score = (Double) jsonObject.get("score");
		} else {
			termString = url_path.get(url_path.size()-2);
			score = Double.parseDouble(lastToken);
		}

		Term term = controller.putTerm(termString, score);
		res.setContentType("application/json");
		out.println(JSONValue.toJSONString(term.toJson()));
		out.close();
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        String url = req.getRequestURL().toString();
        List<String> url_path = Arrays.asList(url.split("/"));

        String termString = url_path.get(url_path.size()-1);

        Term term = controller.getTerm(termString);
        res.setContentType("application/json");
        if (term == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Term " + termString + " not found");
        } else {
            out.println(JSONValue.toJSONString(term.toJson()));
        }
        out.close();
    }
}
