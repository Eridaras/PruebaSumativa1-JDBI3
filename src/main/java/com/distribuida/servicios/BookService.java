package com.distribuida.servicios;

import com.distribuida.dto.Book;

import java.util.List;

public interface BookService {
    List<Book> allBooks();
    Book findByBook(Integer id);
    void createBook(Book book);
    void deleteBook(Integer id);
    void updateBook(Book book,Integer id);
}