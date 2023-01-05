package com.distribuida.rest;

import com.google.gson.Gson;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("books")
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll(){
        String json = new Gson().toJson(bookService.allBooks());
        return json;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findById (@PathParam("id") Integer id) {
        String json = new Gson().toJson(bookService.findByBook(id) );
        return json;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String librito(){
        System.out.println("Si Funciona");
    }
}
