package com.anupama.sinha.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import com.anupama.sinha.service.serviceimpl.SocialMediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class SocialMediaServiceImplTest {
    @InjectMocks
    private SocialMediaServiceImpl mockSocialMediaService;

    @Spy
    private Set<User> spyUsers;

    @Mock
    private User mockUser1;

    @Mock
    private User mockUser2;

    @Mock
    private Set<User> mockFollowers;

    @Mock
    private Set<User> mockFollowing;

    @BeforeEach
    void setUp() {
        mockSocialMediaService.initializeUsers();
    }

    @Test
    void testGetAllUsers() {
        // when
        Set<User> actual = mockSocialMediaService.getAllUsers();
        // then
        assertEquals(10, actual.size(), "Testing GET Users");
    }

    @Test
    void testAddFollowing() {
        // when
        User actual = mockSocialMediaService.addFollowing(1, 2);
        // then
        assertEquals(1, actual.getFollowing().size(), "Testing Add Following");
    }

    @Test
    void testRemoveFollowing() {
        // when
        User actual = mockSocialMediaService.removeFollowing(1, 2);
        // then
        assertEquals(0, actual.getFollowing().size(), "Testing Remove Following");
    }

    @Test
    void testAddPost() {
        // when
        User actual = mockSocialMediaService.addPost(1, "Hello World");
        // then
        assertEquals(1, actual.getId(), "Testing Add Post");
    }

    @Test
    void testGetAllPost() {
        // when
        mockSocialMediaService.addPost(1, "Hello World");
        Set<Post> actual = mockSocialMediaService.getAllPost(1);
        // then
        assertEquals(1, actual.size(), "Testing Get All Post");
    }
}