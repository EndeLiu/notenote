package com.note.back.dao;

import com.note.back.pojo.Category;
import com.note.back.pojo.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDao extends JpaRepository<Note,Integer> {
    List<Note> findAllByCategory(Category category);
}
