package com.example.dataaccess;

import com.example.entities.Message;
import com.example.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeoplePopulator {

    PersonRepository repo;

    @Autowired
    public PeoplePopulator(PersonRepository repo) {
        this.repo = repo;
    }
    public void populate() {
        Person person = new Person("Lola", "shfkhkdsf");
        this.repo.save(person);
        Person person1 = new Person("Maria", "jeflehrtl");
        this.repo.save(person1);
        Person person2 = new Person("Luis", "dngdjlgndljng");
        this.repo.save(person2);
        Person person3 = new Person("Marta", "djbgkdrhgj");
        this.repo.save(person3);

    }
}
