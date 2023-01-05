package com.distribuida.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;

@ApplicationScoped
@Data
public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private Double price;
}
