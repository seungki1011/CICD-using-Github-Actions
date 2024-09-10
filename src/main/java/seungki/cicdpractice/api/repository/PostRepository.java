package seungki.cicdpractice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import seungki.cicdpractice.api.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
