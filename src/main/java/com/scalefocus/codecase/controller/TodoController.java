package com.scalefocus.codecase.controller;

import com.scalefocus.codecase.model.Todo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private static final String API = "/todos/";

    @Value("codecase.app.fakeurl")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity getTodos() {
        ResponseEntity<Todo> forEntity = restTemplate.getForEntity(baseUrl + API , Todo.class);
        return forEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodosBy(@PathParam("id") Long id) {
        ResponseEntity<Todo> forEntity = restTemplate.getForEntity(baseUrl + API + id, Todo.class);

        return forEntity;
    }
}
