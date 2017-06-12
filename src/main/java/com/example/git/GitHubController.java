package com.example.git;

import com.example.git.model.GitHubClient;
import com.example.git.model.GitHubUser;
import com.example.git.model.GitHubUserProfile;
import com.example.git.model.exception.UserApiException;
import com.example.git.model.exception.UserUiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public GitHubUserProfile getUser(@PathVariable("username") String login) throws UserApiException {
		if (login.equals("exception")) throw new UserApiException(login);

		return gitHubClient.getUser(login);
	}

	@GetMapping("/{username}")
	public String getUser(@PathVariable("username") String login, Model model) throws UserUiException {
		if (login.equals("exception")) throw new UserUiException(login);

		GitHubUserProfile profile = gitHubClient.getUser(login);
		model.addAttribute("user", profile);
		return "profile";
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST,
			reason = "Error casing name")
	@ExceptionHandler(UserApiException.class)
	public void handleUserException() {
		//Nothing to do here
	}

	@ExceptionHandler(UserUiException.class)
	public String handleUserUiException(UserUiException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "errors/error";
	}
}
