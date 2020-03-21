package com.note.back.service;

import com.note.back.dao.CategoryDao;
import com.note.back.dao.NoteDao;
import com.note.back.pojo.Category;
import com.note.back.pojo.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteDao noteDao;
    @Autowired
    CategoryDao categoryDao;

    public List<Note> getNotesByCategory(int id){
        Category category = categoryDao.getOne(id);
        return noteDao.findAllByCategory(category);
    }

}
