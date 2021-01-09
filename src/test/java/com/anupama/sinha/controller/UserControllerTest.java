package com.anupama.sinha.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.anupama.sinha.dto.PostDTO;
import com.anupama.sinha.service.SocialMediaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SocialMediaService mockSocialMediaService;

    @Mock
    private PostDTO mockPostDTO;

    @Test
    void testGetUser() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders.get("/users");
        // when
        MvcResult result = mockMvc.perform(request).andReturn();
        // then
        assertEquals(200, result.getResponse().getStatus(), "Check Get User");
        verify(mockSocialMediaService, times(1)).getAllUsers();

    }

    @Test
    void testGetAllPosts() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1/posts");
        // when
        MvcResult result = mockMvc.perform(request).andReturn();
        // then
        assertEquals(200, result.getResponse().getStatus(), "Check Get All Posts");
        verify(mockSocialMediaService, times(1)).getAllPost(anyInt());
    }

    @Test
    void testFollowUser() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1/follow/2");
        // when
        MvcResult result = mockMvc.perform(request).andReturn();
        // then
        assertEquals(200, result.getResponse().getStatus(), "Check Follow User");
        verify(mockSocialMediaService, times(1)).addFollowing(anyInt(), anyInt());
    }

    @Test
    void testUnFollowUser() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1/unfollow/2");
        // when
        MvcResult result = mockMvc.perform(request).andReturn();
        // then
        assertEquals(200, result.getResponse().getStatus(), "Check Unfollow User");
        verify(mockSocialMediaService, times(1)).removeFollowing(anyInt(), anyInt());
    }

    @Test
    void testAddPost() throws Exception {
        // given
        PostDTO postDto = new PostDTO(1, "Hello World");
        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users/posts", 42L)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(postDto))).andReturn();
        // then
        assertEquals(200, result.getResponse().getStatus(), "Check Add Post");
        verify(mockSocialMediaService, times(1)).addPost(anyInt(), anyString());
    }
}