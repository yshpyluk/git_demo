package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GitHubClient {

	private RestTemplate restTemplate;

	public GitHubClient() {
		restTemplate = new RestTemplate();
	}

	public List<GitHubUser> getUsers() {
		ResponseEntity<GitHubUser[]> responseEntity = restTemplate.getForEntity("https://api.github.com/users", GitHubUser[].class);
		return Arrays.asList(responseEntity.getBody());
	}

	public GitHubUserProfile getUser(String login) {
		ResponseEntity<GitHubUserProfile> responseEntity = restTemplate.getForEntity(
				"https://api.github.com/users/{login}",
				GitHubUserProfile.class,
				login
		);
		return responseEntity.getBody();
	}
}
