package ru.svitkin.eshopserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiAuthorizationTest {
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAuthorizationApi() throws Exception {
		AuthInputDto authInputDto = new AuthInputDto("admin", "admin");
		String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authInputDto);

		this.mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.token").exists());
	}

	@Test
	public void testUnauthorizedAccess() throws Exception {
		this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isUnauthorized());
	}
}
