package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {

    private final List<BasketItem> basketItems;
    private final int totalPrice;

    public UserBasket(List<BasketItem> items){
        this.basketItems = items;
        this.totalPrice = items.stream().
                mapToInt(i -> i.getProduct().getPrice() * i.getAmount()).sum();
    }

    public List<BasketItem> getItems() {
        return basketItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

}
