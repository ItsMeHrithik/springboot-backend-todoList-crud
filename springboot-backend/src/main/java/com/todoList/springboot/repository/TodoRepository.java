package com.todoList.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoList.springboot.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long>{
	

}
