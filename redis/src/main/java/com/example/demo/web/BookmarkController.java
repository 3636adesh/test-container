package com.example.demo.web;


import com.example.demo.domain.Bookmark;
import com.example.demo.domain.BookmarkService;
import com.example.demo.domain.PagedResult;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService service;

    public BookmarkController(BookmarkService service) {
        this.service = service;
    }

    @GetMapping
    public PagedResult<Bookmark> getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return service.getBookmarks(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable String id) {
        return service.getBookmarkById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound()
                .build());
    }

    @PostMapping
    public ResponseEntity<Bookmark> save(@Valid @RequestBody Bookmark bookmark) {
        bookmark.setId(null);
        bookmark.setCreatedAt(Instant.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(bookmark));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
