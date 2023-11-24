package com.example.integrationtests;

import com.example.entities.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageWithFullHttpRequest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGettingAllMessages() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/messages");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        Message[] messages = mapper.readValue(response.getEntity().getContent(), Message[].class);

        assertEquals("First test message", messages[0].getContent());
        assertEquals("Second test message", messages[1].getContent());
        assertEquals("Third test message", messages[2].getContent());
        assertEquals("Fourth test message", messages[3].getContent());
    }

    @Test
    public void testGetMessageById() throws IOException {
        Long messageId = 1L;
        HttpUriRequest request = new HttpGet("http://localhost:8080/messages/" + messageId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        System.out.println(messageId);

    }
}
