package com.eriktest.validation;

import javax.validation.constraints.Size;

public class BoardPost {

	private int id;
	@Size(min = 2)
	private String message;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoardPost [message=" + message + ", name=" + name + "]";
	}

}