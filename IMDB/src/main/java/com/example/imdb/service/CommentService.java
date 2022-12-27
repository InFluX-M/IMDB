package com.example.imdb.service;

import com.example.imdb.exception.CustomException;
import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.Comment;
import com.example.imdb.model.Movie;
import com.example.imdb.model.requests.CommentRequest;
import com.example.imdb.model.responses.CommentResponse;
import com.example.imdb.repository.CommentRepository;
import com.example.imdb.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private MovieRepository movieRepository;
    private MovieService movieService;
    private CommentRepository commentRepository;

    public CommentResponse addComment(String titleId, CommentRequest commentRequest) {
        if (UserService.currentUser == null) {
            throw new CustomException("You must be logged in to comment", HttpStatus.UNAUTHORIZED);
        }
        Movie movie = movieService.checkMovieId(titleId);
        Comment comment = Comment.builder()
                .body(commentRequest.body())
                .movie(movie)
                .replies(new ArrayList<>())
                .user(UserService.currentUser)
                .build();

        movie.getComments().add(comment);
        movieRepository.save(movie);
        return commentRepository.save(comment).response();
    }

    public List<CommentResponse> getAllComments(String titleId) {
        return movieService.checkMovieId(titleId).getComments().stream().filter(c -> c.getParent() == null).map(Comment::response).toList();
    }

    public List<CommentResponse> getAllReplyComments(Integer commentId) {
        return commentRepository.findById(commentId).get().getReplies().stream().map(Comment::response).toList();
    }

    public void deleteComment(Integer commentId) {
        if (commentRepository.findById(commentId).isEmpty()) {
            return;
        }

        for (Comment c : commentRepository.findById(commentId).get().getReplies()) {
            deleteComment(c.getId());
        }

        commentRepository.deleteById(commentId);
    }

    public CommentResponse addReplyComment(Integer commentId, CommentRequest commentRequest) {
        if (UserService.currentUser == null) {
            throw new CustomException("You must be logged in to comment", HttpStatus.UNAUTHORIZED);
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment", commentId.toString()));
        Comment reply = Comment.builder()
                .parent(comment)
                .body(commentRequest.body())
                .user(UserService.currentUser)
                .replies(new ArrayList<>())
                .movie(comment.getMovie())
                .build();
        comment.getReplies().add(reply);
        commentRepository.save(comment);
        commentRepository.save(reply);
        return reply.response();
    }

    public CommentResponse updateComment(Integer commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId).get();
        if (commentRequest.body() != null) comment.setBody(commentRequest.body());
        return commentRepository.save(comment).response();
    }

}
