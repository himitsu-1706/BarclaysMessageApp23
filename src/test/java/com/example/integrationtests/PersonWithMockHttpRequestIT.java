package com.example.integrationtests;

import com.example.entities.Message;
import com.example.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Note: test class name regex '[A-Z][A-Za-z\d]*Test(s|Case)?|Test[A-Z][A-Za-z\d]*|IT(.*)|(.*)IT(Case)?'
//Note: class names nouns, method names verbs, collections plural
@Sql("classpath:test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class PersonWithMockHttpRequestIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGettingAllMessages() throws Exception {

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/people")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(content().json(expectedJson))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Person[] actualPerson = mapper.readValue(contentAsJson, Person[].class);

        assertEquals("Lola", actualPerson[0].getName());
        assertEquals("Maria", actualPerson[1].getName());
        assertEquals("Luis", actualPerson[2].getName());
        assertEquals("Marta", actualPerson[3].getName());
    }

}

