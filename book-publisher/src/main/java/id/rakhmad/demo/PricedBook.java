package id.rakhmad.demo;

import java.math.BigDecimal;

import javax.json.bind.annotation.JsonbCreator;

public class PricedBook {

    private String title;
    private BigDecimal price;

    private PricedBook(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    @JsonbCreator
    public static PricedBook of(String title, double price) {
        return new PricedBook(title, new BigDecimal(price).setScale(2));
    }

    public static PricedBook of(String title, BigDecimal price) {
        return new PricedBook(title, price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
