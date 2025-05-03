package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService() {
        this.storageService = new StorageService();
    }

    public List<SearchResult> search(String SearchString) {
        return storageService.getSearchableItems()
                .stream()
                .filter(searchable -> searchable.searchTerm().toLowerCase().contains(SearchString.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}
