package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public final class Article implements Searchable {
    private final String title;
    private final String content;
    private final UUID id;

    public Article(String title, String content, UUID id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    @Override
    public String toString() {
        return title + '\n' + content;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return this.title;
    }

    @Override
    public UUID getId(){
        return id;
    }

}

