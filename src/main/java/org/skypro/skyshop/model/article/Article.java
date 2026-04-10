package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String content;

    private final UUID id;

    public Article(String title, UUID id, String content) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return this.toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    public UUID getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Article article = (Article) obj;
        return Objects.equals(this.title, article.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
