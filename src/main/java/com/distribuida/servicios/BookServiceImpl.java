package com.distribuida.servicios;

import com.distribuida.dto.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import javax.sql.DataSource;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject
    private DataSource dataSource;
    @Inject
    private Book book;

    //Listar todos los libros
    @Override
    public List<Book> allBooks() {
        Jdbi jdbi = Jdbi.create(dataSource);
        Handle handle = jdbi.open();
        List<Book> books =
                handle.createQuery("SELECT * FROM books ORDER BY id ASC")
                        .mapToBean(Book.class)
                        .list();
        return books;
    }
    @Override
    public Book findByBook(Integer id) {
        Jdbi jdbi = Jdbi.create(dataSource);
        Handle con = jdbi.open();
        Book book = con.select("select * from books where id = ?", id)
                .mapToBean(Book.class)
                .first();
        return book;
    }

}