package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if(price <= 0){
            throw new IllegalArgumentException("Price of product can be only positive");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ":" + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
