package id.rakhmad.demo;

import java.math.BigDecimal;

public class Wine {

    private String name;

    private Wine(String name) {
        this.name = name;
    }

    public static Wine of(String name) {
        return new Wine(name);
    }

    public PricedWine withPrice(BigDecimal price) {
        return PricedWine.of(this.name, price);
    }

    public String getName() {
        return name;
    }

}