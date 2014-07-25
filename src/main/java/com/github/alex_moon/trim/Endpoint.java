package com.github.alex_moon.trim;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.alex_moon.trim.term.Term;
import com.github.alex_moon.trim.text.Text;
import com.sun.jersey.spi.resource.Singleton;

@Path("/")
@Singleton
public class Endpoint {
    private Text text;

    @GET
    @Path("term/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public Term getTerm(@PathParam("term") String term) {
        return Application.getTermController().getTerm(term);
    }

    @POST
    @Path("term/{term}/{score}")
    @Produces(MediaType.APPLICATION_JSON)
    public Term putTerm(@PathParam("term") String term, @PathParam("score") Double score) {
        return Application.getTermController().putTerm(term, score);
    }

    @POST
    @Path("term/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Term putTermAsJson(Map<String, Object> termData) {
        String term = (String) termData.get("term");
        Double score = (Double) termData.get("score");
        return Application.getTermController().putTerm(term, score);
    }

    @POST
    @Path("text/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Text putTextAsJson(Map<String, String> textData) {
        String textString = textData.get("text");
        if (textData.containsKey("uuid")) {
            String uuid = textData.get("uuid");
            text = new Text(textString, uuid);
        } else {
            text = new Text(textString);
        }

        Map<String, Double> proportions = text.getProportions();
        for (String termString : proportions.keySet()) {
            Term term = Application.getTermController().getTerm(termString);
            Double proportion = proportions.get(termString);
            if (term == null) {
                term = Application.getTermController().putTerm(termString, proportion);
            } else {
                term.update(proportion);
            }
        }

        for (String termString : proportions.keySet()) {
            for (String coString : proportions.keySet()) {
                if (!coString.equals(termString)) {
                    Term term = Application.getTermController().getTerm(termString);
                    Term coTerm = Application.getTermController().getTerm(coString);
                    Application.getCorrelationController().updateCorrelation(term, coTerm);
                }
            }
        }

        return text;
    }
}
