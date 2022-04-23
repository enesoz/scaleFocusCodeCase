package com.scalefocus.codecase.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todos")
public class Todo {
    @Id
    private long id;
    private long userId;
    private String title;
    private boolean completed;
}
