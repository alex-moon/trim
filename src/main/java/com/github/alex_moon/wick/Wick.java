package com.github.alex_moon.wick;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/term")
public class Wick {
	private class Term {
		private String term;
		public Term(String shit) {
			term = shit;
		}
		public String toString() {
			return term;
		}
	}

    @GET
    @Path("/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public Term term(@PathParam("term") String term) {
        System.out.println(term);
        return new Term(term);
    }
    
    @PostContstruct
    public void init() {
    	System.out.println("fuck");
    }
}
