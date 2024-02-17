package com.booksOfTheWorld.service;

import com.booksOfTheWorld.entity.MyBookList;
import com.booksOfTheWorld.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

    @Autowired
    private MyBookRepository mybook;

    public void saveMyBooks(MyBookList book) {
        mybook.save(book);
    }

    public List<MyBookList> getAllOfMyBooks(){
        return mybook.findAll();
    }

    public void deleteById(int id){
        mybook.deleteById(id);
    }

}
