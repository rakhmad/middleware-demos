package id.rakhmad.demo;

import java.math.BigDecimal;

import javax.json.bind.annotation.JsonbCreator;

public class PricedWine {

    private String name;

    private BigDecimal price;

    private PricedWine(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @JsonbCreator
    public static PricedWine of(String name, double price) {
        return new PricedWine(name, new BigDecimal(price).setScale(2));
    }

    public static PricedWine of(String name, BigDecimal price) {
        return new PricedWine(name, price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

}