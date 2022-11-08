package com.example.imdb.service;

import com.example.imdb.model.Comment;
import com.example.imdb.model.Movie;
import com.example.imdb.model.requests.CommentRequest;
import com.example.imdb.model.responses.CommentMovieResponse;
import com.example.imdb.model.responses.CommentResponse;
import com.example.imdb.repository.CommentRepository;
import com.example.imdb.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private MovieRepository movieRepository;
    private MovieService movieService;
    private CommentRepository commentRepository;

    public CommentResponse addComment(String titleId, CommentRequest commentRequest) {
        Movie movie = movieService.checkMovieId(titleId);
        Comment comment = Comment.builder()
                .body(commentRequest.getBody())
                .build();
        movie.getComments().add(comment);
        movieRepository.save(movie);
        return commentRepository.save(comment).response();
    }

    public List<CommentMovieResponse> getAllComments(String titleId) {
        return movieService.checkMovieId(titleId).getComments().stream().map(Comment::movieResponse).toList();
    }

    public List<CommentResponse> getAllReplyComments(Integer commentId) {
        return commentRepository.findById(commentId).get().getReplies().stream().map(Comment::response).toList();
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteAll(commentRepository.findById(commentId).get().getReplies());
        commentRepository.deleteById(commentId);
    }

    public void deleteReplyComment(Integer commentId, Integer replyId) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.getReplies().remove(commentRepository.findById(replyId).get());
        commentRepository.deleteById(replyId);
        commentRepository.save(comment);
    }

    public CommentResponse addReplyComment(Integer commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId).get();
        Comment reply = Comment.builder()
                .body(commentRequest.getBody())
                .build();
        comment.getReplies().add(reply);
        commentRepository.save(comment);
        commentRepository.save(reply);
        return reply.response();
    }

    public CommentResponse updateComment(Integer commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId).get();
        if(commentRequest.getBody() != null) comment.setBody(commentRequest.getBody());
        return commentRepository.save(comment).response();
    }


}
