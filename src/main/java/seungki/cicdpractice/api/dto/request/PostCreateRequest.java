package seungki.cicdpractice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import seungki.cicdpractice.api.domain.Post;

@ToString
@Getter
public class PostCreateRequest {

	@NotBlank(message = "제목은 비어있으면 안됩니다.")
	private final String title;
	@NotBlank(message = "제목은 비어있으면 안됩니다.")
	private final String content;

	@Builder
	public PostCreateRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Post toEntity() {
		return Post.builder()
			.title(title)
			.content(content)
			.build();
	}

}
