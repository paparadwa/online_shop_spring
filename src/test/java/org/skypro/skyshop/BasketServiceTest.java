package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Optional;
import java.util.UUID;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    ProductBasket productBasket;
    @Mock
    StorageService storageService;
    @InjectMocks
    BasketService basketService;

    @Test
    void addNotExistingProductTest() {
        UUID invalidId = UUID.randomUUID();
        Mockito.when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidId));
    }

    @Test
    void addExistingProductTest() {
        SimpleProduct product = new SimpleProduct("Чебурек", UUID.randomUUID(), 100);
        Mockito.when(storageService.getProductById(product.getId())).thenReturn(Optional.of(product));
        basketService.addProduct(product.getId());
        Mockito.verify(productBasket, times(1)).addProduct(product.getId());
    }

    @Test
    void emptyBasketIfProductBasketIsEmpty() {
        Mockito.when(productBasket.getBasket()).thenReturn(new HashMap<>());
        UserBasket result = basketService.getUserBasket();
        assertThat(result.getTotal()).isZero();
        assertThat(result.getProducts()).isEmpty();
    }

    @Test
    void suitableBasketIfProductBasketNotEmpty() {
        // Given
        SimpleProduct product1 = new SimpleProduct("Чебурек", UUID.randomUUID(), 100);
        SimpleProduct product2 = new SimpleProduct("Перец", UUID.randomUUID(), 50);
        Map<UUID, Integer> testMap = new HashMap<>();
        testMap.put(product1.getId(), 1);
        testMap.put(product2.getId(), 1);
        Mockito.when(productBasket.getBasket()).thenReturn(testMap);
        Mockito.when(storageService.getProductById(product1.getId())).thenReturn(Optional.of(product1));
        Mockito.when(storageService.getProductById(product2.getId())).thenReturn(Optional.of(product2));
        UserBasket result = basketService.getUserBasket();
        assertThat(result.getProducts()).hasSize(2);
        assertThat(result.getTotal()).isEqualTo(150);
    }
}
