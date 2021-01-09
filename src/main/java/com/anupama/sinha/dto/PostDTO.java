package com.anupama.sinha.dto;

public class PostDTO {
    public Integer userId;
    public String content;

    public PostDTO(Integer userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
