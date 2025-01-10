package com.example.demo.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class BookmarkService {
    private final BookmarkRepository repo;

    public BookmarkService(BookmarkRepository repo) {
        this.repo = repo;
    }

    public PagedResult<Bookmark> getBookmarks(int pageNo) {
        return repo.findAll(pageNo);
    }

    public Optional<Bookmark> getBookmarkById(String id) {
        return repo.findById(id);
    }

    public Bookmark save(Bookmark bookmark) {
        return repo.save(bookmark);
    }

    public void deleteById(String id) {
        Optional<Bookmark> bookmark = repo.findById(id);
        if (bookmark.isPresent()) {
            repo.deleteById(id);
        }
    }
}
