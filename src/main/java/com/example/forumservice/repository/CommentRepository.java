package com.example.forumservice.repository;

import com.example.forumservice.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Comment c SET c.profilePicture = :profilePicture WHERE c.userId = :userId")
    void updateProfilePictureByUserId(@Param("userId") String userId, @Param("profilePicture") String profilePicture);
}
