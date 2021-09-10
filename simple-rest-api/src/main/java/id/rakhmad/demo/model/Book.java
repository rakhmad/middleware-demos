package id.rakhmad.demo.model;

import java.util.List;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Book extends PanacheEntity {
    public String title;
    public String author;
    public String isbn_code;
    public String publisher;

    public static List<Book> findByPublisher(String publisher) {
        return find("publisher", publisher).list();
    }
}
