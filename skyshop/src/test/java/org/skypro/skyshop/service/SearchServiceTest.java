package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;


    @Test
    void search_noItems() {
        System.out.println("StorageService mock: " + storageService);
        lenient().when(storageService.getSearchableItems()).thenReturn(Collections.emptyList());
        List<SearchResult> results = searchService.search("book");
        assertTrue(results.isEmpty());
    }

    @Test
    void search_noMatch() {
        List<Searchable> searchableItems = new ArrayList<>();
        searchableItems.add(new SimpleProduct("Apple", 1, UUID.randomUUID()));
        lenient().when(storageService.getSearchableItems()).thenReturn(searchableItems);

        List<SearchResult> results = searchService.search("banana");
        assertTrue(results.isEmpty());
    }

    @Test
    void search_withMatch() {

        List<Searchable> searchableItems = new ArrayList<>();
        UUID bananaID = UUID.randomUUID();
        searchableItems.add(new SimpleProduct("banana", 1, bananaID));
        when(storageService.getSearchableItems()).thenReturn(searchableItems);


        List<SearchResult> results = searchService.search("banana");

        assertEquals(1, results.size());
        assertEquals(bananaID.toString(), results.get(0).getId());
        assertEquals("banana", results.get(0).getName());
        assertEquals("PRODUCT", results.get(0).getContentType());

    }
}
