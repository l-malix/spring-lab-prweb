/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Person;
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
public class LoginController {
    
    @RequestMapping(value="index.do", method=RequestMethod.GET)
    public ModelAndView handleGet(HttpServletRequest request) {
        ModelAndView returned;

        returned = new ModelAndView("index");
        
        return returned;
    }
    
    @Autowired
    private PersonRepository personRepository;
    
    @RequestMapping(value="index.do", method=RequestMethod.POST)
    public ModelAndView handlePost(MyUser user) {
        ModelAndView returned;
        
        String login = user.getLogin();
        String passwd = user.getPasswd();
        
        if ((login != null) && (passwd != null) 
                && (login.equals("admin")) && (passwd.equals("admin"))) {
            
            List<Person> myList = personRepository.findAll();
            returned = new ModelAndView("users");
            returned.addObject("listPerson", myList);
        } else {
            returned = new ModelAndView("index");
        }
        return returned;
    }
}
