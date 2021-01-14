package com.anupama.sinha.service;

import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface SocialMediaService {

    Set<User> getAllUsers();

    User addFollowing(Integer originalUser, Integer toFollow);

    User removeFollowing(Integer originalUser, Integer toFollow);

    User addPost(Integer userId, String content);

    Set<Post> getAllPost(Integer userId);

    User getUserById(Integer id);
}
