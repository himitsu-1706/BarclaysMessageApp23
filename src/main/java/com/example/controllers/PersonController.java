package com.example.controllers;

import com.example.entities.Message;
import com.example.entities.Person;
import com.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PersonController {
    PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")

    public Iterable<Person> getAllPeople(){
        return personService.findAll();
    }

    @GetMapping("/people/{personId}")
    public  Person getPersonById(@PathVariable long personId){
        Person person = personService.getPersonById(personId);
        if (person == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        return person;
    }
}
