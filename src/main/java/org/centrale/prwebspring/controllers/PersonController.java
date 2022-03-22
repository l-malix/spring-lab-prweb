/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.items.Borrow;
import org.centrale.prwebspring.items.Person;
import org.centrale.prwebspring.repositories.BookRepository;
import org.centrale.prwebspring.repositories.BorrowRepositoryCustomImpl;
import org.centrale.prwebspring.repositories.PersonRepository;
import org.centrale.prwebspring.repositories.PersonRepositoryCustomImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lmalix
 */
@Controller
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "editUser.do", method = RequestMethod.POST)
    public ModelAndView editUser(HttpServletRequest request) {
        ModelAndView returned;
        String personIdStr = request.getParameter("personId");
        int id = -1;
        try {
            id = Integer.parseInt(personIdStr);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }

        if (id > 0) {
            Person person = personRepository.getOne(id);
            returned = new ModelAndView("user");
            returned.addObject("person", person);

        } else {
            List<Person> persons = personRepository.findAll();
            returned = new ModelAndView("users");
            returned.addObject("listPerson", persons);
        }

        List<Book> books = bookRepository.findAll();
        returned.addObject("books", books);

        return returned;
    }

    private Date getDateFromStrinf(String strDate, String format) {
        Date returnedValue = null;
        try {
            SimpleDateFormat aFormater = new SimpleDateFormat(format);
            returnedValue = aFormater.parse(strDate);
        } catch (ParseException ex) {

        }
        if (returnedValue != null) {
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(returnedValue);
        }
        return returnedValue;

    }

    @Autowired
    PersonRepositoryCustomImpl personRepositoryCustomImpl;

    @RequestMapping(value = "saveUser.do", method = RequestMethod.POST)
    public ModelAndView saveUser(HttpServletRequest request) {
        ModelAndView returned;

        String id = request.getParameter("id");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String dobStr = request.getParameter("dob");

        if (Integer.parseInt(id) < 0) {
            personRepositoryCustomImpl.create(firstName, lastName, getDateFromStrinf(dobStr, "yyyy-MM-dd"));
        } else {
            personRepositoryCustomImpl.update(Integer.parseInt(id), firstName, lastName, getDateFromStrinf(dobStr, "yyyy-MM-dd"));
        }

        List<Person> myList = personRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("listPerson", myList);

        return returned;

    }

    @RequestMapping(value = "delUser.do", method = RequestMethod.POST)
    public ModelAndView delUser(HttpServletRequest request) {
        ModelAndView returned;

        String id = request.getParameter("personId");

        Person person = personRepository.getOne(Integer.parseInt(id));
        personRepository.delete(person);

        List<Person> myList = personRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("listPerson", myList);

        return returned;

    }

    @RequestMapping(value = "addUser.do", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request) {
        ModelAndView returned;

        Person person = new Person();
        returned = new ModelAndView("user");
        returned.addObject("person", person);

        return returned;
    }

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "toBooks.do", method = RequestMethod.POST)
    public ModelAndView toBooks(HttpServletRequest request) {
        ModelAndView returned;

        List<Book> books = bookRepository.findAll();

        returned = new ModelAndView("books");
        returned.addObject("books", books);

        return returned;

    }

    @Autowired
    BorrowRepositoryCustomImpl borrowRepositoryCustomImpl;

    @RequestMapping(value = "addBorrowedBook.do", method = RequestMethod.POST)
    public ModelAndView addBorrowedBook(HttpServletRequest request) {
        ModelAndView returned;

        String borrowedBookId = request.getParameter("bookId");
        String personId = request.getParameter("id");
        
        Book borrowedBook = bookRepository.getOne(Integer.parseInt(borrowedBookId));
        
        Person person = personRepository.getOne(Integer.parseInt(personId));
        
        Borrow borrow = borrowRepositoryCustomImpl.create(new Date(System.currentTimeMillis()), borrowedBook, person);

        
        
        
        List<Book> books = bookRepository.findAll();
        person = personRepository.getOne(Integer.parseInt(personId));
        
        returned = new ModelAndView("user");
        
        returned.addObject("person", person);
        returned.addObject("books", books);

        return returned;
    }
    
    @RequestMapping(value = "returnBorrow.do", method = RequestMethod.POST)
    public ModelAndView returnBorrow(HttpServletRequest request) {
        
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject returnedObject = new JSONObject();
        
        String borrowStr = request.getParameter("id");
        int borrowId = Integer.parseInt(borrowStr);
        
        Borrow borrow = borrowRepositoryCustomImpl.returnBook(borrowId);
        
        if (borrow != null) {
            returnedObject.append("id", borrow.getBookId());
        } else {
            returned.setStatus(HttpStatus.BAD_REQUEST);
        }
        
        returned.addObject("theResponse", returnedObject.toString());
        
        return returned;
    }

}
