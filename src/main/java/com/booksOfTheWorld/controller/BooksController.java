package com.booksOfTheWorld.controller;

import com.booksOfTheWorld.entity.Book;
import com.booksOfTheWorld.entity.MyBookList;
import com.booksOfTheWorld.service.BookService;
import com.booksOfTheWorld.service.MyBookListService;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBooks() {
        List<Book> list = service.getAllBooks();
//        ModelAndView m = new ModelAndView();
//        m.setView("bookList");
//        m.addObject("book",list);
        return new ModelAndView("availableBooks", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myBookService.getAllOfMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b = service.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = service.getBookById(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/available_books";
    }

}
