package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class GitHubUser {
	private String login;
	private long id;
	private URL url;
}
