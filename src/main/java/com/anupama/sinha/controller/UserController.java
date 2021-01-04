package com.anupama.sinha.controller;

import com.anupama.sinha.dto.PostDTO;
import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;
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
    SocialMediaServiceImpl socialMediaServiceImpl;

    Map<Integer, Integer> map;

    @GetMapping("/users")
    Set<User> getUser(){
        return socialMediaServiceImpl.getAllUsers();
    }

    @GetMapping("/users/{id}/posts")
    Set<Post> getAllPost(@PathVariable("id") Integer id){
        return socialMediaServiceImpl.getAllPost(id);
    }

    @PostMapping("/users/posts")
    User addPost(@RequestBody PostDTO post){
        return socialMediaServiceImpl.addPost(post.userId, post.content);
    }

    @GetMapping("/users/{id}/follow/{follow_id}")
    User followUser(@PathVariable("id") Integer id, @PathVariable("follow_id") Integer followId){
        return socialMediaServiceImpl.addFollowing(id, followId);
    }

    @GetMapping("/users/{id}/unfollow/{follow_id}")
    User unfollowUser(@PathVariable("id") Integer id, @PathVariable("follow_id") Integer unfollowId){
        return socialMediaServiceImpl.removeFollowing(id, unfollowId);
    }
}