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

    //Listar todos los libros
    @Override
    public List<Book> allBooks() {
        Jdbi jdbi = Jdbi.create(dataSource);
        Handle con = jdbi.open();
        List<Book> books =
                con.createQuery("SELECT * FROM books ORDER BY id ASC")
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

    @Override
    public void createBook(Book book) {
        Jdbi jdbi = Jdbi.create(dataSource);
        Handle con = jdbi.open();
        con.createUpdate("insert into books (isbn, title, author, price ) values (?, ?, ?, ?)")
                .bind(0, book.getIsbn())
                .bind(1, book.getTitle())
                .bind(2, book.getAuthor())
                .bind(3, book.getPrice())
                .execute();
    }
    @Override
    public void deleteBook(Integer id) {
        Jdbi jdbi = Jdbi.create(dataSource);
        Handle con = jdbi.open();
        int count = con.execute("DELETE FROM \"books\" WHERE id =  ?", id);
    }
    @Override
    public void updateBook(Book book, Integer id) {
        Jdbi jdbi = Jdbi.create(dataSource);
        Handle con = jdbi.open();
        int count = con.createUpdate("UPDATE books SET isbn = :isbn, title=:title, author=:author, price=:price WHERE id = :id").
                bind("isbn",book.getIsbn()).
                bind("title", book.getTitle()).
                bind("author", book.getAuthor()).
                bind("price", book.getPrice()).
                bind("id",id)
                .execute();
    }
}