package com.danny.dictionary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WordControllerTest {
    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void getWordShouldReturnWord() throws Exception {
        String uri = "/words/1";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Word word = mapFromJson(content, Word.class);

        Word expected = new Word(
                1L,
                "given name",
                "n",
                "",
                "first name (American English)",
                "My given name is Nicholas."
        );
        assertEquals(expected, word);
    }

    @Test
    @Order(2)
    void getWordShouldReturnError() throws Exception {
        String uri = "/words/4";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_PLAIN_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("No word with id = 4 exists", content);
    }

    @Test
    @Order(3)
    void getWordsShouldReturnList() throws Exception {
        String uri = "/words";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Word[] words = mapFromJson(content, Word[].class);
        assertEquals(2, words.length);
    }

    @Test
    @Order(4)
    void addWord() throws Exception {
        String uri = "/words";
        Word nameAfter = new Word(
                null,
                "name after",
                "phr v",
                "",
                "give someone or something the same name as another person or thing",
                "He was named after his father.");


        String inputJson = mapToJson(nameAfter);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Word word = mapFromJson(content, Word.class);
        word.setId(null);
        assertEquals(nameAfter, word);
    }

    @Test
    @Order(5)
    void replaceWord() throws Exception {
        String uri = "/words/1";
        Word givenName = new Word(
                1L,
                "given name",
                "n",
                "",
                "first name (American English)",
                "My given name is Danny."
        );

        String inputJson = mapToJson(givenName);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Word word = mapFromJson(content, Word.class);
        assertEquals(givenName, word);
    }

    @Test
    @Order(6)
    void deleteWord() throws Exception {
        String uri = "/words/2";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        int length = mvcResult.getResponse().getContentLength();
        assertEquals(0, length);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("", content);
    }

    @Test
    @Order(7)
    void deleteWordShouldReturnError() throws Exception {
        String uri = "/words/10";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("No word with id = 10 exists", content);
    }
}