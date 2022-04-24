package com.scalefocus.codecase.controller;

import com.scalefocus.codecase.model.Todo;
import com.scalefocus.codecase.repository.TodoRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private static final String API = "/todos";

    @Value("${codecase.app.fakeurl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TodoRepo todoRepo;

    @GetMapping("/")
    public ResponseEntity getTodos() {
        ResponseEntity<Todo[]> forEntity = restTemplate.getForEntity(baseUrl + API , Todo[].class);
        todoRepo.saveAll(Arrays.stream(forEntity.getBody()).toList());
        return forEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodosBy(@PathVariable("id") Long id) {
        ResponseEntity<Todo> forEntity = restTemplate.getForEntity(baseUrl + API +"/"+ id, Todo.class);
        todoRepo.save(forEntity.getBody());
        return forEntity;
    }
}
