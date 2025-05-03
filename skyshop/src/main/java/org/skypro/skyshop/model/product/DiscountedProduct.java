package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(String name, int basePrice, int discount, UUID id) {
        super(name, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Price of product can be only positive");
        }
        this.basePrice = basePrice;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount can be only between 0 and 100 including");
        }
        this.discount = discount;

    }

    @Override
    public int getPrice() {
        return basePrice * (100 - discount) / 100;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + getPrice() + "(" + this.discount + "%)";

    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
