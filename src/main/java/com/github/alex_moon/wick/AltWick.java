package com.github.alex_moon.wick;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (url != null) {
            res.setContentType("application/json");
            List<String> url_path = Arrays.asList(url.split("/"));
            String termString = url_path.get(url_path.size()-2);
            Double score = Double.parseDouble(url_path.get(url_path.size()-1));
            Term term = controller.putTerm(termString, score);
            out.println(JSONValue.toJSONString(term.toJson()));
        }
        out.close();
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        String url = req.getRequestURL().toString();
        if (url != null) {
            res.setContentType("application/json");
            List<String> url_path = Arrays.asList(url.split("/"));
            Term term = controller.getTerm(url_path.get(url_path.size()-1));
            out.println(JSONValue.toJSONString(term.toJson()));
        }
        out.close();
    }
}
