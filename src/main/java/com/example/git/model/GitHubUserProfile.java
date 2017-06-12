package com.example.git.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GitHubUserProfile extends GitHubUser {
	private String name;
	private int public_repos;
	private int public_gists;
	private String created_at;
	private String updated_at;
}
