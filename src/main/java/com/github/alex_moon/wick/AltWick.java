package com.github.alex_moon.wick;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        String url = req.getRequestURL().toString();
        if (url != null) {
            res.setContentType("application/json");
            // Term query = controller.getTerm(term);
            // out.println(JSONValue.toJSONString(factsToJson));
            out.println(url);
        }
        out.close();
    }
}
