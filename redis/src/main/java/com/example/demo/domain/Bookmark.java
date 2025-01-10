package com.example.demo.domain;




import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;


@RedisHash("bookmarks")
public class Bookmark {
    @Id
    private String id;

    @NotEmpty(message = "Title is mandatory")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @NotEmpty(message = "Url is mandatory")
    private String url;

    private Instant createdAt;

    public Bookmark() {

    }


    public Bookmark(String title, String url, Instant createdAt) {
        this.title = title;
        this.url = url;
        this.createdAt = createdAt;
    }

}
