/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import org.centrale.prwebspring.items.Book;

/**
 *
 * @author lmalix
 */
public interface BookRepositoryCustom {
    
    public Book update(int id, String title, String authors);
    public Book create(String title, String authors);
}
