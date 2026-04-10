package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String name) {
        return storageService.mergeMaps().values().stream()
                .filter(searchable -> searchable.searchTerm().toLowerCase().contains(name))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
