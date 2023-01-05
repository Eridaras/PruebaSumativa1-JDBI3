package com.distribuida.servicios;

import com.distribuida.dto.Book;

import java.util.List;

public interface BookService {
    List<Book> allBooks();
    Book findByBook(Integer id);
}