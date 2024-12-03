package com.book.crud.controller;

import com.book.crud.entity.Book;
import com.book.crud.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        Book book = new Book();
        book.setTitle("Existing Book");
        book.setAuthor("Author");
        book.setIsbn("1111111111111");
        book.setPublishedDate(LocalDate.now());
        bookRepository.save(book);
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Integration Test Book");
        book.setAuthor("Integration Test Author");
        book.setIsbn("1234567890123");
        book.setPublishedDate(LocalDate.now());

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Integration Test Book"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBookById() throws Exception {
        // Retrieve the first book from the repository
        Long bookId = bookRepository.findAll().get(0).getId();

        mockMvc.perform(get("/books/" + bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId));
    }

    @Test
    void testUpdateBook() throws Exception {
        // Retrieve the first book from the repository
        Long bookId = bookRepository.findAll().get(0).getId();

        Book book = new Book();
        book.setTitle("Updated Title");
        book.setAuthor("Updated Author");
        book.setIsbn("1234567890123");
        book.setPublishedDate(LocalDate.now());

        mockMvc.perform(put("/books/" + bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteBook() throws Exception {
        // Retrieve the first book from the repository
        Long bookId = bookRepository.findAll().get(0).getId();

        mockMvc.perform(delete("/books/" + bookId))
                .andExpect(status().isNoContent());
    }
}
