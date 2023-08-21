package ru.svitkin.eshopserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthOutputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceInputDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiDevicesTest {
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	private String token;

	@BeforeEach
	public void authorize() throws Exception {
		AuthInputDto authInputDto = new AuthInputDto("admin", "admin");
		String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authInputDto);

		String response = this.mockMvc
				.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print()).andReturn().getResponse().getContentAsString();
		AuthOutputDto authOutputDto = objectMapper.readValue(response, AuthOutputDto.class);
		token = authOutputDto.getToken();
	}

	@Test
	public void testCreateDevice() throws Exception {
		DeviceInputDto deviceInputDto = new DeviceInputDto("Тестовый товар", 20, 1, 1, 1, List.of());
		String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deviceInputDto);

		this.mockMvc
				.perform(post("/devices").header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());
	}
}