package com.example.controllers;

import com.example.entities.Message;
import com.example.entities.Person;
import com.example.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PersonControllerTestSomeSpringTest {

    @Autowired
    PersonController personController;

    @MockBean
    PersonService mockPersonService;

    @Test
    public void getObjectsFromContextAndTestBehavior() {
        Iterable<Person> people = personController.personService.findAll();

        verify(mockPersonService, times(1)).findAll();
    }
}
