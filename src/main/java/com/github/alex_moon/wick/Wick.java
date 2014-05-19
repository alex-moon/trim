package com.github.alex_moon.wick;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.alex_moon.wick.term.Controller;
import com.github.alex_moon.wick.term.Term;
import com.sun.jersey.spi.resource.Singleton;

@Path("/term")
@Singleton
public class Wick {
	private Controller controller;

	public Wick() {
    	controller = new Controller();
    	controller.start();
    	System.out.println("Term Controller is not started! Starting...");
	}

	@POST
	@Path("/{term}/{score}")
	@Produces(MediaType.APPLICATION_JSON)
	public Term putTerm(@PathParam("term") String term, @PathParam("score") Double score) {
		// we want to create a new term if it doesn't exist, then upgrade it with
		// the given score - this should all be doable with existing Controller methods
		// @todo use POST body data for something
		return controller.putTerm(term, score);
	}

    @GET
    @Path("/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public Term getTerm(@PathParam("term") String term) {
        return controller.getTerm(term);
    }
}
