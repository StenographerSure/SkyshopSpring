package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> basket;

    public ProductBasket(Map<UUID, Integer> basket) {
        this.basket = basket;
    }

    public void addProduct(UUID productID){
        if(basket.containsKey(productID)){
            basket.put(productID, basket.get(productID) + 1);
        } else {
            basket.put(productID, 1);
        }
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
