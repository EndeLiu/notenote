package com.note.back.service;

import com.note.back.dao.CategoryDao;
import com.note.back.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> getAll(){
        return categoryDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

}
