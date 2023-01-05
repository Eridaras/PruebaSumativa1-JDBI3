package com.distribuida.rest;

import com.distribuida.dto.Book;
import com.google.gson.Gson;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("books")
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
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
    public Response addBook(Book book){
        bookService.createBook(book);
        return Response.status((Response.Status.OK) ).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") Integer id){
        bookService.deleteBook(id);
        return Response.status((Response.Status.OK) ).build();

    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("id") Integer id){
        bookService.updateBook(book, id);
        return Response.status((Response.Status.OK) ).build();
    }
}
