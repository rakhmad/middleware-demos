package id.rakhmad.demo.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Book extends PanacheEntity {
    public String title;
    public String author;
    public String isbn_code;
    public String publisher;
}
