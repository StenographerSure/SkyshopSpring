package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;


    public Product(String name, UUID id) {
        if (name.isBlank() || name == null) {
            throw new IllegalArgumentException("Name of the product can't be blank or null");
        }
        this.name = name;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getStringRepresentation() {
        return this.name;
    }

    @Override
    public UUID getId() {
        return id;
    }

}
