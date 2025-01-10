package com.example.demo.adapter;


import com.example.demo.domain.Bookmark;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

interface RedisBookmarkRepository
        extends ListCrudRepository<Bookmark, String>, ListPagingAndSortingRepository<Bookmark, String> {}