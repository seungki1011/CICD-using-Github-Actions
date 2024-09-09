package seungki.cicdpractice.api.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import seungki.cicdpractice.api.domain.Post;
import seungki.cicdpractice.api.dto.reponse.PostResponse;
import seungki.cicdpractice.api.dto.request.PostCreateRequest;
import seungki.cicdpractice.api.repository.PostRepository;


@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("유효한 데이터로 새로운 포스트를 성공적으로 생성한다")
    void test1() {

        PostCreateRequest request = PostCreateRequest.builder()
                .title("title test 1")
                .content("content test 1")
                .build();
        Post post = Post.builder()
                .title("title test 1")
                .content("content test 1")
                .build();
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostResponse postResponse = postService.createPost(request);

        assertThat(postResponse).isNotNull();
        assertThat(postResponse.getTitle()).isEqualTo("title test 1");
        assertThat(postResponse.getContent()).isEqualTo("content test 1");

        /**
         * Mockito는 기본적으로 객체의 동등성을 비교하기 위해서 equals() 메서드를 사용한다
         * 만약 equals()를 오버라이드하지 않는다면 기본적으로 참조를 통해 비교하게 된다
         * equals(), hashCode() 오버라이딩이 필요하다
         */
        verify(postRepository, times(1)).save(post);

        // 오버라이딩 하지 않는 경우, 인자로 전달되는 객체의 속성만 검증하는 방법을 사용할 수 있다
//        verify(postRepository, times(1)).save(any(Post.class));

    }

}