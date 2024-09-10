package seungki.cicdpractice.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import seungki.cicdpractice.api.dto.reponse.PostResponse;
import seungki.cicdpractice.api.dto.request.PostCreateRequest;
import seungki.cicdpractice.api.service.PostService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request) {
		PostResponse response = postService.createPost(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(response);
	}

}
