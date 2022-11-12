package com.example.imdb.exception;

public class InvalidRatingException extends RuntimeException {
    public InvalidRatingException() {
        super("invalid rating number");
    }
}

