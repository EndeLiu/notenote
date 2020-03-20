package com.note.back.dao;

import com.note.back.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
