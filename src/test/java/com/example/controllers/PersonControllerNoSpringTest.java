package com.example.controllers;

import com.example.entities.Person;
import com.example.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PersonControllerNoSpringTest {

    PersonService mockPersonService;
    PersonController personController;

    @BeforeEach
    void beforeEach() {
        this.mockPersonService = mock(PersonService.class);
        this.personController = new PersonController(this.mockPersonService);
    }

    @Test
    void getAllPeople() {
        this.personController.getAllPeople();
        verify(this.mockPersonService, times(1)).findAll();
    }

    @Test
    void testGetPersonById()  {

        long personId = 1L;

        try {
            this.personController.getPersonById(personId);
            when(this.mockPersonService.getPersonById(personId)).thenReturn(new Person("Lola" , "emaiol"));
        } catch (ResponseStatusException rse) {
            // We shouldn't have  a record there, it doesn't matter, we're testing behavior below
            System.out.println("Expected exception thrown;");
        }

        verify(this.mockPersonService, times(1)).getPersonById(personId);
    }

    @Test
    void testGetPersonByIdBadRequest() {
        when(this.mockPersonService.getPersonById(0)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            this.personController.getPersonById(0);
        });
    }

}