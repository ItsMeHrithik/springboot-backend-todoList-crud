package com.todoList.springboot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "todo")
	private String todo;
	
	@Column(name = "date")
	private String date;
	
	public Todo() {
		
	}
	
	public Todo(String todo, String date) {
		super();
		this.todo = todo;
		this.date = date;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	} 
	
}
