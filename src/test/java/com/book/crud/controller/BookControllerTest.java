package com.book.crud.controller;

import com.book.crud.entity.Book;
import com.book.crud.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPublishedDate(LocalDate.now());

        when(bookService.createBook(book)).thenReturn(book);

        ResponseEntity<Book> response = bookController.createBook(book);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).createBook(book);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        when(bookService.getAllBooks(Pageable.ofSize(2))).thenReturn(new PageImpl(books));

        ResponseEntity<Page<Book>> response = bookController.getAllBooks(Pageable.ofSize(2));

        assertEquals(2, Objects.requireNonNull(response.getBody()).getTotalElements());
        verify(bookService, times(1)).getAllBooks(Pageable.ofSize(2));
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPublishedDate(LocalDate.now());

        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Title");
        book.setAuthor("Updated Author");
        book.setIsbn("1234567890");
        book.setPublishedDate(LocalDate.now());

        when(bookService.updateBook(1L, book)).thenReturn(book);

        ResponseEntity<Book> response = bookController.updateBook(1L, book);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).updateBook(1L, book);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
