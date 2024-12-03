package com.book.crud.service;
import com.book.crud.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book createBook(Book book);
    Page<Book> getAllBooks(Pageable pageable);
    Book getBookById(Long id);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
}
