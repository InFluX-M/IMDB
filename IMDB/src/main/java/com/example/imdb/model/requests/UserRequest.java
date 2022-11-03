package com.example.imdb.model.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserRequest {

    String username;
    String password;
}
