package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final HashMap<UUID, Integer> basket = new HashMap<>();

    public ProductBasket() {
    }

    public void addProduct(UUID id) {
        basket.computeIfAbsent(id, k -> 0);
        basket.put(id, basket.get(id) + 1);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
