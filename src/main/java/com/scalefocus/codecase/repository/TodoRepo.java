package com.scalefocus.codecase.repository;

import com.scalefocus.codecase.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends MongoRepository<Todo,Long> {
}
