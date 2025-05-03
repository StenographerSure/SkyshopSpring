package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int fixedPrice = 100;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);

    }

    @Override
    public int getPrice() {
        return fixedPrice;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
