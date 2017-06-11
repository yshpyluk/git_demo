package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class GitHubController {
	@Autowired
	private GitHubClient gitHubClient;

	@GetMapping("/api")
	@ResponseBody
	public List<GitHubUser> getUsers() {
		return gitHubClient.getUsers();
	}

	@GetMapping()
	public String getUsers(Model model) {
		List<GitHubUser> users = gitHubClient.getUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/api/{username}")
	@ResponseBody
	public GitHubUserProfile getUser(@PathVariable("username") String login) {
		return gitHubClient.getUser(login);
	}

	@GetMapping("/{username}")
	public String getUser(@PathVariable("username") String login, Model model) {
		GitHubUserProfile profile = gitHubClient.getUser(login);
		model.addAttribute("user", profile);
		return "profile";
	}
}
