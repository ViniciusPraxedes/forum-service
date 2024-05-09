package com.example.forumservice.repository;

import com.example.forumservice.model.Post;
import com.example.forumservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    Optional<Post> findById(String id);

    List<Post> getAllByTopic(String topic);

    @Query("SELECT p FROM Post p ORDER BY p.likeCount DESC")
    List<Post> getAllByLikeCountDesc();

}
