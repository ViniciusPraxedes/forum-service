package com.example.forumservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @NotNull
    @Size(min = 1, max = 50) // Example size constraint, adjust as needed
    private String username;

    @NotNull
    @Size(min = 1, max = 2048) // Example size constraint, adjust as needed
    private String title;

    @NotNull
    @Size(min = 1, max = 2048) // Example size constraint, adjust as needed
    private String content;
    @NotNull
    private String topic;

    private String userId;
    private String profilePic;

}
