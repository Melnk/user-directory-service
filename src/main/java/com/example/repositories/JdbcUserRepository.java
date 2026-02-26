package com.example.repositories;

import com.example.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserEntity create(String name) {
        String n = norm(name);

        UUID id = jdbcTemplate.queryForObject("insert into users(name) values (?) RETURNING id", UUID.class, n);

        return new UserEntity(id, n);
    }

    public UserEntity getUser(String name) {
        String n = norm(name);
        try {
            UUID id = jdbcTemplate.queryForObject("select id from users where name = ?", UUID.class, n);
            return new UserEntity(id, name);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("User not found " + n);
        }
    }

    public boolean deleteUser(String name) {
        String n = norm(name);
        return jdbcTemplate.update("delete from users where name = ?", n) == 1;
    }

    public UserEntity updateUser(String oldName, String newName) {
        String oldN = norm(oldName);
        String newN = norm(newName);

        try {
            UUID id = jdbcTemplate.queryForObject(
                "update users set name = ? where name = ? returning id",
                UUID.class,
                newN, oldN
            );
            return new UserEntity(id, newN);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("User not found: " + oldN);
        }
    }

    private static String norm(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        String n = name.trim();
        if (n.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        return n;
    }
}
