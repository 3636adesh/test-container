package com.example.demo.web;

import com.example.demo.domain.Bookmark;
import com.example.demo.domain.BookmarkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookmarkControllerTests {

    @Container
    @ServiceConnection(name = "redis")
    static final GenericContainer<?> redis = new GenericContainer<>("redis:7.2.4-alpine").withExposedPorts(6379);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarkRepository repo;


    @ParameterizedTest
    @CsvSource({"1,15,2,1,true,false,true,false", "2,15,2,2,false,true,false,true"})
    void shouldFetchBookmarksByPageNumber(
            int pageNo,
            int totalElements,
            int totalPages,
            int pageNumber,
            boolean isFirst,
            boolean isLast,
            boolean hasNext,
            boolean hasPrevious)
            throws Exception {
        this.mockMvc
                .perform(get("/api/bookmarks?page=" + pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", equalTo(totalPages)))
                .andExpect(jsonPath("$.pageNumber", equalTo(pageNumber)))
                .andExpect(jsonPath("$.isFirst", equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", equalTo(hasPrevious)));
    }

    @Test
    void shouldFetchBookmarksById() throws Exception {
        final var title = "title";
        final var url = "url";
        var bookmark = new Bookmark();
        bookmark.setCreatedAt(Instant.now());
        bookmark.setTitle(title);
        bookmark.setUrl(url);
        var savedBookmark = this.repo.save(bookmark);

        mockMvc.perform(get("/api/bookmarks/{id}", savedBookmark.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(savedBookmark.getTitle())))
                .andExpect(jsonPath("$.url", equalTo(savedBookmark.getUrl())));

    }

    @Test
    void shouldCreateBookmark() throws Exception {
        mockMvc
                .perform(
                        post("/api/bookmarks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                          {
                                              "title": "SivaLabs Blog",
                                              "url": "https://sivalabs.in"
                                          }
                                        """)
                )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.title", equalTo("SivaLabs Blog")))
        .andExpect(jsonPath("$.url", equalTo("https://sivalabs.in")));
    }


}
