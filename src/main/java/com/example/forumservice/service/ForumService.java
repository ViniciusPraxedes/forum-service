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
                .profilePicture(request.getProfilePic())
                .userId(request.getUserId())
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
                .profilePicture(request.getProfilePic())
                .username(request.getUsername())
                .datePosted(LocalDate.now())
                .content(request.getContent())
                .post(request.getPostId())
                .userId(request.getUserId())
                .build();


        postRepository.getReferenceById(request.getPostId()).getComments().add(comment);

        postRepository.getReferenceById(request.getPostId()).setCommentCount(postRepository.getReferenceById(request.getPostId()).getCommentCount()+1);

        commentRepository.save(comment);
    }

    public void updateProfilePic(ChangePictureDTO request){
        postRepository.updateProfilePictureByUserId(request.getUserId(), request.getProfilePicture());
        commentRepository.updateProfilePictureByUserId(request.getUserId(), request.getProfilePicture());
    }


    public boolean didTheUserLikeThisPost(String postId, String userId){

        for (int i = 0; i < postRepository.findById(postId).get().getUsersThatLikedThisPost().size(); i++){
            if (postRepository.findById(postId).get().getUsersThatLikedThisPost().get(i).equals(userId)){
                return true;
            }
        }

        return false;
    }

    public void addLikeToPost(String postId, String userId){

        for (int i = 0; i < postRepository.findById(postId).get().getUsersThatLikedThisPost().size(); i++){
            if (postRepository.findById(postId).get().getUsersThatLikedThisPost().get(i).equals(userId)){
                throw new RuntimeException("User has already liked this post");
            }
        }

        postRepository.getReferenceById(postId).getUsersThatLikedThisPost().add(userId);
        postRepository.getReferenceById(postId).setLikeCount(postRepository.getReferenceById(postId).getLikeCount()+1);
        postRepository.save(postRepository.getReferenceById(postId));

    }
    public void subtractLikeFromPost(String postId, String userId){
        System.out.println("here");

        if (postRepository.getReferenceById(postId).getUsersThatLikedThisPost().isEmpty()){
            System.out.println("no one has liked");
            throw new RuntimeException("No one has liked the post");
        }

        for (int i = 0; i < postRepository.findById(postId).get().getUsersThatLikedThisPost().size(); i++){
            System.out.println("here2");

            //The problem is here
            if (!postRepository.getReferenceById(postId).getUsersThatLikedThisPost().contains(userId)){
                System.out.println("User not liked");
                throw new RuntimeException("User has not liked this post");
            }
        }
        System.out.println("here3");

        postRepository.getReferenceById(postId).getUsersThatLikedThisPost().remove(userId);
        postRepository.getReferenceById(postId).setLikeCount(postRepository.getReferenceById(postId).getLikeCount()-1);
        postRepository.save(postRepository.getReferenceById(postId));
    }

    public Integer getLikeCount(String postId){
        return postRepository.findById(postId).get().getUsersThatLikedThisPost().size();
    }








    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public List<Post> getMostPopular(){
        return postRepository.getAllByLikeCountDesc();
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



}
