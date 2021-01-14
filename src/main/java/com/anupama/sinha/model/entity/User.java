package com.anupama.sinha.model.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class User {
    private Integer id;

    private Set<User> followers;

    private Set<User> following;

    private Set<String> postIds;

    private User(){

    }

    public User(Integer id) {
        this.id = id;
        this.followers = ConcurrentHashMap.newKeySet();
        this.following = ConcurrentHashMap.newKeySet();
        this.postIds = ConcurrentHashMap.newKeySet();
    }

    @JsonGetter("followers")
    public Set<Integer> getFollowersId(){
        return followers.parallelStream().map(User::getId).collect(Collectors.toSet());
    }

    @JsonGetter("following")
    public Set<Integer> getFollowingId(){
        return following.parallelStream().map(User::getId).collect(Collectors.toSet());
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