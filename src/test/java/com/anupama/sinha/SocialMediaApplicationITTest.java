package com.anupama.sinha;

import com.anupama.sinha.dto.PostDTO;
import com.anupama.sinha.model.entity.Post;
import com.anupama.sinha.model.entity.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SocialMediaApplicationITTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllUsers() {
        Set<User> userSet = this.restTemplate
                .getForObject("http://localhost:" + port + "/users", Set.class);

        assertEquals(userSet.size(), 10);
    }

    @Order(1)
    @Test
    public void testCreatingPost() {
        PostDTO postDTO = new PostDTO(1, "Hello from test");
        User user = this.restTemplate
                .postForObject("http://localhost:" + port + "/users/posts", postDTO, User.class);
        assertEquals(1, user.getId());
    }

    @Test
    public void testAllPost() {
        Set<Post> userSet = this.restTemplate
                .getForObject("http://localhost:" + port + "/users/1/posts", Set.class);

        assertEquals(userSet.size(), 1);
    }

    @Test
    public void testFollow() {
        User user = this.restTemplate
                .getForObject("http://localhost:" + port + "/users/1/follow/5", User.class);

        User user2 = this.restTemplate
                .getForObject("http://localhost:" + port + "/users/5", User.class);

        assertEquals(1, user.getId());
        assertEquals(1,user.getFollowing().size());
        assertEquals(true, user.getFollowingId().contains(5));

        assertEquals(true, user2.getFollowersId().contains(1));


    }

    @Test
    public void testUnFollow() {
        User user = this.restTemplate
                .getForObject("http://localhost:" + port + "/users/1/unfollow/5", User.class);

        User user2 = this.restTemplate
                .getForObject("http://localhost:" + port + "/users/5", User.class);

        assertEquals(user.getId(), 1);
        assertEquals(0, user.getFollowing().size());
        assertEquals(false, user.getFollowingId().contains(5));

        assertEquals(false, user2.getFollowersId().contains(1));
    }
}