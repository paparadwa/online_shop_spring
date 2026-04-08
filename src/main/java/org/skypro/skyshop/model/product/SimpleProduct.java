package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, UUID id, int price) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Некорректная цена продукта!");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return name + ": " + price;
    }
}
