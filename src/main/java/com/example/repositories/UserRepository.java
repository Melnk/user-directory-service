package com.example.repositories;

import com.example.models.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserEntity create(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        UUID id = jdbcTemplate.queryForObject("insert into users(name) values (?)", UUID.class, name.trim());

        return new UserEntity(id, name);
    }

    public UserEntity getUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        UUID id = jdbcTemplate.queryForObject("select id from users where name = ?", UUID.class, name.trim());

        return new UserEntity(id, name);
    }

    public boolean deleteUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        UUID id = jdbcTemplate.queryForObject("select id from users where name = ?", UUID.class, name);
        try {
            jdbcTemplate.update("delete from users where id = ?", id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public UserEntity updateUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        UUID id = jdbcTemplate.queryForObject("select id from users where name = ?", UUID.class, name.trim());
        jdbcTemplate.update("update users set name = ? where id = ?", name, id);

        return new UserEntity(id, name);
    }
}
