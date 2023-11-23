package com.example.services;

import com.example.dataaccess.PersonRepository;
import com.example.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
class PersonServiceSomeSpringTest {

    @Autowired
    PersonService personService;

    @MockBean
    PersonRepository mockRepoPeople;


    public static List<Person> createPeopleList()  {
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Person("Lola", "emaiol");
        Person person2 = new Person("Maria", "emaiol");
        Person person3 = new Person("Luis", "emaiol");
        Person person4 = new Person("Marta", "emaiol");
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        return people;
    }

    @Test
    void findAllWithSpringDi() {
        List<Person> people = createPeopleList();

        when(this.mockRepoPeople.findAll()).thenReturn(people);
        Iterable<Person> actualPeople=personService.findAll();

        Assertions.assertEquals(people,actualPeople);

    }

    @Test
    void testGetPeopleByIdOptionalEmpty() {
        long personId = 1;
        Optional<Person> optionalPerson = Optional.empty();
        when(this.mockRepoPeople.findById(personId)).thenReturn(optionalPerson);
        Person actual = personService.getPersonById(personId);
        Assertions.assertNull(actual);
    }

    @Test
    void testGetPeopleByIdOptionalNotEmpty() {
        Person person = new Person("Howdy", "email");
        when(this.mockRepoPeople.findById(any())).thenReturn(Optional.of(person));

        Person actual = personService.getPersonById(1L);

        Assertions.assertEquals(person.getName(), actual.getName());
        Assertions.assertEquals(person.getId(), actual.getId());
    }
}