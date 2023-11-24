package com.example.services;

import com.example.dataaccess.PersonRepository;
import com.example.entities.Message;
import com.example.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person getPersonById(long personId){
        Optional<Person> person = this.personRepository.findById(personId);
        return person.orElse(null);
    }
}
