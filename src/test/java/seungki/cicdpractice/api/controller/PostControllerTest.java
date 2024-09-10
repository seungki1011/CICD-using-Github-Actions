package seungki.cicdpractice.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import seungki.cicdpractice.api.dto.request.PostCreateRequest;
import seungki.cicdpractice.api.service.PostService;

@WebMvcTest(PostController.class)
class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private PostService postService;

	@Test
	@DisplayName("POST /posts: 포스트 생성에 성공하면 201 CREATED를 응답으로 받는다")
	void test1() throws Exception {

		PostCreateRequest request = PostCreateRequest.builder()
			.title("Test title")
			.content("Test content")
			.build();
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/v1/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated())
			//                .andExpect(status().isBadRequest())
			.andDo(print());
	}

}
