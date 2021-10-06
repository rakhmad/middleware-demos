package id.rakhmad.demo;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Book {

    public String title;
    public int year;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public static Book of(String title) {
        int year = ThreadLocalRandom.current().nextInt(1980, 2020);
        return new Book(title, year);
    }

    public PricedBook withPrice(BigDecimal price) {
        return PricedBook.of(this.title, price);
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("Book %s is published on %d.", title, year);
    }
}
