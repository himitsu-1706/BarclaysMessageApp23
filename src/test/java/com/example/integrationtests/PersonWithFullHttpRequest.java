package com.example.integrationtests;

import com.example.entities.Message;
import com.example.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonWithFullHttpRequest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGettingAllPeople() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/people");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        Person[] people = mapper.readValue(response.getEntity().getContent(), Person[].class);

        assertEquals("Lola", people[0].getName());
        assertEquals("Maria", people[1].getName());
        assertEquals("Luis", people[2].getName());
        assertEquals("Marta", people[3].getName());
    }

    @Test
    public void testGetMessageById() throws IOException {
        Long personId = 1L;
        HttpUriRequest request = new HttpGet("http://localhost:8080/people/" + personId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        System.out.println(personId);

    }
}