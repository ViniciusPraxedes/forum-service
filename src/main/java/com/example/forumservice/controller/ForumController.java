package com.example.forumservice.controller;

import com.example.forumservice.model.Comment;
import com.example.forumservice.model.CommentRequest;
import com.example.forumservice.model.Post;
import com.example.forumservice.model.PostRequest;
import com.example.forumservice.service.ForumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forum")
@CrossOrigin("*")
public class ForumController {

    private final ForumService forumService;
    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping("/post")
    public Post createPost(@Valid @RequestBody PostRequest request){
        return forumService.createPost(request);
    }
    @PostMapping("/comment")
    public void addCommentToPost(@Valid @RequestBody CommentRequest request){
        forumService.addCommentToPost(request);
    }
    @PostMapping("/like/{postId}")
    public void addLikeToPost(@PathVariable String postId){
        forumService.addLikeToPost(postId);
    }
    @PostMapping("/dislike/{postId}")
    public void subtractLikeFromPost(@PathVariable String postId){
        forumService.addLikeToPost(postId);
    }

    @GetMapping("/post/topic/")
    public List<Post> getAllPosts(){
        return forumService.getAllPosts();
    }
    @GetMapping("/post/{postId}")
    public Post getPostByPostId(@PathVariable String postId){
        return forumService.getPost(postId);
    }
    @GetMapping("/comment/{postId}")
    public List<Comment> getAllCommentsInThePost(@PathVariable String postId){
        return forumService.getAllCommentsInPost(postId);
    }
    @GetMapping("/post/topic/{topic}")
    public List<Post> getAllPostsByTopic (@PathVariable String topic){
        return forumService.getAllPostsByTopic(topic);
    }
}
