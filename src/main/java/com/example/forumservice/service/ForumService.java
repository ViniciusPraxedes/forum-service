package com.example.forumservice.service;

import com.example.forumservice.model.*;
import com.example.forumservice.repository.CommentRepository;
import com.example.forumservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ForumService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    @Autowired
    public ForumService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    public Post createPost(PostRequest request){

        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .profilePicture("https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg")
                .username(request.getUsername())
                .datePosted(LocalDate.now())
                .title(request.getTitle())
                .content(request.getContent())
                .likeCount(0)
                .commentCount(0)
                .comments(new ArrayList<>())
                .topic(request.getTopic())
                .build();

        postRepository.save(post);

        return post;
    }

    public void addCommentToPost(CommentRequest request){
        String id = UUID.randomUUID().toString();

        Comment comment = Comment.builder()
                .id(id)
                .profilePicture("https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg")
                .username(request.getUsername())
                .datePosted(LocalDate.now())
                .content(request.getContent())
                .post(request.getPostId())
                .build();


        postRepository.getReferenceById(request.getPostId()).getComments().add(comment);

        postRepository.getReferenceById(request.getPostId()).setCommentCount(postRepository.getReferenceById(request.getPostId()).getCommentCount()+1);

        commentRepository.save(comment);
    }

    public void addLikeToPost(String postId){
        postRepository.getReferenceById(postId).setLikeCount(postRepository.getReferenceById(postId).getLikeCount()+1);
        postRepository.save(postRepository.getReferenceById(postId));
    }

    public void subtractLikeFromPost(String postId){
        if (postRepository.getReferenceById(postId).getLikeCount() == 0){
            throw new RuntimeException("like count is zero");
        }
        postRepository.getReferenceById(postId).setLikeCount(postRepository.getReferenceById(postId).getLikeCount()-1);
        postRepository.save(postRepository.getReferenceById(postId));
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public Post getPost(String postId){
        return postRepository.findById(postId).get();
    }

    public List<Comment> getAllCommentsInPost(String postId){
        return postRepository.getReferenceById(postId).getComments();
    }
    public List<Post> getAllPostsByTopic(String topic){
        return postRepository.getAllByTopic(topic);
    }
    public Integer getLikeCount(String postId){
        return postRepository.findById(postId).get().getLikeCount();
    }



}
