package com.github.alex_moon.wick;

import javax.ws.rs.GET;
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

    @GET
    @Path("/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public Term term(@PathParam("term") String term) {
        if (controller == null) {
        	controller = new Controller();
        	controller.start();
        	System.out.println("Term Controller is not started! Starting...");
        }
        return controller.getTerm(term);
    }
}
