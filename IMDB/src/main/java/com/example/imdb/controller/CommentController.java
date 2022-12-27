package com.example.imdb.controller;

import com.example.imdb.model.requests.CommentRequest;
import com.example.imdb.model.responses.CommentResponse;
import com.example.imdb.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @PostMapping("/{titleId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable String titleId,
                                                      @RequestBody CommentRequest request) {
        return new ResponseEntity<>(commentService.addComment(titleId, request), HttpStatus.CREATED);
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable String titleId) {
        return new ResponseEntity<>(commentService.getAllComments(titleId), HttpStatus.OK);
    }

    @PostMapping("/reply/{commentId}")
    public ResponseEntity<CommentResponse> addReply(@PathVariable Integer commentId,
                                                    @RequestBody CommentRequest request) {
        return new ResponseEntity<>(commentService.addReplyComment(commentId, request), HttpStatus.CREATED);
    }

    @GetMapping("/replies/{commentId}")
    public ResponseEntity<List<CommentResponse>> getReplies(@PathVariable Integer commentId) {
        return new ResponseEntity<>(commentService.getAllReplyComments(commentId), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Integer commentId,
                                                         @RequestBody CommentRequest request) {
        return new ResponseEntity<>(commentService.updateComment(commentId, request), HttpStatus.OK);
    }

}
