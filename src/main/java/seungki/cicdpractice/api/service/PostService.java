package seungki.cicdpractice.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import seungki.cicdpractice.api.dto.reponse.PostResponse;
import seungki.cicdpractice.api.dto.request.PostCreateRequest;
import seungki.cicdpractice.api.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public PostResponse createPost(PostCreateRequest request) {
		return PostResponse.fromEntity(postRepository.save(request.toEntity()));
	}

}
