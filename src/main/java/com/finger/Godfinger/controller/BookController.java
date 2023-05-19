package com.finger.Godfinger.controller;

import com.finger.Godfinger.dto.BookResponse;
import com.finger.Godfinger.model.Book;
import com.finger.Godfinger.service.BookService;
import com.finger.Godfinger.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
   @Autowired
   private BookService bookService;
   private final AppUtil util;

   @PostMapping("/put")
   public BookResponse bookMe(@RequestBody Book book) {
      return bookService.saveBook(book);


   }
   @PostMapping("/get")
   public BookResponse borrowBooks(@RequestParam Long id){
      System.out.println("entered");
//      System.out.println(util.getLoggedStudent());
      return bookService.borrowBooks(id);
   }

   @PostMapping("/getAll")
   public BookResponse listOfBorrowedBooks(@RequestParam Long studentId){
      return bookService.listOfBorrowedBooks(studentId);
   }

   @PostMapping("/returnBorrowedbook")
   public BookResponse returnBorrowedbook(@RequestParam Long bookId){
      return bookService.returnBorrowedBook(bookId);
   }
}

