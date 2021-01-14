package com.anupama.sinha.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Post implements Comparable<Post> {
    String content;
    Timestamp postedOn;
    Integer userId;
    String postId;

    private Post(){

    }

    public Post(Integer userId, String content) {
        this.userId = userId;
        this.content = content;
        this.postedOn = new Timestamp(System.currentTimeMillis());
        this.postId = System.currentTimeMillis() + userId.toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Timestamp postedOn) {
        this.postedOn = postedOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return userId.equals(post.userId) &&
                Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }

    @Override
    public int compareTo(Post o) {
        return Long.compare(o.postedOn.getTime(), this.postedOn.getTime());
    }
}