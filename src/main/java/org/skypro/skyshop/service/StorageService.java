package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService(Map<UUID, Product> productMap, Map<UUID, Article> articleMap) {
        this.productMap = productMap;
        this.articleMap = articleMap;
        fillMap();
    }

    public Map<UUID, Article> getArticleMap() {
        return articleMap;
    }

    public Map<UUID, Product> getProductMap() {
        return productMap;
    }

    private void fillMap() {
        DiscountedProduct product1 = new DiscountedProduct("Помидор", UUID.randomUUID(), 20, 5);
        FixPriceProduct product2 = new FixPriceProduct("Огурец", UUID.randomUUID());
        SimpleProduct product3 = new SimpleProduct("Перец", UUID.randomUUID(), 15);
        Article article1 = new Article("Помидор", UUID.randomUUID(), "Красный овощ");
        Article article2 = new Article("Огурец", UUID.randomUUID(), "Зелёный овощ");
        Article article3 = new Article("Перец", UUID.randomUUID(), "Жёлтый/зелёный/красный овощ");
        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        productMap.put(product3.getId(), product3);
        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
        articleMap.put(article3.getId(), article3);
    }

    public Map<UUID, Searchable> mergeMaps() {
        Map<UUID, Searchable> result = new HashMap<>();
        result.putAll(productMap);
        result.putAll(articleMap);
        return result;
    }
}
