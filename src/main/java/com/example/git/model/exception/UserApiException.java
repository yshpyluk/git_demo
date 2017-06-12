package com.example.git.model.exception;

public class UserApiException extends Exception {

	public UserApiException(String login) {
		super("Exception triggered by specific username: " + login);
	}
}
