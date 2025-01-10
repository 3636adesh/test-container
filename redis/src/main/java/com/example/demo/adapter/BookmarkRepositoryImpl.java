package com.example.demo.adapter;


import com.example.demo.domain.Bookmark;
import com.example.demo.domain.BookmarkRepository;
import com.example.demo.domain.PagedResult;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private static final int PAGE_SIZE = 10;
    private final RedisBookmarkRepository repo;

    public BookmarkRepositoryImpl(RedisBookmarkRepository repo) {
        this.repo = repo;
    }

    @Override
    public PagedResult<Bookmark> findAll(int pageNo) {
        Pageable pageable = getPageable(pageNo);
        return new PagedResult<>(repo.findAll(pageable));
    }

    @Override
    public Optional<Bookmark> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Bookmark save(Bookmark bookmark) {
        return repo.save(bookmark);
    }

    @Override
    public Iterable<Bookmark> saveAll(Iterable<Bookmark> bookmarks) {
        return repo.saveAll(bookmarks);
    }

    @Override
    public void deleteById(String id) {
        Optional<Bookmark> bookmark = repo.findById(id);
        if (bookmark.isPresent()) {
            repo.deleteById(id);
        }
    }

    @Override
    public long count() {
        return repo.count();
    }

    private Pageable getPageable(int pageNo) {
        int page = (pageNo > 0) ? pageNo - 1 : 0;
        return PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}