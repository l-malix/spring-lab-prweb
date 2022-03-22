/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;


import org.centrale.prwebspring.items.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author lmalix
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom{
    
}
