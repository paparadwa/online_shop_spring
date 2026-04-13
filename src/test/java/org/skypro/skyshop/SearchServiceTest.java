package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    StorageService storageService;
    @InjectMocks
    SearchService searchService;

    @Test
    void searchNoObjectsInStorageServiceTest() {
        Mockito.when(storageService.mergeMaps()).thenReturn(new HashMap<>());
        Collection<SearchResult> results = searchService.search("Помидор");
        assertThat(results).isEmpty();
    }

    @Test
    void searchIfObjectsExistButNoSuitableTest() {
        Product product1 = new SimpleProduct("Помидор", UUID.randomUUID(), 100);
        Product product2 = new SimpleProduct("Огурец", UUID.randomUUID(), 80);
        Map<UUID, Searchable> testMap = new HashMap<>();
        testMap.put(product1.getId(), product1);
        testMap.put(product2.getId(), product2);
        Mockito.when(storageService.mergeMaps()).thenReturn(testMap);
        Collection<SearchResult> results = searchService.search("Хлеб");
        assertThat(results).isEmpty();
    }

    @Test
    void searchSuitableObjectTest() {
        Product product1 = new SimpleProduct("Помидор", UUID.randomUUID(), 100);
        Product product2 = new SimpleProduct("Огурец", UUID.randomUUID(), 80);
        Map<UUID, Searchable> testMap = new HashMap<>();
        testMap.put(product1.getId(), product1);
        testMap.put(product2.getId(), product2);
        Mockito.when(storageService.mergeMaps()).thenReturn(testMap);
        Collection<SearchResult> results = searchService.search("Помидор");
        assertThat(results).isNotEmpty();
        assertEquals(1, results.size());
    }

}
