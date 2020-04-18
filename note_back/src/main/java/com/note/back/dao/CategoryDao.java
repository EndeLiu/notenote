package com.note.back.dao;

import com.note.back.pojo.Category;
import com.note.back.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    List<Category> findByAuthor(User author);
}
