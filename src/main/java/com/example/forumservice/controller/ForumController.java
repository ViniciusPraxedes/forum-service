package com.example.forumservice.controller;

import com.example.forumservice.model.*;
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

    @GetMapping("/isLiked/{postId}/{userId}")
    public boolean isLiked(@PathVariable String postId, @PathVariable String userId){
        return forumService.didTheUserLikeThisPost(postId,userId);
    }

    @PostMapping("/like/{postId}/{userId}")
    public void addLikeToPost(@PathVariable String postId, @PathVariable String userId){
        forumService.addLikeToPost(postId, userId);
    }
    @PostMapping("/unlike/{postId}/{userId}")
    public void subtractLikeFromPost(@PathVariable String postId, @PathVariable String userId){
        forumService.subtractLikeFromPost(postId, userId);
    }

    @GetMapping("/post/likeCount/{postId}")
    public Integer getLikeCount (@PathVariable String postId){
        return forumService.getLikeCount(postId);
    }











    @PutMapping("/updateProfilePicture")
    public void changeProfilePic(@RequestBody ChangePictureDTO request){
        forumService.updateProfilePic(request);
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
    @GetMapping("/post/popular")
    public List<Post> getMostPopular(){
        return forumService.getMostPopular();
    }
    @GetMapping("/post/topic/{topic}")
    public List<Post> getAllPostsByTopic (@PathVariable String topic){
        return forumService.getAllPostsByTopic(topic);
    }

}
