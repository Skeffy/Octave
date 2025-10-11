package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.dao.PostDao;
import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.service.PrincipalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostDao dao;

    public PostController(PostDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Post> getPostsByAccount(@RequestBody int userId) {
        try {
            return dao.getPostsByAccount(userId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(Principal principal, @RequestBody Post post) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return dao.createPost(userId, post);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PutMapping
    public int hidePost(Principal principal, @RequestBody int postId) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return dao.deleteOwnPost(userId, postId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PutMapping("/admin")
    public Integer adminHidePost(Principal principal, @RequestBody int postId) {
        try {
            int userId = PrincipalService.getUserId(principal);
            if(PrincipalService.isUserAdmin(userId)) {
                return dao.adminDeletePost(postId);
            }
            return null;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
