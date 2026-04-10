package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discountPercent;

    public DiscountedProduct(String name, UUID id, int basePrice, int discountPercent) {
        super(name, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Некорректная цена продукта!");
        }
        this.basePrice = basePrice;
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Некорректный процент для скидки!");
        }
        this.discountPercent = discountPercent;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1f - (discountPercent / 100f)));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return name + ": " + getPrice() + " (скидка " + discountPercent + "%)";
    }
}
