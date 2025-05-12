package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> availableProducts;
    private final Map<UUID, Article> availableArticles;

    public StorageService() {
        this.availableProducts = new HashMap<>();
        this.availableArticles = new HashMap<>();
        this.createTestData();
    }

    public Collection<Product> getProducts() {
        return availableProducts.values();
    }

    public Collection<Article> getArticles() {
        return availableArticles.values();
    }

    public Collection<Searchable> getSearchableItems() {
        List<Searchable> searchableItems = new ArrayList<>();
        searchableItems.addAll(this.availableProducts.values());
        searchableItems.addAll(this.availableArticles.values());
        return searchableItems;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }


    private void createTestData() {
        Product apple = new SimpleProduct("apple", 5, UUID.randomUUID());
        Product banana = new DiscountedProduct("Banana", 20, 35, UUID.randomUUID());
        Product sausage = new DiscountedProduct("Sausage", 50, 5, UUID.randomUUID());
        Product applesauce = new SimpleProduct("applesauce", 15, UUID.randomUUID());
        Product applejam = new SimpleProduct("applejam", 15, UUID.randomUUID());

        Article newArticle = new Article("A New app", "delicious applications beginning", UUID.randomUUID());
        Article newAppArticle = new Article("New app", "appappapp", UUID.randomUUID());

        this.availableProducts.put(apple.getId(), apple);
        this.availableProducts.put(banana.getId(), banana);
        this.availableProducts.put(sausage.getId(), sausage);
        this.availableProducts.put(applesauce.getId(), applesauce);
        this.availableProducts.put(applejam.getId(), applejam);
        this.availableArticles.put(newArticle.getId(), newArticle);
        this.availableArticles.put(newAppArticle.getId(), newAppArticle);
    }
}
