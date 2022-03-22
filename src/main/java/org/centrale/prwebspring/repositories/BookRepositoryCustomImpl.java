/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import java.util.Optional;
import org.centrale.prwebspring.items.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
/**
 *
 * @author lmalix
 */
@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom{
    
    @Autowired
    @Lazy
    BookRepository bookRepository;

    @Override
    public Book update(int id, String title, String authors) {
        
        if (id > 0) {
            Book item = bookRepository.getOne(id);
            item.setBookTitle(title);
            item.setBookAuthors(authors);
            
            bookRepository.save(item);
            return item;
        }
        return null;
    }
    
    @Override
    public Book create(String title, String authors) {
        
        Book book = new Book();
        book.setBookTitle(title);
        book.setBookAuthors(authors);
        
        bookRepository.save(book);
        
        Optional<Book> result = bookRepository.findById(book.getBookId());
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
}
