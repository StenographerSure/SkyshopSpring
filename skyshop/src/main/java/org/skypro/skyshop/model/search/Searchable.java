package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String searchTerm();

    String getContentType();

    default String getStringRepresentation() {
        return "имя Searchable -объекта — тип Searchable -объекта";
    }

    UUID getId();
}
