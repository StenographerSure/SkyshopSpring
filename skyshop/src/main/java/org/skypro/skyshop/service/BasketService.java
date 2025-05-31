package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException("No such product");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Supplier<NoSuchProductException> exceptionSupplier = () -> new NoSuchProductException("No such product");
        List<BasketItem> items = productBasket.getBasket()
                .entrySet()
                .stream()
                .map(p -> new BasketItem(
                        storageService.getProductById(p.getKey()).orElseThrow(exceptionSupplier),
                        p.getValue()))
                .toList();
        return new UserBasket(items);

    }

}
