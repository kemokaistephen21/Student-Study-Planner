package com.studentstudyplanner.data.repository;

import com.studentstudyplanner.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity findByLoginName(String loginName) {
        String sql = "SELECT * FROM users WHERE username = '" + loginName + "'";
        try {
            List<UserEntity> users = jdbcTemplate.query(sql, new UserExtractor());
            return users.isEmpty() ? null : users.get(0);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<UserEntity> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserExtractor());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        jdbcTemplate.update(sql);
    }

    public UserEntity save(UserEntity userEntity) {
        // new user
        if (userEntity.getId() == null) {
            String sql = "INSERT INTO users (username, password) VALUES ('"
                    + userEntity.getUsername() + "', '"
                    + userEntity.getPassword() + "')";
            jdbcTemplate.update(sql);
            Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
            userEntity.setId(id);
        } else {
            String sql = "UPDATE users SET username = '" + userEntity.getUsername()
                    + "', password = '" + userEntity.getPassword()
                    + "'  WHERE id = " + userEntity.getId();
            jdbcTemplate.update(sql);
        }

        return userEntity;
    }

    public UserEntity findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = " + id;
        try {
            List<UserEntity> users = jdbcTemplate.query(sql, new UserExtractor());
            return users.isEmpty() ? null : users.get(0);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public long count() {
        String sql = "SELECT COUNT(*) FROM users";
        Long result = jdbcTemplate.queryForObject(sql, Long.class);
        return result != null ? result : 0;
    }

    public void delete(UserEntity user) {
        deleteById(user.getId());
    }

    public void deleteAll() {
        String sql = "DELETE FROM users";
        jdbcTemplate.update(sql);
    }

    public void deleteAll(Iterable<? extends UserEntity> users) {
        for (UserEntity user : users) {
            delete(user);
        }
    }

    public List<UserEntity> saveAll(Iterable<UserEntity> users) {
        for (UserEntity user : users) {
            save(user);
        }
        return (List<UserEntity>) users;
    }

    private static class UserExtractor implements ResultSetExtractor<List<UserEntity>> {
        @Override
        public List<UserEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, UserEntity> userMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                UserEntity user = userMap.get(id);
                if (user == null) {
                    user = new UserEntity();
                    user.setId(id);
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    userMap.put(id, user);
                }
            }
            return new ArrayList<>(userMap.values());
        }
    }
}
