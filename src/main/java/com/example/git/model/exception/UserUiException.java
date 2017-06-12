package com.example.git.model.exception;

public class UserUiException extends Exception {

	public UserUiException(String login) {
		super("Exception triggered by specific username: " + login);
	}
}
