package com.example.forumservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;
    private String profilePicture;
    private String username;
    private LocalDate datePosted;
    @Column(name = "title", length = 5048)
    private String title;
    @Column(name = "content", length = 5048)
    private String content;
    private Integer likeCount;
    private Integer commentCount;
    private String topic;
    @OneToMany
    private List<Comment> comments;

}
