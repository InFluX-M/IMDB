package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.User;
import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.UserResponse;
import com.example.imdb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserResponse addUser(UserRequest request) {
        // todo username tekrari . password validation?
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        return userRepository.save(user).response();
    }

    public void updateUser(String username, UserRequest request) {
        // todo exception nnn: username tekrari . valid pass?
        User user = checkUsername(username);
        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getPassword() != null) user.setPassword(request.getPassword());
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String username) {
        return checkUsername(username);
    }

    public User checkUsername(String username) {
        Optional<User> loaded = userRepository.findById(username);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(User.class.getName(), username);
        return loaded.get();
    }
}
