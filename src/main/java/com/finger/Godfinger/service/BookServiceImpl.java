package com.finger.Godfinger.service;

import com.finger.Godfinger.dto.BookResponse;

import com.finger.Godfinger.model.Book;
import com.finger.Godfinger.model.BorrowEBooks;
import com.finger.Godfinger.model.Student;
import com.finger.Godfinger.repository.BookRepository;
import com.finger.Godfinger.repository.BorrowedBookRepository;
;
import com.finger.Godfinger.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;
  private final StudentRepository studentRepository;
  @Autowired
  private BorrowedBookRepository borrowedBookRepository;

  @Override
  public BookResponse saveBook(Book book) {
    Optional<Book> book1 = bookRepository.findByIsbn(book.getIsbn());
    if (book1.isPresent()) {
      return new BookResponse<>("book found", "book already exist", book1);
    }
    bookRepository.save(book);
    return new BookResponse<>("success", " new book added ", book);
  }


  public BookResponse borrowBooks(Long id) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    Student student = studentRepository.findByEmail(email).get();

    Optional<Book> book = bookRepository.findById(id);
    if (book.isEmpty())
      return new BookResponse("failed", "Book was not found", null);

    if (student.getListOfBorrowed().size() >= 3) {
      return new BookResponse("failed", "you cant borrow more than three books ", book);
    }

    Book book1 = book.get();

    if (book1.getIsAvailable()) {
      BorrowEBooks borrowEBooks = new BorrowEBooks();
      borrowEBooks.setBorrowedBookId(book1.getId());
      borrowEBooks.setStudent(student);
      book1.setIsAvailable(false);
      bookRepository.save(book1);
      borrowedBookRepository.save(borrowEBooks);
      return new BookResponse("sucess", "Book added to you dashboard", borrowEBooks);
    }
    return new BookResponse("failed", "Book has been borowed by another student", null);
  }

  @Override
  public BookResponse listOfBorrowedBooks(Long studentId) {
    Student student = studentRepository.findById(studentId).get();
    List<BorrowEBooks> borrowEBooksList = borrowedBookRepository.findAllByStudent(student);
    return new BookResponse("success", "found all", borrowEBooksList);
  }



  @Override
  public BookResponse returnBorrowedBook(Long bookId) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Student student = studentRepository.findByEmail(email).get();

    List<BorrowEBooks> currentListOfBorrowedBooks = student.getListOfBorrowed();

    for (int i = 0; i < currentListOfBorrowedBooks.size(); i++) {
      BorrowEBooks b =currentListOfBorrowedBooks.get(i);
      if (b.getBorrowedBookId().equals(bookId)) {
        currentListOfBorrowedBooks.remove(currentListOfBorrowedBooks.get(i));
      }
    }
    student.setListOfBorrowed(currentListOfBorrowedBooks);
    studentRepository.save(student);
      return new BookResponse<>("sucess", "book returnd", currentListOfBorrowedBooks);
  }

}