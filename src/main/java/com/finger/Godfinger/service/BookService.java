package com.finger.Godfinger.service;

import com.finger.Godfinger.dto.BookResponse;
import com.finger.Godfinger.model.Book;

public interface BookService {
    BookResponse saveBook(Book book);

    BookResponse borrowBooks(Long id);

    BookResponse listOfBorrowedBooks(Long id);

    BookResponse returnBorrowedBook(Long bookId);
}
