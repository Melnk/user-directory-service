package com.example.services;

import com.example.models.UserEntity;
import com.example.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository repository;

    public UserEntity create(String name) {
        return repository.create(name);
    }

    public UserEntity getUser(String name) {
        return repository.getUser(name);
    }

    public boolean deleteUser(String name) {
        return repository.deleteUser(name);
    }

    public UserEntity updateUser(String name) {
        return repository.updateUser(name);
    }
}
