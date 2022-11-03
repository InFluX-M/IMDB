package com.example.imdb.model.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FavoriteListRequest {

    String name;
    int size;
}
