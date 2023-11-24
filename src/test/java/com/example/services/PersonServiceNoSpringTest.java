package com.example.services;

import com.example.Utilities;
import com.example.dataaccess.MessageRepository;
import com.example.dataaccess.PersonRepository;
import com.example.entities.Message;
import com.example.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class PersonServiceNoSpringTest {
    PersonRepository mockPeopleRepo;
    PersonService personService;

    @BeforeEach
    void beforeEach() {
        this.mockPeopleRepo = mock(PersonRepository.class);
        this.personService = new PersonService(this.mockPeopleRepo);
    }

    @Test
    void findAll() {
        List<Person> people = createPeopleList();
        when(this.mockPeopleRepo.findAll()).thenReturn(people);
        List<Person> actualPeople = personService.findAll();

        Assertions.assertEquals(people, actualPeople);
    }

    @Test
    void testRepoCalled() {
        List<Person> actualPeople = personService.findAll();

        verify(mockPeopleRepo, times(1)).findAll();
    }

    @Test
    void testGetMessageByIdOptionalEmpty() {
        long personId = 1;
        Optional<Person> optionalPeople = Optional.empty();
        when(this.mockPeopleRepo.findById(personId)).thenReturn(optionalPeople);
        Person actual = personService.getPersonById(personId);
        Assertions.assertNull(actual);
    }

    @Test
    void testGetMessageByIdOptionalNotEmpty() {
        Person person = new Person("Howdy", "emaiol");

        when(this.mockPeopleRepo.findById(any())).thenReturn(Optional.of(person));

        Person actual = personService.getPersonById(1L);

        verify(mockPeopleRepo, times(1)).findById(any());
        Assertions.assertEquals(person.getName(), actual.getName());
        Assertions.assertEquals(person.getId(), actual.getId());
    }

    @Test
    void testGetMessageByIdBadRequest() {
    }

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

}