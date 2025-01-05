package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )

    @PostMapping("/posts/{post-id}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "post-id") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{post-id}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "post-id") long postId){
        return commentService.getCommentByPostId(postId);
    }

    @GetMapping("/posts/{post-id}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "post-id") Long postId, @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return  new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("/posts/{post-id}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "post-id") Long postId, @PathVariable(value = "id") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("/posts/{post-id}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "post-id") Long postId, @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
