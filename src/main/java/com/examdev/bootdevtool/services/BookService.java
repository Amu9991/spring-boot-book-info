package com.examdev.bootdevtool.services;

import com.examdev.bootdevtool.entities.BookEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {

    private static List <BookEntity> list = new ArrayList<> ();

static {
    list.add(new BookEntity(12,"Java Complete reference","Rakesh"));
            list.add(new BookEntity(13,"Java","Ram"));
    list.add(new BookEntity(14,"Java Rest","Raju"));

}

//get all books
public List<BookEntity> getAllBooks(){
    return list;
}

//get book by id
public BookEntity getBookById(int id){

    BookEntity book=null;
    try{book=list.stream().filter(e->e.getId()==id).findFirst().get();

    }catch(Exception e){
        e.printStackTrace();
    }

    return book;
}

public BookEntity addBook(BookEntity b){
    list.add(b);
    return b;
}

public void deleteBook(int bid){
    list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
}

public void updateBook(BookEntity bookEntity,int bookId){
    list=list.stream().map(b->{if(b.getId()==bookId){
    b.setTitle(bookEntity.getTitle());
    b.setAuthor(bookEntity.getAuthor());
    }
    return b;
    }).collect(Collectors.toList());

}
}
