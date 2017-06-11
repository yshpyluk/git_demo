package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
//@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UsersTest {
	@Autowired
	private MockMvc mockMvc;

	//test /users && /users/api endpoint
	@Test
	public void testContentOfUsersApiResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/api"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("\"login\":\"mojombo\"")));
	}

	@Test
	public void testLengthOfUsersApiResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/api"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", hasSize(30)));
	}

	@Test
	public void testViewOfUsersList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("users"));
	}

	//test /users/{profile} && /users/api/profile endpoint
	@Test
	public void testContentOfProfileApiResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/api/yshpyluk"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("\"id\":8058782")));
	}

	@Test
	public void testViewOfProfile() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/yshpyluk"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("profile"));
	}
}
