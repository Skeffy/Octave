package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.dao.CommentDao;
import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Comment;
import io.github.skeffy.octave.service.PrincipalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentDao dao;

    public CommentController(CommentDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Comment> getComments(@RequestBody int parent_id) {
        try {
            return dao.getCommentsByParentId(parent_id);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(Principal principal, @RequestBody Comment comment) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return dao.createComment(userId, comment);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PutMapping("/delete")
    public int hideComment(Principal principal, @RequestBody int commentId) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return dao.deleteOwnComment(userId, commentId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PutMapping("/admin/delete")
    public Integer adminHideComment(Principal principal, @RequestBody int commentId) {
        try {
            int userId = PrincipalService.getUserId(principal);
            if(PrincipalService.isUserAdmin(userId)) {
                return dao.adminDeleteComment(commentId);
            }
            return null;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
