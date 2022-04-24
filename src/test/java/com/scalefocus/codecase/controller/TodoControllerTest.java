package com.scalefocus.codecase.controller;

import com.scalefocus.codecase.CodeCaseApplication;
import com.scalefocus.codecase.model.Todo;
import com.scalefocus.codecase.repository.TodoRepo;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@NoArgsConstructor
public class TodoControllerTest {

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    TodoRepo todoRepo;

    @Autowired private TestRestTemplate template;

    @Before public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/todo/1");
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() throws Exception {
        ResponseEntity< Todo > response = template.getForEntity(base.toString(), Todo.class);
        assertThat(response.getBody(), equalTo(todoRepo.findById(1L)));
    }


}