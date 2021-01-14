package com.anupama.sinha.controller;

import com.anupama.sinha.dto.PostDTO;
import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;
import com.anupama.sinha.service.SocialMediaService;
import com.anupama.sinha.service.serviceimpl.SocialMediaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    SocialMediaService socialMediaService;

    Map<Integer, Integer> map;

    @GetMapping("/users")
    Set<User> getUser(){
        return socialMediaService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Integer id) {
        return socialMediaService.getUserById(id);
    }

    @GetMapping("/users/{id}/posts")
    Set<Post> getAllPost(@PathVariable("id") Integer id){
        return socialMediaService.getAllPost(id);
    }

    @PostMapping("/users/posts")
    User addPost(@RequestBody PostDTO post){
        return socialMediaService.addPost(post.userId, post.content);
    }

    @GetMapping("/users/{id}/follow/{follow_id}")
    User followUser(@PathVariable("id") Integer id, @PathVariable("follow_id") Integer followId){
        return socialMediaService.addFollowing(id, followId);
    }

    @GetMapping("/users/{id}/unfollow/{follow_id}")
    User unfollowUser(@PathVariable("id") Integer id, @PathVariable("follow_id") Integer unfollowId){
        return socialMediaService.removeFollowing(id, unfollowId);
    }
}