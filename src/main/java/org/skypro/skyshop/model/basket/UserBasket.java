package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.basket.BasketItem;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> products;
    private final int total;

    public UserBasket(List<BasketItem> products) {
        this.products = products;
        this.total = products.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getProducts() {
        return products;
    }

    public int getTotal() {
        return total;
    }
}
