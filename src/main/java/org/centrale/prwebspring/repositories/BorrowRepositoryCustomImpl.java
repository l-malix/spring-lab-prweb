/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.items.Borrow;
import org.centrale.prwebspring.items.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author lmalix
 */
public class BorrowRepositoryCustomImpl implements BorrowRepositoryCustom{
   
    @Autowired
    @Lazy
    BorrowRepository borrowRepository;
    
    @Autowired
    @Lazy
    PersonRepository personRepository;
    
    @Autowired
    @Lazy
    BookRepository bookRepository;
    
    @Override
    public Borrow create(Date borrowDate, Book book, Person person) {
        
        Borrow borrow = new Borrow();
        borrow.setBorrowDate(borrowDate);
        borrow.setPersonId(person);
        borrow.setBookId(book);
        
        borrowRepository.saveAndFlush(borrow);
        
        
        
        Optional<Borrow> result = borrowRepository.findById(borrow.getBorrowId());
        
        person.getBorrowCollection().add(borrow);
        personRepository.saveAndFlush(person);
        
        book.getBorrowCollection().add(borrow);
        bookRepository.saveAndFlush(book);
                    
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public Borrow returnBook(Borrow item, Date date) {
        if ((item != null) && (date != null)) {
            item.setBorrowReturn(date);
            borrowRepository.save(item);
            return item;
        }
        return null;
    }

    @Override
    public Borrow returnBook(Borrow item) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return returnBook(item, date);
    }

    @Override
    public Borrow returnBook(int borrowId) {
        if (borrowId > 0) {
            Borrow item = borrowRepository.getOne(borrowId);
            if (item != null) {
                return returnBook(item);
            }
        }
        return null;
    }
}
