package com.anupama.sinha.service;

import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface SocialMediaService {
    Set<User> userList = Collections.newSetFromMap(new ConcurrentHashMap<>());
    Map<Integer, Set<String>> postsByUserId = new ConcurrentHashMap<>();
    Map<String, Post> globalPosts = new ConcurrentHashMap<>();

    public Set<User> getAllUsers();

    public User addFollowing(Integer originalUser, Integer toFollow);

    public User removeFollowing(Integer originalUser, Integer toFollow);

    public User addPost(Integer userId, String content);

    public Set<Post> getAllPost(Integer userId);

}
