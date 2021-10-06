package id.rakhmad.demo;

import java.math.BigDecimal;

public class Book {

    private String title;

    private Book(String title) {
        this.title = title;
    }

    public static Book of(String title) {
        return new Book(title);
    }

    public PricedBook withPrice(BigDecimal price) {
        return PricedBook.of(this.title, price);
    }

    public String getTitle() {
        return title;
    }

}
