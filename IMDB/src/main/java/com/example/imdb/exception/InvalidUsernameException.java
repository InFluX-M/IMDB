package com.example.imdb.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(String username) {
        super("username: " + username + " already exists");
    }
}

