package com.example.controllers;

import com.example.entities.Person;
import com.example.services.MessageService;
import com.example.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PersonControllerFullSpringMvcTest {

    @MockBean
    PersonService mockPersonservice;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService mockMessageService;
    @Test
    void testServiceCalledFor_getAllPeople() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/people");
        mockMvc.perform(requestBuilder);

        verify(mockPersonservice, times(1)).findAll();
    }

    @Test
    void testGetPersonById() throws Exception {
        long personId = 1;

        Person person = new Person("Lola", "lola@gmail.com");
        when(mockPersonservice.getPersonById(personId)).thenReturn(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people/" + Long.toString(personId));
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPersonservice, times(1)).getPersonById(personId);
    }

    @Test
    void testGetMessageByIdBadIndex() throws Exception {
        long messageId = 0;

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/messages/" + messageId);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();
    }
}