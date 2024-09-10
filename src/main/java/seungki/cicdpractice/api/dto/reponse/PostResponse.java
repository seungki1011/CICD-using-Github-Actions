package seungki.cicdpractice.api.dto.reponse;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import seungki.cicdpractice.api.domain.Post;

@ToString
@Getter
public class PostResponse {

	private final Long id;
	private final String title;
	private final String content;

	@Builder
	public PostResponse(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public static PostResponse fromEntity(Post post) {
		return PostResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.build();
	}

}
