/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import java.util.Date;
import java.util.Optional;
import org.centrale.prwebspring.items.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
/**
 *
 * @author lmalix
 */
@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom{
    
    @Autowired
    @Lazy
    private PersonRepository personRepository;
    
    @Override
    public Person update(int id, String firstName, String lastName, Date birthdate) {
        if (id > 0) {
            Person item = personRepository.getOne(id);
            item.setPersonFirstname(firstName);
            item.setPersonLastname(lastName);
            if(birthdate != null) {
                item.setPersonBirthdate(birthdate);
            }
            personRepository.save(item);
            return item;
        }
        return null;
    }

    @Override
    public Person create(String firstName, String lastName, Date birthdate) {
        
        Person person = new Person();
        person.setPersonFirstname(firstName);
        person.setPersonLastname(lastName);
        if (birthdate != null) {
            person.setPersonBirthdate(birthdate);
        }
        personRepository.save(person);
        
        Optional<Person> result = personRepository.findById(person.getPersonId());
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
}
