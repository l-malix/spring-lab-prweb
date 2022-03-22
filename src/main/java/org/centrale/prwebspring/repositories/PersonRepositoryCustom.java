/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import java.util.Date;
import org.centrale.prwebspring.items.Person;

/**
 *
 * @author lmalix
 */
public interface PersonRepositoryCustom {
    
    public Person update(int id, String firstName, String lastName, Date birthdate);
    public Person create(String firstName, String lastName, Date birthdate);
    
}
