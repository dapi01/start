package org.codenova.start.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Book {
    private int id;
    private String author;
    private String title;
    private String publisher;
    private LocalDate publishedAt;
}
