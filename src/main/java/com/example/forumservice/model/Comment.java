package com.example.forumservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String id;
    private String profilePicture;
    private String username;
    private LocalDate datePosted;
    @Column(length = 5048)
    private String content;
    private String post;
}
