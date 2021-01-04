package com.anupama.sinha.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class User {
    private Integer id;
    @JsonIgnore
    private Set<User> followers;

    @JsonIgnore
    private Set<User> following;

    private Set<String> postIds;

    User(){

    }

    public User(Integer id) {
        this.id = id;
        this.followers = Collections.newSetFromMap(new ConcurrentHashMap<User, Boolean>());
        this.following = Collections.newSetFromMap(new ConcurrentHashMap<User, Boolean>());
        this.postIds = ConcurrentHashMap.newKeySet();
    }

    public boolean addFollower(User user){
        return this.followers.add(user);
    }

    public boolean removeFollower(User user) {
        return this.followers.remove(user);
    }

    public boolean addFollowing(User user){
        return this.following.add(user);
    }

    public boolean removeFollowing(User user){
        return this.following.remove(user);
    }

    public boolean addPost(String postId) {
        return postIds.add(postId);
    }

    @JsonProperty("followers")
    public Set<Integer> getFollowersId(){
        return followers.parallelStream().map(User::getId).collect(Collectors.toSet());
    }

    @JsonProperty("following")
    public Set<Integer> getFollowingId(){
        return following.parallelStream().map(User::getId).collect(Collectors.toSet());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}