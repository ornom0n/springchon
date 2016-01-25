package com.eriktest.validation;

import javax.validation.constraints.Size;

public class BoardPost {

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

	@Override
	public String toString() {
		return "BoardPost [message=" + message + ", name=" + name + "]";
	}

}