package com.examdev.bootdevtool.controllers;

import com.examdev.bootdevtool.entities.BookEntity;
import com.examdev.bootdevtool.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookEntity>> getBooks(){
List<BookEntity> list=bookService.getAllBooks();
if(list.size()<=0){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("id")  int id){
        BookEntity bookEntity=bookService.getBookById(id);
         if(bookEntity==null){
             ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
         return ResponseEntity.of(Optional.of(bookEntity));

    }

    @PostMapping("/books")
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity bookEntity){
        BookEntity b =null;

        try {
             b=this.bookService.addBook(bookEntity);
            return ResponseEntity.of(Optional.of(b));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/books/{bookId}")


    public ResponseEntity<?> deleteBook(@PathVariable("bookId") int bookId){

        try{
            this.bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }


    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookEntity> updateBook(@RequestBody BookEntity bookEntity,@PathVariable("bookId") int bookId){

        try{
            this.bookService.updateBook(bookEntity,bookId);
            return ResponseEntity.ok().body(bookEntity);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }
}
