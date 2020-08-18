package com.todoList.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoList.springboot.exception.ResourceNotFoundException;
import com.todoList.springboot.model.Todo;

import com.todoList.springboot.repository.TodoRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	
	//getting all to-do's
	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}
	
	//creating to-do's
	@PostMapping("/todos")
	public Todo createTodo(@RequestBody Todo todos) {
		return todoRepository.save(todos);
	}
	
	//get to-do by id
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Your TODO with id :"
						+ id + " does not exist"));
		return ResponseEntity.ok(todo);
	}
	
	//update the to-do-list
	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable long id, @RequestBody Todo todoUpdate) {
		
		//get to-do by id
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Your TODO with id :"
						+ id + " does not exist"));
		//set the updated details
		todo.setTodo(todoUpdate.getTodo());
		todo.setDate(todoUpdate.getDate());
		
		//save changes in the data base and save it to return it to the client
		Todo todoUpdated = todoRepository.save(todo);
		
		
		return ResponseEntity.ok(todoUpdated);
	}
	//delete to-do-list
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTodo(@PathVariable Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Your TODO with id :"
						+ id + " does not exist"));
		todoRepository.delete(todo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
}

