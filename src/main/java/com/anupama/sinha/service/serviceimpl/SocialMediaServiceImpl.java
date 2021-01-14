package com.anupama.sinha.service.serviceimpl;

import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;
import com.anupama.sinha.service.SocialMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

    final Set<User> userList = ConcurrentHashMap.newKeySet();
    final Map<Integer, Set<String>> postsByUserId = new ConcurrentHashMap<>();
    final Map<String, Post> globalPosts = new ConcurrentHashMap<>();

    @Override
    public Set<User> getAllUsers() {
        return userList;
    }

    @Override
    public User addFollowing(Integer originalUser, Integer toFollow){
        User u1 = findUserById(originalUser);
        User u2 = findUserById(toFollow);

        u1.addFollowing(u2);
        u2.addFollower(u1);
        return u1;
    }

    @Override
    public User removeFollowing(Integer originalUser, Integer toFollow){
        User u1 = findUserById(originalUser);
        User u2 = findUserById(toFollow);

        u1.removeFollowing(u2);
        u2.removeFollower(u1);
        return u1;
    }

    @Override
    public User addPost(Integer userId, String content) {

        Post post = new Post(userId, content);
        String postId = post.getPostId();

        User user = findUserById(userId);
        user.addPost(postId);

        Set<String> postIdsForUser = postsByUserId.get(userId);
        if(postIdsForUser == null) {
            postIdsForUser = new LinkedHashSet<>();
            postsByUserId.put(userId, postIdsForUser);
        }
        postIdsForUser.add(postId);
        globalPosts.put(postId, post);
        return user;
    }

    @Override
    public Set<Post> getAllPost(Integer userId) {
        User user = findUserById(userId);
        Set<Integer> userFollowingIds = user.getFollowingId();
        userFollowingIds.add(userId);

        Set<String> postIds = getPostIds(userFollowingIds);
        return findPostsById(postIds);
    }

    @Override
    public User getUserById(Integer id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findAny()
                .orElseThrow(() ->  new RuntimeException(("User Not Found")));
    }

    private Set<String> getPostIds(Set<Integer> userIds){
        return userIds.stream().map(userId -> postsByUserId.get(userId))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private User findUserById(Integer id){
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    private Set<Post> findPostsById(Set<String> postIds) {
        return postIds.parallelStream()
                .map(postId -> globalPosts.get(postId))
                .limit(20)
                .collect(Collectors.toSet());
    }

    @PostConstruct
    public void initializeUsers() {
        if(userList.isEmpty()) {
            User user1 = new User(1);
            User user2 = new User(2);
            User user3 = new User(3);
            User user4 = new User(4);
            User user5 = new User(5);
            User user6 = new User(6);
            User user7 = new User(7);
            User user8 = new User(8);
            User user9 = new User(9);
            User user10 = new User(10);

            userList.add(user1);
            userList.add(user2);
            userList.add(user3);
            userList.add(user4);
            userList.add(user5);
            userList.add(user6);
            userList.add(user7);
            userList.add(user8);
            userList.add(user9);
            userList.add(user10);

            System.out.println("Initialisation done");
        }
    }
}