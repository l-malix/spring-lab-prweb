/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.items.Person;
import org.centrale.prwebspring.repositories.BookRepository;
import org.centrale.prwebspring.repositories.BookRepositoryCustomImpl;
import org.centrale.prwebspring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lmalix
 */
@Controller
public class BookController {
    
    
    @Autowired
    BookRepository bookRepository;
    
    @RequestMapping(value = "editBook.do", method = RequestMethod.POST)
    public ModelAndView editBook(HttpServletRequest request) {
        ModelAndView returned;
        
        String bookId = request.getParameter("id");
        
        Book book = bookRepository.getOne(Integer.parseInt(bookId));
        
        returned = new ModelAndView("book");
        returned.addObject("book", book);
        
        return returned;
    }
    
    @Autowired
    BookRepositoryCustomImpl bookRepositoryCustomImpl;
    
    @RequestMapping(value = "saveBook.do", method = RequestMethod.POST)
    public ModelAndView saveBook(HttpServletRequest request) {
        ModelAndView returned;
        
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String authors = request.getParameter("authors");
        
        if (Integer.parseInt(id) < 0) {
            bookRepositoryCustomImpl.create(title, authors);
        } else {
            bookRepositoryCustomImpl.update(Integer.parseInt(id), title, authors);
        }
        
        
        List<Book> books = bookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("books", books);
        
        return returned;
    }
    
    @RequestMapping(value="delBook.do", method = RequestMethod.POST)
    public ModelAndView delBook(HttpServletRequest request) {
        ModelAndView returned;
        
        String id = request.getParameter("id");
        
        Book book = bookRepository.getOne(Integer.parseInt(id));
        bookRepository.delete(book);
        
        List<Book> books = bookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("books", books);
        
        return returned;
    }
    
    @RequestMapping(value = "addBook.do", method = RequestMethod.POST)
    public ModelAndView addBook(HttpServletRequest request) {
        ModelAndView returned;
        
        Book book = new Book();
        returned = new ModelAndView("book");
        returned.addObject("book", book);
        
        return returned;
    }
    
    @Autowired
    PersonRepository personRepository;
    
    @RequestMapping(value = "toPersons.do", method = RequestMethod.POST)
    public ModelAndView toPersons(HttpServletRequest request) {
        ModelAndView returned;
        
        List<Person> persons = personRepository.findAll();
        
        returned = new ModelAndView("users");
        returned.addObject("listPerson", persons);
        
        return returned;
    }
    
}
